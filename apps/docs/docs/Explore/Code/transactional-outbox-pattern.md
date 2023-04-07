---
title: Transactional outbox pattern
slug: transactional-outbox-pattern
sidebar_position: 5
---

# Transactional outbox pattern

## Context and problem

In a microservices system, message/event queues is one of the most popular ways to implement the communication between services. In most of cases, a service typically needs to update its own database and then publish the message/events. For example, in the payment service, after receiving request to create a payment, the service needs to create entry in database and then publish message to order service notifying that, this order has been checked out.

The traditional solution is: using database transaction to update database and then after transaction finished, a message is published to queue. This approach works well until an error occurs between transaction and publishing the corresponding message for any reasons (i.e. network errors, host failure,..). In this case, the database is updated but other services won't be notified by that change. Therefore, a mechanism is needed to ensure that a service update the database and send messages atomically.

## Conceptual overview

If the service needs to update its database, messages/events will be inserted into an `outbox` table as part of local transaction. A separate `message relay` will process this `outbox` table, query the events inserted to database and publish them to message broker. This [diagram](https://microservices.io/patterns/data/transactional-outbox.html) will illustrate the transactional outbox pattern
![outbox-pattern](https://microservices.io/i/patterns/data/ReliablePublication.png)

## Code details

Transactional outbox pattern can be found in `inventory-service`. An outbox object will also be inserted to database as part of local transaction. After that, service'll try to send message. If sending message successfully, the outbox can be safely deleted from database. Otherwise, it should be kept in database for message relay to process later.

![transactional outbox example](/img/transactional-outbox.png)

In this example, message relay is a scheduled task that looks into the `outbox` table at a fixed frequency, try to send those message and finally delete them from the table.

![message relay example](/img/message-relay.png)

This is a very basic example to demonstrate how the `transactional outbox pattern` works. For a more production-grade solution, using [transaction log tailing](https://microservices.io/patterns/data/transaction-log-tailing.html) along with a managed platform like [Debezium](https://debezium.io/) should be considered
