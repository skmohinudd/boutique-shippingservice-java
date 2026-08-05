package com.boutique.shipping.repository;
import com.boutique.shipping.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface ShipmentRepository extends JpaRepository<Shipment,UUID>{ Optional<Shipment> findByOrderId(UUID orderId); }
