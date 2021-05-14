# AirQuality APP

## Description

This project relies on the development of a web app where it is possible to search air metrics of some citys in the world. In my project, and with the external API I used, I was able to let search by city, and latitude and longitude.
In this project there is also the objective of doing an API to contact with the WebApp and this API must have a cache to store data. 
Last but not least, it should have all the sorts of tests.

## Developed by: Ricardo Cruz (93118)

## Notes

To run API, intellij start\n
To run react app, do yarn start\n

Link to access sonarqube, go to localhost:9000 after starting the docker container\n
To run unit and integration tests, do mvn clean verify (react app must be running)\n
To update sonarQube: mvn sonar:sonar   -Dsonar.projectKey=AirQuality   -Dsonar.host.url=http://127.0.0.1:9000   -Dsonar.login=49527d0a4a73e2f4aa85773611abe35eacb65f60 \n

Link to external API used: https://aqicn.org/map/world/ \n
Link to access Swagger: http://localhost:8080/swagger-ui.html \n
Link to SonarCloud: https://sonarcloud.io/dashboard?id=ricardocruz29_TQS \n
Demonstration video: https://youtu.be/bbuCP-OM2u0 \n

