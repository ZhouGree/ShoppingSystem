package com.zhou.dao.Impl.Relate;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.zhou.constant.Constant;
import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.cart;
import com.zhou.po.orders;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersDaoImpl implements GenericDao<orders>, Constant {
    Sql SQL= new Sql();

    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private orders pack(Map<String, Object> OrdersMap) {
       orders orders = new orders();
       orders.setStoring_id((Integer) OrdersMap.get("storing_id"));
       orders.setCount((Integer) OrdersMap.get("count"));
       orders.setId((Integer) OrdersMap.get("id"));
       orders.setMainuser_id((Integer) OrdersMap.get("mainuser_id"));
       orders.setStatus((Integer) OrdersMap.get("status"));
       return orders;
   }

    @Override
    public orders getByName(Map<String, Object> OrdersMap, int Status) {
        String sql = "select * from orders where ";
        if(Status != 0) sql = "select * from orders where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(OrdersMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<orders> getAll(Map<String, Object> OrdersMap) {
        String sql = "select * from orders where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(OrdersMap, sql,list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        List<orders> OrdersList = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : mapList) {
            OrdersList.add(pack(stringObjectMap));
        }
        return OrdersList;
    }

    @Override
    public int insert(Map<String, Object> OrdersMap) {
       Integer Status = 1;
       if(OrdersMap.containsKey("status")) Status = (Integer) OrdersMap.get("status");
       String sql = "insert into orders ( commodity_id, mainuser_id, count, status ) values (?, ?, ?, status)";
        return jdbcUtils.UpData(sql, new Object[]{OrdersMap.get("commodity_id"), OrdersMap.get("mainuser_id"), OrdersMap.get("count"), Status});
    }

    @Override
    public boolean update(Map<String, Object> OrdersMap, Map<String, Object> IfMap) {
       if(OrdersMap == null || IfMap == null) return false;
       int Status = (int) OrdersMap.get("status");
       OrdersMap.remove("status");
       String sql = "update orders set status = ? where id = ?";
        Integer ordersId = (Integer) IfMap.get("orders_id");
        Object[] objects = null;
        List<Object[]> list = new ArrayList<>();
        for(String key : OrdersMap.keySet()){
            objects = new Object[]{Status ,OrdersMap.get(key)};
            list.add(objects);
        }
        return jdbcUtils.BathUpdate(sql, list) != null;
    }
    public int update (Object[] objects){
       String sql = "update orders set count = ? , status = ? where commodity_id = ? and mainuser_id = ?";
       return jdbcUtils.UpData(sql, objects);
    }
    public boolean update(Integer OrdersId, int Status){
       if(OrdersId == 0) return false;
       String sql = "update orders set status = ? where id = ?";
       return jdbcUtils.UpData(sql, new Object[]{Status, OrdersId}) != 0;
    }
    @Override
    public boolean delete(Map<String, Object> OrdersMap) {
       String sql = "update orders set status = 0 where commodity_id = ? and mainuser_id = ?";
        return jdbcUtils.UpData(sql, new Object[]{OrdersMap.get("commodity_id"), OrdersMap.get("mainuser_id")}) != 0;
    }
    public boolean delete(Integer Id){
       String sql = "update orders set status = 0 where id = ?";
       return jdbcUtils.UpData(sql, new Object[]{Id}) != 0;
    }

}
