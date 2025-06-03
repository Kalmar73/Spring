package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;
    private final CsvQuestionDao csvQuestionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        List<Question> questions = csvQuestionDao.findAll();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        try {
            for (Question question:
                    questions) {
                ioService.printFormattedLine(question.text());
                ioService.printLine(question.answers().stream().map(Answer::text).collect(Collectors.joining("\n")));
                String answer = bufferedReader.readLine();
                for (Answer ans:
                     question.answers()) {
                    if(ans.text().equals(answer) && ans.isCorrect()){
                        count++;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        ioService.printLine("You answered "+count+" answers correctly!");
        // Получить вопросы из дао и вывести их с вариантами ответов
    }
}
