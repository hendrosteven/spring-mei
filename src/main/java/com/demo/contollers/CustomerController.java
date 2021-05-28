package com.demo.contollers;

import javax.validation.Valid;

import com.demo.dto.CustomerRequest;
import com.demo.dto.DataResponse;
import com.demo.model.Customer;
import com.demo.service.CustomerService;
import com.demo.utility.ErrorParsingUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<DataResponse> createOne(@Valid @RequestBody CustomerRequest customer, Errors errors) {
        DataResponse response = new DataResponse();
        if (errors.hasErrors()) {
            response.setStatus(false);
            response.setMessages(ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            try {
                response.setStatus(true);
                response.getMessages().add("Customer saved");
                response.setPayload(customerService.create(customer.getName(), customer.getEmail()));
                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                response.setStatus(false);
                response.getMessages().add(ex.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
    }

    @GetMapping
    public Iterable<Customer> findAll(
            @RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {

        return customerService.findAll(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        customerService.remove(id);
    }
}
