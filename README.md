# circuit-breaker-pattern
The Implemetation of circuit breaker pattern using resilience4j, spring boot and docker. 

While using inter-process communication such as RESTful APIs in distributed systems, you have to consider the availability of services. Circuit breaker pattern is used to solve the availability problems in these kind of systems.

For more information about the details of implementations, please checkout the medium post via the link below :
https://medium.com/@DanialEskandari/circuit-breaker-pattern-resilience4j-450f9394c87b

# How to run?

First, clone the repository.
```
git clone git@github.com:eskandaridanial/circuit-breaker-pattern.git
```

Second, build the project via docker-compose.
```
docker-compose build
```

Third, run the project via docker-compose.
```
docker-compose up
```

Now there are two containers up and running :
server appliction on port 8000
client application on port 8001

You can test the circuit breaker pattern using the API specified in the client controller.
```
api/resilience/client/v1/len/{wait}
```
