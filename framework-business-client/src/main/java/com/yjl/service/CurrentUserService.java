package com.yjl.service;

/**
 * @author unbroken
 * @Description 当前用户信息服务接口
 * @Version 1.0
 * @date 2024/7/21 1:10
 */
public interface CurrentUserService {
    /**
     * 获取用户的id
     */
    String getId();
    /**
     * 获取用户的唯一标识
     */
    String getCode();
    /**
     * 获取用户的名称
     */
    String getName();
}
