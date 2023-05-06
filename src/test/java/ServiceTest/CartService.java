package ServiceTest;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.zhou.po.MainUser;
import com.zhou.po.cart;
import com.zhou.po.orders;
import com.zhou.po.storing;
import com.zhou.service.Custom.Impl.CartServiceImpl;
import com.zhou.service.Custom.Impl.OrdersServiceImpl;
import com.zhou.service.Impl.CommodityServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
    @Test
    public void TestAll(){
        CartServiceImpl cartService = new CartServiceImpl();
        List<cart> list = cartService.queryAll(1);
        for(cart value : list){
            System.out.println(value.toString());
        }
    }
    @Test
    public void TestAdd(){
        CartServiceImpl cartService = new CartServiceImpl();
        Map<String, Object> map = new HashMap<>();
        map.put("storing_id", 1);
        map.put("mainuser_id", 2);
        map.put("count", 4);
        System.out.println (cartService.AddId(map));
    }
    @Test
    public void TestOrderAll(){
        CommodityServiceImpl commodityService = new CommodityServiceImpl();
        OrdersServiceImpl ordersService = new OrdersServiceImpl();
        List<orders> list = ordersService.queryAll(1);
        Map<String, Object> map = new HashMap<>();
        List<storing> storingList = new ArrayList<>();
        for (com.zhou.po.orders orders : list) {
            System.out.println(orders.toString());
            map.put("id", orders.getStoring_id());
            storingList = commodityService.queryDetail(map);
        }
        for (com.zhou.po.storing storing : storingList) {
            System.out.println(storing);
        }
    }
    @Test
    public void TestCartAll(){
        CommodityServiceImpl commodityService = new CommodityServiceImpl();
        CartServiceImpl cartService = new CartServiceImpl();
        List<cart> list = cartService.queryAll(1);
        Map<String, Object> map = new HashMap<>();
        List<storing> storingList = new ArrayList<>();
        for (com.zhou.po.cart cart : list) {
            System.out.println(cart.toString());
            map.put("id", cart.getStoring_id());
            storingList = commodityService.queryDetail(map);
        }
        for (com.zhou.po.storing storing : storingList) {
            System.out.println(storing);
        }
    }

}
