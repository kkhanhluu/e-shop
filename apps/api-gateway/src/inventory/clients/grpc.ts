import { credentials } from '@grpc/grpc-js';
import { InventoryServiceClient } from '../../../generated/proto/InventoryService';

export const inventoryServiceClient = new InventoryServiceClient(
  process.env.INVENTORY_SERVICE_GRPC_URI as string,
  credentials.createInsecure()
);
