package com.zhou.service;

import com.zhou.po.Store;

import java.util.List;
import java.util.Map;

public interface StoreService {
    void SetUp(Map<String, Object> StoreMap, Integer MainUserId);
    Store showStore(Map<String, Object> StoreMap);
    boolean AddCommodity(Map<String, Object> CommodityMap, Integer StoreId);
    boolean DeleteCommodity(Integer StoreId, Integer CommodityId);
    boolean ClearCommodity(Integer StoreId, Object[] CommodityIds);
    boolean FoldUp(Integer StoreId, Integer MainUserId);
    List<Store> queryAll(Map<String, Object> map);

}
