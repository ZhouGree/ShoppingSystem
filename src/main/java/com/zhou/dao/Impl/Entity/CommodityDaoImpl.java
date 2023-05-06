package com.zhou.dao.Impl.Entity;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.Commodity;
import com.zhou.po.cart;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommodityDaoImpl implements GenericDao<Commodity> {
    Sql SQL = new Sql();
    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private Commodity pack(Map<String, Object> CommodityMap) {
       Commodity commodity = new Commodity();
       commodity.setId((Integer) CommodityMap.get("id"));
       commodity.setDescription((String) CommodityMap.get("description"));
       commodity.setCommodityname((String) CommodityMap.get("commodityname"));
       commodity.setStatus((Boolean) CommodityMap.get("status"));
       return commodity;
   }


    @Override
    public Commodity getByName(Map<String, Object> CommodityMap, int Status) {
        String sql = "select * from commodity where ";
        if(Status != 0) sql = "select * from commodity where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(CommodityMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<Commodity> getAll(Map<String, Object> CommodityMap) {
        String sql = "select * from commodity where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(CommodityMap, sql,list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql ,list.toArray());
        List<Commodity> CommodityList = new ArrayList<>();
        for(int i = 0; i < mapList.size(); i++){
            CommodityList.add(pack(mapList.get(i)));
        }
        return CommodityList;
    }

    @Override
    public int insert(Map<String, Object> CommodityMap) {
       String sql = "insert into commodity ( ";
       String sql1 = " values ( ";
       List<Object> list = new ArrayList<>();
       sql = SQL.Insert(CommodityMap, sql, sql1, list);
       return jdbcUtils.UpData(sql, list.toArray());
    }

    @Override
    public boolean update(Map<String, Object> CommodityMap, Map<String, Object> IfMap) {
       String sql = "update commodity set " ;
       String sql1 = " where ";
       List<Object> list = new ArrayList<>();
       sql = SQL.Update(CommodityMap, sql, IfMap, sql1, list);

       return jdbcUtils.UpData(sql, list.toArray()) != 0;
    }


    public boolean active(Integer CommodityId){
       String sql = "update commodity set status where id = ?";
       return jdbcUtils.UpData(sql, new Object[]{CommodityId}) != 0;
    }
    @Override
    public boolean delete(Map<String, Object> CommodityMap) {
        String sql = "update commodity set status = 0 where ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Delete(CommodityMap, sql, list);
        return jdbcUtils.UpData(sql, list.toArray()) != 0;
    }


}
