package com.zhou.dao.Impl.Entity;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.Store;
import com.zhou.po.cart;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StoreDaoImpl implements GenericDao<Store> {
    Sql SQL= new Sql();

    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private Store pack(Map<String, Object> StoreMap) {
       Store store = new Store();
       store.setId((Integer) StoreMap.get("id"));
       store.setDescription((String) StoreMap.get("description"));
       store.setPicture((String) StoreMap.get("picture"));
       store.setStorename((String) StoreMap.get("storename"));
       store.setScore((Float) StoreMap.get("score"));
       store.setStatus((Integer) StoreMap.get("status"));
       store.setSales((Long) StoreMap.get("sales"));
       return store;
   }

    @Override
    public Store getByName(Map<String, Object> StoreMap, int Status) {
        String sql = "select * from store where ";
        if(Status == 0) sql = "select * from store where status = 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(StoreMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<Store> getAll(Map<String, Object> StoreMap) {
        String sql = "select * from store where status = ? ";
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, new Object[]{StoreMap.get("status")});
        List<Store> StoreList = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            StoreList.add(pack(map));
            System.out.println(pack(map).toString());
        }
        return StoreList;
    }

    @Override
    public int insert(Map<String, Object> StoreMap) {
       String sql = "insert into store ( ";
       String sql1 = " values ( ";
       List<Object> list = new ArrayList<>();
       sql = SQL.Insert(StoreMap, sql, sql1,list);
        return jdbcUtils.UpData(sql, list.toArray());
    }

    @Override
    public boolean update(Map<String, Object> StoreMap, Map<String, Object> IfMap) {
       String sql = "update store set ";
       String sql1 = " where ";
       List<Object> list = new ArrayList<>();
       sql = SQL.Update(StoreMap, sql, IfMap, sql1,list);
        return jdbcUtils.UpData(sql, list.toArray()) != 0;
    }

    @Override
    public boolean delete(Map<String, Object> StoreMap) {
       String sql = "update store set status = 0 where ";
        List<Object> list = new ArrayList<>();
       sql = SQL.Delete(StoreMap, sql,list);

        return jdbcUtils.UpData(sql, list.toArray()) != 0;
    }


}
