import { publicProcedure, router } from '../../context';
import { createOrder, CreateOrderInput } from '../handlers/create';
import { getOrderById, GetOrderByIdInput } from '../handlers/get';

export const orderRouter = router({
  create: publicProcedure
    .input(CreateOrderInput)
    .query(({ input }) => createOrder(input)),
  get: publicProcedure
    .input(GetOrderByIdInput)
    .query(({ input }) => getOrderById(input)),
});
