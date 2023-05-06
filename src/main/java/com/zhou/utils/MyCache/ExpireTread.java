package com.zhou.utils.MyCache;

import com.zhou.po.MyCache.MyCache;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExpireTread implements Runnable {
    private String cacheName;

    public ExpireTread(String cacheName) {
        this.cacheName = cacheName;
    }

    /**
     * 过期缓存检测线程
     */
    @Override
    public void run() {
        while (true){
            try {
                //每10秒检测一次
                TimeUnit.SECONDS.sleep(10);
                //缓存检测和清楚方法
                expireCache();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    /**
     * 缓存检测和清除方法
     */
    private void expireCache(){
        System.out.println("检测缓存" + cacheName + "是否过期缓存");
        Map<String, MyCache> cacheMap = CacheUtils.getCaCheMap(cacheName);
        for(String key : cacheMap.keySet()){
            MyCache cache = cacheMap.get(key);
            //当前时间 - 写入时间
            long timeoutTime = TimeUnit.NANOSECONDS.toSeconds(
                    System.nanoTime() - cache.getWriteTime()
                    );
            if(cache.getExpireTime() > timeoutTime){
                //没过期
                continue;
            }
            //清除过期缓存
            cacheMap.remove(key);
            String fileName = "D:/5/" + key + ".txt";
            File file = new File(fileName);
            file.delete();
        }
    }
}
