#Telenor Interview Project

* Project technologies
** Maven: Apache Maven 3.6.3
** Java: 1.8.0_202, vendor: AdoptOpenJdk
** Docker: version 19.03.8, build afacb8b
** IntelliJ Idea 2018.3.5
** Spring boot 2.3.3
** Liquibase 3.8.0

* Maven Build
Run "mvn clean install" to build the complete project with tests
or
"mvn clean install -DskipTests" without tests

mvnw command also can be used instead of mvn from the project root

* Docker build and run
** Run Docker build "docker build -t se.telenor.products:1.00 ." to create 0docker image
** Run docker image "docker run --publish 8239:8080 --detach --name telenor.products se.telenor.products:1.00"

RestAPI Server will run on port 8329.

* Project source available on GitHub
** https://github.com/nemethsamusandor/telenor

* Terminal commands
Open "http://localhost:8329/product" with the requires query parameters in Browser, Postman, Terminal (curl) or other tools

e.g. curl http://localhost:8329/product?type=phone