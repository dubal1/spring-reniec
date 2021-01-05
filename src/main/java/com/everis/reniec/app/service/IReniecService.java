package com.everis.reniec.app.service;

import com.everis.reniec.app.dto.Person;
import reactor.core.publisher.Mono;

public interface IReniecService {
    /**
     *
     * @param person
     * @return savePersonConsumer.
     */
    Mono<Person> savePersonConsumer(Person person);
}
