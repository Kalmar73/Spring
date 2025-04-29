package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        // Использовать CsvToBean
        // https://opencsv.sourceforge.net/#collection_based_bean_fields_one_to_many_mappings
        // Использовать QuestionReadException
        // Про ресурсы: https://mkyong.com/java/java-read-a-file-from-resources-folder/
        List<Question> questions = new ArrayList<>();
        try{
            List<QuestionDto> questionDto = new CsvToBeanBuilder<QuestionDto>(new FileReader(fileNameProvider.getTestFileName())).withSeparator(';').withType(QuestionDto.class).withSkipLines(1).build().parse();
            for (QuestionDto question:
                 questionDto) {
                questions.add(question.toDomainObject());
            }
        } catch (Exception e){
            throw new QuestionReadException("Cant load questions!");
        }
        return questions;
    }
}
