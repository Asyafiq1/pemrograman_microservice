package com.teknologiinformasitekoma.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasitekoma.restapi.model.Customer;
import com.teknologiinformasitekoma.restapi.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Endpoint untuk mengambil semua customer
    @GetMapping
    public List<Customer> getAllcustomer() {
        return customerService.getAllcustomer();
    }

    // Endpoint untuk mengambil customer berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getcustomerById(@PathVariable Long id) {
        return customerService.getcustomerById(id)
                .map(customer -> ResponseEntity.ok().body(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint untuk membuat customer baru
    @PostMapping
    public Customer createcustomer(@RequestBody Customer customer) {
        return customerService.createcustomer(customer);
    }

    // Endpoint untuk mengupdate customer yang sudah ada
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updatecustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        try {
            Customer updatedcustomer = customerService.updatecustomer(id, customerDetails);
            return ResponseEntity.ok(updatedcustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint untuk menghapus customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletecustomer(@PathVariable Long id) {
        try {
            customerService.deletecustomer(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "customer berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "customer tidak ditemukan dengan id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
