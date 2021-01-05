package com.everis.reniec.app.controller;

import com.everis.reniec.app.dto.Person;
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
     * @param personMono
     * @return savePerson.
     */
    @PostMapping(value = "/external/reniec/validate")
    public Mono<ResponseEntity<Map<String, Object>>> savePerson(
            @Valid @RequestBody final Mono<Person> personMono) {
        Map<String, Object> objectMap = new HashMap<>();
        return personMono
                .flatMap(person -> {
                    return service.savePersonConsumer(person)
                            .filter(p -> !p.getBlacklist())
                            .map(existingPerson -> {
                                String entityName = null;
                                if (!existingPerson.getFingerprint()) {
                                    entityName = "Reniec";
                                }
                                objectMap.put("entityName", entityName);
                                objectMap.put("success", true);
                                return new ResponseEntity<>(objectMap, HttpStatus.OK);
                            })
                            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
                })
                .onErrorResume(throwable -> {
                    return Mono.just(throwable).cast(WebExchangeBindException.class)
                            .flatMap(e -> Mono.just(e.getFieldErrors()))
                            .flatMapMany(Flux::fromIterable)
                            .map(FieldError::getDefaultMessage)
                            .collectList()
                            .flatMap(listErrors -> {
                                objectMap.put("err", listErrors);
                                objectMap.put("dateTime", new Date());
                                return Mono.just(new ResponseEntity<>(objectMap, HttpStatus.BAD_REQUEST));
                            });
                });
    }
}
