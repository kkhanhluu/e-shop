import { z } from 'zod';
import {
  GetPaymentByOrderIdRequest,
  GetPaymentByOrderIdResponse,
} from '../../../generated/proto/PaymentService';
import { paymentServiceClient } from '../clients/grpc';

export const GetPaymentByOrderIdInput = z.object({
  orderId: z.string().uuid(),
});

export function getPaymentByOrderId(
  input: z.infer<typeof GetPaymentByOrderIdInput>
): Promise<GetPaymentByOrderIdResponse> {
  return new Promise((resolve, reject) => {
    const request = GetPaymentByOrderIdRequest.fromJSON(input);
    paymentServiceClient.getPaymentByOrderId(request, (error, response) => {
      if (error) {
        console.error(error);
        reject(error);
      } else {
        resolve(response);
      }
    });
  });
}
