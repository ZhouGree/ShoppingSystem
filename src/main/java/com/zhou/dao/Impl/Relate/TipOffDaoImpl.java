package com.zhou.dao.Impl.Relate;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.cart;
import com.zhou.po.tipoff;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TipOffDaoImpl implements GenericDao<tipoff> {
    Sql SQL= new Sql();

    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private tipoff pack(Map<String, Object> TipOffMap) {
       tipoff tipoff = new tipoff();
       tipoff.setStoring_id((Integer) TipOffMap.get("storing_id"));
       tipoff.setDescription((String) TipOffMap.get("description"));
       tipoff.setId((Integer) TipOffMap.get("id"));
       tipoff.setMainuser_id((Integer) TipOffMap.get("mainuser_id"));
       tipoff.setStatus((Boolean) TipOffMap.get("status"));
       return tipoff;
   }

    @Override
    public tipoff getByName(Map<String, Object> TipOffMap, int Status) {
        String sql = "select * from tipoff where status = 1 and";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(TipOffMap, sql, list);
        list.remove(0);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<tipoff> getAll(Map<String, Object> TipOffMap) {
        String sql = "select * from tipoff where status = ? ";
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, new Object[] { TipOffMap.get("status")});
        List<tipoff> TipOffList = new ArrayList<>();
        for(int i = 0; i < mapList.size(); i++){
            TipOffList.add(pack(mapList.get(i)));
        }
        return TipOffList;
    }

    @Override
    public int insert(Map<String, Object> StoreMap) {
        return 0;
    }

    @Override
    public boolean update(Map<String, Object> StoreMap, Map<String, Object> IfMap) {
        if(StoreMap == null || IfMap == null) return false;
        String sql = "update tipoff set ";
        String sql1 = " where ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Update(StoreMap, sql,IfMap, sql1, list );
        return jdbcUtils.UpData(sql, list.toArray()) != 0;
    }

    @Override
    public boolean delete(Map<String, Object> StoreMap) {
        return false;
    }


}
