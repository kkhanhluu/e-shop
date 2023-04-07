---
title: Event sourcing
slug: event-sourcing
sidebar_position: 2
---

# Event sourcing

## Context and problem

Sometimes, we don't just want to see current state of data, but also want to know how and what happened to our data. In a traditional database, only current state of entity is stored, the business operation context is usually lost or sometimes stored elsewhere.

## Conceptual overview

Event sourcing is pattern for storing data as events.

In context of event sourcing, `event` represents a fact that took place within your business. Every change made is stored in event database. An entity's current state can be created by replaying all the events in order of occurrence.

Here's an [example](https://www.eventstore.com/event-sourcing) of events in an event sourcing system
![event sourcing example](/img/event-sourcing-example.png)

### Projection

`Projection` provides a view of the underlying event-based data model. Often they represent the logic of translating the source write model into read model. A common scenario is taking events created in write model (OrderCreated, OrderAllocated,...) and calculating a read model view (Order object containing total amount, user purchased order and list of product items in the order). This type of object can be stored in different database and used for queries.

### Subscription

It's a similar concept to `Change Data Capture` in relational databases. Each event inserted to database can trigger notification and subscribers can listen to those notifications. Some cases where this feature can be useful:

- Run a projection to update read model
- Forward the event to message queues or external services.

## Code details

Event sourcing pattern can be checked in order service.

### Write model

- In command handler of [CQRS pattern](/docs/explore/code/simplified-cqrs), an event is published to the state machine, which implements the [Saga pattern](/docs/explore/code/saga-pattern)
- When the state of machine is transited to a new state (e.g. from `PAYMENT_PENDING` to `PAID` or `PAYMENT_EXCEPTION`), an action will be fired.
- When an action is fired, events will stored in eventstore database
  ![action event sourcing](/img/event-sourcing-action-saga.png)

### Read model

- Read models are rebuilt with [subscribe to $all stream](https://developers.eventstore.com/clients/grpc/subscriptions.html#subscribing-to-all) feature of EventStoreDB. The subscription code can be found under `order-service/src/main/java/eshop/orderservice/core/subscription/EventStoreDBSubscriptionToAll.java`
- EventStoreDB subscription forwards event to [Spring Boot Application Event Publisher](https://reflectoring.io/spring-boot-application-events-explained/)
- The `projection` will listen to those spring boot events and store projection data into Postgres table by using `Spring data JPA`. The `projection` code can be found under `order-service/src/main/java/eshop/orderservice/order/projection/OrderProjection.java`. Here is example of an event listener in projection
  ![event-sourcing-projection](/img/event-sourcing-projection.png)
