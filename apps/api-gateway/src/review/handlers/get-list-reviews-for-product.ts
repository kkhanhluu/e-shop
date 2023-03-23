import { z } from 'zod';
import {
  GetReviewsByProductIdRequest,
  GetReviewsByProductIdResponse,
} from '../../../generated/proto/ReviewService';
import { reviewServiceClient } from '../clients/grpc';

export const GetPaginatedListOfReviewsForProductInput = z.object({
  productId: z.string().uuid(),
  page: z.number().min(0),
  size: z.number().positive(),
});

export function getPaginatedListOfReviewsForProduct(
  input: z.infer<typeof GetPaginatedListOfReviewsForProductInput>
): Promise<GetReviewsByProductIdResponse> {
  const request = GetReviewsByProductIdRequest.fromJSON(input);
  return new Promise((resolve, reject) => {
    reviewServiceClient.getReviewsByProductId(request, (error, response) => {
      if (error) {
        console.log(error);
        reject(error);
      } else {
        resolve(response);
      }
    });
  });
}
