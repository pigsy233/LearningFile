package creature.testdemo;


import creature.Animal;
import creature.firm.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring_Test {

    @Test
    public void testPigsy(){
        ApplicationContext context =//BeanFactory context =也可 BeanFactory 在加载配置文件时不会创建对象，在使用对象的时候才会创建
                //而ApplicationContext 在加载配置文件是就会将其中所有对象创建
                new ClassPathXmlApplicationContext("bean.xml");
                //ClassPathXmlApplicationContext 对应的是src文件下的相对路径
                //FileSystemXmlApplicationContext 对应的是带盘符的绝对路径


        Animal animal = context.getBean("animal", Animal.class);

        System.out.println(animal);
        animal.testAni();
    }

    @Test
    public void testFirm(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");

        Employee employee = context.getBean("emp", Employee.class);
        employee.test();
    }
}
