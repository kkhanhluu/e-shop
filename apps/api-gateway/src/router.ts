import { router } from './context';
import { inventoryRouter } from './inventory/routes';
import { orderRouter } from './order/routes';
import { paymentRouter } from './payment/routes';

export const appRouter = router({
  order: orderRouter,
  payment: paymentRouter,
  inventory: inventoryRouter,
});
