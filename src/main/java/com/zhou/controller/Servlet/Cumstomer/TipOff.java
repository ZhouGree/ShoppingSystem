package com.zhou.controller.Servlet.Cumstomer;

import com.alibaba.fastjson.JSONObject;
import com.zhou.controller.Servlet.base.BaseServlet;
import com.zhou.po.MainUser;
import com.zhou.po.result.ResultInfo;
import com.zhou.service.Impl.CommodityServiceImpl;
import com.zhou.utils.GetRequestJsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/TipOff/*")
public class TipOff extends BaseServlet {
CommodityServiceImpl commodityService = new CommodityServiceImpl();
    public void tipOff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MainUser user = (MainUser) request.getSession().getAttribute("user");
        ResultInfo info = new ResultInfo();
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        if(object == null || user == null){
            info.setFlag(false);
            writeValue(info, response);
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("mainuser_id", user.getId());
        map.put("storing_id", object.get("storing_id"));
        map.put("description", object.get("description"));
        commodityService.AddTipOff(map);
        info.setFlag(true);
        writeValue(info, response);
    }
}
