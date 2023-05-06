import com.zhou.dao.Sql;
import com.zhou.utils.JDBCUtils;
import org.junit.Test;

import java.util.*;

public class TestSql {
    @Test
    public void test(){

        Sql sql = new Sql();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "ss");
        map.put("In", 1);
//        String sql1 = "select * from mainuser where status <> 0 and ";
////        System.out.println(m);
//        List<Object> list1 = new ArrayList<>();
//        sql1 =  sql.SS(list1, map, sql1);
//        System.out.println(sql1);
//        System.out.println(list1);
        int count = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            count++;
            if(count == 1){
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
            }
            else {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
            }
        }
//        String s = "insert into detailuser ( id, autonym , address, mainuser_id) values ( ?, ? ,?, ?)";
//        JDBCUtils jdbcUtils = JDBCUtils.getInstance();
//        System.out.println(jdbcUtils.UpData(s, new Object[]{2, "zhou" ,"guangzhou", 2}));

    }
}
