import { credentials } from '@grpc/grpc-js';
import { PaymentServiceClient } from '../../../generated/proto/PaymentService';

export const paymentServiceClient = new PaymentServiceClient(
  'localhost:9091',
  credentials.createInsecure()
);
