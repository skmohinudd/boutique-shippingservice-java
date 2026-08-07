package com.boutique.shipping.repository;
import org.springframework.jdbc.core.JdbcTemplate;import org.springframework.stereotype.Repository;import java.util.UUID;
@Repository public class ShippingInboxRepository{private final JdbcTemplate jdbc;public ShippingInboxRepository(JdbcTemplate jdbc){this.jdbc=jdbc;}public int claim(UUID id){return jdbc.update("insert into shipping_processed_events(event_id,processed_at) values (?,now()) on conflict do nothing",id);}}
