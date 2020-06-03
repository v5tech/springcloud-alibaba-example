package com.samples.integration.order.dubbo;

import com.samples.integration.common.dto.OrderDTO;
import com.samples.integration.common.dubbo.OrderDubboService;
import com.samples.integration.common.response.ObjectResponse;
import com.samples.integration.order.service.ITOrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class OrderDubboServiceImpl implements OrderDubboService {

    @Autowired
    private ITOrderService orderService;

    @Override
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        log.info("全局事务id ：" + RootContext.getXID());
        return orderService.createOrder(orderDTO);
    }
}