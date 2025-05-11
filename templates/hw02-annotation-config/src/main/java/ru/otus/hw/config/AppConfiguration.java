package ru.otus.hw.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;

import java.util.ArrayList;
import java.util.Locale;

@Data
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {
    @Bean
    public Answer Answer() {
        return new Answer("", false);
    }

    @Bean
    public Question Question() {
        return new Question("", new ArrayList<>());
    }

    @Bean
    public Student Student() {
        return new Student("", "");
    }

    @Bean
    public AppProperties AppProperties(@Value("${test.rightAnswersCountToPass}") Integer rightAnswersCountToPass,
                                       @Value("${test.fileName}") String testFileName) {
        AppProperties appProperties = new AppProperties();
        appProperties.setTestFileName(messageSource().getMessage(
                testFileName,
                new String[]{},
                Locale.getDefault()));
        appProperties.setRightAnswersCountToPass(rightAnswersCountToPass);
        return appProperties;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setBasename("/bundle");
        return ms;
    }
}
