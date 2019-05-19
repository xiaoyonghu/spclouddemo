package net.godtao.orderservice.service;

import net.godtao.orderservice.fallback.ProductClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Primary
@FeignClient(name = "product-service",fallback = ProductClientFallBack.class)//降级之后的异常类，直接看注解源代码
public interface ProductClient {

    @GetMapping("/api/v1/product/findById")
    String findById(@RequestParam("id") int id);

}
