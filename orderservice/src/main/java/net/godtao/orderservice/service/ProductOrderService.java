package net.godtao.orderservice.service;

import net.godtao.orderservice.domain.ProductOrder;

public interface ProductOrderService {
    /**
     * 下单接口
     * @param userId
     * @param productId
     * @return
     */
    ProductOrder save(int userId, int productId);

}
