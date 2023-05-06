package com.zhou.dao.Impl.Relate;

import com.zhou.dao.GenericDao;
import com.zhou.dao.Sql;
import com.zhou.po.cart;
import com.zhou.po.comment;
import com.zhou.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentDaoImpl implements GenericDao<comment> {
    Sql SQL= new Sql();

    JDBCUtils jdbcUtils = JDBCUtils.getInstance();
   private comment pack(Map<String, Object> CommentMap) {
       comment comment = new comment();
       comment.setComment((String) CommentMap.get("comment"));
       comment.setStoring_id((Integer) CommentMap.get("storing_id"));
       comment.setId((Integer) CommentMap.get("id"));
       comment.setMainuser_id((Integer) CommentMap.get("mainuser_id"));
       comment.setStatus((Boolean) CommentMap.get("status"));
       return comment;
   }


    @Override
    public comment getByName(Map<String, Object> CommentMap, int Status) {
        String sql = "select * from comment where ";
        if(Status != 0) sql = "select * from comment where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(CommentMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        return pack(mapList.get(0));
    }

    @Override
    public List<comment> getAll(Map<String, Object> CommentMap) {
        String sql = "select * from comment where status <> 0 and ";
        List<Object> list = new ArrayList<>();
        sql = SQL.Select(CommentMap, sql, list);
        List<Map<String, Object>> mapList = jdbcUtils.queryALL(sql, list.toArray());
        List<comment> CommentList = new ArrayList<>();
        for(int i = 0; i < mapList.size(); i++){
            CommentList.add(pack(mapList.get(i)));
        }
        return CommentList;
    }

    @Override
    public int insert(Map<String, Object> CommentMap) {
       String sql = "insert into comment (mainuser_id, storing_id, description) values (?, ?, ?)";
       return jdbcUtils.UpData(sql, new Object[]{CommentMap.get("mainuser_id"), CommentMap.get("storing_id"), CommentMap.get("description")});
    }

    @Override
    public boolean update(Map<String, Object> StoreMap, Map<String, Object> IfMap) {
        return false;
    }

    @Override
    public boolean delete(Map<String, Object> CommentMap) {
       String sql = "update comment set status = 0 where id = ?";
        return jdbcUtils.UpData(sql, new Object[]{CommentMap.get("id")}) == 0;
    }

}
