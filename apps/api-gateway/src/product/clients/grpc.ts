import { credentials } from '@grpc/grpc-js';
import { ProductServiceClient } from '../../../generated/proto/ProductService';

export const productServiceClient = new ProductServiceClient(
  process.env.PRODUCT_SERVICE_GRPC_URI as string,
  credentials.createInsecure()
);
