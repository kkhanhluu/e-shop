---
title: Architecture Overview
slug: architecture-overview
sidebar_position: 1
---

# Overview

![Eshop architecture](/img/architecture.png)

The architecture proposes a microservice architecture implementation with multiple microservices (each one owning its own database). The microservices also showcase different approaches from simple CRUD to CQRS patterns. gRPC is the communication means between API gateway and services, and asynchronous message based communication between microservices.

## Message queue

In this example, RabbitMQ was chosen as the implementation for message queue. There's 1 topic exchange and 5 different queues.
![Rabbitmq architecture](/img/rabbitmq-architecture.png)

## gRPC

Most communications between microservices are decoupled using message queue. However, the communication between API Gateway and the internal services is implemented with gRPC. gRPC is a modern high performance Remote Procedure Call framework that has low bandwidth usage, making it a good candidate for internal microservices communication

## API Gateway

The API gateway is currently implemented with tRPC, a library helps to build type-safe API by defining type safe functions with typescript. The client application can call these functions directly on the inferred type safe router. HTTP is still used under the hood to communicate between client and server.

Currently, the API gateway only performs request forwarding to internal microservices and custom aggregators, giving the client a single endpoint to communicate with. For a more production-grade solution, the pattern [Backens for Frontend (BFF)](https://samnewman.io/patterns/architectural/bff/) should be considered to be implemented, to publish simplified APIs for different clients.

## Microservices

6 microservices are implemented with Java spring. The API gateway is implemented with Node.js. The reason to choose 2 different programming languages is to illustrate the advantage of microservices which allows services to be implemented, maintained easily, independently with different technologies.

## Database

In order to simplify the development, there are 1 Postgres database server with multiple databases, 1 event store server and 1 MongoDB server are all deployed to a single cluster. However, this is not recommended approach for a production-grade solution.
