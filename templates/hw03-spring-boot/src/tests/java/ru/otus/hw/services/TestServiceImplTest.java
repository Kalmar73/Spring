package ru.otus.hw.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;
import ru.otus.hw.service.LocalizedIOService;
import ru.otus.hw.service.TestServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestServiceImplTest {
    @Mock
    private QuestionDao questionDao;
    @Mock
    private LocalizedIOService localizedIOService;
    @InjectMocks
    private TestServiceImpl testServiceImpl;

    @Test
    void testWithNoQuestions() {
        Student student = Mockito.mock(Student.class);
        Mockito.when(student.getFullName()).thenReturn("John Doe");
        Mockito.when(questionDao.findAll()).thenReturn(List.of());
        TestResult testResult = new TestResult(student);
        TestResult testResultExpected = testServiceImpl.executeTestFor(student);
        assertEquals(testResultExpected,testResult);
    }

    @Test
    void testWithSeveralQuestions() {
        Question question1 = new Question("q1", List.of(new Answer("a1", true),new Answer("a2", false)));
        Question question2 = new Question("q2", List.of(new Answer("a1", true),new Answer("a2", false)));
        Question question3 = new Question("q3", List.of(new Answer("a1", true),new Answer("a2", false)));
        Mockito.when(questionDao.findAll()).thenReturn(Arrays.asList(
                question1,question2,question3
        ));
        Mockito.when(localizedIOService.readString()).thenReturn("a1","a2","a1");
        Student student = Mockito.mock(Student.class);
        Mockito.when(student.getFullName()).thenReturn("John Doe");
        TestResult testResult = new TestResult(student);
        testResult.applyAnswer(question1,true);
        testResult.applyAnswer(question2,false);
        testResult.applyAnswer(question3,true);
        TestResult testResultExpected = testServiceImpl.executeTestFor(student);
        assertEquals(testResultExpected,testResult);
    }
}
