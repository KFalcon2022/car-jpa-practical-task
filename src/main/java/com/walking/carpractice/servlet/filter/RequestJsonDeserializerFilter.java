package com.walking.carpractice.servlet.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walking.carpractice.constant.ContextAttributeNames;
import com.walking.carpractice.model.request.CreateCarRequest;
import com.walking.carpractice.model.request.UpdateCarRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestJsonDeserializerFilter extends HttpFilter {
    public static final String POJO_REQUEST_BODY = "pojoRequestBody";

    private Map<String, TypeReference<?>> targetTypes;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute(ContextAttributeNames.OBJECT_MAPPER);

        initTargetTypes();
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!"application/json".equals(request.getContentType()) || request.getContentLength() == 0) {
            chain.doFilter(request, response);
            return;
        }

        byte[] jsonBody = request.getInputStream().readAllBytes();

        try {
            var targetType = getTargetType(request);
            Object pojoBody = objectMapper.readValue(jsonBody, targetType);

            request.setAttribute(POJO_REQUEST_BODY, pojoBody);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка десериализации запроса", e);
        }

        chain.doFilter(request, response);
    }

    private TypeReference<?> getTargetType(HttpServletRequest request) {
        var key = "%s&&%s".formatted(request.getServletPath(), request.getMethod());

        return targetTypes.getOrDefault(key, new TypeReference<>() {
        });
    }

    private void initTargetTypes() {
        targetTypes = new ConcurrentHashMap<>();

        targetTypes.put("/car&&POST", new TypeReference<CreateCarRequest>() {
        });
        targetTypes.put("/car&&PUT", new TypeReference<UpdateCarRequest>() {
        });
    }
}
