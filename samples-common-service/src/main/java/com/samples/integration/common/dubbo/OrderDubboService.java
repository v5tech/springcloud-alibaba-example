package com.samples.integration.common.dubbo;


import com.samples.integration.common.dto.OrderDTO;
import com.samples.integration.common.response.ObjectResponse;

/**
 * @Description  订单服务接口
 */
public interface OrderDubboService {

    /**
     * 创建订单
     */
    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}