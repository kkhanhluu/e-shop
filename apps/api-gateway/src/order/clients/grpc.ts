import { credentials } from '@grpc/grpc-js';
import { OrderServiceClient } from '../../../generated/proto/OrderService';

export const orderServiceClient = new OrderServiceClient(
  'localhost:9090',
  credentials.createInsecure()
);
