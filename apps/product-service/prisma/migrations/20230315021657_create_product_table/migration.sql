-- CreateTable
CREATE TABLE "product" (
    "id" UUID NOT NULL,
    "description" VARCHAR(255),
    "name" VARCHAR(255) NOT NULL,
    "price" DECIMAL(38,2),

    CONSTRAINT "product_pkey" PRIMARY KEY ("id")
);