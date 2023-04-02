-- CreateTable
CREATE TABLE "clients" (
    "id" UUID NOT NULL,
    "auth_method" VARCHAR(255),
    "client_id" VARCHAR(255),
    "grant_types" TEXT[],
    "redirect_uri" VARCHAR(255),
    "scopes" TEXT[],
    "secret" VARCHAR(255),

    CONSTRAINT "clients_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "users" (
    "id" UUID NOT NULL,
    "created_at" TIMESTAMP(6),
    "email" VARCHAR(255),
    "modified_at" TIMESTAMP(6),
    "password" VARCHAR(255),
    "username" VARCHAR(255),
    "role" VARCHAR(255),

    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);
