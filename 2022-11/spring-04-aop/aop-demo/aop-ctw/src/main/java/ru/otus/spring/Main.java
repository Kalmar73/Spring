package ru.otus.spring;

import org.springframework.context.annotation.*;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;

/*
Запуск примера:
    1. cd aop-demo/aop-ctw/
    2. mvn clean package
    3. java -jar target/aop-ctw-1.0-jar-with-dependencies.jar
 */

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        PersonService service = context.getBean(PersonService.class);

        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
