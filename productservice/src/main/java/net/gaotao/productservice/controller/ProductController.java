package net.gaotao.productservice.controller;


import net.gaotao.productservice.domain.Product;
import net.gaotao.productservice.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/product")
@RefreshScope
public class ProductController {

    @Value("${server.port}")
    private String port;

    @Value("${env}")
    private String env;

    @Autowired
    ProductService productService;

    @RequestMapping("list")
    public Object list(){
      return  productService.listProduct();
    }


    @RequestMapping("findById")
    public Object findById(@RequestParam("id") int id){
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Product product=productService.findById(id);
        //Product result=new Product();
        //BeanUtils.copyProperties(product,result);
        // result.setName(result.getName()+"data from port="+port+"env"+env);
        //result.setName(result.getName());
        return product;

    }
}
