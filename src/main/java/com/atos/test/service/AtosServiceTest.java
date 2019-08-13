package com.atos.test.service;

import com.atos.test.dto.CustomerDTO;
import com.atos.test.repository.AtosRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AtosServiceTest implements AtosService {
    private AtosRepository repository;

    public AtosServiceTest(AtosRepository repository) {
        this.repository = repository;
    }

    public Collection<CustomerDTO> getAllCustomers() {
        return repository.getAllCustomers();
    }

    @Override
    public void removeCustomer(long id) {
        this.repository.removeCustomer(id);

    }

    @Override
    public void addCustomer(CustomerDTO customer) {
        this.repository.addCustomer(customer);
    }
}
