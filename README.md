# cadabra-shop

Cadabra company is running a campaign for selecting a weekly winner of a Gift card for their online shoppers.

This service
- uses Spring Batch Framework to import user information and spending and to select lottery participants and winners
- uses in-memory H2 database to store imported data

## Requirements

- Java 21
- Apache Maven 3.9.5+
- Docker 

## Repository Structure

- [cadabra-shop-api](cadabra-shop-api)
- [cadabra-shop-db](cadabra-shop-db)
- [cadabra-shop-impl](cadabra-shop-impl)


ToDo: add module descriptions

## How to build and run the service

To build the service execute the followings commands from the project root directory:

```
mvn clean package
```

```
docker build -f Dockerfile -t cadabra-shop .
```

To run the service:
```
docker run -p 8080:8080 cadabra-shop
```

## Batch jobs

1. `importUsersJob`
   Gets UserDto objects from the API, converts, and saves to the DB.
2. `importUserSpendingJob`
   Gets UserSpendingDto objects from the csv-file, converts, and saves to the DB.
3. `selectLotteryParticipantsJob`
   Gets UserSpendingEntity objects from the DB, selects a winner randomly, builds LotteryParticipantDto objects, converts, and saves to the DB.

## How to run Batch jobs

Use `curl` (or `postman` etc.) to send http-requests:

```
curl -X POST localhost:8080/batch/job/startUsersImport
```

```
curl -X POST localhost:8080/batch/job/startUserSpendingImport
```

```
curl -X POST localhost:8080/batch/job/startLotteryParticipantsSelection
```

## How to check the winner

Use `curl` (or `postman` etc.) to send http-requests:

```
curl localhost:8080/lottery-participants
```

```
curl localhost:8080/lottery-participants/winners
```

Or check the winner via H2 Database console

## H2 Database console

H2 Database console is accessible from the following URL:
```
http://localhost:8080/h2-console/login.jsp
```