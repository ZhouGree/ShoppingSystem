package com.zhou.utils.pack;

import com.zhou.po.MainUser;
import com.zhou.po.cart;
import com.zhou.po.orders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Pack<T> {

    public Map<String, Object> ChangeMap (Map<String, String[]> map){
        Map<String, Object> map1 = new HashMap<>();
        for(String entry : map.keySet()){
            String Value = Arrays.toString(map.get(entry)) .replaceAll("\\[|\\]", "");

//            if(map.get(entry).length > 0){
//                for(String value : map.get(entry)){
//                    Value = Value + value;
//                }
//            }
            map1.put(entry, Value);
        }
        return map1;
    }
    public Map<String, Object> EntityChangeMap(MainUser mainUser){
        Map<String, Object> map = new HashMap<>();
        map.put("username", mainUser.getUsername());
        map.put("id", mainUser.getId());
        map.put("password", mainUser.getPassword());
        map.put("phone", mainUser.getPhone());
        map.put("role", mainUser.getRole());
        map.put("status",mainUser.isStatus());
        map.put("store_id", mainUser.getStore_id());
        return map;
    }
    public cart MapChangeCart(Map<String, Object> map){
        if(map == null) return null;
        cart cart = new cart();
        cart.setId((Integer) map.get("id"));
        cart.setCount((Integer) map.get("coount"));
        cart.setStoring_id((Integer) map.get("storing_id"));
        cart.setMainuser_id((Integer) map.get("mainuser_id"));
        cart.setStatus((Boolean) map.get("status"));
        return cart;
    }
    public orders MapChangeOrders(Map<String, Object> map){
        if(map == null) return null;
        orders orders = new orders();
        orders.setId((Integer) map.get("id"));
        orders.setStoring_id((Integer) map.get("storing_id"));
        orders.setCount((Integer) map.get("count"));
        orders.setMainuser_id((Integer) map.get("mainuser_id"));
        orders.setStatus((Integer) map.get("status"));
        return orders;
    }
}
