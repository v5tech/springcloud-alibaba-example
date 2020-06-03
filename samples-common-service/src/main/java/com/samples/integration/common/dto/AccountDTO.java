package com.samples.integration.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * @Description 账户信息
 */
@Data
public class AccountDTO implements Serializable {

    private Integer id;

    private String userId;

    private BigDecimal amount;

}