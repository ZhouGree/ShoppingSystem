package com.zhou.utils;

import com.zhou.utils.pool2.MyDataSource;

import java.sql.*;
import java.util.*;

public class JDBCUtils extends ArrayList<Object> {

    MyDataSource myDataSource = new MyDataSource();
    private static Connection connection;
    ResultSet resultSet = null;
    static PreparedStatement preparedStatement = null;
    private static JDBCUtils jdbcUtils;
    private JDBCUtils (){

    }
    public static JDBCUtils getInstance(){
        if(jdbcUtils == null){
            jdbcUtils = new JDBCUtils();
        }
        return jdbcUtils;
    }

    /**
     * 获取PreparedStatement对象
     * @param sql sql语句
     * @param args 占位符对应数据的数组
     * @return 返回PreparedStatement
     *
     */
    public PreparedStatement getPreparedStatement(String sql,Object...args) throws SQLException {

        connection = myDataSource.getConnection();
        //获取prepareStatement对象
        preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //sql语句存在问号时，将对应的数据传进去
        if(args!=null && args.length>0){
            for(int i=0;i<args.length;i++){
                preparedStatement.setObject(i+1,args[i]);
            }
        }
        return preparedStatement;
    }

    /**
     * 查询所有数据
     * @param sql sql语句
     * @return 返回list集合，list集合的元素时map集合
     */
    public List<Map<String, Object>> queryALL(String sql, Object[] Data){

            try{
                preparedStatement = getPreparedStatement(sql, Data);
                resultSet = preparedStatement.executeQuery();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                //获取字段数
                int cct = resultSetMetaData.getColumnCount();
                //结果集
                List<Map<String, Object>> resultList = new ArrayList<>();
                while (resultSet.next()){
                    Map<String, Object> map = new HashMap<>();
                    for(int i = 1; i <= cct; i++){
//                        String ColumnName = resultSetMetaData.getColumnLabel(i);
//                        Object value = resultSet.getObject(ColumnName);
                        //字段名为键，字段名对应的值为值
                        map.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
                    }
                    resultList.add(map);
            }
                close(resultSet, preparedStatement, connection);
                return resultList;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            close(resultSet, preparedStatement, connection);
            return Collections.emptyList();
        }


    /**
     * 查询
     * @param sql sql语句
     * @param data 占位符对应的数据
     * @return 返回ArrayList集合
     */
    public ArrayList<Object> Query(String sql, Object[] data){
        int cct = 0;
        ResultSetMetaData resultSetMetaData = null;
        try{
            preparedStatement = getPreparedStatement(sql, data);
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();
            //获取字段数
            cct = resultSetMetaData.getColumnCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ArrayList<Object> list = new ArrayList<>();
        try{
            while (resultSet.next()) {

                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= cct; i++) {
                    map.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
                }
                //将一个键值对看作一个对象存入list中
                list.add(map);
            }

            //System.out.println(list);
            close(resultSet, preparedStatement, connection);
            return  list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(resultSet, preparedStatement, connection);
        return list;

    }

    /**
     * 增删改
     * @param sql sql语句
     * @param data 对应占位符的数据
     * @return 返回影响行数
     */
    public int UpData(String sql, Object[] data){
        int count = 0;
        try{
            preparedStatement = getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (data != null && data.length > 0) {
                for (int i = 0; i < data.length; i++) {
                    preparedStatement.setObject((i + 1), data[i]);
                }
            }
            preparedStatement.executeUpdate();
            //获取id值
           resultSet = preparedStatement.getGeneratedKeys();
            //
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
            close(resultSet, preparedStatement, connection);
            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(resultSet, preparedStatement, connection);
        return count;
    }

    /**
     * 批次处理增删改
     * @param sql sql语句
     * @param DataList 依次修改语句的占位符数据
     * @return 返回每次影响行数组成集合
     */
    public int[] BathUpdate(String sql,  List<Object[]> DataList) {
        int[] count = null;

        try{
            preparedStatement = getPreparedStatement(sql);
            if (DataList != null && !DataList.isEmpty()) {
                for (Object[] data : DataList) {
                    for (int i = 0; i < data.length; i++) {
                        preparedStatement.setObject((i + 1), data[i]);
                    }
                    preparedStatement.addBatch();
                }
                //执行批次处理操作
                count = preparedStatement.executeBatch();
            }
            close(resultSet, preparedStatement, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(resultSet, preparedStatement, connection);
        return count;
    }

    /**
     * 关闭资源
     * @param resultSet resultSet
     * @param preparedStatement preparedStatement
     * @param connection connection
     */
    private static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
        try {
            if(resultSet!=null)
                resultSet.close();
            if(preparedStatement!=null)
                preparedStatement.close();
            if(connection!=null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
