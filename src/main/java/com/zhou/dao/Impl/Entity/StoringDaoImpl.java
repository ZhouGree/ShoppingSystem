package com.zhou.dao.Impl.Entity;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.storing;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StoringDaoImpl implements GenericDao<storing> {
    Sql SQL= new Sql();

    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private storing pack(Map<String, Object> StoringMap) {
       storing storing = new storing();
       storing.setId((Integer) StoringMap.get("id"));
       storing.setCommodity_id((Integer) StoringMap.get("commodity_id"));
       storing.setDescription((String) StoringMap.get("description"));
       storing.setPicture((String) StoringMap.get("picture"));
       storing.setPrice((Integer) StoringMap.get("price"));
       storing.setRepertory((Long) StoringMap.get("repertory"));
       storing.setSales((Long) StoringMap.get("sales"));
       storing.setStore_id((Integer) StoringMap.get("store_id"));
       storing.setStatus((Integer) StoringMap.get("status"));
       storing.setCommodityname((String) StoringMap.get("commodityname"));
       storing.setStorename((String) StoringMap.get("storename"));
       return storing;
   }

    @Override
    public storing getByName(Map<String, Object> StoringMap, int Status) {
       return null;
    }
    public List<storing> getName(Map<String, Object> StoringMap, int Status){
        List<storing> StoringList = new ArrayList<>();
        if(StoringMap.get("id") != null){
            String sql = "select * from storing where status = ? and id = ?";
            List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, new Object[]{Status,StoringMap.get("id")});
            for (Map<String, Object> map : mapList) {
                StoringList.add(pack(map));
            }
            return StoringList;
        }else {
            String sql = "select * from storing where status = ? and commodityname = ?";
            List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, new Object[]{Status,StoringMap.get("commodityname")});
            for (Map<String, Object> map : mapList) {
                StoringList.add(pack(map));
            }
            return StoringList;
        }
    }
    @Override
    public List<storing> getAll(Map<String, Object> StoringMap) {
       if(StoringMap == null || !StoringMap.containsKey("status")) return null;
        List<Map<String, Object>> mapList = new ArrayList<>();
        if(StoringMap.get("store_id") == null){
            String sql = "select * from storing where status = ? " ;
            mapList = jdbcUtils.queryALL(sql,new Object[]{StoringMap.get("status")});
        }else {
            String sql = "select * from storing where status = ? and store_id = ?" ;
            mapList = jdbcUtils.queryALL(sql,new Object[]{StoringMap.get("status"), StoringMap.get("store_id")});
        }
        List<storing> StoringList = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            StoringList.add(pack(map));
        }
        return StoringList;
    }

    @Override
    public int insert(Map<String, Object> StoringMap) {
       String sql = "insert into storing ( ";
       String sql1 = " values ( ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Insert(StoringMap, sql, sql1, list);
        return jdbcUtils.UpData(sql, list.toArray());
    }

    @Override
    public boolean update(Map<String, Object> StoreMap, Map<String, Object> IfMap) {
        return false;
    }

    @Override
    public boolean delete(Map<String, Object> StoreMap) {
        String sql = "update storing set status = 0 where store_id = ? and commodity_id = ?";
        return jdbcUtils.UpData(sql, new Object[]{StoreMap.get("store_id"), StoreMap.get("commodity_id")}) != 0;
    }
    public int deleteStoreId(Integer storeId){
       String sql = "update storing set status = 0 where store_id = ?";
       return jdbcUtils.UpData(sql, new Object[]{storeId});
    }

}
