import { z } from 'zod';
import {
  GetOrderByIdRequest,
  GetOrderByIdResponse,
} from '../../../generated/proto/OrderService';
import { orderServiceClient } from '../clients/grpc';

export const GetOrderByIdInput = z.string().uuid();

export function getOrderById(
  input: z.infer<typeof GetOrderByIdInput>
): Promise<GetOrderByIdResponse> {
  return new Promise((resolve, reject) => {
    const getOrderByIdRequest = GetOrderByIdRequest.create({ orderId: input });
    orderServiceClient.getOrderById(getOrderByIdRequest, (error, response) => {
      if (error) {
        console.error(error);
        reject(error);
      } else {
        resolve(response);
      }
    });
  });
}
