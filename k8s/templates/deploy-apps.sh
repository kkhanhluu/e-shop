#!/bin/bash

kubectl apply -f ./k8s/templates/postgres/deployment.yaml
kubectl apply -f ./k8s/templates/postgres/service.yaml
kubectl apply -f ./k8s/templates/postgres/nodePort.yaml

kubectl apply -f ./k8s/templates/mongo/deployment.yaml
kubectl apply -f ./k8s/templates/mongo/service.yaml

kubectl apply -f ./k8s/templates/eventstore/deployment.yaml
kubectl apply -f ./k8s/templates/eventstore/service.yaml