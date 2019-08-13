package com.atos.test.service;

import com.atos.test.dto.CustomerDTO;

import java.util.Collection;

public interface AtosService {

    Collection<CustomerDTO> getAllCustomers();

    void removeCustomer(long id);

    void addCustomer(CustomerDTO customer);
}
