package com.teknologiinformasi.restapi.order.repository;

import com.teknologiinformasi.restapi.order.model.Produk;
import com.teknologiinformasi.restapi.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
