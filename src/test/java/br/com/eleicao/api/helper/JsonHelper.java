package br.com.eleicao.api.helper;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonHelper {

 private static final ObjectMapper mapper = createObjectMapper();
    
    public static byte[] toJson(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }
    
    public static <T extends Object>  T toObject(byte[] json, Class<T> javaClass) {
        try {
            return mapper.readValue(json, javaClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
