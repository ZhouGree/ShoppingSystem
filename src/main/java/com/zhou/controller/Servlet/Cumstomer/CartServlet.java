package com.zhou.controller.Servlet.Cumstomer;

import com.alibaba.fastjson.JSONObject;
import com.zhou.controller.Servlet.base.BaseServlet;
import com.zhou.constant.Constant;
import com.zhou.po.MainUser;
import com.zhou.po.cart;
import com.zhou.po.result.ResultInfo;
import com.zhou.po.storing;
import com.zhou.service.Custom.Impl.CartServiceImpl;
import com.zhou.service.Impl.CommodityServiceImpl;
import com.zhou.utils.GetRequestJsonUtils;
import com.zhou.utils.pack.Pack;
import sun.applet.Main;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet implements Constant {
    Pack<Object> pack = new Pack<>();
    CartServiceImpl cartService = new CartServiceImpl();
    CommodityServiceImpl commodityService = new CommodityServiceImpl();
    public void ShowCart (HttpServletRequest request, HttpServletResponse response){
        MainUser user = (MainUser) request.getSession().getAttribute("user");
        ResultInfo info = new ResultInfo();
        if(user==null){
            info.setFlag(false);
            info.setErrorMsg("请先登录");
            writeValue(info, response);
            return;
        }
        List<cart>list= cartService.queryAll(user.getId());
        List<storing> storingList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (com.zhou.po.cart cart : list) {
            map.put("id", cart.getStoring_id());
            storingList = commodityService.queryDetail(map);
        }
        info.setFlag(true);
        info.setData(storingList);
        writeValue(info, response);
    }

    public void AddCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        Integer storing_id = Integer.valueOf(object.getString("storing_id"));
        if(object.containsKey("count")){
            map.put("count", object.getString("count"));
        }else {
            map.put("count",1);
        }
        map.put("storing_id", storing_id);
        map.put("mainuser_id", user.getId());
        int id = cartService.AddId(map);
        if (id == 0){
            info.setFlag(false);
            info.setErrorMsg("出错，请稍后重试");
        }else {
            info.setFlag(true);
        }
        writeValue(info, response);
    }

    public void RemoveCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        cartService.Delete(map);
        info.setFlag(true);
        writeValue(info, response);
    }


}
