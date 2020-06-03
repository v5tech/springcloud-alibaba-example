package com.samples.nacos;

import com.samples.nacos.feign.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate urlCleanerRestTemplate;

    @Autowired
    private EchoService echoService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/echo-rest/{str}")
    public String rest(@PathVariable String str) {
        return urlCleanerRestTemplate.getForObject("http://service-provider/echo/" + str,
                String.class);
    }

    @GetMapping("/index")
    public String index() {
        return restTemplate.getForObject("http://service-provider", String.class);
    }

    @GetMapping("/test")
    public String test() {
        return restTemplate.getForObject("http://service-provider/test", String.class);
    }

    @GetMapping("/sleep")
    public String sleep() {
        return restTemplate.getForObject("http://service-provider/sleep", String.class);
    }

    @GetMapping("/notFound-feign")
    public String notFound() {
        return echoService.notFound();
    }

    @GetMapping("/divide-feign")
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return echoService.divide(a, b);
    }

    @GetMapping("/divide-feign2")
    public String divide(@RequestParam Integer a) {
        return echoService.divide(a);
    }

    @GetMapping("/echo-feign/{str}")
    public String feign(@PathVariable String str) {
        return echoService.echo(str);
    }

    @GetMapping("/services/{service}")
    public Object client(@PathVariable String service) {
        return discoveryClient.getInstances(service);
    }

    @GetMapping("/services")
    public Object services() {
        return discoveryClient.getServices();
    }

}
