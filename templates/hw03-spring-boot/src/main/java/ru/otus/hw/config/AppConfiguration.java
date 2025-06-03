package ru.otus.hw.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

import java.util.ArrayList;

@Data
@Configuration
public class AppConfiguration {
    @Bean
    public Answer answer() {
        return new Answer("", false);
    }

    @Bean
    public Question question() {
        return new Question("", new ArrayList<>());
    }

    @Bean
    public Student student() {
        return new Student("", "");
    }

    @Bean
    public TestResult testResult(Student student){
        return new TestResult(student);
    }

    @Bean
    public AppProperties appProperties() {
        return new AppProperties();
    }
}
