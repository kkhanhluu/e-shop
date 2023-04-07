---
title: Simplified CQRS
slug: simplified-cqrs
sidebar_position: 3
---

# Simplified CQRS

## Context and problem

In complex applications, there's often a mismatch between the read and write presentations of the data. On the read side, the application may perform different queries to return different data transfer objects (DTO). On the write side, the model may implement complex validation and business logic. As a result, it can end up with very complex data model.

## Conceptual overview of CQRS

Command and Query Responsibility Segregation (CQRS) pattern can be a solution for the above problem. In simple terms, we separate reads and writes using `commands` and `queries`

- `Commands` are responsible for changing application state, i.e. creating, updating and deleting data.
- `Queries` are responsible for reading the application state.

When handling `commands`, the application model is usually represented by `aggregate`. An `aggregate` is a cluster of associated objects that we treat as a unit for the purpose of data changes. We can take order as an example. An order is a domain object, which consists of several objects: the status, the list of items that user already purchased and the user who purchased the order. In this example, invoice is the `aggregate`. `Aggregate` is also consistency guards. They take current state, verify rules for the particular operation, apply the business logic and return the new state.

## Code details

The CQRS pattern can be checked in Order Service.

![cqrs commands](/img/cqrs-commands.png)

Commands are read only DTO that contain all data that's required to execute the operation

Each command has a specific command handler that's responsible for executing the operations intended for the command

![cqrs command handlers](/img/cqrs-command-handler.png)

Query, on the other hand, just return whatever the client needs, could be a domain object

![cqrs entities](/img/cqrs-entities.png)
