import { publicProcedure, router } from '../../context';
import { createPayment, CreatePaymentInput } from '../handlers/create';
import { getPaymentByOrderId, GetPaymentByOrderIdInput } from '../handlers/get';

export const paymentRouter = router({
  create: publicProcedure
    .input(CreatePaymentInput)
    .query(({ input }) => createPayment(input)),
  get: publicProcedure
    .input(GetPaymentByOrderIdInput)
    .query(({ input }) => getPaymentByOrderId(input)),
});
