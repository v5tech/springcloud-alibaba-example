package com.samples.integration.account.dubbo;

import com.samples.integration.account.service.ITAccountService;
import com.samples.integration.common.dto.AccountDTO;
import com.samples.integration.common.dubbo.AccountDubboService;
import com.samples.integration.common.response.ObjectResponse;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class AccountDubboServiceImpl implements AccountDubboService {

    @Autowired
    private ITAccountService accountService;

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        log.info("全局事务id ：" + RootContext.getXID());
        return accountService.decreaseAccount(accountDTO);
    }
}