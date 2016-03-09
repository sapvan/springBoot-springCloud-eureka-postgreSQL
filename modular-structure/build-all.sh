#!/usr/bin/env bash

cd microservices/core/product-service;                ./gradlew clean publishToMavenLocal; cd -
cd microservices/core/recommendation-service;         ./gradlew clean publishToMavenLocal; cd -
cd microservices/core/review-service;                 ./gradlew clean publishToMavenLocal; cd -
cd microservices/composite/product-composite-service; ./gradlew clean build; cd -
cd microservices/api/product-api-service;             ./gradlew clean build; cd -

cd microservices/support/auth-server;                 ./gradlew clean build; cd -
cd microservices/support/discovery-server;            ./gradlew clean build; cd -
cd microservices/support/edge-server;                 ./gradlew clean build; cd -
cd microservices/support/monitor-dashboard;           ./gradlew clean build; cd -
cd microservices/support/turbine;                     ./gradlew clean build; cd -
