package com.zhou.comtroller.Servlet;

import com.alibaba.fastjson.JSON;
import com.zhou.comtroller.Servlet.base.BaseServlet;
import com.zhou.constant.Constant;
import com.zhou.po.DetailUser;
import com.zhou.po.MainUser;
import com.zhou.po.result.ResultInfo;
import com.zhou.service.Custom.Impl.OrdersServiceImpl;
import com.zhou.service.Impl.UserServiceImpl;
import com.zhou.service.UserService;
import com.zhou.utils.pack.Pack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet implements Constant {
    Pack<MainUser> pack = new Pack<>();
    private UserService userService = new UserServiceImpl();
    OrdersServiceImpl ordersService = new OrdersServiceImpl();
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
        return null;
    }
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        MainUser loginuser = this.Existence(request, response);
        System.out.println("login");
        ResultInfo info = new ResultInfo();
        Map<String, Object> map = pack.ChangeMap(request.getParameterMap());
        Map<String, String[]> stringObjectMap = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : stringObjectMap.entrySet()) {
//            String ColumnName = entry.getKey();
//            Object value = Arrays.toString(entry.getValue());
//            System.out.print(ColumnName + "=" + value + ",");
//        }
//
//        map.put("username", request.getParameter("username"));
//        map.put("password", request.getParameter("password"));
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            String ColumnName = entry.getKey();
//            Object value = entry.getValue();
//            System.out.print(ColumnName + "=" + value + ",");
//        }
        MainUser user = userService.login(map);
        response.setCharacterEncoding("GBK");
        if(user==null){
            System.out.println("登陆失败");
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        else if("NONEXISTENCE".equals(user.getUsername())){
            info.setFlag(false);
            info.setErrorMsg("用户不存在");
            //跳转到登录界面
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else {
            System.out.println("登录成功");
            info.setFlag(true);
            //登陆成功后的mainuser对象存到session中
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath());
        }
        writeValue(info, response);
        return null;
    }

    public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = pack.ChangeMap(request.getParameterMap());
        MainUser user = userService.register(map);
        ResultInfo info = new ResultInfo();
        if(user==null){
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        else if(user.getUsername() == null){
            user.setUsername((String) map.get("username"));
            user.setPassword((String) map.get("password"));
            user.setPhone((String) map.get("phone"));
            info.setFlag(true);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath());
        }else {
            info.setFlag(true);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath());
        }
        writeValue(info, response);
        return null;
    }

    public void ViewMainUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("show");
        HttpSession session = request.getSession();
        MainUser user = (MainUser) session.getAttribute("aa");
        response.setCharacterEncoding("GBK");
        try {
            response.getWriter().write(user.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FindMainUser(HttpServletRequest request, HttpServletResponse response){
        MainUser user = (MainUser) request.getSession().getAttribute("user");
        ResultInfo info = new ResultInfo();
        if(user == null){
            info.setFlag(false);
            info.setErrorMsg("请重试");
        }else {
            info.setFlag(true);
            info.setData(user);
        }
        String json = JSON.toJSONString(info);
        response.setContentType("text/json;charset=UTF-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FindDetailUser(HttpServletRequest request, HttpServletResponse response){
        MainUser user = (MainUser) request.getSession().getAttribute("user");
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

    public void ViewDetailUser(HttpServletRequest request, HttpServletResponse response){

    }

    public void EditMainUser(HttpServletRequest request, HttpServletResponse response){

    }

    public void EditDetailUser(HttpServletRequest request, HttpServletResponse response){

    }

    public void LogOff(HttpServletRequest request, HttpServletResponse response){
        MainUser user = (MainUser) request.getSession().getAttribute("user");
        ResultInfo info = new ResultInfo();
        info.setFlag(userService.logoff(user.getId()));
        writeValue(info, response);
    }

    public void SetStore(HttpServletRequest request, HttpServletResponse response){

    }

    public void Payment(HttpServletRequest request, HttpServletResponse response){
        int OrdersId = Integer.parseInt(request.getParameter("orders_id"));
        ordersService.update(OrdersId, Constant.ORDERS_NOT_SHIPPED);
    }
    public void InspectCommodity(HttpServletRequest request, HttpServletResponse response){
        //点击确定
        int OrdersId = Integer.parseInt(request.getParameter("orders_id"));
        ordersService.update(OrdersId, Constant.ORDERS_SHIPPED);

    }
}
