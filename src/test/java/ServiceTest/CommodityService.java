package ServiceTest;

import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Entity.StoringDaoImpl;
import com.zhou.po.storing;
import com.zhou.service.Impl.CommodityServiceImpl;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommodityService implements Constant {
    @Test
    public void queryAll(){
        Map<String, Object> map = new HashMap<>();
        map.put("status", STORING_IS_UP);
        map.put("store_id", 2);
        CommodityServiceImpl commodityService = new CommodityServiceImpl();
        List<storing> list = commodityService.queryAll(map);
        for (com.zhou.po.storing storing : list) {
            System.out.println(storing.toString());
        }
    }
    @Test
    public void queryAllDao(){
        Map<String, Object> map = new HashMap<>();
        map.put("status", STORING_IS_UP);
        map.put("store_id", 2);
        StoringDaoImpl storingDao = new StoringDaoImpl();
        List<storing> list = storingDao.getAll(map);
        for (com.zhou.po.storing storing : list) {
            System.out.println(storing.toString());
        }
    }

}
