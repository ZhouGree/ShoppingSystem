package DaoTest;

import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Entity.MainUserDaoImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DaoTest implements Constant {
    @Test
    public void MainUserDao() {
        MainUserDaoImpl mainUserDao = new MainUserDaoImpl();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 4);
        Map<String, Object> DataMap = new HashMap<>();
        DataMap.put("id", 1);
        System.out.println( mainUserDao.delete(map));
    }
    @Test
    public void DetailUser(){

    }
    @Test
    public void CommodityDao(){

    }

    @Test
    public void StoreDao(){

    }

    @Test
    public void StoringDao(){

    }
    @Test
    public void CartDao(){

    }


}
