import { PrismaClient } from '@prisma/client';
import { faker } from '@faker-js/faker';

const prisma = new PrismaClient({
  datasources: {
    db: {
      url: 'postgresql://postgres:postgres@localhost:5432/inventory_service',
    },
  },
  log: ['query'],
});

prisma.$on('query', (e) => {
  let str = e.query;
  JSON.parse(e.params).forEach((value, index) => {
    str = str.replace(
      `$${index + 1}`,
      typeof value === 'string' ? `"${value}"` : value
    );
  });
  console.log(str);
});

function main() {
  return prisma.product_inventory.createMany({
    data: new Array(10).fill(null).map(() => ({
      id: faker.datatype.uuid(),
      product_id: faker.datatype.uuid(),
      quantity_on_hand: faker.datatype.number({ min: 50, max: 100 }),
    })),
  });
}

main()
  .then(async () => {
    await prisma.$disconnect();
  })
  .catch(async (e) => {
    console.error(e);
    await prisma.$disconnect();
    process.exit(1);
  });
