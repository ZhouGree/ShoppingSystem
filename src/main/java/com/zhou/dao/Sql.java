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
        StringBuilder sqlBuilder = new StringBuilder(sql);
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sqlBuilder.append(entry.getKey()).append(" = ? ");
            } else {
                sqlBuilder.append(" and ").append(entry.getKey()).append(" = ? ");
            }
            list.add(entry.getValue());
        }
        sql = sqlBuilder.toString();
        return sql;
    }
    public String Delete(Map<String, Object> DataMap, String sql, List<Object> list ){
        if (DataMap == null || sql.isEmpty()) return null;
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
        StringBuilder sqlBuilder = new StringBuilder(sql);
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sqlBuilder.append(entry.getKey()).append(" = ? ");
            } else {
                sqlBuilder.append(" and ").append(entry.getKey()).append(" = ? ");
            }
            list.add(entry.getValue());
        }
        sql = sqlBuilder.toString();
        return sql;
    }

    public String Update(Map<String, Object> DataMap, String sql, Map<String, Object> IfMap, String sql1, List<Object> list){
        if (DataMap == null ) return null;
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
        StringBuilder sqlBuilder = new StringBuilder(sql);
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sqlBuilder.append(entry.getKey()).append(" = ? ");
            }
            else {
                sqlBuilder.append(", ").append(entry.getKey()).append(" = ? ");
            }
           list.add(entry.getValue());
        }
        sql = sqlBuilder.toString();
        count = 0;
        entrySet = IfMap.entrySet();
        StringBuilder sql1Builder = new StringBuilder(sql1);
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sql1Builder.append(entry.getKey()).append(" = ? ");
            } else {
                sql1Builder.append(" and ").append(entry.getKey()).append(" = ? ");
            }
            list.add(entry.getValue());
        }
        sql1 = sql1Builder.toString();
        sql = sql + sql1;
        return sql;
    }

    public String Insert(Map<String, Object> DataMap, String sql, String sql1, List<Object> list){
        if (DataMap == null) return null;
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
        StringBuilder sqlBuilder = new StringBuilder(sql);
        StringBuilder sql1Builder = new StringBuilder(sql1);
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sqlBuilder.append(entry.getKey());
            }
            else {
                sqlBuilder.append(", ").append(entry.getKey());
                sql1Builder.append(", ? ");
            }
            list.add(entry.getValue());
        }
        sql1 = sql1Builder.toString();
        sql = sqlBuilder.toString();
        sql = sql + " ) " + sql1 + " )";
        return sql;
    }
    public String SS(List<Object> list,Map<String, Object> DataMap, String sql ){
        int count = 0;
        Set<Map.Entry<String, Object>> entrySet = DataMap.entrySet();
        StringBuilder sqlBuilder = new StringBuilder(sql);
        for (Map.Entry<String, Object> entry : entrySet){
            count++;
            if(count == 1) {
                sqlBuilder.append(entry.getKey()).append(" = ? ");
            } else {
                sqlBuilder.append(" and ").append(entry.getKey()).append(" = ? ");
            }
            list.add(entry.getValue());
        }
        sql = sqlBuilder.toString();
        return sql;
    }
}
