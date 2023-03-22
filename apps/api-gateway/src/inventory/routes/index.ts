import { publicProcedure, router } from '../../context';
import {
  getInventoryByProductId,
  GetInventoryByProductIdInput,
} from '../handlers/get';

export const inventoryRouter = router({
  get: publicProcedure
    .input(GetInventoryByProductIdInput)
    .query(({ input }) => getInventoryByProductId(input)),
});
