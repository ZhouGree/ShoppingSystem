package ServiceTest;

import com.zhou.po.MainUser;
import com.zhou.service.Impl.UserServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    @Test
    public void LoginTest(){
        Map<String , Object> map = new HashMap<>();
        map.put("username", "22");
        map.put("password", "123");
        UserServiceImpl service = new UserServiceImpl();
        MainUser user = service.login(map);
        if(user == null) System.out.println("null");
        else  System.out.println(user.toString());

    }

    @Test
    public void RegisterTest(){
        Map<String , Object> map = new HashMap<>();
        map.put("username", "aa");
        map.put("password", "AAaa12345");
        map.put("phone", "12738985879");
        UserServiceImpl service = new UserServiceImpl();
        MainUser user = service.register(map);
        if(user == null) System.out.println("null");
        else  System.out.println(user.toString());

    }
}
