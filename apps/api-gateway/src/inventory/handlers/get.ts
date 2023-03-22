import { z } from 'zod';
import {
  GetInventoryByProductIdRequest,
  Inventory,
} from '../../../generated/proto/InventoryService';
import { inventoryServiceClient } from '../clients/grpc';

export const GetInventoryByProductIdInput = z.object({
  productId: z.string().uuid(),
});

export function getInventoryByProductId(
  input: z.infer<typeof GetInventoryByProductIdInput>
): Promise<Inventory> {
  return new Promise((resolve, reject) => {
    const request = GetInventoryByProductIdRequest.fromJSON(input);
    console.log(request);
    inventoryServiceClient.getInventoryByProductId(
      request,
      (error, response) => {
        if (error) {
          console.error(error);
          reject(error);
        } else {
          resolve(response);
        }
      }
    );
  });
}
