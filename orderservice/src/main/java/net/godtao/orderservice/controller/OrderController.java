package net.godtao.orderservice.controller;


//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import net.godtao.orderservice.service.ProductOrderService;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//@RestController
//@RequestMapping("api/v1/order")
//public class OrderController {
//
//    @Autowired
//    private ProductOrderService productOrderService;
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @RequestMapping("save")
//    @HystrixCommand(fallbackMethod = "saveOrderFail")  //采用后背模式，下面回滚方法的参数要和受保护的方法的参数要完全一致
//    public Object save(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId, HttpServletRequest request) {
//        Map<String, Object> data = new HashMap<>();
//        data.put("code",0);
//        data.put("msg", productOrderService.save(userId, productId));
//        return data;
//    }
//
//    //此方法的参数要和受保护的方法的完全一致
//    private Object saveOrderFail(int userId, int productId, HttpServletRequest request) {
//
//        //监控报警
//        String saveOrderKye = "save-order";
//
//        String sendValue = redisTemplate.opsForValue().get(saveOrderKye);
//        final String ip = request.getRemoteAddr();
//
//        System.out.println(sendValue);
//        System.out.println(StringUtils.isBlank(sendValue));
//
//        new Thread( ()->{
//            if (StringUtils.isBlank(sendValue)) {
//                System.out.println("紧急短信，用户下单失败，请离开查找原因,ip地址是="+ip);
//                //发送一个http请求，调用短信服务 TODO
//                redisTemplate.opsForValue().set(saveOrderKye, "save-order-fail", 20, TimeUnit.SECONDS);
//
//            }else{
//                System.out.println("已经发送过短信，20秒内不重复发送");
//            }
//
//        }).start();
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("code", -1);
//        data.put("msg", "抢购的人数太多，请稍后再试");
//        return data;
//    }
//
//
//
//}


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.godtao.orderservice.service.ProductOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("api/v1/order")
public class OrderController {


    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private StringRedisTemplate redisTemplate;





    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")//这个默认的超时时间为1s
    public Object save(@RequestParam("user_id")int userId, @RequestParam("product_id") int productId, HttpServletRequest request){

        String token=request.getHeader("token");
        String cookie=request.getHeader("cookie");

        System.out.println(token);
        System.out.println(cookie);

        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("data", productOrderService.save(userId, productId));
        return  data;
    }


    //注意，方法签名一定要要和api方法一致
    private Object saveOrderFail(int userId, int productId, HttpServletRequest request){


        //监控报警
        String saveOrderKye = "save-order";

        String sendValue = redisTemplate.opsForValue().get(saveOrderKye);

        //System.out.println(sendValue);

        final String ip = request.getRemoteAddr();
        new Thread( ()->{
            if (StringUtils.isBlank(sendValue)) {
                System.out.println("紧急短信，用户下单失败，请离开查找原因,ip地址是="+ip);
                //发送一个http请求，调用短信服务 TODO
                redisTemplate.opsForValue().set(saveOrderKye, "save-order-fail", 20, TimeUnit.SECONDS);

            }else{
                System.out.println("已经发送过短信，20秒内不重复发送");
            }

        }).start();


        Map<String, Object> msg = new HashMap<>();
        msg.put("code", -1);
        msg.put("msg", "抢购人数太多，您被挤出来了，稍等重试");
        return msg;
    }




}
