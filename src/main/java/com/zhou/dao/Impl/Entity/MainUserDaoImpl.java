package com.zhou.dao.Impl.Entity;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.MainUser;
import com.zhou.utils.JDBCUtils;

import java.util.*;

public class MainUserDaoImpl implements GenericDao<MainUser> {
    Sql SQL= new Sql();

    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private MainUser pack(Map<String, Object> MainUserMap) {
       MainUser mainUser = new MainUser();
       mainUser.setId((Integer) MainUserMap.get("id"));
       mainUser.setRole((Integer) MainUserMap.get("role"));
       mainUser.setStore_id((Integer) MainUserMap.get("store_id"));
       mainUser.setUsername((String) MainUserMap.get("username"));
       mainUser.setPicture((String) MainUserMap.get("picture"));
       mainUser.setPassword((String) MainUserMap.get("password"));
       mainUser.setPhone((String) MainUserMap.get("phone"));
       mainUser.setStatus((Boolean) MainUserMap.get("status"));
       return mainUser;
   }

    @Override
    public MainUser getByName(Map<String, Object> MainUserMap, int Status) {
        String sql = "select * from mainuser where ";
        if(Status != 0) sql = "select * from mainuser where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(MainUserMap, sql,list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<MainUser> getAll(Map<String, Object> MainUserMap) {
        String sql = "select * from mainuser where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(MainUserMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        List<MainUser> MainUserList = new ArrayList<>();
        for(int i = 0; i < mapList.size(); i++){
            MainUserList.add(pack(mapList.get(i)));
        }
        return MainUserList;
    }

    @Override
    public int insert(Map<String, Object> MainUserMap) {
        String sql = "insert into mainuser (username, phone, password) values(?, ?, ?)";
        Object[] objects = {MainUserMap.get("username"), MainUserMap.get("phone"), MainUserMap.get("password")};
        return jdbcUtils.UpData(sql, objects);
    }

    @Override
    public boolean update(Map<String, Object> MainUserMap, Map<String, Object> IfMap) {
        String sql = "update mainuser set ";
        String sql1 = " where ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Update(MainUserMap, sql, IfMap, sql1, list);
        return jdbcUtils.UpData(sql, list.toArray()) != 0;
    }
    public void updateRole(Integer MainUserId, Integer role){
       String sql = "update mainuser set role = ? where id = ? ";
        jdbcUtils.UpData(sql, new Object[]{role, MainUserId});
    }
    @Override
    public boolean delete(Map<String, Object> MainUserMap) {
       String sql = "update mainuser set status = 0 where ";
       List<Object> list = new ArrayList<>();
       sql = SQL.Delete(MainUserMap, sql,list);
        return jdbcUtils.UpData(sql, list.toArray()) != 0;
    }
    public boolean delete(Integer UserId){
       String sql = "update mainuser set status = 0 where id = ? ";
       return jdbcUtils.UpData(sql, new Object[]{UserId}) != 0;
    }

}
