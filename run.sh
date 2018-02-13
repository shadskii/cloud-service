#!/bin/bash
# Bash script to start this web service
rm -rf run

cp haproxy.cfg /etc/haproxy/haproxy.cfg

# Start mongo
sudo service mongod start

# Build jars
./gradlew clean lines-loader:bootRepackage lines-service:bootRepackage
mkdir run
mv lines-loader/build/libs/lines-loader-1.0.jar run/lines-loader.jar
mv lines-service/build/libs/lines-service-1.0.jar run/lines-service.jar

# Execute
java -jar run/lines-loader.jar $1
java -jar run/lines-service.jar 9000 &
java -jar run/lines-service.jar 9001 &
java -jar run/lines-service.jar 9002 &

# Start proxy
service haproxy start