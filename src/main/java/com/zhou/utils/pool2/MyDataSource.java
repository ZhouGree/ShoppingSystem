package com.zhou.utils.pool2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public class MyDataSource<url> extends MyDataSourceAdapter{
    private static String driverClassName = "";
    private static String url = "";
    private static String username = "";
    private static String password = "";
    private static int size = 0;
    //private final static int size = 5;
    static {
        System.out.println(">>>>开始加载配置...");
        try {
            //new对象
            Properties pro = new Properties();

            /**
             * 读取配置
             * properties文件与src同级
             */
            pro.load(new FileReader(new File("conn.properties")));
            url = pro.getProperty("url");
            username = pro.getProperty("username");
            password = pro.getProperty("password");
            driverClassName = pro.getProperty("driverClassName");
            size = Integer.parseInt(pro.getProperty("InitConnections"));

            System.out.println(">>>>配置加载完成");

            //加载驱动
            Class.forName(driverClassName);

        } catch (IOException | ClassNotFoundException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }

    }


    private static final LinkedList<Connection> pools = new LinkedList<>();

    static {
        for(int i=0; i < size; i++){
            try {
                pools.addFirst(DriverManager.getConnection(url,username,password));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    @Override
    public Connection getConnection() throws SQLException {
        final Connection conn = pools.removeLast();
        System.out.println("获取完数据库连接之后的连接数："+pools.size());
        Connection proxyConn = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(),
                new Class[]{Connection.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String name = method.getName();
                        if(name.equals("close")){
                            pools.addFirst(conn);
                            System.out.println("返回后数据库连接之后的连接数："+pools.size());
                        }else {
                            return method.invoke(conn,args);
                        }
                        return null;
                    }
                });
        return proxyConn;
    }


    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }
}
