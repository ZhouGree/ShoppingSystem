package com.zhou.dao.Impl.Relate;

import com.alibaba.fastjson.JSON;
import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.cart;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDaoImpl implements GenericDao<cart> {

    Sql SQL= new Sql();
    JDBCUtils jdbcUtils = JDBCUtils.getInstance();

   private cart pack(Map<String, Object> CartMap) {
       cart cart = new cart();
       cart.setId((Integer) CartMap.get("id"));
       cart.setStoring_id((Integer) CartMap.get("storing_id"));
       cart.setCount((Integer) CartMap.get("count"));
       cart.setMainuser_id((Integer) CartMap.get("mainuser_id"));
       cart.setStatus((Boolean) CartMap.get("status"));
       return cart;
   }

    @Override
    public cart getByName(Map<String, Object> CartMap, int Status) {
       String sql = "select * from cart where ";
       if(Status != 0) sql = "select * from cart where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(CartMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<cart> getAll(Map<String, Object> CartMap) {
        String sql = "select * from cart where status <> 0 and ";
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, new Object[]{CartMap.get("mainuser_id")});
        List<cart> CartList = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : mapList) {
            CartList.add(pack(stringObjectMap));
        }
        return CartList;
    }
    public List<cart> getAll(Integer mainUserId) {
        String sql = "select * from cart where status <> 0 and mainuser_id = ? ";
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, new Object[]{mainUserId});
        List<cart> CartList = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : mapList) {
            CartList.add(pack(stringObjectMap));
        }
        return CartList;
    }

    @Override
    public int insert(Map<String, Object> CartMap) {
       String sql = "insert into cart (storing_id, mainuser_id, count ) values ( ?, ?, ? )";
       int count = jdbcUtils.UpData(sql, new Object[]{CartMap.get("storing_id"), CartMap.get("mainuser_id"), CartMap.get("count")});
        if(count == 0) {
            sql = "update cart set status = 1, count = ? where mainuser_id = ? and storing_id = ? ";
            return (jdbcUtils.UpData(sql, new Object[]{CartMap.get("count"),CartMap.get("mainuser_id"), CartMap.get("storing_id") }) +1);
        }else return count;
    }

    @Override
    public boolean update(Map<String, Object> CartMap, Map<String, Object> IfMap) {
       String sql = "update cart set count = ? where storing_id = ? and mainuser_id = ?";
        return jdbcUtils.UpData(sql, new Object[]{CartMap.get("count"), IfMap.get("storing_id"),IfMap.get("mainuser_id")}) != 0;
    }
    public boolean update(Integer Id, int count){
       String sql = "update cart set count = ? where id = ?";
       return jdbcUtils.UpData(sql, new Object[]{count, Id}) != 0;
    }
    @Override
    public boolean delete(Map<String, Object> CartMap) {
       String sql = "update cart set status = 0 where storing_id = ? and mainuser_id = ?";
        return jdbcUtils.UpData(sql, new Object[]{CartMap.get("storing_id"), CartMap.get("mainuser_id")}) != 0;
    }
    public boolean delete(Integer Id){
       String sql = "update cart set status = 0 where id = ?";
       return jdbcUtils.UpData(sql, new Object[]{Id}) != 0;
    }

    public boolean BathDelete( List<Object[]> ids) {
       if(ids == null) return false;
       String sql = "update cart set status = 0 where id = ? ";
       return jdbcUtils.BathUpdate(sql, ids) != null;

    }

}
