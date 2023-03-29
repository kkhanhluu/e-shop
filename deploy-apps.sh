#!/bin/bash

kubectl apply -f ./k8s/templates/postgres/deployment.yaml
kubectl apply -f ./k8s/templates/postgres/service.yaml
kubectl apply -f ./k8s/templates/postgres/nodePort.yaml

kubectl apply -f ./k8s/templates/mongo/deployment.yaml
kubectl apply -f ./k8s/templates/mongo/service.yaml

kubectl apply -f ./k8s/templates/eventstore/deployment.yaml
kubectl apply -f ./k8s/templates/eventstore/service.yaml

kubectl apply -f ./k8s/templates/rabbitmq/deployment.yaml
kubectl apply -f ./k8s/templates/rabbitmq/service.yaml

kubectl apply -f ./k8s/templates/order-service/service.yaml
kubectl apply -f ./k8s/templates/order-service/deployment.yaml

kubectl apply -f ./k8s/templates/payment-service/service.yaml
kubectl apply -f ./k8s/templates/payment-service/deployment.yaml

kubectl apply -f ./k8s/templates/product-service/service.yaml
kubectl apply -f ./k8s/templates/product-service/deployment.yaml

kubectl apply -f ./k8s/templates/review-service/service.yaml
kubectl apply -f ./k8s/templates/review-service/deployment.yaml

kubectl apply -f ./k8s/templates/user-service/service.yaml
kubectl apply -f ./k8s/templates/user-service/deployment.yaml

kubectl apply -f ./k8s/templates/inventory-service/service.yaml
kubectl apply -f ./k8s/templates/inventory-service/deployment.yaml