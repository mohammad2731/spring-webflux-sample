# spring-webflux-sample
A small example to show the basic usage of webflux
In this example you can find basic usage of spring webclient(webflux) to call external rest (github api) with non blocking api.
We tried to design simple application to demonstrate some good practices to develop microsevices such as:
- CQRS
- Hexagonal architecture
- Domain driven development
- onion architecture
- Test driven development
- Using spring boot and spring framework statck

you can run tests by:
> mvn clean verify.

# Run application
## With maven:
> mvn clean spring-boot:run

## In linux shell
after building project
> mvn clean install -Dproduction

maven will create a zip file in target directory and you can extract that and execute application with suitable scripts that located in bin directory.
### to run: 
> $./startup.sh

### to show running status
> $./status.sh

### to stop application
> $./stop.sh

you can add some vm options such as '-Xms in vmoptions

# Test
To test this system you can call system's two rest endpoints with any tools such as 'curl', 'wget', 'postman' or ... . 

## Create contact
first rest endpoint in this system is 'create contact'. with this endpoint you can create new contact. each contact contains {name, phone number, email, organization and github username}.
You can call that endpoint as below:
>$ curl -XPOST -H "Content-Type: application/json" "http://localhost:8080/contact/new" -d {"name":"test","phoneNumber":"123456","email":"user@test.com","organization":"Organization","github":"burrsutter"}

## Search Contact
As 'create contact' you can call this endpoint like this:
> curl -XPOST -H "Content-Type: application/json" "http://localhost:8080/contact/search" -d {"name":"test","phoneNumber":null,"email":null,"organization":null,"github":null}

this endpoint searchs on {name, phone number, email, organization and github username}. all the parameters are optional.
