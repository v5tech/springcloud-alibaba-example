package com.samples.integration.call.feign;

import com.samples.integration.common.dto.CommodityDTO;
import com.samples.integration.common.dto.OrderDTO;
import com.samples.integration.common.response.ObjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * FeignClient
 */
public interface FeignService {

    @FeignClient("samples-storage-service")
    interface StorageService {

        @PostMapping("/storage/dec_storage")
        ObjectResponse decreaseStorage(@RequestBody CommodityDTO commodityDTO);

    }

    @FeignClient("samples-order-service")
    interface OrderService {

        @PostMapping("/order/create_order")
        ObjectResponse<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO);

    }

}
