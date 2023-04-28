package com.zhou.po.MyCache;

import com.zhou.po.MainUser;

import java.io.Serializable;
import java.util.Map;

/**
 * 缓存实体类
 */
public class MyCache implements Comparable<MyCache>, Serializable {
    @Override
    public int compareTo(MyCache o) {
        return hitCount.compareTo(o.hitCount);
    }
    //缓存键
    private Object key;
    //缓存值
    private Map<String, Object> value;
    //最后访问时间
    private long lastTime;
    //创建时间
    private long writeTime;
    //存活时间 以秒为单位
    private long expireTime;
    //命中次数
    private Integer hitCount;

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Map<String, Object> getValue() {
        return value;
    }

    public void setValue(Map<String, Object> value) {
        this.value = value;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public long getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(long writeTime) {
        this.writeTime = writeTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }
}
