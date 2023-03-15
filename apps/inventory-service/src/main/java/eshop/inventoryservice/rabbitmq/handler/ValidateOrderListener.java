package eshop.inventoryservice.rabbitmq.handler;

import eshop.constants.RabbitMQConstants;
import eshop.inventoryservice.entities.ProductInventory;
import eshop.inventoryservice.rabbitmq.event.ValidateOrderRequest;
import eshop.inventoryservice.rabbitmq.event.ValidateOrderResponse;
import eshop.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidateOrderListener {
    private final InventoryRepository inventoryRepository;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConstants.PAYMENT_RESPONSE_QUEUE)
    public void listen(@Payload ValidateOrderRequest validateOrderRequest) {
        try {
            validateOrderRequest.getOrderLines().forEach(orderLine -> {
                Optional<ProductInventory> inventoryOptional = inventoryRepository.findByProductId(orderLine.getProductId());
                if (inventoryOptional.isEmpty()) {
                     handleValidateOrderFail(validateOrderRequest.getOrderID());
                }
                ProductInventory productInventory = inventoryOptional.get();
                if (productInventory.getQuantityOnHand() < orderLine.getQuantity()) {
                    handleValidateOrderFail(validateOrderRequest.getOrderID());
                }
            });
            handleValidateOrderSuccess(validateOrderRequest.getOrderID());
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    private void handleValidateOrderSuccess(UUID orderId) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.VALIDATE_ORDER_RESPONSE_KEY,
                ValidateOrderResponse.builder().orderId(orderId).isValid(true).build());
    }

    private void handleValidateOrderFail(UUID orderId) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.VALIDATE_ORDER_RESPONSE_KEY,
                ValidateOrderResponse.builder().orderId(orderId).isValid(false).build());
    }
}