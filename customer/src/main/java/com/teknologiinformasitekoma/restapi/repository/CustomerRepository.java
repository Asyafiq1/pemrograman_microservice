package com.teknologiinformasitekoma.restapi.repository;

import com.teknologiinformasitekoma.restapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
