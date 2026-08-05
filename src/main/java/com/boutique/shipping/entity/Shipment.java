package com.boutique.shipping.entity;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="shipments")
public class Shipment {
 @Id private UUID id;
 @Column(name="order_id",nullable=false,unique=true) private UUID orderId;
 @Column(name="tracking_number",nullable=false,unique=true) private String trackingNumber;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ShipmentStatus status;
 @Column(name="created_at",nullable=false) private Instant createdAt;
 protected Shipment(){}
 public Shipment(UUID orderId){this.id=UUID.randomUUID();this.orderId=orderId;this.trackingNumber="BTQ-"+UUID.randomUUID().toString().substring(0,12).toUpperCase();this.status=ShipmentStatus.CREATED;this.createdAt=Instant.now();}
 public UUID getId(){return id;} public UUID getOrderId(){return orderId;} public String getTrackingNumber(){return trackingNumber;} public ShipmentStatus getStatus(){return status;}
}
