package com.samples.integration.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.samples.integration.account.entity.TAccount;
import com.samples.integration.common.dto.AccountDTO;
import com.samples.integration.common.response.ObjectResponse;

public interface ITAccountService extends IService<TAccount> {

    /**
     * 扣用户钱
     */
    ObjectResponse decreaseAccount(AccountDTO accountDTO);

}
