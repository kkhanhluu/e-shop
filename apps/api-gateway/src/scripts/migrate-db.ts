import { execSync } from 'node:child_process';

function migrateDatabase(database: string) {
  if (process.env.NODE_ENV === 'development') {
    execSync(
      `export DATABASE_URL=postgresql://${process.env.DATABASE_USERNAME}:${process.env.DATABASE_PASSWORD}@${process.env.DATABASE_HOST}:${process.env.DATABASE_PORT}/${database}_service && npx prisma migrate deploy --schema ./apps/api-gateway/src/${database}/prisma/schema.prisma`,
      { stdio: 'inherit' }
    );
  } else {
    execSync(
      `export DATABASE_URL=postgresql://${process.env.DATABASE_USERNAME}:${process.env.DATABASE_PASSWORD}@${process.env.DATABASE_HOST}:${process.env.DATABASE_PORT}/${database}_service && npx prisma migrate deploy --schema ./api-gateway/${database}/prisma/schema.prisma`,
      { stdio: 'inherit' }
    );
  }
}

export function migrateDatabases() {
  migrateDatabase('inventory');
  migrateDatabase('order');
  migrateDatabase('product');
  migrateDatabase('user');
}
