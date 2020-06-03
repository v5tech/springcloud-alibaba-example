package com.samples.integration.common.dubbo;


import com.samples.integration.common.dto.AccountDTO;
import com.samples.integration.common.response.ObjectResponse;

/**
 * @Description  账户服务接口
 */
public interface AccountDubboService {

    /**
     * 从账户扣钱
     */
    ObjectResponse decreaseAccount(AccountDTO accountDTO);
}