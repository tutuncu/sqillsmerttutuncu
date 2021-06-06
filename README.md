# Sqillsmerttutuncu project

Purpose of this project is to introduce an endpoint to receive a string and process that string according to business rules.


## Testing
A postman collection is available in root folder for testing purposes.


In case you wouldn't like to use postman, simply ;

>Send a request to http://localhost:8080/api with Content-Type as application/json and body as follows:

```shell script
{
    "inputStr":"com.SQILLS.assignment an.ot8er        Sample.1nput-Str"
}
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw clean package -Dquarkus.container-image.build=true
```
After packaging the application, it can be run using:
```shell script
docker-compose up
```
2021, Mert Tutuncu
