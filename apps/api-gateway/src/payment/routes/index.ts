import { router } from '../../context';
import { authenticatedProcedure } from '../../middleware/authenticated';
import { createPayment, CreatePaymentInput } from '../handlers/create';
import { getPaymentByOrderId, GetPaymentByOrderIdInput } from '../handlers/get';

export const paymentRouter = router({
  create: authenticatedProcedure
    .input(CreatePaymentInput)
    .query(({ input }) => createPayment(input)),
  get: authenticatedProcedure
    .input(GetPaymentByOrderIdInput)
    .query(({ input }) => getPaymentByOrderId(input)),
});
