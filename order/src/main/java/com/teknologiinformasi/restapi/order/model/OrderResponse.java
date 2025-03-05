package com.teknologiinformasi.restapi.order.model;

public class OrderResponse {
    private Order order;
    private Produk product;
 
 
    public OrderResponse() {}
 
 
  
    // Getters dan Setters
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Produk getProduct() {
        return product;
    }
    public void setProduct(Produk product) {
        this.product = product;
    }
 }
 
 