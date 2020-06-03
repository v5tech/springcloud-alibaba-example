package com.samples.integration.call.dubbo.feign;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import com.samples.integration.common.dto.CommodityDTO;
import com.samples.integration.common.dto.OrderDTO;
import com.samples.integration.common.response.ObjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * FeignClient Dubbo Transport
 */
public interface DubboFeignService {

    @DubboTransported(protocol = "dubbo")
    @FeignClient("samples-storage-service")
    interface DubboStorageService {

        @PostMapping("/storage/dec_storage")
        ObjectResponse decreaseStorage(@RequestBody CommodityDTO commodityDTO);

    }

    @DubboTransported(protocol = "dubbo")
    @FeignClient("samples-order-service")
    interface DubboOrderService {

        @PostMapping("/order/create_order")
        ObjectResponse<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO);

    }

}
