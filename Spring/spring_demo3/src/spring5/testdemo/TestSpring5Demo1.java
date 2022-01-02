package spring5.testdemo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.config.SpringConfig;
import spring5.service.UserService;

public class TestSpring5Demo1 {

    @Test
    public void testUserService(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        UserService  userService = context.getBean("userService", UserService.class);
        userService.add();
    }

    @Test
    public void testConfig(){
        //加载配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }
}
