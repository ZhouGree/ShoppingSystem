package com.zhou.service.Impl;

import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Entity.CommodityDaoImpl;
import com.zhou.dao.Impl.Relate.CommentDaoImpl;
import com.zhou.dao.Impl.Entity.StoreDaoImpl;
import com.zhou.dao.Impl.Entity.StoringDaoImpl;

import com.zhou.dao.Impl.Relate.TipOffDaoImpl;
import com.zhou.po.Commodity;
import com.zhou.po.comment;
import com.zhou.po.storing;
import com.zhou.service.CommodityService;

import java.util.*;

public class CommodityServiceImpl implements CommodityService, Constant {

    CommodityDaoImpl commodityDao = new CommodityDaoImpl();
    StoringDaoImpl storingDao = new StoringDaoImpl();
    StoreDaoImpl storeDao = new StoreDaoImpl();
    CommentDaoImpl commentDao = new CommentDaoImpl();
    TipOffDaoImpl tipOffDao = new TipOffDaoImpl();
    @Override
    public List<storing> queryDetail(Map<String, Object> DataMap) {
        if( DataMap == null ) return null;
        return storingDao.getName(DataMap, STORING_IS_UP);
    }

    @Override
    public List<storing> queryAll(Map<String, Object> DataMap) {
        if(DataMap == null) return null;
        return storingDao.getAll(DataMap);
    }

    @Override
    public int AddDetail(Map<String, Object> DetailMap) {
        if(DetailMap == null) return 0;
        Map<String, Object> map = new HashMap<>();
        if(!DetailMap.containsKey("commodity_id")){
            if(DetailMap.containsKey("commodityname")){
                int key = 0;
                map.put("commodityname", DetailMap.get("commodityname"));
                DetailMap.remove("commodityname");
                Commodity commodity =  commodityDao.getByName(map, STORING_IS_DOWN);
                if(commodity == null){
                    key = commodityDao.insert(map);
                    if(key == 0) return 0;
                }else key = commodity.getId();
                DetailMap.put("commodity_id", key);
            }else return 0;

        }
        return storingDao.insert(DetailMap);
    }

    @Override
    public boolean update(Map<String, Object> IfMap, Map<String, Object> CommodityMap) {
        if(CommodityMap.containsKey("commodityname")){
            if(IfMap.containsKey("commodity_id")){
                Map<String, Object> map = new HashMap<>();
                Map<String, Object> map1 = new HashMap<>();
                map.put("id", IfMap.get("commodity_id"));
                map1.put("commodityname", CommodityMap.get("commodityname"));
                IfMap.remove("commodity_id");
                CommodityMap.remove("commodityname");
                return commodityDao.update(map1, map);
            }
        }
        return commodityDao.update(CommodityMap, IfMap);
    }
    @Override
    public int AddComment(Map<String, Object> CommentMap) {
        if(CommentMap == null) return 0;
        return commentDao.insert(CommentMap);
    }

    @Override
    public List<comment> QueryComment(Integer StoringId) {
        if(StoringId == 0) return null;
        Map<String, Object> map = new HashMap<>();
        return null;
    }

    public boolean SetStatus(Map<String, Object> map, int Status) {
        if(map == null) return false;
        Map<String, Object> StatusMap = new HashMap<>();
        StatusMap.put("status", Status);
        return storingDao.update(StatusMap, map);
    }

    public int AddTipOff(Map<String, Object> map){
        if(map == null) return 0;
        return tipOffDao.insert(map);
    }
}
