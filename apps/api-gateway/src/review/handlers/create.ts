import { z } from 'zod';
import {
  CreateReviewRequest,
  Review,
} from '../../../generated/proto/ReviewService';
import { reviewServiceClient } from '../clients/grpc';

export const CreateReviewInput = z.object({
  productId: z.string().uuid(),
  rate: z.number().positive().max(5),
  text: z.string(),
});

export async function createReview(
  input: z.infer<typeof CreateReviewInput>
): Promise<Review> {
  const request = CreateReviewRequest.fromJSON(input);
  return new Promise((resolve, reject) => {
    reviewServiceClient.createReview(request, (error, response) => {
      if (error) {
        console.log(error);
        reject(error);
      } else {
        resolve(response);
      }
    });
  });
}
