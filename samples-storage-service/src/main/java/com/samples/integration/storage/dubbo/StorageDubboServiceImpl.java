package com.samples.integration.storage.dubbo;

import com.samples.integration.common.dto.CommodityDTO;
import com.samples.integration.common.dubbo.StorageDubboService;
import com.samples.integration.common.response.ObjectResponse;
import com.samples.integration.storage.service.ITStorageService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
@Service
public class StorageDubboServiceImpl implements StorageDubboService {

    @Autowired
    private ITStorageService storageService;

    @Override
    public ObjectResponse decreaseStorage(CommodityDTO commodityDTO) {
        log.info("全局事务id ：" + RootContext.getXID());
        return storageService.decreaseStorage(commodityDTO);
    }

}