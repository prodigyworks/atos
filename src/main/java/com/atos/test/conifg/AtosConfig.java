package com.atos.test.conifg;

import com.atos.test.Application;
import com.atos.test.dto.CustomerDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Profile("local")
public class AtosConfig {

    @Bean
    public Map<Long, CustomerDTO> customerRepository() {
        Map<Long, CustomerDTO> customers = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Application.class.getResourceAsStream("/customers.json"), "UTF-8"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Long id = Long.parseLong(data[0]);

                customers.put(id, new CustomerDTO(id, data[1], data[2]));
            }
        } catch (UnsupportedEncodingException e) {
            return Collections.emptyMap();

        } catch (IOException e) {
            return Collections.emptyMap();
        }

        return customers;
    }
}
