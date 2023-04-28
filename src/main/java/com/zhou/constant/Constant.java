package com.zhou.constant;

public interface Constant {
    /**
     * redis中存储分类列表的key
     */
    String STORE_CATEGORY_LIST="STORE_CATEGORY_LIST";

    /**
     * redis的服务器地址
     */
    String REDIS_HOST = "192.168.17.136";

    /**
     * redis的服务器端口号
     */
    int  REDIS_PORT =  6379;

    /**
     * 店铺：运营中
     */
    int STORE_OPERATION = 0;
    /**
     * 店铺：审核中
     */
    int STORE_UNCHECK = 1;
    /**
     * 店铺：倒闭
     */
    int STORE_SHUT = 2;

    /**
     * 商品：上架
     */
    int STORING_IS_UP = 0;
    /**
     * 商品：审核中
     */
    int STORING_UNCHECK = 1;
    /**
     * 商品：下架
     */
    int STORING_IS_DOWN = 2;

    /**
     * 不存在
     */
    int NONEXISTENCE = 0;

    /**
     * 存在
     */
    int EXISTENCE = 1;

    /**
     *payment: 已付款
     */
    int PAYMENT = 1;
    /**
     * payment: 未付款
     */
    int NON_PAYMENT = 0;

    /**
     * 订单不存在
     */
    int ORDERS_DELETE = 0;

    /**
     * 订单状态 未付款
     */
    int ORDERS_NON_PAYMENT = 1;

    /**
     * 订单状态 未发货
     */
    int ORDERS_NOT_SHIPPED = 2;

    /**
     * 订单状态 收货
     */
    int ORDERS_SHIPPED = 3;

    /**
     * 订单状态 售后
     */
    int ORDERS_AFTER_SALE = 4;

    /**
     * 普通用户
     */
    int ROLE_regularUser = 0;
    /**
     * 店铺管理员
     */
    int ROLE_storeMANAGER = 1;

    /**
     * 网站管理员
     */
    int ROLE_webMANAGER = 2;

    /**
     * 投诉：未审核
     */
    int UNCHECK_COMPLAINT = 1;
    /**
     * 投诉：审核
     */
    int CHECKED_COMPLAINT = 0;
}
