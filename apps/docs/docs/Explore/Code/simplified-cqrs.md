---
title: Simplified CQRS
slug: simplified-cqrs
sidebar_position: 3
---

# Simplified CQRS

## Context and problem

In complex applications, there is often a mismatch between the read and write presentations of the data. On the read side, the application may perform different queries to return different data transfer objects (DTOs). On the write side, the model may implement complex validation and business logic. As a result, it can end up with a very complex data model.

## Conceptual overview of CQRS

The Command and Query Responsibility Segregation (CQRS) pattern can be a solution to the above problem. In simple terms, we separate reads and writes using `commands` and `queries`.

- `Commands` are responsible for changing the application state, i.e., creating, updating, and deleting data.
- `Queries` are responsible for reading the application state.

When handling `commands`, the application model is usually represented by an `aggregate`. An `aggregate` is a cluster of associated objects that we treat as a unit for the purpose of data changes. We can take an order as an example. An order is a domain object that consists of several objects: the status, the list of items that the user already purchased, and the user who purchased the order. In this example, the invoice is the `aggregate`. An `aggregate` is also a consistency guard. It takes the current state, verifies rules for the particular operation, applies the business logic, and returns the new state.

## Code details

The CQRS pattern can be checked in Order Service.

![cqrs commands](/img/cqrs-commands.png)

Commands are read-only DTOs that contain all the data required to execute the operation.

Each command has a specific command handler that is responsible for executing the operations intended for the command.

![cqrs command handlers](/img/cqrs-command-handler.png)

On the other hand, queries just return whatever the client needs, which could be a domain object.

![cqrs entities](/img/cqrs-entities.png)
