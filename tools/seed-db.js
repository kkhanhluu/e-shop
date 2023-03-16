const { Client } = require('pg');
const { faker } = require('@faker-js/faker');
const { MongoClient } = require('mongodb');

const CONFIG = {
  host: 'localhost',
  port: 5432,
  user: 'postgres',
  password: 'postgres',
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
const reviewClient = new MongoClient(
  'mongodb://mongo:mongo@localhost:27017?authMechanism=DEFAULT'
);

async function main() {
  await productClient.connect();
  await inventoryClient.connect();
  await userClient.connect();
  await reviewClient.connect();

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
}
main()
  .then(() => console.log('Done'))
  .catch((e) => console.log('Error', e))
  .finally(async () => {
    await productClient.end();
    await userClient.end();
    await inventoryClient.end();
    await reviewClient.close();
  });
