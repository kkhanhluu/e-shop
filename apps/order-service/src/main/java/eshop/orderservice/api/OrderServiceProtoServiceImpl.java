package eshop.orderservice.api;

import eshop.orderservice.grpc.CreateOrderRequest;
import eshop.orderservice.grpc.CreateOrderResponse;
import eshop.orderservice.grpc.OrderServiceGrpc;
import eshop.orderservice.order.command.OrderCommandService;
import eshop.orderservice.order.command.commands.CreateOrderCommand;
import eshop.orderservice.order.query.entity.OrderLine;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
@Service
public class OrderServiceProtoServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {
    private final OrderCommandService orderCommandService;

    @Override
    public void createOrder(CreateOrderRequest request, StreamObserver<CreateOrderResponse> responseObserver) {
        UUID orderId = UUID.randomUUID();
        Set<OrderLine> orderLineItems = new HashSet<>();
        request.getOrderLineItemsList().forEach(orderLineItem -> {
            orderLineItems.add(OrderLine.builder()
                            .id(UUID.randomUUID())
                            .productId(UUID.fromString((orderLineItem.getProductId())))
                            .quantity(orderLineItem.getQuantity())
                            .productPrice(new BigDecimal((orderLineItem.getProductPrice())))
                    .build());
        });

        UUID createdOrderId = orderCommandService.handle(
                new CreateOrderCommand(orderId, UUID.fromString(request.getUserId()), orderLineItems));

        CreateOrderResponse createOrderResponse = CreateOrderResponse.newBuilder().setOrderId(createdOrderId.toString()).build();
        responseObserver.onNext(createOrderResponse);
        responseObserver.onCompleted();
    }
}