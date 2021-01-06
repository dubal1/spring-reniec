package com.everis.reniec.app.controller;

import com.everis.reniec.app.model.Dummy;
import com.everis.reniec.app.model.Person;
import com.everis.reniec.app.service.IReniecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ReniecController {
    /**
     * inject IFingerPrintsService.
     */
    @Autowired
    private IReniecService service;

    /**
     * @param person
     * @return savePerson.
     */
    @PostMapping(value = "/external/reniec/validate")
    public Mono<Dummy> savePerson(
            @Valid @RequestBody final Person person) {
        return service.savePersonConsumer(person);
    }
}
