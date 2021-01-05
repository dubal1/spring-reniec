package com.everis.reniec.app.repository;

import com.everis.reniec.app.dto.Person;
import reactor.core.publisher.Mono;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReniecRepository {

    /**
     *
     * @param person .
     * @return savePerson.
     */
    @POST("/core/persons")
    Mono<Person> savePerson(@Body Person person);
}
