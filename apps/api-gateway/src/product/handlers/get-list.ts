import { z } from 'zod';
import {
  GetProductsRequest,
  GetProductsResponse,
} from '../../../generated/proto/ProductService';
import { getInventoryByProductId } from '../../inventory/handlers/get';
import { productServiceClient } from '../clients/grpc';

export const GetProductsInput = z.object({
  page: z.number().min(0),
  size: z.number().min(1),
});

export async function getPaginatedListOfProducts(
  input: z.infer<typeof GetProductsInput>
) {
  const { products, ...rest } = await getProducts(input.page, input.size);
  const productsResponse = await Promise.all(
    products.map(async (product) => {
      const { quantityOnHand } = await getInventoryByProductId({
        productId: product.id,
      });
      return {
        ...product,
        quantityOnHand,
      };
    })
  );

  return {
    products: productsResponse,
    ...rest,
  };
}

function getProducts(page: number, size: number): Promise<GetProductsResponse> {
  return new Promise((resolve, reject) => {
    const request = GetProductsRequest.create({ page, size });
    productServiceClient.getProducts(request, (error, response) => {
      if (error) {
        console.error(error);
        reject(error);
      } else {
        resolve(response);
      }
    });
  });
}
