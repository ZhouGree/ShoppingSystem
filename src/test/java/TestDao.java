
import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Entity.MainUserDaoImpl;
import com.zhou.po.MainUser;
import org.junit.Test;

import java.util.*;

public class TestDao implements Constant {
    @Test
    public void TestDao () {
        MainUser mainUser = new MainUser();
//        MainUser mainUser1 = new MainUser();
//        mainUser.setName("aa");
//        UserDaoImpl mainUserUserDao = new UserDaoImpl();

//        mainUser = mainUserUserDao.getByName(mainUser);
//        System.out.println(mainUser.getId());
//        System.out.println(mainUser.getName());
//        System.out.println(mainUser.getPassword());
//        System.out.println(mainUser.getPhone());
//        System.out.println(mainUser.getPicture());
//        System.out.println(mainUser.getRoleId());
//        System.out.println(mainUser.getStatus());
//        System.out.println(mainUser.getStoreId());
//        List<Map<String, Object>> list = new ArrayList<>();
//        List<Map<String, Object>> list = genericDao.getAll("mainuser");
//        Map<String, Object> map = null;
//        for(int i = 0; i < list.size(); i++){
//            map = list.get(i);
//            for(Map.Entry<String, Object> entry : map.entrySet()){
//                String ColumnName = entry.getKey();
//                Object value = entry.getValue();
//                System.out.print(ColumnName + "=" + value + ",");
//            }
//
//        }
//        genericDao.delete("mainuser", map);
        MainUserDaoImpl mainUserDao = new MainUserDaoImpl();
        Map<String, Object> map = new HashMap<>();

        Map<String, Object> DataMap = new HashMap<>();


//
//        mainUserUserDao.insert(mainUser);
        DataMap.put("username", "FiFi");
        DataMap.put("password", "123");
        DataMap.put("phone", "145678345689");
        int count = mainUserDao.insert(DataMap);
        System.out.println(count);
        map.put("username", "FiFi");
        mainUser =  mainUserDao.getByName(map, NONEXISTENCE);
        System.out.println(mainUser.toString());

//        Map<String, Object> map = genericDao.getByName("mainuser", "aa", "username");
//        for(Map.Entry<String, Object> entry : map.entrySet()){
//            String ColumnName = entry.getKey();
//                Object value = entry.getValue();
//                System.out.print(ColumnName + "=" + value + ",");
//        }
//        List<Map<String, Object>> list = new ArrayList<>();

//        List<Map<String, Object>> list = genericDao.getAll("mainuser");
//        for(int i = 0; i < list.size(); i++) {
//            map = list.get(i);
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                String ColumnName = entry.getKey();
//                Object value = entry.getValue();
//                System.out.print(ColumnName + "=" + value + ",");
//            }
//        }

//        genericDao.delete("mainuser", id);
       // genericDao.BathDelete("mainuser",ids);
//        genericDao.update("mainuser", map);
    }
}
