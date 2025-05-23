package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
