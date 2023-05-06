package com.zhou.service.Impl;

import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Entity.DetailUserDaoImpl;
import com.zhou.dao.Impl.Entity.MainUserDaoImpl;
import com.zhou.dao.Impl.Relate.FollowDaoImpl;
import com.zhou.dao.Impl.Relate.TipOffDaoImpl;
import com.zhou.po.MainUser;
import com.zhou.service.UserService;
import com.zhou.utils.MyCache.CacheUtils;
import com.zhou.utils.MyCache.ExpireTread;

import java.util.*;

public class UserServiceImpl implements UserService, Constant {
    MainUserDaoImpl mainUserDao = new  MainUserDaoImpl();
    FollowDaoImpl followDao = new FollowDaoImpl();
    DetailUserDaoImpl detailUserDao = new DetailUserDaoImpl();
    /**
     *登录账户
     * @param MainUserMap 登录时填入的用户信息
     * @return 返回MainUer
     */
    @Override
    public MainUser login(Map<String, Object> MainUserMap) {
        if(MainUserMap == null || !MainUserMap.containsKey("username") || !MainUserMap.containsKey("password")) return null;
        //先判断本地缓存是否存在
//        //创建ExpiredThread线程对象
//        Thread expireTread = new Thread(new ExpireTread("UserCache"));
//        //启动线程
//        expireTread.start();
//        Object result = CacheUtils.get((String) MainUserMap.get("username"), "UserCache");
//        MainUser user = (MainUser) result;
//        if(user!=null) return user;
        //不存在 去数据库取数据，并存到本地缓存
        Map<String, Object> map = new HashMap<>();
        //判断用账号登录还是用手机号码登录
        if('1' == MainUserMap.get("username").toString().charAt(0)){
            map.put("phone", MainUserMap.get("username"));
        }else map.put("username", MainUserMap.get("username"));
        map.put("password", MainUserMap.get("password"));
        //只有账号和密码都对才能查询到该信息

//        CacheUtils.put("CartCache", user.getUsername(), user, 60*60*24*7);
        return mainUserDao.getByName(map, EXISTENCE);
    }

    /**
     *注册账户
     * @param MainUserMap 要注册的账户的信息
     * @return 返回MainUser
     */
    @Override
    public MainUser register(Map<String, Object> MainUserMap) {
        if(MainUserMap == null || !MainUserMap.containsKey("password") || !MainUserMap.containsKey("username") || !MainUserMap.containsKey("phone")) return null;
        Map<String, Object> map = new HashMap<>();
        map.put("username", MainUserMap.get("username"));
        MainUser user = mainUserDao.getByName(map, NONEXISTENCE);
        if(user == null){
            int count = mainUserDao.insert(MainUserMap);
            if (count != 0){
                user = new MainUser();
                user.setId(count);
            }
            return user;
        }else {
            if (!user.isStatus()) {
                MainUserMap.remove("username");
                MainUserMap.put("status", EXISTENCE);
                Map<String, Object> ifMap = new HashMap<>();
                ifMap.put("id", user.getId());
                if ( !mainUserDao.update(MainUserMap, ifMap) ) return null;
                return user;
            }
        }
        return null;
    }

    /**
     *注销账户（账户状态设为0）
     * @param UserId 用户id。
     * @return 返回boolean值
     */
    @Override
    public boolean logoff(Integer UserId) {
        if(UserId == 0) return false;
        return mainUserDao.delete(UserId);
//        return false;
    }

    /**
     *关注
     * @param UserId 用户id
     * @param StoreId 店铺id
     * @param WhetherFollow 如果关注，fo为1，取消关注fo为0
     * @return 返回boolean值
     */
    @Override
    public boolean follow(Integer UserId, Integer StoreId, int WhetherFollow) {
        if(UserId == 0 || StoreId == 0) return false;
        if (followDao.Follow(UserId, StoreId, WhetherFollow)) return true;
        Map<String, Object> map = new HashMap<>();
        map.put("mainuser_id", UserId);
        map.put("store_id", StoreId);
        return followDao.insert(map) != 0;
    }

    /**
     *聊天功能
     * @param UserId 用户id
     * @param StoreId 店铺id
     * @param DataChat 聊天信息
     * @return 返回boolean
     */
    @Override
    public boolean Chat(Integer UserId, Integer StoreId, List<Map<String, Object>> DataChat) {

        ArrayList<Object> objects = new ArrayList<>();
        List<Object[]> list = new ArrayList<>();
        for (Map<String, Object> map : DataChat) {
            objects.add(map.get("mainuser_id"));
            objects.add(map.get("store_id"));
            objects.add(map.get("content"));
            objects.add(map.get("time"));
            list.add(objects.toArray());
        }
      return false;
    }

    /**
     * 更改信息
     * @param DataMap 存放要更改的信息
     * @return 返回true/false
     */
    @Override
    public boolean update(Map<String, Object> DataMap) {
        if(DataMap == null ) return false;
        Map<String, Object> map = new HashMap<>();
        if(!DataMap.containsKey("id")){
            if(!DataMap.containsKey("username")) return false;
            map.put("username", DataMap.get("username"));
        }else map.put("id", DataMap.get("id"));
        return mainUserDao.update(DataMap, map);
    }

    public boolean UpdateDetail(Map<String, Object> DataMap, Integer Id){
        if(DataMap == null) return false;
        Map<String, Object> map = new HashMap<>();
        map.put("id", Id);
        return mainUserDao.update(DataMap, map);
    }
    /**
     * 展示信息
     * @param Id 用户id
     * @return 返回用户信息
     */
    @Override
    public Object ShowDetail( Integer  Id) {
        if(Id == 0) return null;
        return detailUserDao.getByName(Id);
    }

    public boolean SetStatus(Map<String, Object> map, int Status) {
        if(map == null) return false;
        TipOffDaoImpl tipOffDao = new TipOffDaoImpl();
        Map<String, Object> StatusMap = new HashMap<>();
        StatusMap.put("status", Status);
        return tipOffDao.update(StatusMap, map);
    }
}
