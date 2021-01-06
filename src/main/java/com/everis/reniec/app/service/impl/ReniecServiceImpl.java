package com.everis.reniec.app.service.impl;

import com.everis.reniec.app.model.Dummy;
import com.everis.reniec.app.model.Person;
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
    public Mono<Dummy> savePersonConsumer(final Person person) {
        return repository.savePerson(person)
                .filter(p -> !p.getBlacklist() && !p.getFingerprint())
                .map(per -> new Dummy("Reniec", true));
    }

}
