package com.boutique.shipping.controller;
import com.boutique.shipping.entity.Shipment;
import com.boutique.shipping.service.ShipmentService;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController @RequestMapping("/api/v1/shipments")
public class ShipmentController {
 private final ShipmentService service;
 public ShipmentController(ShipmentService service){this.service=service;}
 @PostMapping public Shipment create(@RequestParam UUID orderId){return service.create(orderId);}
 @GetMapping("/{id}") public Shipment get(@PathVariable UUID id){return service.get(id);}
}
