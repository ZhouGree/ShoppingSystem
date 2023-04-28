package com.zhou.dao.Impl.Entity;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.DetailUser;
import com.zhou.po.cart;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailUserDaoImpl implements GenericDao<DetailUser> {
    Sql SQL= new Sql();

    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private DetailUser pack(Map<String, Object> DetailUserMap) {
       DetailUser detailUser = new DetailUser();
       detailUser.setId((Integer) DetailUserMap.get("id"));
       detailUser.setMainUser_id((Integer) DetailUserMap.get("mainuser_id"));
       detailUser.setAutonym((String) DetailUserMap.get("autonym"));
       detailUser.setMail((String) DetailUserMap.get("mail"));
       detailUser.setAddress((String) DetailUserMap.get("address"));
       detailUser.setSignature((String) DetailUserMap.get("sidnature"));
       return detailUser;
   }

    @Override
    public DetailUser getByName(Map<String, Object> DetailUserMap, int Status) {
        String sql = "select * from detailuser where ";
        if(Status != 0) sql = "select * from detailuser where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(DetailUserMap, sql,list);
        list.remove(0);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }
    public DetailUser getByName(Integer UserId) {
        String sql = "select * from detailuser where id = ?";
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, new Object[]{UserId});
        return pack(mapList.get(0));
    }
    @Override
    public List<DetailUser> getAll(Map<String, Object> DetailUserMap) {
        String sql = "select * from detailuser where status <> 0, ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(DetailUserMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        List<DetailUser> DetailUserList = new ArrayList<>();
        for(int i = 0; i < mapList.size(); i++){
            DetailUserList.add(pack(mapList.get(i)));
        }
        return DetailUserList;
    }

    @Override
    public int insert(Map<String, Object> StoreMap) {
        return 0;
    }
    public int insertId(Integer UserId){
       String sql = "insert into detaiuser ( id ) values ( ? )";
       return jdbcUtils.UpData(sql, new Object[]{UserId});
    }
    public int insert(Integer UserId){
       String sql = "insert into detailuser ( id ) values ( ?)";
       return jdbcUtils.UpData(sql, new Object[]{UserId});
    }
    @Override
    public boolean update(Map<String, Object> DetailUserMap, Map<String, Object> IfMap) {
        return false;
    }
    public boolean update(Map<String, Object> DetailUserMap, Integer UserId){
       String sql = "update detailuser set ";
//        list = SQL.Update(DetailUserMap, sql);
//        sql
        return false;
    }
    @Override
    public boolean delete(Map<String, Object> StoreMap) {
        return false;
    }

    public boolean delete(Integer UserId){
       String sql = "update detailuser set status = 0 where id = ? and mainuser_id = ?";
       return jdbcUtils.UpData(sql, new Object[]{UserId, UserId}) != 0;
    }

}
