package net.godtao.orderservice.fallback;

import net.godtao.orderservice.service.ProductClient;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallBack implements ProductClient {

    @Override
    public String findById(int id) {
        System.out.println("feign 调用product-service findById 异常");
        return null;

    }
}
