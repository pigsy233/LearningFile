package spring5.testdemo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.bean.Orders;
import spring5.collectiontype.Book;
import spring5.collectiontype.Course;
import spring5.collectiontype.Student;
import spring5.factorybean.MyBean;

public class TestCollectionType {

    @Test
    public void testStudent(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        Student student = context.getBean("student", Student.class);
        student.test();
    }

    @Test
    public void testBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        Book book = context.getBean("book", Book.class);
        book.test();
    }

    @Test
    public void testMyBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");

        Course course = context.getBean("myBean", Course.class);
        System.out.println(course);
    }

    @Test
    public void testOrders(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");

        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("第四步");

        ((ClassPathXmlApplicationContext)context).close();

    }
}
