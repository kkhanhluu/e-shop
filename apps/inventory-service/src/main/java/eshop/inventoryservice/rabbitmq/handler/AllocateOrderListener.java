package eshop.inventoryservice.rabbitmq.handler;

import eshop.api.exceptions.NotFoundException;
import eshop.constants.RabbitMQConstants;
import eshop.inventoryservice.entities.AllocateOrderOutbox;
import eshop.inventoryservice.entities.ProductInventory;
import eshop.inventoryservice.rabbitmq.event.AllocateOrderRequest;
import eshop.inventoryservice.rabbitmq.event.AllocateOrderResponse;
import eshop.inventoryservice.repository.AllocateOrderOutboxRepository;
import eshop.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class AllocateOrderListener {
    private final InventoryRepository inventoryRepository;
    private final AllocateOrderOutboxRepository outboxRepository;
    private final RabbitTemplate rabbitTemplate;
    private final TransactionTemplate transactionTemplate;

    @RabbitListener(queues = RabbitMQConstants.ALLOCATE_ORDER_QUEUE)
    public void listen(@Payload AllocateOrderRequest allocateOrderRequest) {
        try {
            AllocateOrderOutbox outbox = AllocateOrderOutbox.builder()
                    .orderId(allocateOrderRequest.getOrderId())
                    .build();
            if (Math.random() > 0.8) {
                outbox.setSuccessful(false);
                outboxRepository.save(outbox);

                rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME,
                        RabbitMQConstants.ALLOCATE_ORDER_RESPONSE_KEY,
                        AllocateOrderResponse.builder()
                                .orderId(allocateOrderRequest.getOrderId())
                                .isSuccessful(false)
                                .build());

                outboxRepository.deleteById(outbox.getId());
                return;
            }

            outbox.setSuccessful(true);
            transactionTemplate.executeWithoutResult(status -> {
                allocateOrderRequest.getOrderLines().forEach(orderLine -> {
                    ProductInventory productInventory = inventoryRepository.findByProductId(orderLine.getProductId())
                            .orElseThrow(
                                    NotFoundException::new);
                    productInventory.setQuantityOnHand(productInventory.getQuantityOnHand() - orderLine.getQuantity());
                    inventoryRepository.save(productInventory);
                });
                outboxRepository.save(outbox);
            });

            rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME,
                    RabbitMQConstants.ALLOCATE_ORDER_RESPONSE_KEY,
                    AllocateOrderResponse.builder()
                            .orderId(allocateOrderRequest.getOrderId())
                            .isSuccessful(true)
                            .build());

            outboxRepository.deleteById(outbox.getId());
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}