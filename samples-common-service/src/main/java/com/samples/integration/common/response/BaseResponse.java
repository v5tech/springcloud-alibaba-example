package com.samples.integration.common.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseResponse implements Serializable {

    private int status = 200;

    private String message;

}