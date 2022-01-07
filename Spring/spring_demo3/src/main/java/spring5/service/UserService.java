package main.java.spring5.service;


import main.java.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//在注解中value可不写（默认值为首字母小写）
@Service(value = "userService")//形同<bean id="userService" class=".."/>
public class UserService {

//    //无需set方法，底层自带
//    @Autowired
//    @Qualifier(value = "userDaoImpl")
//    private UserDao userDao;

    private UserDao userDao;

    @Value(value = "pig")
    private String name;

    public void add(){
        System.out.println("UserService activated");
        System.out.println(name);
    }
}
