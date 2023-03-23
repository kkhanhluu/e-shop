import { credentials } from '@grpc/grpc-js';
import { ProductServiceClient } from '../../../generated/proto/ProductService';

export const productServiceClient = new ProductServiceClient(
  'localhost:9093',
  credentials.createInsecure()
);
