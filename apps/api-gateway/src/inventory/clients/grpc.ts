import { credentials } from '@grpc/grpc-js';
import { InventoryServiceClient } from '../../../generated/proto/InventoryService';

export const inventoryServiceClient = new InventoryServiceClient(
  'localhost:9092',
  credentials.createInsecure()
);
