package org.example.config;

import org.example.ContactsBookInitializer;
import org.example.EmptyContactsBook;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources(value = {@PropertySource("classpath:application-empty.properties")})
@Profile("empty")
public class EmptyAppConfig {
    @Bean
    public ContactsBookInitializer initializer(){
        return new EmptyContactsBook();
    }
}
