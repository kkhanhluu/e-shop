---
title: Architecture Overview
slug: architecture-overview
sidebar_position: 1
---

# Overview

![Eshop architecture](/img/architecture.png)

The architecture proposes a microservice implementation with multiple microservices, each one having its own database. The microservices showcase different approaches, ranging from simple CRUD to CQRS patterns. The communication between the API gateway and services is established using gRPC, while an asynchronous, message-based communication is used between microservices.

## Message queue

In this example, RabbitMQ was chosen as the message queue implementation, with one topic exchange and five different queues. Here's the RabbitMQ architecture:
![Rabbitmq architecture](/img/rabbitmq-architecture.png)

## gRPC

Most communications between microservices are decoupled using a message queue. However, the communication between the API Gateway and the internal services is implemented with gRPC. gRPC is a modern high-performance Remote Procedure Call framework that uses low bandwidth, making it a good candidate for internal microservices communication.

## API Gateway

The API gateway is currently implemented with tRPC, a library that helps build type-safe APIs by defining type-safe functions with TypeScript. The client application can directly call these functions on the inferred type-safe router. HTTP is still used under the hood to communicate between the client and server.

Currently, the API gateway only forwards requests to internal microservices and custom aggregators, giving the client a single endpoint to communicate with. For a more production-grade solution, the [Backens for Frontend (BFF)](https://samnewman.io/patterns/architectural/bff/) pattern should be considered, which publishes simplified APIs for different clients.

## Microservices

6 microservices are implemented with Java spring. The API gateway is implemented with Node.js. The reason to choose 2 different programming languages is to illustrate the advantage of microservices which allows services to be implemented, maintained easily, independently with different technologies.

## Database

In order to simplify development, one Postgres database server with multiple databases, one event store server, and one MongoDB server are all deployed to a single cluster. However, this is not a recommended approach for a production-grade solution.
