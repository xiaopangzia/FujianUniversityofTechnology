#!/usr/bin/bash
mvn -Dmaven.test.skip=true clean package -U
docker build -t xiaopangzia/config .
docker push xiaopangzia/config