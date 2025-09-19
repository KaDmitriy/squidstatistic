docker compose down -v
docker stop squid-db
docker rm squid-db
docker rm squid-statistic

docker compose -f docker-compose-dev.yaml  build
docker login -u <USERNAME> -p <PASSWORD> nexus.csir.reb:8282
docker tag 62b0d19b3c65 nexus.csir.reb:8282/squid/squid-statistic:0.2
docker push nexus.csir.reb:8282/squid/squid-statistic:0.2

curl -X POST -F "file=@/home/kda/workspace/squid/data/access_6000.log" http://localhost:9007/uploadaccess?node=1
