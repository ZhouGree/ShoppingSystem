package com.zhou.controller.Servlet;
import com.alibaba.fastjson.JSONObject;
import com.zhou.controller.Servlet.base.BaseServlet;
import com.zhou.constant.Constant;
import com.zhou.po.DetailUser;
import com.zhou.po.MainUser;
import com.zhou.po.cart;
import com.zhou.po.result.ResultInfo;
import com.zhou.service.Custom.Impl.CartServiceImpl;
import com.zhou.service.Custom.Impl.OrdersServiceImpl;
import com.zhou.service.Impl.UserServiceImpl;
import com.zhou.utils.GetRequestJsonUtils;
import com.zhou.utils.pack.Pack;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet implements Constant {
    Pack<MainUser> pack = new Pack<>();
    UserServiceImpl userService = new UserServiceImpl();
    OrdersServiceImpl ordersService = new OrdersServiceImpl();

    /**
     * 退出登录
     * @param request 请求
     * @param response 响应
     * @throws IOException 抛出异常
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
//        response.sendRedirect(request.getContextPath());
    }

    /**
     *登录
     * @param request 请求
     * @param response 响应
     * @throws IOException 抛出异常
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object>map = new HashMap<>();
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        map.put("username", object.getString("username"));
        map.put("password", object.getString("password"));
        MainUser user = userService.login(map);
        ResultInfo info = new ResultInfo();
        if(user==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        } else {
            info.setFlag(true);
            info.setData(user);
            //登陆成功后的mainuser对象存到session中
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
//            response.sendRedirect(request.getContextPath());
            System.out.println(session);
            System.out.println(session.getAttribute("user").toString());
        }
        writeValue(info, response);

    }

    /**
     * 注册
     * @param request 请求
     * @param response 响应
     * @throws IOException 抛出异常
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object>map = new HashMap<>();
        JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
        map.put("username", object.getString("username"));
        map.put("password", object.getString("password"));
        map.put("phone", object.getString("phone"));
        MainUser user = userService.register(map);
        ResultInfo info = new ResultInfo();
        if(user == null){
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        else if(user.getUsername() == null){
            user.setUsername((String) map.get("username"));
            user.setPassword((String) map.get("password"));
            user.setPhone((String) map.get("phone"));
            info.setFlag(true);
            info.setData(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
//            response.sendRedirect(request.getContextPath());
        }else {
            user.setPassword((String) map.get("password"));
            user.setPhone((String) map.get("phone"));
            user.setStatus(true);
            info.setFlag(true);
            info.setData(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
//            response.sendRedirect(request.getContextPath());
        }
        writeValue(info, response);
    }

    /**
     * 展示主要信息
     * @param request 请求
     * @param response 响应
     */
    public void FindMainUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        MainUser user = (MainUser)session.getAttribute("user");
        ResultInfo info = new ResultInfo();
        if(user == null){
            info.setFlag(false);
            info.setErrorMsg("暂无信息");
        }else {
            info.setFlag(true);
            info.setData(user);
        }
       writeValue(info, response);
    }

    /**
     * 展示详细信息
     * @param request 请求
     * @param response 响应
     */
    public void FindDetailUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        MainUser user = (MainUser) session.getAttribute("user");
        DetailUser detailUser = (DetailUser) userService.ShowDetail(user.getId());
        ResultInfo info = new ResultInfo();
        if(detailUser == null){
            info.setFlag(false);
            info.setErrorMsg("请重试");
        }else {
            info.setFlag(true);
            info.setData(detailUser);
        }
        writeValue(info, response);
    }

    /**
     * 修改主要信息
     * @param request 请求
     * @param response 响应
     */
    public void EditMainUser(HttpServletRequest request, HttpServletResponse response){

    }

    /**
     * 注销账户
     * @param request 请求
     * @param response 响应
     */
    public void LogOff(HttpServletRequest request, HttpServletResponse response){
        MainUser user = (MainUser) request.getSession().getAttribute("user");
        ResultInfo info = new ResultInfo();
        info.setFlag(userService.logoff(user.getId()));
        request.getSession().invalidate();
        writeValue(info, response);
    }

    /**
     * 开店铺
     * @param request 请求
     * @param response 响应
     */
    public void SetStore(HttpServletRequest request, HttpServletResponse response){

    }

    /**
     * 支付
     * @param request 请求
     * @param response 响应
     */
    public void Payment(HttpServletRequest request, HttpServletResponse response){
        int OrdersId = Integer.parseInt(request.getParameter("orders_id"));
        ordersService.update(OrdersId, Constant.ORDERS_NOT_SHIPPED);
    }

    /**
     * 确认收货
     * @param request 请求
     * @param response 响应
     */
    public void InspectCommodity(HttpServletRequest request, HttpServletResponse response){
        //点击确定
        int OrdersId = Integer.parseInt(request.getParameter("orders_id"));
        ordersService.update(OrdersId, Constant.ORDERS_SHIPPED);

    }

    /**
     * 展示购物车
     * @param request 请求
     * @param response 响应
     */
    public void ShowCart(HttpServletRequest request, HttpServletResponse response){
        MainUser user = (MainUser) request.getSession().getAttribute("user");
        ResultInfo info = new ResultInfo();
        if(user == null){
            info.setFlag(false);
            info.setErrorMsg("请求失败，请重新操作");
            writeValue(info, response);
            return;
        }
        CartServiceImpl cartService = new CartServiceImpl();
        List<cart> cartList = cartService.queryAll(user.getId());
        if(cartList == null){
            info.setFlag(false);
            info.setErrorMsg("暂无数据");
        }else {
            info.setFlag(true);
            info.setData(cartList);
        }
        writeValue(info, response);
    }


}
