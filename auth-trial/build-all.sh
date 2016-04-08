#!/usr/bin/env bash

set -e

cd util;                                              ./gradlew clean build publishToMavenLocal; cd -

cd microservices/core/product-service;                ./gradlew clean build; cd -

cd microservices/support/discovery-server;            ./gradlew clean build; cd -
cd microservices/support/edge-server;                 ./gradlew clean build; cd -

