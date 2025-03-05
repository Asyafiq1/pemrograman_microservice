package com.teknologiinformasi.restapi.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import com.teknologiinformasi.restapi.order.model.Order;
import com.teknologiinformasi.restapi.order.model.OrderResponse;
import com.teknologiinformasi.restapi.order.model.Produk;
import com.teknologiinformasi.restapi.order.service.OrderService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/api/orders")
public class OrderController {


   @Autowired
   private OrderService orderService;

   @Autowired
   private RestTemplate restTemplate;


   // GET semua order
   @GetMapping
   public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
   }

   @GetMapping("/{id}")
   public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id).orElse(null);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        // Panggil Product Service untuk mendapatkan data produk terkait
        String productServiceUrl = "http://localhost:8081/api/produk/" + order.getProductId();
        Produk product = restTemplate.getForObject(productServiceUrl, Produk.class);


        OrderResponse response = new OrderResponse();
        response.setOrder(order);
        response.setProduct(product);
        return ResponseEntity.ok(response);
   }


//    // GET order berdasarkan ID dan ambil detail order dari order Service
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
//        return orderService.getOrderById(id)
//                .map(order -> ResponseEntity.ok().body(order))
//                .orElse(ResponseEntity.notFound().build());
//    }
   // POST buat order baru
   @PostMapping
   public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // (Opsional) Validasi keberadaan produk dengan memanggil Product Service bisa ditambahkan di sini
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
   }


   // PUT update order
   @PutMapping("/{id}")
   public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        try {
            Order updatedOrder = orderService.updateOrder(id, orderDetails);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
   }


   // DELETE order
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order deleted successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
   }
}

