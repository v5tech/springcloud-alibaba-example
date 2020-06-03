package com.samples.nacos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-provider", fallback = EchoServiceFallbackImpl.class,configuration = FeignConfiguration.class)
public interface EchoService {

    @GetMapping("/echo/{str}")
    String echo(@PathVariable("str") String str);

    @GetMapping("/divide")
    String divide(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

    default String divide(Integer a) {
        return divide(a, 0);
    }

    @GetMapping("/notFound")
    String notFound();

}

class FeignConfiguration {

    @Bean
    public EchoServiceFallbackImpl echoServiceFallback() {
        return new EchoServiceFallbackImpl();
    }

}

class EchoServiceFallbackImpl implements EchoService {

    @Override
    public String echo(@PathVariable("str") String str) {
        return "echo fallback";
    }

    @Override
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return "divide fallback";
    }

    @Override
    public String notFound() {
        return "notFound fallback";
    }

}