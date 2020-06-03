package com.samples.integration.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.samples.integration.order.entity.TOrder;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface TOrderMapper extends BaseMapper<TOrder> {

    /**
     * 创建订单
     * @Param:  order 订单信息
     * @Return:
     */
    void createOrder(@Param("order") TOrder order);

}
