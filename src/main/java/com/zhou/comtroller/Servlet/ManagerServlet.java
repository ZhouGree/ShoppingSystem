package com.zhou.comtroller.Servlet;

import com.zhou.comtroller.Servlet.base.BaseServlet;
import com.zhou.constant.Constant;
import com.zhou.dao.Impl.Entity.Relate.TipOffDaoImpl;
import com.zhou.po.result.ResultInfo;
import com.zhou.service.Impl.CommodityServiceImpl;
import com.zhou.service.Impl.StoreServiceImpl;
import com.zhou.service.Impl.UserServiceImpl;
import com.zhou.utils.pack.Pack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ManagerServlet extends BaseServlet implements Constant {
    Pack<Object> pack = new Pack<>();
    CommodityServiceImpl commodityService = new CommodityServiceImpl();
    StoreServiceImpl storeService = new StoreServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    ResultInfo info = new ResultInfo();
    public void CheckCommodity(HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> map = pack.ChangeMap(request.getParameterMap());
        if(map == null){
            info.setFlag(false);
            info.setErrorMsg("操作失误，请重试！");
            writeValue(info, response);
            return;
        }
        if (commodityService.SetStatus(map, Constant.STORING_IS_UP)) {
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("数据更新失败，请重新输入");
        }
        writeValue(info, response);
    }
    public void CheckStore(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = pack.ChangeMap(request.getParameterMap());
        if(map == null){
            info.setFlag(false);
            info.setErrorMsg("操作失误，请重试！");
            writeValue(info, response);
            return;
        }
        if (storeService.SetStatus(map, STORE_OPERATION)) {
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("请稍后重试！");
        }
        writeValue(info, response);
    }
    public void Complaint(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = pack.ChangeMap(request.getParameterMap());
        if(map == null){
            info.setFlag(false);
            info.setErrorMsg("操作失误，请重试！");
            writeValue(info, response);
            return;
        }
       if (userService.SetStatus(map, CHECKED_COMPLAINT)){
           info.setFlag(true);
       }else {
           info.setFlag(false);
           info.setErrorMsg("请稍后重试！");
       }
       writeValue(info, response);
    }
}
