package eshop.orderservice.order.query.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private UUID id;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private UUID userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<OrderLine> orderLineItems;

    @Column(nullable = false)
    @JsonIgnore
    private long lastProcessedPosition;

    public void addOrderLineItem(OrderLine orderLineItem) {
        if (orderLineItems == null) {
            this.orderLineItems = new HashSet<>();
        }
        orderLineItem.order = this;
        this.orderLineItems.add(orderLineItem);
    }
}