package org.example.blog.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    public static String toJsonString(Object obj) {
        try {
            return INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return obj.toString();
        }
    }

    public static String handleRequest(HttpServletRequest request) {

        // 遍历所有请求头，并将它们存储到requestMap中
        Map<String, Object> requestMap = new HashMap<>();
        Map<String, List<String>> headers = new HashMap<>();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();

        // 获取所有头字段名称的迭代器
        Enumeration<String> headerNamesEnum = request.getHeaderNames();
        while (headerNamesEnum.hasMoreElements()) {
            String headerName = headerNamesEnum.nextElement();
            Enumeration<String> valuesEnum = request.getHeaders(headerName);
            List<String> valuesList = Collections.list(valuesEnum);

            if (!valuesList.isEmpty()) {
                headers.put(headerName, valuesList);
            }
        }

        Map<String, String> data = parseParameters(parameterMap);

        // 将headers Map 存入requestMap
        requestMap.put("headers", headers);
        requestMap.put("method", method);
        requestMap.put("path", uri);
        requestMap.put("data", data);

        try {
            return INSTANCE.writeValueAsString(requestMap);
        } catch (JsonProcessingException e) {
            return requestMap.toString();
        }
    }

    private static Map<String, String> parseParameters(Map<String, String[]> parameterMap) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (values.length == 1) {
                result.put(key, values[0]);
            } else {
                result.put(key, Arrays.asList(values).toString());
            }
        }
        return result;
    }
}

