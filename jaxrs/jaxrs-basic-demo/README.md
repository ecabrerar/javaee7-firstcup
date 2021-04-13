Prerequisites 
============
Java 8+
Apache Maven 3.6+


Getting Started
===============

* Clone the repository

Example:

```bash
git clone https://github.com/ecabrerar/javaee7-firstcup.git
cd javaee7-firstcup/jaxrs/jaxrs-basic-demo
```

 * Run the project
 
```bash
  mvn install
 
```

 To access our service we must use the urls:
 
  * http://localhost:8080/jaxrs-basic-demo/rest/paises/todos     Retrieve All
  * http://localhost:8080/jaxrs-basic-demo/rest/paises/1         Retrieve by Id 1.


To perform some tests, we can use tools like curl, postman or any REST client.

Example using curl:


```bash
curl -X GET -i http://localhost:8080/jaxrs-basic-demo/rest/paises/todos
curl -X GET -i http://localhost:8080/jaxrs-basic-demo/rest/paises/1

```
#### Author

* [Eudris Cabrera](https://github.com/ecabrerar)
