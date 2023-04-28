package com.zhou.comtroller.Servlet.base;

import com.zhou.po.MainUser;
import com.zhou.po.Store;
import com.zhou.po.result.ResultInfo;
import com.zhou.service.Impl.StoreServiceImpl;
import com.zhou.service.StoreService;
import com.zhou.utils.pack.Pack;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/store/*")
public class StoreServlet extends BaseServlet {
    Pack<Store> pack = new Pack<>();
    StoreService storeService = new StoreServiceImpl();
    ResultInfo info = new ResultInfo();
    public void ShowStore(HttpServletRequest request, HttpServletResponse response){
       Map<String, Object> map = new HashMap<>();
        if (request.getParameter("id") != null){
           map.put("id", Integer.parseInt(request.getParameter("id")));
       }else if(request.getParameter("storename") != null){
            map.put("storename", request.getParameter("storename"));
        }else {
            info.setFlag(false);
            info.setErrorMsg("找不到信息");
        }
        Store store = storeService.showStore(map);
        if(store == null){
            info.setFlag(false);
            info.setErrorMsg("找不到信息");
        }else {
            info.setFlag(true);
            info.setData(store);
        }
        writeValue(info, response);
    }
    public void ShowCommodity(HttpServletRequest request, HttpServletResponse response){

    }
    public void ShutUp(HttpServletRequest request, HttpServletResponse response){

        MainUser user = (MainUser) request.getSession().getAttribute("user");
        Map<String, Object> map = pack.ChangeMap(request.getParameterMap());
        storeService.SetUp(map, user.getId());
    }
}
