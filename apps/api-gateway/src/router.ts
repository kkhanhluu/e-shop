import { router } from './context';
import { orderRouter } from './order/routes';
import { paymentRouter } from './payment/routes';

export const appRouter = router({
  order: orderRouter,
  payment: paymentRouter,
});
