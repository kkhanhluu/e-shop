import { z } from 'zod';
import {
  GetAverageRatingForProductRequest,
  GetAverageRatingForProductResponse,
} from '../../../generated/proto/ReviewService';
import { reviewServiceClient } from '../clients/grpc';

const Input = z.string().uuid();

export async function getAverageRatingForProduct(
  input: z.infer<typeof Input>
): Promise<GetAverageRatingForProductResponse> {
  const request = GetAverageRatingForProductRequest.create({
    productId: input,
  });
  return new Promise((resolve, reject) => {
    reviewServiceClient.getAverageRatingForProduct(
      request,
      (error, response) => {
        if (error) {
          console.log(error);
          reject(error);
        } else {
          resolve(response);
        }
      }
    );
  });
}
