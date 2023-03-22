import { publicProcedure, router } from '../../context';
import { z } from 'zod';
import { createOrder, CreateOrderInput } from '../handlers/create';

export const orderRouter = router({
  createOrder: publicProcedure
    .input(CreateOrderInput)
    .query(({ input }) => createOrder(input)),
  get: publicProcedure.input(z.string().uuid()).query((req) => {
    return `Hello world from getOrder: ${req.input}`;
  }),
});
