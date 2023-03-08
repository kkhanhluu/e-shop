package eshop.orderservice.order.repository;

import eshop.orderservice.order.query.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}