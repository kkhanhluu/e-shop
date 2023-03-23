import { publicProcedure, router } from '../../context';
import { createReview, CreateReviewInput } from '../handlers/create';
import {
  getPaginatedListOfReviewsForProduct,
  GetPaginatedListOfReviewsForProductInput,
} from '../handlers/get-list-reviews-for-product';

export const reviewRouter = router({
  create: publicProcedure
    .input(CreateReviewInput)
    .query(({ input }) => createReview(input)),
  getListForProduct: publicProcedure
    .input(GetPaginatedListOfReviewsForProductInput)
    .query(({ input }) => getPaginatedListOfReviewsForProduct(input)),
});
