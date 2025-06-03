package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw.domain.Student;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;
    private final MessageSource ms;
    @Override
    public Student determineCurrentStudent() {
        var firstName = ioService.readStringWithPrompt(ms.getMessage("application.inputName", new String[] {}, Locale.getDefault()));
        var lastName = ioService.readStringWithPrompt(ms.getMessage("application.inputLastName", new String[] {}, Locale.getDefault()));
        return new Student(firstName, lastName);
    }
}
