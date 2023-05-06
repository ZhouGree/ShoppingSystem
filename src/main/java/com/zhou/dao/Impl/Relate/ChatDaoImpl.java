package com.zhou.dao.Impl.Relate;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.cart;
import com.zhou.po.chat;
import com.zhou.utils.JDBCUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatDaoImpl implements GenericDao<chat> {
    Sql SQL= new Sql();

    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private chat pack(Map<String, Object> ChatMap) {
       chat chat = new chat();
       chat.setId((Integer) ChatMap.get("id"));
       chat.setContent((String) ChatMap.get("content"));
       chat.setMainuser_id((Integer) ChatMap.get("mainuser_id"));
       chat.setStore_id((Integer) ChatMap.get("store_id"));
       chat.setTime((Timestamp) ChatMap.get("time"));
       chat.setStatus((Boolean) ChatMap.get("status"));
       return chat;
   }


    @Override
    public chat getByName(Map<String, Object> ChatMap, int Status) {
        String sql = "select * from chat where ";
        if(Status != 0) sql = "select * from chat where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(ChatMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<chat> getAll(Map<String, Object> ChatMap) {
        String sql = "select * from chat where status <> 0, ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(ChatMap, sql,list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        List<chat> ChatList = new ArrayList<>();
        for(int i = 0; i < mapList.size(); i++){
            ChatList.add(pack(mapList.get(i)));
        }
        return ChatList;
    }

    @Override
    public int insert(Map<String, Object> DataChatMap) {
        String sql = "insert into chat (mainuser_id, store_id, content, time) values (?, ?, ?, ?)";
        ArrayList<Object> objects = new ArrayList<>();
        List<Object[]> list = new ArrayList<>();
       return 0;
    }

    @Override
    public boolean update(Map<String, Object> StoreMap, Map<String, Object> IfMap) {
        return false;
    }

    @Override
    public boolean delete(Map<String, Object> StoreMap) {
        return false;
    }

}
