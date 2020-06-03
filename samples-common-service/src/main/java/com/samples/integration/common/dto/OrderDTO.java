package com.samples.integration.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * @Description 订单信息
 */
@Data
public class OrderDTO implements Serializable {

    private String orderNo;

    private String userId;

    private String commodityCode;

    private Integer orderCount;

    private BigDecimal orderAmount;

}