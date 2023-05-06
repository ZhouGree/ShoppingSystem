package com.zhou.service;

import com.zhou.po.MainUser;
import com.zhou.po.comment;
import com.zhou.po.storing;

import java.util.List;
import java.util.Map;

public interface CommodityService {
    /**
     *
     * @param DataMap
     * @return
     */
    List<storing>  queryDetail(Map<String, Object> DataMap);
    List<storing> queryAll(Map<String, Object> DataMap);
    int AddDetail(Map<String, Object> DetailMap);
    boolean update(Map<String, Object> IfMap,  Map<String, Object> CommodityMap);
    int AddComment(Map<String, Object> CommentMap);
    List <comment> QueryComment(Integer StoringId);

}
