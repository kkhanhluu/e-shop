import { router } from '../../context';
import { authenticatedProcedure } from '../../middleware/authenticated';
import { createOrder, CreateOrderInput } from '../handlers/create';
import { getOrderById, GetOrderByIdInput } from '../handlers/get';

export const orderRouter = router({
  create: authenticatedProcedure
    .input(CreateOrderInput)
    .query(({ input }) => createOrder(input)),
  get: authenticatedProcedure
    .input(GetOrderByIdInput)
    .query(({ input }) => getOrderById(input)),
});
