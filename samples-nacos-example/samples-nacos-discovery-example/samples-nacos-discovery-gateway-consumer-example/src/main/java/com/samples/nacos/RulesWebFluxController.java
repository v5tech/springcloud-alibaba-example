package com.samples.nacos;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
public class RulesWebFluxController {

    @GetMapping("/gateway")
    public Mono<Set<GatewayFlowRule>> apiGateway() {
        return Mono.just(GatewayRuleManager.getRules());
    }

}
