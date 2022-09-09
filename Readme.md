**Order Validation Service** is a sample application that performs operations via Rest Webservices.

It exposes 2 endpoint as following list as follows :

imdb Service

- POST localhost:8090/api/validation/validateOrder (gets an order json object and returns validation result)
- GET localhost:8090/api/validation/validationHistory (returns all validation request history)



## How to setup the application
It is a docker based application. Running below command in project's directory builds single running container:

```bash
docker-compose up
```
**Note** : It may take time, because it needs to download all dependencies of the project.




## How to use
This application follows OpenAPI specification in API documentation. Thanks to SwaggerUI, you can see endpoints documentation in a graphical UI and try their functionality and see the response. After running the containers, you can access the application links as :


- SwaggerUI : http://localhost:8090/swagger-ui.html


To Test the endpoints please go to *http://localhost:8080/swagger-ui.html*. It helps you to read endpoints documentation and give you a UI to try them out.



## Valid departments name

| Department name | 
|   :---:   |  
| GOoD analysis department   | 
| GOoD repair department     | 
| GOoD replacement department     |
|IT department  |
