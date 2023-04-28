package com.zhou.service;

import com.zhou.po.MainUser;
import com.zhou.po.chat;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 登录功能
     * @param MainUserMap 存放mainuser信息的map集合
     * @return 返回实体类对象
     */
    MainUser login(Map<String, Object> MainUserMap);

    /**
     * 注册功能
     * @param MainUserMap 注册的信息
     * @return 返回实体类对象
     */
    MainUser register(Map<String, Object> MainUserMap);

    /**
     * 注销账户功能
     * @param UserId 用户id
     * @return 返回Boolean值
     */
    boolean logoff(Integer UserId);

    /**
     * 关注与取消关注功能
     * @param UserId 用户id
     * @param StoreId 店铺id
     * @param WhetherFollow 0为取消关注，1为关注
     * @return 返回Boolean值
     */
    boolean follow(Integer UserId, Integer StoreId, int WhetherFollow);

    /**
     * 聊天功能
     * @param UserId 用户id
     * @param StoreId 店铺id
     * @param DataChat 聊天数据
     * @return
     */
    boolean Chat(Integer UserId, Integer StoreId, List<Map<String, Object>> DataChat);
    boolean update(Map<String, Object> DataMap);
    Object ShowDetail(Integer Id);
}
