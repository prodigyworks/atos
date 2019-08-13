package com.atos.test.repository;

import com.atos.test.dto.CustomerDTO;
import org.springframework.stereotype.Repository;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Map;

@Repository
public class AtosRepositoryImpl implements AtosRepository {
    private Map<Long, CustomerDTO>  customerRepository;

    public AtosRepositoryImpl(Map<Long, CustomerDTO> customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Collection<CustomerDTO> getAllCustomers() {
        return customerRepository.values();
    }

    @Override
    public void removeCustomer(long id) {
        if (customerRepository.containsKey(id)) {
            customerRepository.remove(id);

        } else {
            throw new ValidationException("Invalid customer");
        }
    }

    @Override
    public void addCustomer(CustomerDTO customer) {
        if (! customerRepository.containsKey(customer.getId())) {
            customerRepository.put(customer.getId(), customer);

        } else {
            throw new ValidationException("Customer already exists");
        }
    }
}
