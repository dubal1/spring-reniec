package com.everis.reniec.app.service;

import com.everis.reniec.app.model.Dummy;
import com.everis.reniec.app.model.Person;
import reactor.core.publisher.Mono;

public interface IReniecService {
    /**
     *
     * @param person
     * @return savePersonConsumer.
     */
    Mono<Dummy> savePersonConsumer(Person person);
}
