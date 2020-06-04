# springcloud-alibaba-example

> 实验环境：
>
> Nacos 1.2.1
>
> Seata 1.2.0
>
> Dubbo 2.7.6
>
> seata-spring-boot-starter 1.2.0（务必留意）
>
> Spring Boot 2.2.5.RELEASE
>
> Spring Cloud Alibaba 2.2.1.RELEASE
>
> Spring Cloud Hoxton.SR4
>


操作步骤：

1、执行`docker`目录中定义的`docker-compose.yaml`。务必按`README.md`中的要求执行

2、依次按序启动samples-storage-service、samples-account-service、samples-order-service、samples-business-service

3、浏览器访问测试

* Dubbo方式调用

http://localhost:8104/buy

* RestTemplate方式调用

http://localhost:8104/rest

* FeignClient方式调用

http://localhost:8104/feign

* FeignClient方式调用(Dubbo协议)

http://localhost:8104/dubbofeign


