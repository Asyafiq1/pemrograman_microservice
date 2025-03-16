package com.teknologiinformasi.restapi.order.event;

import java.io.Serializable;
import java.time.LocalDateTime;
public class OrderCreatedEvent implements Serializable {
   private String orderid;
   private Long productId;
   private int quantity;
   private LocalDateTime orderDate;
   private String orderStatus; // misal: CREATED
   public OrderCreatedEvent() {}
   public OrderCreatedEvent(String id, Long productId, int quantity, LocalDateTime orderDate, String orderStatus) {
       this.orderid = id;
       this.productId = productId;
       this.quantity = quantity;
       this.orderDate = orderDate;
       this.orderStatus = orderStatus;
   }
   // Getters & Setters
   public String getid() {
       return orderid;
   }
   public void setid(String id) {
       this.orderid = id;
   }
   public Long getProductId() {
       return productId;
   }
   public void setProductId(Long productId) {
       this.productId = productId;
   }
   public int getQuantity() {
       return quantity;
   }
   public void setQuantity(int quantity) {
       this.quantity = quantity;
   }
   public LocalDateTime getorderDate() {
       return orderDate;
   }
   public void setorderDate(LocalDateTime orderDate) {
       this.orderDate = orderDate;
   }
   public String getOrderStatus() {
       return orderStatus;
   }
   public void setOrderStatus(String orderStatus) {
       this.orderStatus = orderStatus;
   }
   @Override
   public String toString() {
       return "OrderCreatedEvent{" +
               "orderid=" + orderid +
               ", productId=" + productId +
               ", quantity=" + quantity +
               ", orderDate=" + orderDate +
               ", orderStatus='" + orderStatus + '\'' +
               '}';
   }
}
