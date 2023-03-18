package eshop.paymentservice.payment.repository;

import eshop.paymentservice.payment.Payment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, ObjectId> {
    Optional<Payment> findPaymentByOrderId(String orderId);
}