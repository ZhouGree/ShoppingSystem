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

    /**
     * 查询mainuser数据，
     * @param MainUserMap 储存条件的map集合
     * @param Status 查找的status条件
     * @return 返回mainuser对象
     */
    @Override
    public MainUser getByName(Map<String, Object> MainUserMap, int Status) {
        String sql = "select * from mainuser where ";
        if(Status != 0) sql = "select * from mainuser where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(MainUserMap, sql,list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        if(mapList.isEmpty()) return null;
        return pack(mapList.get(0));
    }

    @Override
    public List<MainUser> getAll(Map<String, Object> MainUserMap) {
        String sql = "select * from mainuser where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(MainUserMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        List<MainUser> MainUserList = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            MainUserList.add(pack(map));
        }
        return MainUserList;
    }

    /**
     * 添加数据
     * @param MainUserMap （只添加姓名，手机号，密码） 因为只从前端接收这三个数据
     * @return 返回出入数据库后的id值
     */
    @Override
    public int insert(Map<String, Object> MainUserMap) {
        String sql = "insert into mainuser (username, phone, password) values(?, ?, ?)";
        Object[] objects = {MainUserMap.get("username"), MainUserMap.get("phone"), MainUserMap.get("password")};
        return jdbcUtils.UpData(sql, objects);
    }

    /**
     * 修改数据
     * @param MainUserMap 修改数据的集合
     * @param IfMap 条件集合
     * @return 返回Boolean值
     */
    @Override
    public boolean update(Map<String, Object> MainUserMap, Map<String, Object> IfMap) {
        String sql = "update mainuser set ";
        String sql1 = " where ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Update(MainUserMap, sql, IfMap, sql1, list);
        return jdbcUtils.UpData(sql, list.toArray()) == 0;
    }

    /**
     * 修改角色
     * @param MainUserId 用户id
     * @param role 角色码
     * @return 返回Boolean值
     */
    public boolean updateRole(Integer MainUserId, int role){
       String sql = "update mainuser set role = ? where id = ? ";
       return jdbcUtils.UpData(sql, new Object[]{role, MainUserId}) == 0;
    }

    /**
     *删除数据（修改状态码）
     * @param MainUserMap 条件集合
     * @return 返回boolean值
     */
    @Override
    public boolean delete(Map<String, Object> MainUserMap) {
        if(MainUserMap == null) return false;
       String sql = "update mainuser set status = 0 where ";
       List<Object> list = new ArrayList<>();
       sql = SQL.Delete(MainUserMap, sql,list);
        return jdbcUtils.UpData(sql, list.toArray()) == 0;
    }
    public boolean delete(Integer UserId){
        if(UserId == 0) return false;
       String sql = "update mainuser set status = 0 where id = ? ";
       return jdbcUtils.UpData(sql, new Object[]{UserId}) == 0;
    }

}
