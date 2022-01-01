package spring5.factorybean;

import org.springframework.beans.factory.FactoryBean;
import spring5.collectiontype.Course;

public class MyBean implements FactoryBean<Course> {
    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setName("fuck");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
