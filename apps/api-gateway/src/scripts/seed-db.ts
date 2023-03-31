import { Client } from 'pg';
import { faker } from '@faker-js/faker';
import { MongoClient } from 'mongodb';

const CONFIG = {
  host: process.env.DATABASE_HOST,
  port: Number.parseInt(process.env.DATABASE_PORT as string),
  user: process.env.DATABASE_USERNAME,
  password: process.env.DATABASE_PASSWORD,
};

const productClient = new Client({
  ...CONFIG,
  database: 'product_service',
});
const userClient = new Client({
  ...CONFIG,
  database: 'user_service',
});
const inventoryClient = new Client({
  ...CONFIG,
  database: 'inventory_service',
});
const reviewClient = new MongoClient(process.env.MONGODB_URI as string);

export async function seedDB() {
  try {
    await productClient.connect();
    await inventoryClient.connect();
    await userClient.connect();
    await reviewClient.connect();

    const result = await productClient.query({
      text: 'SELECT * from public.product',
    });
    if (result.rowCount > 0) {
      return;
    }

    const products = (
      await Promise.all(
        new Array(10).fill(null).map(() =>
          productClient.query({
            text: 'INSERT INTO public.product (id, description, name, price) VALUES ($1, $2, $3, $4) RETURNING *',
            values: [
              faker.datatype.uuid(),
              faker.commerce.productDescription(),
              faker.commerce.productName(),
              faker.commerce.price(5, 50),
            ],
          })
        )
      )
    ).flatMap((result) => result.rows);

    await Promise.all(
      products.map((product) =>
        inventoryClient.query({
          text: 'INSERT INTO public.product_inventory (id, product_id, quantity_on_hand) VALUES ($1, $2, $3) RETURNING *',
          values: [
            faker.datatype.uuid(),
            product.id,
            faker.datatype.number({ min: 10, max: 100 }),
          ],
        })
      )
    );

    const reviewDb = reviewClient.db('review-service');
    const reviewCollection = reviewDb.collection('reviews');
    await reviewCollection.insertMany(
      products
        .map((product) =>
          new Array(10).fill(null).map(() => ({
            productId: product.id,
            text: faker.lorem.paragraph(5),
            rate: faker.datatype.number({ min: 1, max: 5, precision: 1 }),
            createdAt: faker.date.past(),
          }))
        )
        .flat()
    );
    console.log('Database was successfully seeded 🌱');
  } catch (error) {
    console.error(`Cannot seed database: ${error}`);
    throw error;
  } finally {
    await cleanUp();
  }
}

async function cleanUp() {
  await productClient.end();
  await userClient.end();
  await inventoryClient.end();
  await reviewClient.close();
}
