import { router } from './context';
import { orderRouter } from './order/routes';

export const appRouter = router({
  order: orderRouter,
});
