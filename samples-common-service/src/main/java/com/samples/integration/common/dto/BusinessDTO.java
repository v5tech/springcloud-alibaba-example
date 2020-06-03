package com.samples.integration.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * @Description
 */
@Data
public class BusinessDTO implements Serializable {

    private String userId;

    private String commodityCode;

    private String name;

    private Integer count;

    private BigDecimal amount;

}