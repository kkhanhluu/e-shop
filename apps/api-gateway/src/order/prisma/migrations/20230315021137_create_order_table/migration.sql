-- CreateTable
CREATE TABLE "order_line" (
    "id" UUID NOT NULL,
    "product_id" UUID,
    "product_price" DECIMAL(38,2),
    "quantity" INTEGER,
    "order_id" UUID NOT NULL,

    CONSTRAINT "order_line_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "orders" (
    "id" UUID NOT NULL,
    "created_date" TIMESTAMP(6),
    "last_modified_date" TIMESTAMP(6),
    "last_processed_position" BIGINT NOT NULL,
    "status" VARCHAR(255),
    "user_id" UUID,

    CONSTRAINT "orders_pkey" PRIMARY KEY ("id")
);

-- AddForeignKey
ALTER TABLE "order_line" ADD CONSTRAINT "fkk9f9t1tmkbq5w27u8rrjbxxg6" FOREIGN KEY ("order_id") REFERENCES "orders"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
