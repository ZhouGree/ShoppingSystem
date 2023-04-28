package com.zhou.utils.MyCache;

import com.zhou.po.MyCache.MyCache;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Cache全局类
 */
public class CacheUtils {
    //全局缓存对象
    public final static Map<String, MyCache>[] concurrentMaps = new ConcurrentHashMap[11];
   static {
       for(int i = 0; i < concurrentMaps.length; i++){
           concurrentMaps[i] = new ConcurrentHashMap<>();
       }
    }

    public static Map<String, MyCache> getCaCheMap(String name){
       int index = Math.abs(name.hashCode() % concurrentMaps.length);
       return concurrentMaps[index];
    }

    public static Object getCache(String cacheName, String key){
       Map<String,MyCache> cacheMap = getCaCheMap(cacheName);
       if(cacheMap.containsKey(key)){
           return cacheMap.get(key).getValue();
       }else {
           return null;
       }
    }

    public static void put(String cacheName, String key, Map<String, Object> value, long expire){
       Map<String, MyCache> cacheMap = getCaCheMap(cacheName);
       //非空判断
        if(StringUtils.isBlank(key))
            return;
        //当缓存存在时，更新缓存
        if(cacheMap.containsKey(key)){
            MyCache cache = cacheMap.get(key);
            cache.setHitCount(cache.getHitCount() + 1);
            cache.setWriteTime(System.currentTimeMillis());
            cache.setLastTime(System.currentTimeMillis());
            cache.setExpireTime(expire);
            cache.setValue(value);
            return;
        }
        //创建缓存
        MyCache cache = new MyCache();
        cache.setKey(key);
        cache.setValue(value);
        cache.setWriteTime(System.currentTimeMillis());
        cache.setLastTime(System.currentTimeMillis());
        cache.setHitCount(1);
        cache.setExpireTime(expire);
        cacheMap.put(key, cache);
    }

    public static void removeCache(String cacheNme, String key){
       Map<String, MyCache> cacheMap = getCaCheMap(cacheNme);
       if(cacheMap.containsKey(key)){
           cacheMap.remove(key);
       }
    }

    public static Map<String, Object> get(String key, String cacheName){
        Map<String, MyCache> cacheMap = getCaCheMap(cacheName);
        //非空判断
        if(StringUtils.isBlank(key))
            return null;
        if(cacheMap.isEmpty())
            return null;
        if(!cacheMap.containsKey(key))
            return null;

        MyCache cache = cacheMap.get(key);
        if(cache == null)
            return null;

        //惰性删除， 判断缓存是否过期
        long timeOutTime = TimeUnit.NANOSECONDS.toSeconds(
                System.nanoTime()-cache.getWriteTime()
        );
        System.out.println(timeOutTime);
        System.out.println(cache.getExpireTime());
        //缓存过期
        if(cache.getExpireTime() <= timeOutTime){
            //清除过期缓存
            cacheMap.remove(key);
            return null;
        }
        cache.setHitCount(cache.getHitCount() + 1);
        cache.setLastTime(System.currentTimeMillis());
        return cache.getValue();
    }
}
