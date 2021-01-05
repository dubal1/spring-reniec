package com.everis.reniec.app.service.impl;

import com.everis.reniec.app.dto.Person;
import com.everis.reniec.app.repository.ReniecRepository;
import com.everis.reniec.app.service.IReniecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReniecServiceImpl implements IReniecService {
    /**
     * inject FingerPrintsRepository.
     */
    @Autowired
    private ReniecRepository repository;

    /**
     *
     * @param person
     * @return savePersonConsumer.
     */
    @Override
    public Mono<Person> savePersonConsumer(final Person person) {
        return repository.savePerson(person);
    }

}
