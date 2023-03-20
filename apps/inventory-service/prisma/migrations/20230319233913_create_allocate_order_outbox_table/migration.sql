-- CreateTable
CREATE TABLE "allocate_order_outbox" (
    "id" UUID NOT NULL,
    "order_id" UUID,
    "is_successful" BOOLEAN,

    CONSTRAINT "allocate_order_outbox_pkey" PRIMARY KEY ("id")
);
