package com.safaricom.lipanabonga.services.impl;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class UtilService {
    /*--------------------- Map JSON String to Object -------------------------*/
    public static Object jsonStringToPojo(String jsonString, Object inputClass) {
        Object object = new Object();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
            object = objectMapper.readValue(jsonString, inputClass.getClass());

        } catch (IOException e) {
            log.error("-----------!!! ERROR IN MAPPING JSON TO CLASS !!!---------------- {}", e.getMessage());

        }
        return object;
    }
}
