
import com.zhou.utils.MyCache.CacheUtils;
import com.zhou.utils.MyCache.ExpireTread;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MyCacheTest {
    @Test
    public void MyTest(){
        //创建ExpiredThread线程对象
        Thread expireTread = new Thread(new ExpireTread("UserCache"));
        //启动线程
        expireTread.start();
//
//        Map<String, Object> map = new HashMap<>();
//        String key = "zhangsan";
//        map.put("zhangsan", "123");
//        map.put("lisi", "123");
//        map.put("li", "123");
//        CacheUtils.put("CartCache", key, map, 60*60*24*7);
        Object result = CacheUtils.get("zhangsan", "CartCache");
//        if(result == null) return;

//        CacheUtils.removeCache("UserCache", "zhangsan");

    }

    @Test
    public void myCacheTest(){
        //创建ExpiredThread线程对象
        Thread expireTread = new Thread(new ExpireTread("UserCache"));
        //启动线程
        expireTread.start();
        Map<String, Object> map = new HashMap<>();
        String key = "zhang";
        map.put("zhang", "123");
        map.put("li", "123");
        map.put("shi", "123");
        CacheUtils.put("CartCache", key, map, 60*60*24*7);

//        Serialization.Serialization(CacheUtils.getCaCheMap("CartCache"));

        Object result = CacheUtils.get("zhang", "CartCache");
//        if(result == null) return;
//        for(Map.Entry<String, Object> entry : result.entrySet()){
//            String username = entry.getKey();
//            Object password = entry.getValue();
//            System.out.print(username + "=" + password + ",");
//        }
    }
    @Test
    public void DeleteTest(){

    }
}
