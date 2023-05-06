package com.zhou.service.Custom.Impl;

import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Relate.OrdersDaoImpl;
import com.zhou.po.MainUser;
import com.zhou.po.orders;
import com.zhou.service.Custom.CustomService;

import java.util.*;

public class OrdersServiceImpl implements CustomService <orders>, Constant {
    OrdersDaoImpl ordersDao = new OrdersDaoImpl();

//    private List<Object[]> Change(Map<String, Object> DataMap, String sql1, String sql2, String sql){
//        if (DataMap == null) return null;
//        ArrayList<Objects> objectsArrayList = new ArrayList<>();
//        Map.Entry<String, Object> firstEntry = DataMap.entrySet().iterator().next();
//        sql = sql + firstEntry.getKey() + sql2;
//        objectsArrayList.add((Objects) firstEntry.getValue());
//        DataMap.remove(DataMap.keySet().iterator().next());
//        List<Object[]> list = new ArrayList<>();
//        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
//        for (Map.Entry<String, Object> entry : entrySet){
//            sql = sql + sql1 + entry.getKey() + sql2;
//            objectsArrayList.add((Objects) entry.getValue());
//        }
//
//        list.add(new Object[]{sql});
//        list.add(objectsArrayList.toArray());
//        return list;
//    }

    /**
     * 查询订单信息
     * @param DataMap 存放订单信息的map集合
     * @return 返回订单的信息是orders对象
     */
    @Override
    public orders query(Map<String, Object> DataMap) {
        if(DataMap == null) return null;
        return ordersDao.getByName(DataMap, ORDERS_NON_PAYMENT);
    }
    public List<orders> queryAll(Integer UserId){
        if(UserId==0) return null;
        Map<String, Object> map = new HashMap<>();
        map.put("mainuser_id", UserId);
        return ordersDao.getAll(map);
    }

    /**
     * 添加信息
     * @param DataMap 存放订单信息的map集合
     * @return 返回添加的信息的id值
     */
    @Override
    public int AddId(Map<String, Object> DataMap) {
        if(DataMap == null || !DataMap.containsKey("commodity_id") || !DataMap.containsKey("mainuser_id")) return 0;
        Integer status = 1;
        if( DataMap.containsKey("status") ) {
            if((int)DataMap.get("status") != 0){
                status = (Integer) DataMap.get("status");
            }
        }
        DataMap.put("status", status);
        orders orders = ordersDao.getByName(DataMap, ORDERS_NON_PAYMENT);
       if(orders == null){
           return ordersDao.insert(DataMap);
       }else {
          if(!DataMap.containsKey("count")){
              DataMap.put("count", 1);
          }
          Object[] objects = {DataMap.get("count"), DataMap.get("status"), DataMap.get("commodity_id"), DataMap.get("mainuser_id")};
          return ordersDao.update(objects);
       }
    }

    /**
     * 删除订单
     * @param DataMap 存放订单信息的map集合
     * @return 返回Boolean值
     */
    @Override
    public boolean Delete(Map<String, Object> DataMap) {
       if (DataMap == null || !DataMap.containsKey("commodity_id") || !DataMap.containsKey("mainuser_id"))
           return false;
       if(DataMap.containsKey("id")){
           return ordersDao.delete((Integer) DataMap.get("id"));
       }
       return ordersDao.delete(DataMap);
    }

    public boolean update(Integer OrdersId, int Status){
        if(OrdersId == 0) return false;
        return ordersDao.update(OrdersId, Status);
    }

    @Override
    public boolean bathAdd(Map<String, Object> DataMap, List<Map<String, Object>> Datalist) {
        return false;
    }
    public boolean bathUpdate(Map<String, Object> DataMap){
        return false;
    }
    @Override
    public boolean bathDelete( List<Integer> OrdersIds) {
        return false;
    }

    @Override
    public boolean DeleteCommodity(Map<String, Object> DataMap) {
        return false;
    }
}
