package com.teknologiinformasitekoma.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teknologiinformasitekoma.restapi.model.Customer;
import com.teknologiinformasitekoma.restapi.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllcustomer() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getcustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createcustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updatecustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("customer tidak ditemukan dengan id " + id));
        customer.setname(customerDetails.getname());
        customer.setemail(customerDetails.getemail());
        customer.setaddress(customerDetails.getaddress());
        return customerRepository.save(customer);
    }

    public void deletecustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("customer tidak ditemukan dengan id " + id));
        customerRepository.delete(customer);
    }
}
