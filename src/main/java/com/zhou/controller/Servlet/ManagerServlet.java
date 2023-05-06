package com.zhou.controller.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.zhou.controller.Servlet.base.BaseServlet;
import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Relate.TipOffDaoImpl;
import com.zhou.po.Store;
import com.zhou.po.result.ResultInfo;
import com.zhou.po.storing;
import com.zhou.po.tipoff;
import com.zhou.service.Impl.CommodityServiceImpl;
import com.zhou.service.Impl.StoreServiceImpl;
import com.zhou.service.Impl.UserServiceImpl;
import com.zhou.utils.GetRequestJsonUtils;
import com.zhou.utils.pack.Pack;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Manager/*")
public class ManagerServlet extends BaseServlet implements Constant {
    Pack<Object> pack = new Pack<>();
    CommodityServiceImpl commodityService = new CommodityServiceImpl();
    TipOffDaoImpl tipOffDao = new TipOffDaoImpl();
    StoreServiceImpl storeService = new StoreServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    ResultInfo info = new ResultInfo();
    public void ShowCommodity(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("status", STORING_UNCHECK);
        List<storing> list = commodityService.queryAll(map);
        if(list==null){
            info.setFlag(false);
            info.setErrorMsg("暂无数据");
        }else {
            info.setFlag(true);
            info.setData(list);
        }
        writeValue(info, response);
    }
    public void CheckCommodity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        if(object == null){
            info.setFlag(false);
            info.setErrorMsg("操作失误，请重试！");
            writeValue(info, response);
            return;
        }
        Map<String, Object>map = new HashMap<>();
        map.put("id", object.get("id"));
        if (commodityService.SetStatus(map, Constant.STORING_IS_UP)) {
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("数据更新失败，请重新输入");
        }
        writeValue(info, response);
    }
    public void ShowStore(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("status", STORE_UNCHECK);
        List<Store> list = storeService.queryAll(map);
        if(list==null){
            info.setFlag(false);
            info.setErrorMsg("暂无数据");
        }else {
            info.setFlag(true);
            info.setData(list);
        }
        writeValue(info, response);
    }
    public void CheckStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        if(object == null){
            info.setFlag(false);
            info.setErrorMsg("操作失误，请重试！");
            writeValue(info, response);
            return;
        }
        Map<String, Object>map = new HashMap<>();
        map.put("id", object.get("id"));
        if (storeService.SetStatus(map, STORE_OPERATION)) {
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("请稍后重试！");
        }
        writeValue(info, response);
    }
    public void ShowComplaint(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("status", UNCHECK_COMPLAINT);
        List<tipoff> list = tipOffDao.getAll(map);
        if(list==null){
            info.setFlag(false);
            info.setErrorMsg("暂无数据");
        }else {
            info.setFlag(true);
            info.setData(list);
        }
        writeValue(info, response);
    }
    public void Complaint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        if(object == null){
            info.setFlag(false);
            info.setErrorMsg("操作失误，请重试！");
            writeValue(info, response);
            return;
        }
        Map<String, Object>map = new HashMap<>();
        map.put("id", object.get("id"));
       if (userService.SetStatus(map, CHECKED_COMPLAINT)){
           info.setFlag(true);
       }else {
           info.setFlag(false);
           info.setErrorMsg("请稍后重试！");
       }
       writeValue(info, response);
    }
}
