package net.gaotao.dockerdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @ResponseBody
    @RequestMapping("/api/user/find")
    public Object findUser(){
        Map<String,String> map=new HashMap<>();
        map.put("name","GodTao");
        return map;
    }
}
