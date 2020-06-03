package com.samples.integration.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.samples.integration.account.entity.TAccount;
import org.apache.ibatis.annotations.Param;

public interface TAccountMapper extends BaseMapper<TAccount> {

    /**
     * 减少账户余额
     * @param userId
     * @param amount
     * @return
     */
    int decreaseAccount(@Param("userId") String userId, @Param("amount") Double amount);
}
