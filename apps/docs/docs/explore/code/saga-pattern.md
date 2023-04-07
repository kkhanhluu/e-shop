---
title: Saga pattern
slug: saga-pattern
sidebar_position: 4
---

# Saga pattern

## Context and problem

In context of a microservice system, it's very likely that you have applied [database per service pattern](https://microservices.io/patterns/data/database-per-service.html). Some transactions, however, span multiple services and we need a mechanism to implement such transactions. Since each service owns different database, a local ACID transaction cannot simply be applied.

## Conceptual overview

The saga pattern provides transaction management using a sequence of local transactions. Each local transaction updates its own database and publishes a message or event to trigger the next transaction in the saga. If a local transaction fails, the saga must execute compensating transactions that undo changes that were made by preceding local transactions. This example from [Microsoft](https://learn.microsoft.com/en-us/azure/architecture/reference-architectures/saga/saga) illustrates the pattern
![saga-example](https://learn.microsoft.com/en-us/azure/architecture/reference-architectures/saga/images/saga-overview.png)

### Choreography

`Choreography` is a way to coordinate sagas `without` a centralized point of control. Each service is aware of the next saga participant after updating its own database. Here's an example of `choreography saga` taken from [Microsoft](https://learn.microsoft.com/en-us/azure/architecture/reference-architectures/saga/saga)
![saga-choreography](https://learn.microsoft.com/en-us/azure/architecture/reference-architectures/saga/images/choreography-pattern.png)

### Orchestration

`Orchestration` is a way to coordinate sagas `with` a centralized point of control. An orchestrator (object) tells the participant what local transactions to execute. Here's an example of `orchestration saga` taken from [Microsoft](https://learn.microsoft.com/en-us/azure/architecture/reference-architectures/saga/saga)
![saga-choreography](https://learn.microsoft.com/en-us/azure/architecture/reference-architectures/saga/images/orchestrator.png)

## Code details

Saga pattern can be checked in order service. In this example application, `orchestration` was chosen to implement the saga pattern. The orchestrator object is a [Spring state machine](https://docs.spring.io/spring-statemachine/docs/current/reference/) which leverages traditional [finite-state machine concepts in computer science](https://en.wikipedia.org/wiki/Finite-state_machine). This diagram illustrates states, state transitions and actions of the state machine.

![state-machine](/img/state-machine.png)
States that are decorated with double circles are start and end states. The arrow illustrate the state transition. There's an action, that will be fired, when the state is changed.

Here is the state machine configuration

![state machine configuration](/img/sm-configuration.png)

And here is the definition for state machine actions

![state machine actions](/img/sm-actions.png)

### Compensating logic

An example for compensating logic can be found in `ValidateOrderFailedAction.java`, where we want to send a message to payment service and revert the payment that user has already checked out
