package eshop.paymentservice.api;


import eshop.paymentservice.grpc.*;
import eshop.paymentservice.payment.Payment;
import eshop.paymentservice.payment.service.PaymentService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class PaymentServiceProtoServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase {
    private final PaymentService paymentService;

    @Override
    public void createPayment(CreatePaymentRequest request, StreamObserver<CreatePaymentResponse> responseObserver) {
        boolean isSuccessful = paymentService.createPayment(request);

        CreatePaymentResponse response = CreatePaymentResponse.newBuilder().setIsSuccessful(isSuccessful).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getPaymentByOrderId(GetPaymentByOrderIdRequest request,
                                    StreamObserver<GetPaymentByOrderIdResponse> responseObserver) {
        Payment payment = paymentService.getPaymentByOrderId(request);

        GetPaymentByOrderIdResponse response = GetPaymentByOrderIdResponse.newBuilder()
                .setId(payment.getId().toString())
                .setValue(payment.getValue().doubleValue())
                .setIsCompensated(payment.getIsCompensated().toString())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}