package com.zhou.service.Custom.Impl;

import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Relate.CartDaoImpl;
import com.zhou.po.cart;
import com.zhou.po.orders;
import com.zhou.service.Custom.CustomService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CustomService <cart>, Constant {

    CartDaoImpl cartDao = new CartDaoImpl();

    @Override
    public cart query(Map<String, Object> DataMap) {
        if(DataMap == null) return null;
        Map<String, Object> map = new HashMap<>();
        if(DataMap.containsKey("id")){
            map.put("id", DataMap.get("id"));
            return cartDao.getByName(map, EXISTENCE);
        }
        return null;
    }

    @Override
    public int AddId(Map<String, Object> DataMap) {
        if(DataMap == null || !DataMap.containsKey("storing_id") || !DataMap.containsKey("mainuser_id")) return 0;
        return cartDao.insert(DataMap);
    }

    @Override
    public boolean Delete(Map<String, Object> DataMap) {
        if(DataMap == null || !DataMap.containsKey("commodity_id") || !DataMap.containsKey("mainuser_id")) return false;
        return cartDao.delete(DataMap);
    }

    @Override
    public boolean bathAdd(Map<String, Object> DataMap, List<Map<String, Object>> Datalist) {
        return false;
    }

    @Override
    public boolean bathDelete(List<Integer> CartIds) {
        if(CartIds == null) return false;
        List<Object[]> list = new ArrayList<>();
        for (Integer cartId : CartIds) {
            list.add(new Object[]{cartId});
        }
        return cartDao.BathDelete(list);
    }

    @Override
    public boolean DeleteCommodity(Map<String, Object> DataMap) {

        return false;
    }

    public List<cart> queryAll(Integer MainUserId){
        if(MainUserId == 0) return null;
        return cartDao.getAll(MainUserId);
    }
}
