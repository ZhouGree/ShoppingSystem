package com.zhou.dao.Impl.Relate;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.cart;
import com.zhou.po.payment;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentDaoImpl implements GenericDao<payment> {
    Sql SQL= new Sql();
    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private payment pack(Map<String, Object> PaymentMap) {
       payment payment = new payment();
       payment.setStoring_id((Integer) PaymentMap.get("storing_id"));
       payment.setId((Integer) PaymentMap.get("id"));
       payment.setMainuser_id((Integer) PaymentMap.get("mainuser_id"));
       payment.setStatus((Boolean) PaymentMap.get("status"));
       return payment;
   }

    @Override
    public payment getByName(Map<String, Object> PaymentMap, int Status) {
        String sql = "select * from payment where ";
        if(Status != 0) sql = "select * from payment where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(PaymentMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<payment> getAll(Map<String, Object> PaymentMap) {
        String sql = "select * from payment where  status <> 0, ";
        List<Object> list = new ArrayList<>();
        sql= SQL.Select(PaymentMap, sql,list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        List<payment> PaymentList = new ArrayList<>();
        for(int i = 0; i < mapList.size(); i++){
            PaymentList.add(pack(mapList.get(i)));
        }
        return PaymentList;
    }

    @Override
    public int insert(Map<String, Object> StoreMap) {
        return 0;
    }

    @Override
    public boolean update(Map<String, Object> StoreMap, Map<String, Object> IfMap) {
        return false;
    }

    @Override
    public boolean delete(Map<String, Object> StoreMap) {
       String sql = "update set status = 0 where";
        return false;
    }


}
