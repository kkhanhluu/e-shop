---
sidebar_position: 2
title: Getting started
slug: getting-started
---

# Getting started

## Software requirements

- Docker desktop
- Local kubernetes cluster (i.e minikube)
- Java 18 (Optional)
- Node.js 18 (Optional)

## How to run

- Ensure that docker is running.
- Ensure that a local kubernetes cluster is running. Recommended config for the cluster is: 8 cpu and 8192 memory
- Give execute permission for `deploy-apps.sh` file by running

```
chmod +x ./deploy-apps.sh
```

- Deploy kubernetes cluster by running this command

```
./deploy-apps.sh all
```

- If you don't want to re-deploy infrastructure services: postgres, mongodb, eventstore and rabbitmq, just run this command

```
./deploy-apps.sh all
```

- Verify that all pods are running well by running `kubectl get all`
  ![k8s status](/img/k8s-status.png)

## Get the access token to call authorized api endpoints

### Register an user

- Get the url to the `user-service NodePort` by running `minikube service user-service-cluster-ip-service`
- Send a post request to create an user

```
curl -d '{"username":"test", "email":"test@gmail.com", "password": "test", "role": "USER"}' -H "Content-Type: application/json" -X POST <url-to-user-service-node-port>
```

### Sign the user in

These steps will illustrate the process to get `access_token` with `Oauth 2.0 authorization code flow`

- Go to browser and go to this url `<url-to-service>/oauth2/authorize?response_type=code&client_id=eshop&scope=openid&redirect_uri=https://springone.io/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256`. Make sure that `client_id` and `redirect_uri` are same as the example because a client with the redirect uri was created in database while `api-gateway` is deployed.
- You'll be redirected to the login page. Type the username and password for the user.
- After login successfully, you'll be redirected to the `springone.io` page with the url like this.
  ![k8s status](/img/login.png)
- Copy the `code` from the url
- send a post request to authorization server with a basic auth header `username: eshop, password: eshop-secret` to log in

```
curl --location --request POST '<url-to-service>/oauth2/token?client_id=eshop&redirect_uri=https%3A%2F%2Fspringone.io%2Fauthorized&grant_type=authorization_code&code=<authorization-code-from-previous-step>' \
--header 'Authorization: Basic ZXNob3A6ZXNob3Atc2VjcmV0' \
--header 'Cookie: csrf_token_806060ca5bf70dff3caa0e5c860002aade9d470a5a4dce73bcfa7ba10778f481=eZE6/yLRPQSXhOvYA3KqlQDSSmKq+dDxQHFf/HkSt04='
```

- Here is an example of a login request

```
{
    "access_token": "",
    "scope": "openid",
    "id_token": "",
    "token_type": "Bearer",
    "expires_in": 86399
}
```

- If you decode the access token with [jwt.io](https://jwt.io/), here is an example of payload data

```
{
  "sub": "test@gmail.com",
  "aud": "eshop",
  "nbf": 1680653930,
  "role": "ADMIN",
  "scope": [
    "openid"
  ],
  "iss": "http://localhost:8080",
  "name": "kkhanhluu",
  "id": "90b5e3cf-fe53-46c7-9df2-494ffc6f27b7",
  "exp": 1680740330,
  "iat": 1680653930,
  "email": "test@gmail.com"
}
```

## Call request to the API Gateway

- Get the url to the `api-gateway` by running `minikube service api-gateway-svc`
- Go to `http://<api-gateway-url>/trpc-playground` on browser, you can find a playground to send request to the gateway
- Here is an example request to get list of products
  ![tRPC](/img/trpc.png)
- For the endpoints that need authentication (i.e: create order), you need to send the header `Authorization: Bearer <access-token>` along with the request.
