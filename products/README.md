# Telenor Interview Project

## Project technologies
* Apache Maven 3.6.3
* Java 1.8.0_202, vendor: AdoptOpenJdk
* Docker version 19.03.8, build afacb8b
* IntelliJ Idea 2018.3.5
* Spring boot 2.3.3
* Liquibase 3.8.0
* H2 database 1.4.200

---

## Clone project from GitHub
```git clone https://github.com/nemethsamusandor/telenor.git```

## Maven Build
Run ```mvn clean install``` to build the complete project with tests

or

```mvn clean install -DskipTests``` without tests

```./mvnw``` command also can be used instead of mvn from the project root

## Docker build and run
* Run Docker build ```docker build -t se.telenor.products:1.00 .``` to create docker image
* Run Docker image ```docker run --publish 8239:8080 --detach --name telenor.products se.telenor.products:1.00```

# Test links
RestAPI endpoint is accessible on port 8329.

Open ```http://localhost:8329/product``` with the requires query parameters in Browser, Postman, Terminal (curl) or other tools

Query Parameter		    | Description
----------------------- | -----------------------------------------------------------------
*type*				    | The product type. (String. Can be 'phone' or 'subscription')
*min_price*		        | The minimum price in SEK. (Number)
*max_price*			    | The maximum price in SEK. (Number)
*city*					| The city in which a store is located. (String)
*property*				| The name of the property. (String. Can be 'color' or 'gb_limit')
*property:color*		| The color of the phone. (String)
*property:gb_limit_min* | The minimum GB limit of the subscription. (Number)
*property:gb_limit_max* | The maximum GB limit of the subscription. (Number)

e.g. curl http://localhost:8329/product?type=phone
