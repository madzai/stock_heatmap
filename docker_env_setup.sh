# ! Step 1: Stop down all docker container
docker compose stop heatmap-ui-app stock-data-app frontend-app

# ! Step 2: Remove old container if any
docker rm stock-data-app
docker rm heatmap-ui-app
docker rm frontend-app

# ! Step 3: maven install and docker build
cd java-stock-data
mvn clean install -DskipTests
docker build -t stock-data:0.0.1 -f Dockerfile .
cd ..
cd java-heatmap-ui
mvn clean install -DskipTests
docker build -t heatmap-ui:0.0.1 -f Dockerfile .
cd ..
cd react
docker build -t react:0.0.1 -f Dockerfile .
cd ..

# ! Step 4: docker run (docker-compose is shortcut for docker run)
docker compose up -d