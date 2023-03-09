package eshop.constants;

public class RabbitMQConstants {
    public static final String EXCHANGE_NAME = "exchange.eshop";
    public static final String PAYMENT_RESPONSE_QUEUE = "q.payment-response";
    public static final String PAYMENT_RESPONSE_KEY = "payment-response";
    public static final String VALIDATE_ORDER_QUEUE = "q.validate-order";
    public static final String VALIDATE_ORDER_KEY = "validate-order";
    public static final String VALIDATE_ORDER_RESPONSE_QUEUE = "q.validate-order-response";
    public static final String VALIDATE_ORDER_RESPONSE_KEY = "validate-order-response";
    public static final String ALLOCATE_ORDER_QUEUE = "q.allocate-order";
    public static final String ALLOCATE_ORDER_KEY = "allocate-order";
    public static final String ALLOCATE_ORDER_RESPONSE_QUEUE = "q.allocate-order-response";
    public static final String ALLOCATE_ORDER_RESPONSE_KEY = "allocate-order-response";
    public static final String DELIVER_ORDER_QUEUE = "q.deliver-order";
    public static final String DELIVER_ORDER_KEY = "deliver-order";
    public static final String DELIVER_ORDER_RESPONSE_QUEUE = "q.deliver-order-response";
    public static final String DELIVER_ORDER_RESPONSE_KEY = "deliver-order-response";
}