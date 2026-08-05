package com.boutique.shipping.service;
import com.boutique.shipping.entity.Shipment;
import com.boutique.shipping.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
@Service
public class ShipmentService {
 private final ShipmentRepository repo;
 public ShipmentService(ShipmentRepository repo){this.repo=repo;}
 @Transactional public Shipment create(UUID orderId){return repo.findByOrderId(orderId).orElseGet(()->repo.save(new Shipment(orderId)));}
 @Transactional(readOnly=true) public Shipment get(UUID id){return repo.findById(id).orElseThrow();}
}
