package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw.config.TestConfig;
import ru.otus.hw.domain.TestResult;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class ResultServiceImpl implements ResultService {

    private final TestConfig testConfig;

    private final IOService ioService;

    private final MessageSource ms;

    @Override
    public void showResult(TestResult testResult) {
        ioService.printLine("");
        ioService.printLine(ms.getMessage("application.testResults",new String[0],Locale.getDefault()));
        ioService.printFormattedLine(ms.getMessage("application.studentName",new String[] {testResult.getStudent().getFullName()},Locale.getDefault()));
        ioService.printFormattedLine(ms.getMessage("application.answeredQuestionCount", new String[] {String.valueOf(testResult.getAnsweredQuestions().size())},Locale.getDefault()) );
        ioService.printFormattedLine(ms.getMessage("application.rightAnswers", new String[] {String.valueOf(testResult.getRightAnswersCount())},Locale.getDefault()));

        if (testResult.getRightAnswersCount() >= testConfig.getRightAnswersCountToPass()) {
            ioService.printLine(ms.getMessage("application.testSuccess",new String[0],Locale.getDefault()));
            return;
        }
        ioService.printLine(ms.getMessage("application.testFail",new String[0],Locale.getDefault()));
    }
}
