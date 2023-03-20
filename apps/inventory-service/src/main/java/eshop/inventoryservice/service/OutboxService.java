package eshop.inventoryservice.service;

import eshop.constants.RabbitMQConstants;
import eshop.inventoryservice.entities.AllocateOrderOutbox;
import eshop.inventoryservice.rabbitmq.event.AllocateOrderResponse;
import eshop.inventoryservice.repository.AllocateOrderOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxService {
    private final AllocateOrderOutboxRepository outboxRepository;
    private final RabbitTemplate rabbitTemplate;
    @Scheduled(fixedDelayString = "10000")
    public void retrySendMessageFromOutbox() {
        List<AllocateOrderOutbox> outboxItems = outboxRepository.findAll();
        outboxItems.forEach(outbox -> {
            rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME,
                    RabbitMQConstants.ALLOCATE_ORDER_RESPONSE_KEY,
                    AllocateOrderResponse.builder()
                            .orderId(outbox.getOrderId())
                            .isSuccessful(outbox.isSuccessful())
                            .build());
            outboxRepository.deleteById(outbox.getId());
        });
    }
}