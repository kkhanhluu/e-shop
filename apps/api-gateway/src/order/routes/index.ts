import { publicProcedure, router } from '../../context';
import { z } from 'zod';

export const orderRouter = router({
  createOrder: publicProcedure
    .input(
      z.object({
        userId: z.string().uuid(),
        orderLineItems: z.array(
          z.object({
            productId: z.string().uuid(),
            productPrice: z.number().positive(),
            quantity: z.number().int().positive(),
          })
        ),
      })
    )
    .query(() => 'Hello world from createOrder'),
  get: publicProcedure.input(z.string().uuid()).query((req) => {
    return `Hello world from getOrder: ${req.input}`;
  }),
});
