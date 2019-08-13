package com.atos.test.conifg;

import com.atos.test.dto.CustomerDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("test")
public class AtosTestConfig {

    @Bean
    public Map<Long, CustomerDTO> customerRepository() {
        return new HashMap<>();
    }
}
