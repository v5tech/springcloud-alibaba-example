package com.samples.integration.storage.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.samples.integration.common.dto.CommodityDTO;
import com.samples.integration.common.response.ObjectResponse;
import com.samples.integration.storage.entity.TStorage;

/**
 * 仓库服务
 */
public interface ITStorageService extends IService<TStorage> {

    /**
     * 扣减库存
     */
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);

}