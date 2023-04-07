# E-shop

This project is a practical microservices reference example for demonstrating the basic of Command Query Responsibility Segregation (CQRS), Event Sourcing and Saga pattern. Application consists of loosely coupled components that communicate using Message Queue or gRPC.

## Ideas for the application

This example illustrates several important concepts:

- Decompose a big, monolithic application into microservices.
- Using an event-driven architecture to loose coupling between components, which communicate with each other by events
- Apply transactional outbox pattern to update database and messages/events atomically
- Using event sourcing to store data as event instead of justing storing entity's state in database
- Using Command Query Responsibility Segregation (CQRS) - update requests (commands) and view requests (queries) are handled by separate services.

## Architecture overview

![Eshop architecture](https://kkhanhluu.github.io/e-shop/assets/images/architecture-64ba597d4898075c06b04b8ee331a9d4.png)

Information and details to help you getting to know e-shop from these points of view:

- [Architecture](/docs/tutorial-basics/congratulations)
- [Code](/docs/tutorial-basics/congratulations)

## Tech stack

- Spring boot: Java framework to build web server
- [Spring oauth2 authorization server](https://docs.spring.io/spring-authorization-server/docs/current/reference/html/index.html): Framework that provides implementation of OAuth 2.1 and OpenID specifications.
- [Express.js](https://expressjs.com/): framework to build web server in Node.js
- PostgresSQL: SQL database
- MongoDB: NoSQL database
- [Event store](https://www.eventstore.com/): state-transition database to store events
- RabbitMQ: Message broker
- [gRPC](https://grpc.io/): a high performance framework for Remote Procedure Call (RPC)
- [tRPC](https://trpc.io/): a library to create fully type-safe API with typescript. HTTP is still used under the hood to communicate client and server.
- Kubernetes: container orchestration

## Documentation

For more information, please visit the [documentation site](https://kkhanhluu.github.io/e-shop/).
