package com.zhou.dao;

import javax.sql.rowset.serial.SerialStruct;
import java.util.List;
import java.util.Map;

public interface GenericDao<T> {


    T getByName(Map<String, Object> StoreMap, int Status);


    List<T> getAll(Map<String, Object> StoreMap);


    int insert(Map<String, Object> StoreMap);


     boolean update(Map<String, Object> StoreMap,  Map<String, Object> IfMap);


    boolean delete(Map<String, Object> StoreMap);


}
