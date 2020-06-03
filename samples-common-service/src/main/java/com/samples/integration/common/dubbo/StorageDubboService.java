package com.samples.integration.common.dubbo;


import com.samples.integration.common.dto.CommodityDTO;
import com.samples.integration.common.response.ObjectResponse;

/**
 * @Description  库存服务
 */
public interface StorageDubboService {

    /**
     * 扣减库存
     */
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);
}