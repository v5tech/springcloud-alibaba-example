package com.samples.integration.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.samples.integration.storage.entity.TStorage;
import org.apache.ibatis.annotations.Param;


public interface TStorageMapper extends BaseMapper<TStorage> {

    /**
     * 扣减商品库存
     *
     * @Param: commodityCode 商品code  count扣减数量
     * @Return:
     */
    int decreaseStorage(@Param("commodityCode") String commodityCode, @Param("count") Integer count);

}