package com.samples.integration.storage.controller;

import com.samples.integration.common.dto.CommodityDTO;
import com.samples.integration.common.response.ObjectResponse;
import com.samples.integration.storage.service.ITStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/storage")
public class TStorageController {

    @Autowired
    private ITStorageService storageService;

    /**
     * 扣减库存
     */
    @PostMapping("dec_storage")
    ObjectResponse decreaseStorage(@RequestBody CommodityDTO commodityDTO){
        log.info("请求库存微服务：{}",commodityDTO.toString());
        return storageService.decreaseStorage(commodityDTO);
    }

}