#!/usr/bin/bash
mvn -Dmaven.test.skip=true clean package -U
docker build -t xiaopangzia/eureka .
docker push xiaopangzia/eureka