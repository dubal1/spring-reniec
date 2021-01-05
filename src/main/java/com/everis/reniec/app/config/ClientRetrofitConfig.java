package com.everis.reniec.app.config;

import com.everis.reniec.app.repository.ReniecRepository;
import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class ClientRetrofitConfig {
    /**
     *
     * @return repository.
     */
    @Bean
    ReniecRepository repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8001")
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ReniecRepository.class);
    }
}
