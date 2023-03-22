package eshop.orderservice.api;

import eshop.orderservice.grpc.*;
import eshop.orderservice.order.command.OrderCommandService;
import eshop.orderservice.order.command.commands.CreateOrderCommand;
import eshop.orderservice.order.query.entity.Order;
import eshop.orderservice.order.query.entity.OrderLine;
import eshop.orderservice.order.query.service.OrderQueryService;
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
    private final OrderQueryService orderQueryService;

    @Override
    public void createOrder(CreateOrderRequest request, StreamObserver<CreateOrderResponse> responseObserver) {
        UUID orderId = UUID.randomUUID();
        Set<OrderLine> orderLineItems = new HashSet<>();
        request.getOrderLineItemsList().forEach(orderLineItem -> {
            orderLineItems.add(OrderLine.builder()
                    .id(UUID.randomUUID())
                    .productId(UUID.fromString((orderLineItem.getProductId())))
                    .quantity(orderLineItem.getQuantity())
                    .productPrice(BigDecimal.valueOf(orderLineItem.getProductPrice()))
                    .build());
        });

        UUID createdOrderId = orderCommandService.handle(
                new CreateOrderCommand(orderId, UUID.fromString(request.getUserId()), orderLineItems));

        CreateOrderResponse createOrderResponse = CreateOrderResponse.newBuilder()
                .setOrderId(createdOrderId.toString())
                .build();
        responseObserver.onNext(createOrderResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getOrderById(GetOrderByIdRequest request, StreamObserver<GetOrderByIdResponse> responseObserver) {
        Order order = orderQueryService.getOrderById(UUID.fromString(request.getOrderId()));

        GetOrderByIdResponse.Builder builder = GetOrderByIdResponse.newBuilder()
                .setId(order.getId().toString())
                .setUserId(order.getUserId().toString())
                .setCreatedDate(order.getCreatedDate().toString())
                .setStatus(order.getStatus().toString())
                .setLastModifiedDate(order.getLastModifiedDate().toString());

        for (OrderLine orderLineItem : order.getOrderLineItems()) {
            OrderLineResponse orderLineResponse = OrderLineResponse.newBuilder()
                    .setId(orderLineItem.getId().toString())
                    .setQuantity(orderLineItem.getQuantity())
                    .setProductId(orderLineItem.getProductId().toString())
                    .setProductPrice(orderLineItem.getProductPrice().doubleValue())
                    .build();
            builder.addOrderLineItems(orderLineResponse);
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}