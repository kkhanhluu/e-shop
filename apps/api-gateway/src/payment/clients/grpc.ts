import { credentials } from '@grpc/grpc-js';
import { PaymentServiceClient } from '../../../generated/proto/PaymentService';

export const paymentServiceClient = new PaymentServiceClient(
  process.env.PAYMENT_SERVICE_GRPC_URI as string,
  credentials.createInsecure()
);
