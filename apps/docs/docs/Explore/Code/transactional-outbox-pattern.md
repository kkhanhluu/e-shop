---
title: Transactional outbox pattern
slug: transactional-outbox-pattern
sidebar_position: 5
---

# Transactional outbox pattern

## Context and problem

In a microservices system, message/event queues are one of the most popular ways to implement communication between services. In most cases, a service needs to update its own database and then publish the message/events. For example, in the payment service, after receiving a request to create a payment, the service needs to create an entry in the database and then publish a message to the order service notifying that the order has been checked out.

The traditional solution is to use a database transaction to update the database and then, after the transaction is finished, publish the corresponding message to the queue. This approach works well until an error occurs between the transaction and publishing the corresponding message for any reason (i.e., network errors, host failure, etc.). In this case, the database is updated, but other services won't be notified of that change. Therefore, a mechanism is needed to ensure that a service updates the database and sends messages atomically.

## Conceptual overview

If the service needs to update its database, messages/events will be inserted into an `outbox` table as part of local transaction. A separate `message relay` will process this `outbox` table, query the events inserted to database and publish them to message broker. This [diagram](https://microservices.io/patterns/data/transactional-outbox.html) will illustrate the transactional outbox pattern
![outbox-pattern](https://microservices.io/i/patterns/data/ReliablePublication.png)

## Code details

Transactional outbox pattern can be found in `inventory-service`. An outbox object will also be inserted to database as part of local transaction. After that, service'll try to send message. If sending message successfully, the outbox can be safely deleted from database. Otherwise, it should be kept in database for message relay to process later.

![transactional outbox example](/img/transactional-outbox.png)

In this example, message relay is a scheduled task that looks into the `outbox` table at a fixed frequency, try to send those message and finally delete them from the table.

![message relay example](/img/message-relay.png)

This is a very basic example to demonstrate how the `transactional outbox pattern` works. For a more production-grade solution, using [transaction log tailing](https://microservices.io/patterns/data/transaction-log-tailing.html) along with a managed platform like [Debezium](https://debezium.io/) should be considered
