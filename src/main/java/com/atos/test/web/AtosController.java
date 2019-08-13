package com.atos.test.web;

import com.atos.test.dto.CustomerDTO;
import com.atos.test.service.AtosServiceTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/atos/customers")
public class AtosController {

    private AtosServiceTest atosService;

    public AtosController(AtosServiceTest atosService) {
        this.atosService = atosService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<CustomerDTO>> getAllCustomers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(atosService.getAllCustomers());

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeCustomer(@PathVariable("id") long customerId) {
        try {
            atosService.removeCustomer(customerId);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity addCustomer(@RequestBody final CustomerDTO customerDTO) {
        try {
            atosService.addCustomer(customerDTO);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
