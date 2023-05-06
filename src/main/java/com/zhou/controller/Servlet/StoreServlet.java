package com.zhou.controller.Servlet;
import com.alibaba.fastjson.JSONObject;
import com.zhou.controller.Servlet.base.BaseServlet;
import com.zhou.constant.Constant;
import com.zhou.po.MainUser;
import com.zhou.po.Store;
import com.zhou.po.result.ResultInfo;
import com.zhou.po.storing;
import com.zhou.service.CommodityService;
import com.zhou.service.Impl.CommodityServiceImpl;
import com.zhou.service.Impl.StoreServiceImpl;
import com.zhou.utils.GetRequestJsonUtils;
import com.zhou.utils.pack.Pack;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/store/*")
public class StoreServlet extends BaseServlet implements Constant {
    Pack<Object> pack = new Pack<>();
    CommodityService  commodityService = new CommodityServiceImpl();
    StoreServiceImpl storeService = new StoreServiceImpl();
    public void showStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String storename = (String) request.getSession().getAttribute("store");
        Map<String, Object> map = new HashMap<>();
        map.put("storename", storename);
        ResultInfo info = new ResultInfo();
        Store store = storeService.showStore(map);
        if(store == null){
            info.setFlag(false);
            writeValue(info, response);
            return;
        }
        System.out.println(store.toString());
        info.setFlag(true);
        info.setData(store);
        request.getSession().removeAttribute("store");
        writeValue(info, response);
    }
    public void RememberStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        ResultInfo info = new ResultInfo();
        if(object == null){
            info.setFlag(false);
            writeValue(info, response);
            return;
        }
        info.setFlag(true);
        HttpSession session = request.getSession();
        session.setAttribute("store", object.get("storename"));
        writeValue(info, response);
    }
    public void showCommodity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        Map<String, Object> map = new HashMap<>();
        map.put("commodityname", object.get("commodityname"));
        ResultInfo info = new ResultInfo();
        List<storing> storingList = commodityService.queryDetail(map);
       if(storingList == null) {
           info.setFlag(false);
           info.setErrorMsg("暂无商品");
       }else {
           info.setFlag(true);
           info.setData(storingList);
       }
        writeValue(info, response);
    }
    public void QueryAllCommodity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultInfo info = new ResultInfo();
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        Map<String, Object> map = new HashMap<>();
        map.put("status", STORING_IS_UP);
        if(object == null){
            MainUser user = (MainUser) request.getSession().getAttribute("user");
            if(user.getStore_id()==0){
                info.setFlag(false);
                writeValue(info, response);
                return;
            }
            map.put("store_id", user.getStore_id());
        }else {
            map.put("store_id", object.get("id"));
        }
        map.put("status", STORING_IS_UP);
        System.out.println(map.get("store_id"));
        List <storing> storingList = commodityService.queryAll(map);
        if(storingList == null){
            info.setFlag(false);
            info.setErrorMsg("暂无商品");
        }else {
            info.setFlag(true);
            info.setData(storingList);
        }
        writeValue(info, response);
    }
    public void AddCommodity(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = pack.ChangeMap(request.getParameterMap());
        if(map==null||(int)map.get("store_id") == 0) return;
        ResultInfo info = new ResultInfo();
        Integer StoreId = (Integer) map.get("store_id");
        if (storeService.AddCommodity(map, StoreId)) {
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("操作失败！");
        }
    }
    public void DeleteCommodity(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = pack.ChangeMap(request.getParameterMap());
        if(map == null) return;
        ResultInfo info = new ResultInfo();
        if (storeService.DeleteCommodity((Integer) map.get("store_id"), (Integer) map.get("commodity_id"))){
            info.setFlag(true);
        }else {
            info.setErrorMsg("出错，请重试");
        }
        writeValue(info, response);
    }
    public void Chat(HttpServletRequest request, HttpServletResponse response){

    }
    public void Shipments(HttpServletRequest request, HttpServletResponse response){
        //在展示已付款商品点击确定,
        Map<String, String[]> map = request.getParameterMap();


    }



}
