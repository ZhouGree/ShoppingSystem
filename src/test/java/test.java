import com.zhou.po.MainUser;
import com.zhou.utils.JDBCUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Map;

public class test {
    @Test
    public void test() throws SQLException {
//        JDBCUtils jdbcUtils = JDBCUtils.getInstance();
//        ConnectionPool connectionPool = new ConnectionPoolImpl();
//        Connection connection = connectionPool.getConnection();
//        String sql  = "insert into userlogin (id,username,password) values (8,'ee','123')";
//        Statement statement = connection.createStatement();
//        statement.execute(sql);
//        statement.close();
//        connection.close();
//        MyDataSource myDataSource = new MyDataSource();
//        Connection connection = myDataSource.getConnection();
//        String sql  = "insert into userlogin (id,username,password) values (8,'ee','123')";
//        Statement statement = connection.createStatement();
//        statement.execute(sql);
//        statement.close();
//        connection.close();
//          MainUser user = new MainUser();
//          user.setId(1);
//          user.setUsername("aa");
//        user.setPassword("123");
//        user.setPhone("17833884422");
//        user.setPicture("defaultpicture");
//        user.getStatus();
//        user.setRoleId(1);
//        user.setStoreId(1);
        //测试增删改功能
        //jdbcUtils.UpData("INSERT INTO mainuser (id,username,password,phone,picture,status,role,store_id) VALUES (?,?,?,?,?,?,?,?)",
                //new Object[]{user.getId(),user.getName(), user.getPassword(),user.getPhone(),user.getPicture(),user.getStatus(),user.getRoleId(),user.getStoreId()});

        //测试查询所有功能
//        List<Map<String, Object>> list = jdbcUtils.queryALL("SELECT * FROM mainuser");
//        for(int i = 0; i < list.size(); i++){
//            Map<String, Object> map = list.get(i);
//            for(Map.Entry<String, Object> entry : map.entrySet()){
//                String ColumnName = entry.getKey();
//                Object value = entry.getValue();
//                System.out.print(ColumnName + "=" + value + ",");
//            }
//            System.out.println();
//        }

//        String sql1 = "insert into follow (store_id, mainuser_id) values (?, ?)";
//        if(jdbcUtils.UpData(sql1, new Object[]{1, 1}) == 0){
//            System.out.println("插入失败！");
//        }else {
//            System.out.println("插入成功！");
//        }
//
//        String sql = "update store set status = 0 where id = ?";
//        if (jdbcUtils.UpData(sql, new Object[]{3}) == 0){
//            System.out.println("数据库不存在该信息！");
//        }else {
//            System.out.println("删除成功！");
//        }


    }

}
