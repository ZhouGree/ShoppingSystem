import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Pack {
    @Test
    public void test (){
        com.zhou.utils.pack.Pack pack = new com.zhou.utils.pack.Pack();
        Map<String, String[]> stringArrayMap = new HashMap<>();
// 假设这里有一个String数组Map
        stringArrayMap.put("key1", new String[]{ "value2"});
        stringArrayMap.put("key2", new String[]{"value3", "value4", "value5"});
        Map<String, Object> map = pack.ChangeMap(stringArrayMap);
        for(String entry : map.keySet()){
            System.out.println(entry + " : " + map.get(entry));
        }
    }
}
