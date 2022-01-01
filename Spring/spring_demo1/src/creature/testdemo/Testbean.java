package creature.testdemo;

import creature.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testbean {

    @Test
    public void testbean(){

        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean1.xml");

        AnimalService animalService = context.getBean("animalservice",AnimalService.class);
        animalService.add();

    }
}
