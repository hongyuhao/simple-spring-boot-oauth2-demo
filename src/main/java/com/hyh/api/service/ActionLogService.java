package com.hyh.api.service;

import com.hyh.api.entity.ActionLog;

import java.util.List;

/**
 * 业务逻辑类，service层
 * @author hyhsoftware@163.com
 * @version 0.0.2
 */
public interface ActionLogService {

    /**
     * 添加动作记录
     * @param name
     * @param code
     */
    void addActionLog(String name, String code);

    /**
     * 获取动作记录
     * @param id
     * @return
     */
    ActionLog getActionLog(int id);

    /**
     * 获取动作列表
     * @return
     */
    List<ActionLog> listActionLog();

    /**
     * 删除动作记录
     * @param id
     */
    void deleteActionLog(int id);
}
