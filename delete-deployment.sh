#!/bin/bash

kubectl delete deploy/order-service-deployment
kubectl delete service/order-service-svc

kubectl delete deploy/review-service-deployment
kubectl delete service/review-service-cluster-ip-service

kubectl delete deploy/user-service-deployment
kubectl delete service/user-service-cluster-ip-service

kubectl delete deploy/payment-service-deployment
kubectl delete service/payment-service-cluster-ip-service

kubectl delete deploy/product-service-deployment
kubectl delete service/product-service-cluster-ip-service

kubectl delete deploy/inventory-service-deployment
kubectl delete service/inventory-service-cluster-ip-service

kubectl delete deploy/api-gateway-deployment
kubectl delete service/api-gateway-cluster-ip-service

if [ "$1" = "all" ]; then
	kubectl delete deploy/mongo-deployment
	kubectl delete service/mongo-cluster-ip-service

	kubectl delete deploy/postgres-deployment
	kubectl delete service/postgres-cluster-ip-service

	kubectl delete deploy/rabbitmq-deployment
	kubectl delete service/rabbitmq-service

	kubectl delete statefulset eventstore-stateful-set
	kubectl delete service/eventstore-cluster-ip-service
else
	echo "mysql, redis and other services may still be running"
fi