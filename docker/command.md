docker compose down -v
docker stop squid-db
docker rm squid-db
docker rm squid-statistic

docker login -u <USERNAME> -p <PASSWORD> nexus.csir.reb:8282
docker tag 62b0d19b3c65 nexus.csir.reb:8282/squid/squid-statistic:0.2
docker push nexus.csir.reb:8282/squid/squid-statistic:0.2
