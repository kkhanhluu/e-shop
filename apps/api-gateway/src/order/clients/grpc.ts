import { credentials } from '@grpc/grpc-js';
import { OrderServiceClient } from '../../../generated/proto/OrderService';

export const orderServiceClient = new OrderServiceClient(
  process.env.ORDER_SERVICE_GRPC_URI as string,
  credentials.createInsecure()
);
