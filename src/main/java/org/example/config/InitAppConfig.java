package org.example.config;

import org.example.ContactsBookInitializer;
import org.example.FilledContactsBook;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources(value = {@PropertySource("classpath:application-init.properties")})
@Profile("init")
public class InitAppConfig {
    @Bean
    public ContactsBookInitializer initializer(){
        return new FilledContactsBook();
    }
}

