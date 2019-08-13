package com.atos.test.repository;

import com.atos.test.dto.CustomerDTO;

import java.util.Collection;

public interface AtosRepository {

    public Collection<CustomerDTO> getAllCustomers();

    public void removeCustomer(long id);

    public void addCustomer(CustomerDTO customer);

}
