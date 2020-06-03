package com.samples.integration.call.service;

import com.alibaba.fastjson.JSONObject;
import com.samples.integration.call.dubbo.feign.DubboFeignService;
import com.samples.integration.call.feign.FeignService;
import com.samples.integration.common.dto.BusinessDTO;
import com.samples.integration.common.dto.CommodityDTO;
import com.samples.integration.common.dto.OrderDTO;
import com.samples.integration.common.dubbo.OrderDubboService;
import com.samples.integration.common.dubbo.StorageDubboService;
import com.samples.integration.common.enums.RspStatusEnum;
import com.samples.integration.common.exception.DefaultException;
import com.samples.integration.common.response.ObjectResponse;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Random;

import static com.samples.integration.common.enums.RspStatusEnum.SUCCESS;

@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    /****** Dubbo服务 *****/
    @Reference
    private StorageDubboService storageDubboService;

    /****** Dubbo服务 *****/
    @Reference
    private OrderDubboService orderDubboService;

    /****** Feign服务 *****/
    @Autowired
    private FeignService.StorageService storageService;

    /****** Feign服务 *****/
    @Autowired
    private FeignService.OrderService orderService;

    /****** Dubbo Feign服务 *****/
    @Autowired
    private DubboFeignService.DubboStorageService dubboStorageService;

    /****** Dubbo Feign服务 *****/
    @Autowired
    private DubboFeignService.DubboOrderService dubboOrderService;

    /****** RestTemplate *****/
    @Autowired
    // @LoadBalanced
    private RestTemplate restTemplate;

    /**
     * 处理业务逻辑 正常的业务逻辑
     * @Param:
     * @Return:
     */
    @GlobalTransactional(timeoutMills = 300000, name = "cloud-dubbo-nacos-seata-example")
    @Override
    public ObjectResponse buy() {
        BusinessDTO businessDTO = businessDTO();
        System.out.println("---------------:"+JSONObject.toJSONString(businessDTO));
        log.info("开始全局事务，XID = " + RootContext.getXID());
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        //1、扣减库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(businessDTO.getCommodityCode());
        commodityDTO.setCount(businessDTO.getCount());
        ObjectResponse storageResponse = storageDubboService.decreaseStorage(commodityDTO);
        //2、创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        orderDTO.setOrderAmount(businessDTO.getAmount());
        ObjectResponse<OrderDTO> response = orderDubboService.createOrder(orderDTO);

        if (storageResponse.getStatus() != 200 || response.getStatus() != 200) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        boolean b = new Random().nextBoolean();

        if(b){
            throw new RuntimeException("抛异常，测试分布式事务回滚！");
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        objectResponse.setData(response.getData());
        return objectResponse;
    }

    @GlobalTransactional(timeoutMills = 300000, name = "cloud-dubbo-nacos-seata-example")
    @Override
    public ObjectResponse rest() {
        log.info("开始全局事务，XID = " + RootContext.getXID());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setId(1);
        commodityDTO.setCommodityCode("C201901140001");
        commodityDTO.setName("水杯");
        commodityDTO.setCount(50);

        HttpEntity<CommodityDTO> commodityHttpEntity = new HttpEntity<>(commodityDTO, headers);

        ResponseEntity<ObjectResponse> responseCommodityEntity = restTemplate.postForEntity(
                "http://127.0.0.1:8109/storage/dec_storage",
                commodityHttpEntity,
                ObjectResponse.class);

        System.out.println(JSONObject.toJSONString(responseCommodityEntity));

        if (SUCCESS.getCode()!=responseCommodityEntity.getStatusCodeValue()) {
            throw new RuntimeException();
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId("1");
        orderDTO.setCommodityCode("C201901140001");
        orderDTO.setOrderCount(50);
        orderDTO.setOrderAmount(new BigDecimal("100"));

        HttpEntity<OrderDTO> orderHttpEntity = new HttpEntity<>(orderDTO, headers);

        ResponseEntity<ObjectResponse> responseOrderEntity = restTemplate.postForEntity(
                "http://127.0.0.1:8101/order/create_order",
                orderHttpEntity,
                ObjectResponse.class);

        System.out.println(JSONObject.toJSONString(responseOrderEntity));

        if (SUCCESS.getCode()!=responseOrderEntity.getStatusCodeValue()) {
            throw new RuntimeException();
        }

        boolean b = new Random().nextBoolean();

        if(b){
            throw new RuntimeException("抛异常，测试分布式事务回滚！");
        }

        return responseOrderEntity.getBody();
    }

    @GlobalTransactional(timeoutMills = 300000, name = "cloud-dubbo-nacos-seata-example")
    @Override
    public ObjectResponse feign() {
        log.info("开始全局事务，XID = " + RootContext.getXID());

        // 库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setId(1);
        commodityDTO.setCommodityCode("C201901140001");
        commodityDTO.setName("水杯");
        commodityDTO.setCount(50);

        ObjectResponse objectResponse = storageService.decreaseStorage(commodityDTO);

        if (SUCCESS.getCode()!=objectResponse.getStatus()) {
            throw new RuntimeException();
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId("1");
        orderDTO.setCommodityCode("C201901140001");
        orderDTO.setOrderCount(50);
        orderDTO.setOrderAmount(new BigDecimal("100"));

        objectResponse = orderService.createOrder(orderDTO);

        if (SUCCESS.getCode()!=objectResponse.getStatus()) {
            throw new RuntimeException();
        }

        boolean b = new Random().nextBoolean();

        if(b){
            throw new RuntimeException("抛异常，测试分布式事务回滚！");
        }

        return objectResponse;
    }


    @GlobalTransactional(timeoutMills = 300000, name = "cloud-dubbo-nacos-seata-example")
    @Override
    public ObjectResponse dubbofeign() {
        log.info("开始全局事务，XID = " + RootContext.getXID());

        // 库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setId(1);
        commodityDTO.setCommodityCode("C201901140001");
        commodityDTO.setName("水杯");
        commodityDTO.setCount(50);

        ObjectResponse objectResponse = dubboStorageService.decreaseStorage(commodityDTO);

        if (SUCCESS.getCode()!=objectResponse.getStatus()) {
            throw new RuntimeException();
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId("1");
        orderDTO.setCommodityCode("C201901140001");
        orderDTO.setOrderCount(50);
        orderDTO.setOrderAmount(new BigDecimal("100"));

        objectResponse = dubboOrderService.createOrder(orderDTO);

        if (SUCCESS.getCode()!=objectResponse.getStatus()) {
            throw new RuntimeException();
        }

        boolean b = new Random().nextBoolean();

        if(b){
            throw new RuntimeException("抛异常，测试分布式事务回滚！");
        }

        return objectResponse;
    }

    /**
     * 模拟测试数据
     * @return
     */
    private BusinessDTO businessDTO() {
        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setUserId("1");
        businessDTO.setCommodityCode("C201901140001");
        businessDTO.setName("水杯");
        businessDTO.setCount(50);
        businessDTO.setAmount(new BigDecimal("100"));
        log.info("请求参数：{}", businessDTO.toString());
        return businessDTO;
    }
}
