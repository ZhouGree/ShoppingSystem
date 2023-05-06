package com.zhou.controller.Servlet.Cumstomer;
import com.alibaba.fastjson.JSONObject;
import com.zhou.controller.Servlet.base.BaseServlet;
import com.zhou.constant.Constant;
import com.zhou.po.MainUser;
import com.zhou.po.orders;
import com.zhou.po.result.ResultInfo;
import com.zhou.po.storing;
import com.zhou.service.Custom.Impl.OrdersServiceImpl;
import com.zhou.service.Impl.CommodityServiceImpl;
import com.zhou.utils.GetRequestJsonUtils;
import com.zhou.utils.pack.Pack;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/order/*")
public class OrdersServlet extends BaseServlet implements Constant {
    Pack<Object> pack = new Pack<>();
    OrdersServiceImpl ordersService = new OrdersServiceImpl();
    CommodityServiceImpl commodityService = new CommodityServiceImpl();
    ResultInfo info = new ResultInfo();
    public void ShowOrders (HttpServletRequest request, HttpServletResponse response){
        MainUser user = (MainUser) request.getSession().getAttribute("user");
        if(user==null){
            info.setFlag(false);
            info.setErrorMsg("请先登录");
            writeValue(info, response);
            return;
        }
        List<orders>list= ordersService.queryAll(user.getId());
        List<storing> storingList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (com.zhou.po.orders orders : list) {
            map.put("id", orders.getStoring_id());
            List<storing> List = commodityService.queryDetail(map);
            storingList.add(List.get(0));
        }
        info.setFlag(true);
        info.setData(storingList);
        writeValue(info, response);
    }
    public void AddOrders(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> stringMap = request.getParameterMap();
        Map<String, Object> map = pack.ChangeMap(stringMap);
        if(map == null ) return;

        ordersService.AddId(map);
    }
    public void Delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MainUser user = (MainUser) request.getSession().getAttribute("user");
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        ResultInfo info = new ResultInfo();
        if(object.isEmpty() || !object.containsKey("storing_id") || user == null) {
            info.setFlag(false);
            info.setErrorMsg("出错，请稍后重试");
            writeValue(info, response);
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("mainuser_id", user.getId());
        map.put("storing_id", object.get("storing_id"));
        ordersService.Delete(map);
        info.setFlag(true);
        writeValue(info, response);
    }

    public void Payment(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = pack.ChangeMap(request.getParameterMap());

    }

}
