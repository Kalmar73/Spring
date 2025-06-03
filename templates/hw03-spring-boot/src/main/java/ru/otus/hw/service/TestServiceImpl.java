package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printLineLocalized("TestService.answer.the.questions");
        ioService.printLine("");

        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question: questions) {
            ioService.printFormattedLine(question.text());
            ioService.printLine(question.answers().stream().map(Answer::text).collect(Collectors.joining("\n")));
            String answer = ioService.readString();
            for (Answer ans:
                    question.answers()) {
                if(ans.text().equals(answer)){
                    testResult.applyAnswer(question, ans.isCorrect());
                    break;
                }
            }
        }
        return testResult;
    }

}
