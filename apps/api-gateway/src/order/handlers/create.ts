import { create } from 'domain';
import { z } from 'zod';
import {
  CreateOrderRequest,
  CreateOrderResponse,
} from '../../../generated/proto/OrderService';
import { orderServiceClient } from '../clients/grpc';

export const CreateOrderInput = z.object({
  userId: z.string().uuid(),
  orderLineItems: z.array(
    z.object({
      productId: z.string().uuid(),
      productPrice: z.number().positive(),
      quantity: z.number().int().positive(),
    })
  ),
});

export function createOrder(
  input: z.infer<typeof CreateOrderInput>
): Promise<CreateOrderResponse> {
  return new Promise((resolve, reject) => {
    const createOrderRequest = CreateOrderRequest.fromJSON(input);
    console.log(JSON.stringify(createOrderRequest, null, 4));
    orderServiceClient.createOrder(createOrderRequest, (error, response) => {
      if (error) {
        console.error(error);
        reject(error);
      } else {
        resolve(response);
      }
    });
  });
}
