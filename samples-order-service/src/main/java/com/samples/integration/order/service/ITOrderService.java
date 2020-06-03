package com.samples.integration.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.samples.integration.common.dto.OrderDTO;
import com.samples.integration.common.response.ObjectResponse;
import com.samples.integration.order.entity.TOrder;

/**
 * <p>
 *  创建订单
 * </p>
 */
public interface ITOrderService extends IService<TOrder> {

    /**
     * 创建订单
     */
    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);

}