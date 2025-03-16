// package com.teknologiinformasi.restapi.order.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.client.HttpClientErrorException;
// import org.springframework.web.client.RestTemplate;


// import com.teknologiinformasi.restapi.order.model.Order;
// import com.teknologiinformasi.restapi.order.model.OrderResponse;
// import com.teknologiinformasi.restapi.order.model.Produk;
// import com.teknologiinformasi.restapi.order.service.OrderService;


// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;




// @RestController
// @RequestMapping("/api/orders")
// public class OrderController {


//    @Autowired
//    private OrderService orderService;

//    @Autowired
//    private RestTemplate restTemplate;


//    // GET semua order
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//         List<Order> orders = orderService.getAllOrders();
//         return ResponseEntity.ok(orders);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
//         Order order = orderService.getOrderById(id).orElse(null);
//         if (order == null) {
//             return ResponseEntity.notFound().build();
//         }
//         // Panggil Product Service untuk mendapatkan data produk terkait
//         String productServiceUrl = "http://localhost:8081/api/produk/" + order.getProductId();
//         Produk product = restTemplate.getForObject(productServiceUrl, Produk.class);


//         OrderResponse response = new OrderResponse();
//         response.setOrder(order);
//         response.setProduct(product);
//         return ResponseEntity.ok(response);
//    }


// //    // GET order berdasarkan ID dan ambil detail order dari order Service
// //    @GetMapping("/{id}")
// //    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
// //        return orderService.getOrderById(id)
// //                .map(order -> ResponseEntity.ok().body(order))
// //                .orElse(ResponseEntity.notFound().build());
// //    }
//    // POST buat order baru
// //    @PostMapping
// //    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
// //         // (Opsional) Validasi keberadaan produk dengan memanggil Product Service bisa ditambahkan di sini
// //         Order createdOrder = orderService.createOrder(order);
// //         return ResponseEntity.ok(createdOrder);
// //    }

//    // POST buat order baru
//    @PostMapping
//     public ResponseEntity<?> createOrder(@RequestBody Order order) {
//     // URL Product Service untuk memeriksa keberadaan produk
//     String productServiceUrl = "http://localhost:8081/api/produk/" + order.getProductId();
    
//         try {
//             // Coba ambil produk dari Product Service
//             restTemplate.getForObject(productServiceUrl, Produk.class);
//         } catch (HttpClientErrorException.NotFound e) {
//             // Jika produk tidak ditemukan, kembalikan pesan error
//             return ResponseEntity
//                     .status(HttpStatus.BAD_REQUEST)
//                     .body(Map.of("message", "Gagal membuat order, produk dengan ID " + order.getProductId() + " tidak ditemukan"));
//         }

//         // Jika produk ditemukan, lanjutkan membuat order
//         Order createdOrder = orderService.createOrder(order);
//         return ResponseEntity.ok(createdOrder);
//     }





//    // PUT update order
//    @PutMapping("/{id}")
//    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
//         try {
//             Order updatedOrder = orderService.updateOrder(id, orderDetails);
//             return ResponseEntity.ok(updatedOrder);
//         } catch (RuntimeException ex) {
//             return ResponseEntity.notFound().build();
//         }
//    }


//    // DELETE order
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
//         try {
//             orderService.deleteOrder(id);
//             return ResponseEntity.ok("Order deleted successfully");
//         } catch (RuntimeException ex) {
//             return ResponseEntity.notFound().build();
//         }
//    }
// }



package com.teknologiinformasi.restapi.order.controller;

// import java.util.List;
import java.util.UUID;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasi.restapi.order.command.CreateOrderCommand;
import com.teknologiinformasi.restapi.order.command.UpdateOrderCommand;
import com.teknologiinformasi.restapi.order.model.CreateOrderRequest;
import com.teknologiinformasi.restapi.order.model.UpdateOrderRequest;
import com.teknologiinformasi.restapi.order.repository.OrderSummaryRepository;




@RestController
@RequestMapping("/api/orders")
public class OrderController {


   @Autowired
   private CommandGateway commandGateway;


//    @Autowired
//    private OrderSummaryRepository orderSummaryRepository;


    public OrderController(CommandGateway commandGateway, OrderSummaryRepository orderRepository) {
       this.commandGateway = commandGateway;
    //    this.orderSummaryRepository = orderRepository;
   }
   // Endpoint untuk membuat order (Command)
   @PostMapping
   public String createOrder(@RequestBody CreateOrderRequest request) {
       String orderId = UUID.randomUUID().toString();
       CreateOrderCommand command = new CreateOrderCommand(orderId, request.getProductId(), request.getQuantity());
       commandGateway.sendAndWait(command);
       // Ambil data order yang baru dibuat dari database
   return orderId;
   }


   // Endpoint untuk memperbarui order (Command)
   @PutMapping("/{orderId}")
   public String updateOrder(@PathVariable String orderId, @RequestBody UpdateOrderRequest request) {
       UpdateOrderCommand command = new UpdateOrderCommand(orderId, request.getProductId(), request.getQuantity(), request.getOrderStatus());
       commandGateway.sendAndWait(command);
       return orderId;
   }


  
}