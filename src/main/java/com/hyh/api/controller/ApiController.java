package com.hyh.api.controller;

import com.hyh.api.service.ActionLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * restful入口类
 * @author hyhsoftware@163.com
 * @version 0.0.2
 */
@RestController
public class ApiController {

    @Resource
    private ActionLogService actionLogService;

    /**
     * 在授权控制范围内的接口
     */
    private static final String API_TEST_WITH_AUTHORIZED = "/api/test";
    /**
     * 动作记录，在授权控制范围内的接口
     */
    private static final String API_ALTION_LOG = "/api/actionLog";
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

    @PostMapping(value = API_ALTION_LOG)
    public Object postActionLog(HttpServletRequest request) {
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        actionLogService.addActionLog(name, code);
        return "{\"tips\":\"success\", \"code\":1}";
    }
}
