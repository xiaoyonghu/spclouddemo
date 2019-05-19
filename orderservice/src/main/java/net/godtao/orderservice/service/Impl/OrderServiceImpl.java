package net.godtao.orderservice.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import net.godtao.orderservice.Utils.JsonUtils;
import net.godtao.orderservice.domain.ProductOrder;
import net.godtao.orderservice.service.ProductClient;
import net.godtao.orderservice.service.ProductOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements ProductOrderService {

//    @Autowired
//    private LoadBalancerClient loadBalancer;

//    @Autowired
//    private  RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    private static final Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public ProductOrder save(int userId, int productId) {
//        Map<String,Object> productMap= restTemplate.getForObject("http://product-service/api/v1/product/findById?id="+productId, Map.class);//除了这种写法以外还有一种写法，见spring cloud微服务实战98
            //调用相关服务 比如积分服务等

//        if(userId == 1){
//            return null;
//        }
          logger.info("order service findById");
          String response=productClient.findById(productId);




          JsonNode jsonNode= JsonUtils.str2JsonNode(response);

//        ServiceInstance instance=loadBalancer.choose("product-service");
//
//        String url=String.format("http://%s:%s/api/v1/product/findById?id="+productId,instance.getHost(),instance.getPort());
//        RestTemplate restTemplate=new RestTemplate();
//        Map<String,Object> productMap= restTemplate.getForObject(url, Map.class);

        ProductOrder productOrder=new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
