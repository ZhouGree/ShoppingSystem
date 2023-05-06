package com.zhou.service.Custom;

import java.util.List;
import java.util.Map;

public interface CustomService <T>{
    T query(Map<String, Object> DataMap);
    int AddId(Map<String, Object> DataMap);
    boolean Delete(Map<String, Object> DataMap);
    boolean bathAdd(Map<String, Object> DataMap, List<Map<String, Object>> Datalist) ;
    boolean bathDelete( List<Integer> Ids);
    boolean DeleteCommodity(Map<String, Object> DataMap);
}
