package net.godtao.orderservice.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper=new ObjectMapper();


    /**
     * 把Str转换为JsonNode
     * @param str
     * @return
     */
    public static JsonNode str2JsonNode(String str){
        try {
            return objectMapper.readTree(str);
        } catch (IOException e) {
           return null;
        }
    }


}
