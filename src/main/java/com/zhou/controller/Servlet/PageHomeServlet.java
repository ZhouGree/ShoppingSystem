package com.zhou.controller.Servlet;

import com.zhou.controller.Servlet.base.BaseServlet;
import com.zhou.constant.Constant;
import com.zhou.po.Store;
import com.zhou.po.result.ResultInfo;
import com.zhou.po.storing;
import com.zhou.service.CommodityService;
import com.zhou.service.Impl.CommodityServiceImpl;
import com.zhou.service.Impl.StoreServiceImpl;
import com.zhou.service.StoreService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/pageHome/*")
public class PageHomeServlet extends BaseServlet implements Constant {
    StoreService storeService = new StoreServiceImpl();
    CommodityService commodityService = new CommodityServiceImpl();

    public void showCommodity(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("status", STORING_IS_UP);
        List<storing> list =  commodityService.queryAll(map);
        ResultInfo info = new ResultInfo();
        if(list == null){
            info.setFlag(false);
            info.setErrorMsg("暂无数据");
        }else {
            info.setFlag(true);
            info.setData(list);
        }
        writeValue(info, response);
    }

    public void showStore(HttpServletRequest request, HttpServletResponse response){
        ResultInfo info = new ResultInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("status", STORE_OPERATION);
        List<Store> list =  storeService.queryAll(map);
        if(list == null){
            info.setFlag(false);
            info.setErrorMsg("暂无数据");
        }else {
            info.setFlag(true);
            info.setData(list);
        }
        writeValue(info, response);
    }

    public void RedirectLogin(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RedirectRegister(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("/register.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
