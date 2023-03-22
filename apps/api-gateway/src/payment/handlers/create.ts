import { z } from 'zod';
import { paymentServiceClient } from '../clients/grpc';
import {
  CreatePaymentRequest,
  CreatePaymentResponse,
} from '../../../generated/proto/PaymentService';
export const CreatePaymentInput = z.object({
  userId: z.string().uuid(),
  orderId: z.string().uuid(),
  value: z.number().positive(),
});

export function createPayment(
  input: z.infer<typeof CreatePaymentInput>
): Promise<CreatePaymentResponse> {
  return new Promise((resolve, reject) => {
    const request = CreatePaymentRequest.fromJSON(input);
    paymentServiceClient.createPayment(request, (error, response) => {
      if (error) {
        console.error(error);
        reject(error);
      } else {
        resolve(response);
      }
    });
  });
}
