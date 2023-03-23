import { z } from 'zod';
import {
  GetProductByIdRequest,
  Product,
} from '../../../generated/proto/ProductService';
import { getInventoryByProductId } from '../../inventory/handlers/get';
import { productServiceClient } from '../clients/grpc';

export const GetProductByIdInput = z.string().uuid();

export async function getProductById(
  input: z.infer<typeof GetProductByIdInput>
) {
  const product = await getProductFromProductService(input);
  const { quantityOnHand } = await getInventoryByProductId({
    productId: product.id,
  });
  return {
    ...product,
    quantityOnHand,
  };
}

function getProductFromProductService(productId: string): Promise<Product> {
  return new Promise((resolve, reject) => {
    const request = GetProductByIdRequest.create({ productId });
    productServiceClient.getProductById(request, (error, response) => {
      if (error) {
        console.error(error);
        reject(error);
      } else {
        resolve(response);
      }
    });
  });
}
