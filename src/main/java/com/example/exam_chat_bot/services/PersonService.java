package com.example.exam_chat_bot.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.CrudRepositoryService;
import com.example.exam_chat_bot.PersonRepository;
import com.example.exam_chat_bot.entities.Person;

@BrowserCallable
@AnonymousAllowed
public class PersonService extends CrudRepositoryService<Person,Long, PersonRepository> {
}
