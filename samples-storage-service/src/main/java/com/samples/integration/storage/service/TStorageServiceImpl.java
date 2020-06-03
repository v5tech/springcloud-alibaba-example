package com.samples.integration.storage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.samples.integration.common.dto.CommodityDTO;
import com.samples.integration.common.enums.RspStatusEnum;
import com.samples.integration.common.response.ObjectResponse;
import com.samples.integration.storage.entity.TStorage;
import com.samples.integration.storage.mapper.TStorageMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  库存服务实现类
 * </p>
 */
@Service
public class TStorageServiceImpl extends ServiceImpl<TStorageMapper, TStorage> implements ITStorageService {

    @Override
    public ObjectResponse decreaseStorage(CommodityDTO commodityDTO) {
        int storage = baseMapper.decreaseStorage(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        ObjectResponse<Object> response = new ObjectResponse<>();
        if (storage > 0){
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.FAIL.getCode());
        response.setMessage(RspStatusEnum.FAIL.getMessage());
        return response;
    }

}