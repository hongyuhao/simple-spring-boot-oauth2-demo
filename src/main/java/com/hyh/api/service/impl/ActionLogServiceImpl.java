package com.hyh.api.service.impl;

import com.hyh.api.dao.ActionLogMapper;
import com.hyh.api.entity.ActionLog;
import com.hyh.api.service.ActionLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 业务实现类
 * @author hyhsoftware@163.com
 * @version 0.0.2
 */
@Service
// 添加事务回滚策略
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
public class ActionLogServiceImpl implements ActionLogService {

    @Resource
    private ActionLogMapper actionLogMapper;

    /**
     * 添加动作记录
     *
     * @param name
     * @param code
     */
    @Override
    public void addActionLog(String name, String code) {
        ActionLog actionLog = new ActionLog();
        actionLog.setActionCode(code);
        actionLog.setName(name);
        actionLogMapper.insert(actionLog);
    }

    /**
     * 获取动作记录
     *
     * @param id
     *
     * @return
     */
    @Override
    public ActionLog getActionLog(int id) {
        return null;
    }

    /**
     * 获取动作列表
     *
     * @return
     */
    @Override
    public List<ActionLog> listActionLog() {
        return null;
    }

    /**
     * 删除动作记录
     *
     * @param id
     */
    @Override
    public void deleteActionLog(int id) {

    }
}
