package com.samples.integration.call.service;

import com.samples.integration.common.response.ObjectResponse;

public interface BusinessService {

    /**
     * 处理业务
     * @return
     */
    ObjectResponse buy();

    /**
     * rest 方式调用
     * @return
     */
    ObjectResponse rest();

    /**
     * feign 方式调用
     * @return
     */
    ObjectResponse feign();

    /**
     * dubbo feign 方式调用
     * @return
     */
    ObjectResponse dubbofeign();
}