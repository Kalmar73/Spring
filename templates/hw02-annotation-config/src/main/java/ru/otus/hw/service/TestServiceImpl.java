package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;
    private final MessageSource ms;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine(ms.getMessage("application.question",new String[] {}, Locale.getDefault()));
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            for (var question: questions) {
                ioService.printFormattedLine(question.text());
                ioService.printLine(question.answers().stream().map(Answer::text).collect(Collectors.joining("\n")));
                String answer = bufferedReader.readLine();
                for (Answer ans:
                        question.answers()) {
                    if(ans.text().equals(answer)){
                        testResult.applyAnswer(question, ans.isCorrect());
                        break;
                    }
                }
            }
        } catch (Exception e){
            ioService.printLine(e.getMessage());
        }
        return testResult;
    }
}
