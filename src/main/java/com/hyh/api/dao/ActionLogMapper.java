package com.hyh.api.dao;

import com.hyh.api.entity.ActionLog;

/**
 * 插件生成的Mapper类
 * @author hyhsoftware@163.com
 * @version 0.0.2
 */
public interface ActionLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table action_log
     *
     * @mbggenerated Mon Aug 28 11:10:37 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table action_log
     *
     * @mbggenerated Mon Aug 28 11:10:37 CST 2017
     */
    int insert(ActionLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table action_log
     *
     * @mbggenerated Mon Aug 28 11:10:37 CST 2017
     */
    int insertSelective(ActionLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table action_log
     *
     * @mbggenerated Mon Aug 28 11:10:37 CST 2017
     */
    ActionLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table action_log
     *
     * @mbggenerated Mon Aug 28 11:10:37 CST 2017
     */
    int updateByPrimaryKeySelective(ActionLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table action_log
     *
     * @mbggenerated Mon Aug 28 11:10:37 CST 2017
     */
    int updateByPrimaryKey(ActionLog record);
}