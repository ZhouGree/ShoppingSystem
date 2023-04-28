package com.zhou.service.Impl;

import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Entity.CommodityDaoImpl;
import com.zhou.dao.Impl.Entity.MainUserDaoImpl;
import com.zhou.dao.Impl.Entity.StoreDaoImpl;
import com.zhou.dao.Impl.Entity.StoringDaoImpl;
import com.zhou.po.Commodity;
import com.zhou.po.Store;
import com.zhou.po.storing;
import com.zhou.service.StoreService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreServiceImpl implements StoreService, Constant {
    StoreDaoImpl storeDao = new StoreDaoImpl();
    CommodityDaoImpl commodityDao = new CommodityDaoImpl();
    StoringDaoImpl storingDao = new StoringDaoImpl();
    MainUserDaoImpl mainUserDao = new MainUserDaoImpl();
    /**
     * 开店铺
     * @param StoreMap 申请开店铺时填入的信息
     * @param MainUserId 用户id（申请开店铺的用户）
     */
    @Override
    public void SetUp(Map<String, Object> StoreMap, Integer MainUserId) {
        if(StoreMap == null || !StoreMap.containsKey("storename")) return ;
        Map<String, Object> map = new HashMap<>();
        map.put("storename", StoreMap.get("storename"));
        Store store = storeDao.getByName(map, STORE_SHUT);
        if(store != null){
            if(store.getStatus() != STORE_SHUT) {
            }
            else {
                StoreMap.remove("storename");
                StoreMap.put("status", STORE_UNCHECK);
                if(storeDao.update(StoreMap, map)) {
                    store.setDescription((String) StoreMap.get("description"));
                    store.setPicture((String) StoreMap.get("picture"));
                    store.setScore(4);
                    mainUserDao.updateRole(MainUserId, ROLE_storeMANAGER);
                }
            }
            return ;
        }
        int count = storeDao.insert(StoreMap);
        if( count != 0) {
            map.clear();
            map.put("id", MainUserId);
            StoreMap.clear();
            StoreMap.put("role", ROLE_storeMANAGER);
            StoreMap.put("store_id", count);
            mainUserDao.update(StoreMap, map);
        }
    }

    @Override
    public Store showStore(Map<String, Object> StoreMap) {
        if(StoreMap == null) return null;
       return storeDao.getByName(StoreMap, STORE_OPERATION);
    }

    @Override
    public boolean AddCommodity(Map<String, Object> CommodityMap, Integer StoreId) {
        if (CommodityMap == null || !CommodityMap.containsKey("commodityname")) return false;
        Map<String, Object> map = new HashMap<>();
        map.put("commodityname", CommodityMap.get("commodityname"));
        //CommodityMap集合去除商品名称信息
        CommodityMap.remove("commodityname");
        Commodity commodity = commodityDao.getByName(map, NONEXISTENCE);
        //商品表中是否已存在该商品
        if(commodity == null){
            int id = commodityDao.insert(map);
            if(id != 0){
                CommodityMap.put("commodity_id", id);
                return storingDao.insert(CommodityMap) != 0;
            }
           return false;
        }else {
            if(!commodity.isStatus()){
                if(!commodityDao.active(commodity.getId())) return false;
            }
            //map集合只保留两个id值
            map.put("commodity_id", commodity.getId());
            map.put("store_id", StoreId);
            map.remove("commodityname");
            storing storing = storingDao.getByName(map, STORE_OPERATION);

            if(storing == null){
                CommodityMap.put("store_id",StoreId);
                CommodityMap.put("commodity_id", commodity.getId());
                return storingDao.insert(CommodityMap) != 0;
            }else {
                CommodityMap.remove("commodity_id");
                CommodityMap.remove("store_id");
                CommodityMap.put("status", true);
                Map<String, Object> storingMap = new HashMap<>();
                storingMap.put("id", storing.getId());
                return storingDao.update(CommodityMap, storingMap);
            }
        }
    }

    @Override
    public boolean DeleteCommodity(Integer StoreId, Integer CommodityId) {
        if(StoreId == 0 || CommodityId == 0) return false;
        Map<String, Object> map = new HashMap<>();
        map.put("commodity_id", CommodityId);
        map.put("store_id", StoreId);
        return storingDao.delete(map);
    }

    @Override
    public boolean ClearCommodity(Integer StoreId, Object[] ids) {
        if(StoreId == 0 || ids == null) return false;
        return false;
    }

    /**
     * 关店倒闭
     * @param StoreId 店铺id
     * @param MainUserId 与店铺关联的用户id（开店铺的用户）
     * @return Boolean值
     */
    @Override
    public boolean FoldUp(Integer StoreId, Integer MainUserId) {
        if(StoreId == 0 || MainUserId == 0) return false;
        Map<String, Object> map = new HashMap<>();
        map.put("id", StoreId);
        //将店铺的status设为0
        if(storeDao.delete(map)){
            ////将该店铺关联的用户的店铺id设为0
            mainUserDao.updateRole(StoreId, 0);
            //将该店铺是商品下架
            storingDao.deleteStoreId(StoreId);
            return true;
        }
        return false;
    }

    @Override
    public List<Store> queryAll(Map<String, Object> map) {

        return null;
    }

    public boolean SetStatus(Map<String, Object> map, int Status) {

        return false;
    }
}
