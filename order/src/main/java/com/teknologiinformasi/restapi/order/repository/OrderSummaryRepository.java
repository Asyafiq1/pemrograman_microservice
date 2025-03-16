package com.teknologiinformasi.restapi.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teknologiinformasi.restapi.order.model.OrderSummary;


@Repository
public interface OrderSummaryRepository extends JpaRepository<OrderSummary, String> {
}