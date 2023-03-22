import { publicProcedure, router } from '../../context';
import { getProductById, GetProductByIdInput } from '../handlers/get';
import {
  getPaginatedListOfProducts,
  GetProductsInput,
} from '../handlers/get-list';

export const productRouter = router({
  get: publicProcedure
    .input(GetProductByIdInput)
    .query(({ input }) => getProductById(input)),
  list: publicProcedure
    .input(GetProductsInput)
    .query(({ input }) => getPaginatedListOfProducts(input)),
});
