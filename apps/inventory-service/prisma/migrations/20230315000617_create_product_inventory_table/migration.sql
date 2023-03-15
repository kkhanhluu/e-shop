-- CreateTable
CREATE TABLE "product_inventory" (
    "id" UUID NOT NULL,
    "product_id" UUID,
    "quantity_on_hand" INTEGER,

    CONSTRAINT "product_inventory_pkey" PRIMARY KEY ("id")
);