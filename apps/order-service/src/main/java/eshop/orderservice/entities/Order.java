package eshop.orderservice.entities;

import eshop.orderservice.cqrs.command.model.OrderDomainEvent;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private UUID userId;

    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<OrderLine> orderLines;

    public void when(OrderDomainEvent event) {
        switch(event) {
            case OrderDomainEvent.OrderCreatedEvent orderCreatedEvent:
                id = orderCreatedEvent.orderId();
                userId = orderCreatedEvent.getUserId();
                orderLines = orderCreatedEvent.getOrderLineItems();
                status = OrderStatus.CREATED;
                break;
            case null:
                throw new IllegalArgumentException("Event cannot be null");
        }
    }

    public static Order getEmptyOrder() {
        return new Order();
    }
}