package com.samples.integration.common.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description 商品信息
 */
@Data
public class CommodityDTO implements Serializable {

    private Integer id;

    private String commodityCode;

    private String name;

    private Integer count;

}