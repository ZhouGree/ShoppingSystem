package com.zhou.dao.Impl.Relate;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.cart;
import com.zhou.po.follow;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FollowDaoImpl implements GenericDao<follow> {
    Sql SQL= new Sql();

    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private follow pack(Map<String, Object> FollowMap) {
       follow follow = new follow();
       follow.setId((Integer) FollowMap.get("id"));
       follow.setMainuser_id((Integer) FollowMap.get("mainuser_id"));
       follow.setStatus((Boolean) FollowMap.get("status"));
       follow.setStore_id((Integer) FollowMap.get("store_id"));
       return follow;
   }


    @Override
    public follow getByName(Map<String, Object> FollowMap, int Status) {
        String sql = "select * from follow where ";
        if(Status != 0) sql = "select * from follow where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(FollowMap, sql,list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<follow> getAll(Map<String, Object> FollowMap) {
        String sql = "select * from follow where status <> 0, ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(FollowMap, sql,list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        List<follow> FollowList = new ArrayList<>();
        for(int i = 0; i < mapList.size(); i++){
            FollowList.add(pack(mapList.get(i)));
        }
        return FollowList;
    }

    @Override
    public int insert(Map<String, Object> FollowMap) {
        String sql = "insert into follow (mainuser_id, store_id) values(?,?)" ;
        Object[] objects = {FollowMap.get("mainuser_id"), FollowMap.get("store_id")};
        return jdbcUtils.UpData(sql, objects);
    }

    @Override
    public boolean update(Map<String, Object> FollowMap, Map<String, Object> IfMap) {
        return false;
    }

    @Override
    public boolean delete(Map<String, Object> FollowMap) {
        return false;
    }


    public boolean Follow(Integer UserId, Integer StoreId, int Status){
       String sql = "update follow set status = ? where user_id = ? and store_id = ?";
       Object[] objects = {Status, UserId, StoreId};
       return jdbcUtils.UpData(sql, objects) != 0;
    }
}
