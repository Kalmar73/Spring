package ru.otus.example.beansscopesdemo.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("prototypeGreetingService")
@Scope("prototype")
public class PrototypeGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
