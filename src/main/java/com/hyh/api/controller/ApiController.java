package com.hyh.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * restful入口类
 * @author hyhsoftware@163.com
 * @version 0.0.1
 */
@RestController
public class ApiController {

    /**
     * 在授权控制范围内的接口
     */
    private static final String API_TEST_WITH_AUTHORIZED = "/api/test";
    /**
     * 不需要授权的接口
     */
    private static final String API_TEST_WITHOUT_AUTHORIZED = "/api1/test";

    @GetMapping(value = API_TEST_WITH_AUTHORIZED)
    public Object testApiWithAuthorized(HttpServletRequest request) {
        return "{\"tips\":\"this is a restful api with authorized\", \"code\":1}";
    }

    @GetMapping(value = API_TEST_WITHOUT_AUTHORIZED)
    public Object testApiWithoutAuthorized(HttpServletRequest request) {
        return "{\"tips\":\"this is a restful api without authorized\", \"code\":1}";
    }
}
