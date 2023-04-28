package com.zhou.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Sql {

    public String Select(Map<String, Object> DataMap, String sql, List<Object> list){
        if (DataMap == null || sql.isEmpty()) return null;
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sql = sql + entry.getKey() + " = ? ";
            } else {
                sql = sql + " and " + entry.getKey() + " = ? ";
            }
            list.add(entry.getValue());
        }
        return sql;
    }
    public String Delete(Map<String, Object> DataMap, String sql, List<Object> list ){
        if (DataMap == null || sql.isEmpty()) return null;
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sql = sql + entry.getKey() + " = ? ";
            } else {
                sql = sql + " and " + entry.getKey() + " = ? ";
            }
            list.add(entry.getValue());
        }
        return sql;
    }

    public String Update(Map<String, Object> DataMap, String sql, Map<String, Object> IfMap, String sql1, List<Object> list){
        if (DataMap == null ) return null;
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sql = sql + entry.getKey() + " = ? ";
            }
            else {
                sql = sql + ", " + entry.getKey() + " = ? ";
            }
           list.add(entry.getValue());
        }
        entrySet = IfMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sql1 = sql1 + entry.getKey() + " = ? ";
            } else {
                sql1 = sql1 + " and " + entry.getKey() + " = ? ";
            }
            list.add(entry.getValue());
        }
        sql = sql + sql1;
        return sql;
    }

    public String Insert(Map<String, Object> DataMap, String sql, String sql1, List<Object> list){
        if (DataMap == null) return null;
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sql = sql + entry.getKey();
            }
            else {
                sql = sql + ", " + entry.getKey();
                sql1 = sql1 + ", ? ";
            }
            list.add(entry.getValue());
        }
        sql = sql + " ) " + sql1 + " )";
        return sql;
    }
    public String SS(List<Object> list,Map<String, Object> DataMap, String sql ){
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sql = sql + entry.getKey() + " = ? ";
            } else {
                sql = sql + " and " + entry.getKey() + " = ? ";
            }
            list.add(entry.getValue());
        }
        return sql;
    }
}
