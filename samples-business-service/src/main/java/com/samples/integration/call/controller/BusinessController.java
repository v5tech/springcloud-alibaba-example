package com.samples.integration.call.controller;

import com.samples.integration.call.service.BusinessService;
import com.samples.integration.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 模拟用户购买商品下单业务逻辑流程
     */
    @GetMapping("/buy")
    ObjectResponse buy(){
        return businessService.buy();
    }

    @GetMapping(value = "/rest")
    public ObjectResponse rest() {
        return businessService.rest();
    }

    @GetMapping(value = "/feign")
    public ObjectResponse feign() {
        return businessService.feign();
    }

    @GetMapping(value = "/dubbofeign")
    public ObjectResponse dubbofeign() {
        return businessService.dubbofeign();
    }

}
