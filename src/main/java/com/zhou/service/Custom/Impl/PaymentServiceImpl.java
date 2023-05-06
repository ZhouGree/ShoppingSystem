package com.zhou.service.Custom.Impl;

import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Relate.PaymentDaoImpl;
import com.zhou.po.payment;
import com.zhou.service.Custom.CustomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements CustomService<payment> , Constant {
    PaymentDaoImpl paymentDao = new PaymentDaoImpl();
    @Override
    public payment query(Map<String, Object> DataMap) {
        if(DataMap == null) return null;
        Map<String, Object> map = new HashMap<>();
        if(DataMap.containsKey("id")) {
            map.put("id", DataMap.get("id"));
            payment payment = new payment();
            payment = paymentDao.getByName(map, PAYMENT);
            return payment;
        }
        map.put("storing_id", DataMap.get("storing_id"));
        map.put("mainuser_id", DataMap.get("mainuser_id"));
        return paymentDao.getByName(map, PAYMENT);
    }

    @Override
    public int AddId(Map<String, Object> DataMap) {
        if(DataMap == null) return 0;
        Map<String, Object> map = new HashMap<>();
        map.put("storing_id", DataMap.get("storing_id"));
        map.put("mainuser_id", DataMap.get("mainuser_id"));
        payment payment = paymentDao.getByName(map, NON_PAYMENT);
        if(payment == null)
            return paymentDao.insert(DataMap);
        map.clear();
        map.put("status",true);
        map.put("count",DataMap.get("count"));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", payment.getId());
        if (paymentDao.update(map, map1)) return payment.getId();
        return 0;
    }

    @Override
    public boolean Delete(Map<String, Object> DataMap) {
        if(DataMap == null) return false;
        return paymentDao.delete(DataMap);
    }

    @Override
    public boolean bathAdd(Map<String, Object> DataMap, List<Map<String, Object>> Datalist) {
        return false;
    }

    @Override
    public boolean bathDelete(List<Integer> PaymentIds) {
        return false;
    }

    @Override
    public boolean DeleteCommodity(Map<String, Object> DataMap) {
        return false;
    }
}
