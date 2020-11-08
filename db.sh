docker run --ulimit memlock=-1:-1 -it -d --rm=true --memory-swappiness=0 --name orderdb -e POSTGRES_USER=eventos-da-hora -e POSTGRES_PASSWORD=eventos-da-hora -e POSTGRES_DB=orderdb -p 5435:5432 postgres:10.5

