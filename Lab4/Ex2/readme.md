# Exercise 2

Taking in consideration the exercise 1, now lets do one of our own. Let's start by going to Spring Boot Initializr to create a Spring Boot project. Add developer tools, Data JPA, h2Database and spring web to dependencies.

It is recommended to use a TDD approach, write the test first, make sure the project can compile without errors, defeter the actual implementation of prodution code as much as possible. This is a top-down approach, starting by creating the controller, then the service, and then the repository. It is better this way because the code that we have to test is less and it is simpler. But how can we simulate the behaviour of the bottom classes? Like we have seen in the previous lessons we can use mockito to do this. 

## a)

To test the CarController we need to have implemented 2 classes. CarController and Car. We can see in diagram that class Car has a lot of getters and setters and to make our lifes easy, we can use a library called Lombok that automatizes this proccess.
Next, create the CarController with methods Post and Get, with address /api/cars. In post we create the car, and in get we get all cars. We should have an additional method in path /api/cars/id which returns the car to that id.

To do this test, we will test only the carController. We do not need to have the other classes. For that we the annotation @WebMvcTest that contains a larger group of annotations.
we need to take care of the dependency injection of mvc with @Autowired, and @MockBean to create an instance of carService.
In the first test, we need to create a object car, define the expected result and then with the mvc class, perfom a post and check if the results are the correct. We shoudl also verify if the method of the service, was called the correct amount of times.
In the secont one, it is very similar but we perform a post to /api/cars/1 so we can see the expected result for id=1


## b)

The flow of this test is pretty similar to last one. We dont have the repository yet, so we will mock it. Then Inject the mock car service with the annotation @InjectMocks. Having this done we need to setUp the repository, predefining some values before the tests run.
Once this is done, we need to compare the results stored in the mock, and the returned and check if they are the same. We need to do 2 tests, for a validId and an invalidId

## c)

In this one, this is the last one of the list and so we dont need mock. We simply add a Car to the repository and then test the method findByCarId and check if it is the same. We must do this, like the previous, to test a validId and an invalidId.

## d)

Having all the previous tests passing, we will now do an integration test to verify the whole API. Similar to section E from previous exercise.

For that we will post a car to path /api/cars. After that we will test the method findAll() using the method extracting and seeing if the results are as expected. 
After we can test the method get, testing if the status is 200, and checking if the cars are the correct

# Exercise 3

This exercise is done in the same folder as the last one because now we need to adapt the code to use a real database. For that we can run a mysql, using Docker, change the POM to include mysql, change the appplication-integrationtest.properties and then instead of using the annotation @AutoConfigureTestDatabase we should use @TestPropertySource which goes to the source to see the database, and makes tests on it.