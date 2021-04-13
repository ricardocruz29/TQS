# Exercise 1

This application follows commons practices to implement a Spring Boot solution:
-Employee: entity (@Entity) representing a domain concept.-EmployeeRepository: the interface (@Repository) defining the data access methods on the target entity, based on the framework JpaRepository. “Standard” requests can be inferred and automatically supported by the framework (no additionalimplementation required).-EmployeeServiceand EmployeeServiceImpl: define the interface and its implementation (@Service) of a service related to the “businesslogic” of the application. Elaborated decisions/algorithms, for example, would be implemented in this component.-EmployeeRestController: the component that implements the REST-endpoint/boundary (@RestController): handles the HTTP requests and delegates to the EmployeeService.

We need to test this out. In IES we used to go to postman and check if the results were as expected. The idea here, is to automate this process with tests.
Check the table in exercise 1 description to check the tests that are being done.

After studying the tests done, we should be able to answer this questions:

## a)Identify a couple of examples on the use of AssertJ expressive methods chaining.
assertJ is a library similar to hamcrest or Junit that allow us to do a different type of asserts, doing verifications like a chain. First we verify one thing, then another, and then other until we verify everything we want.
We can see some examples of this being used for example in all "assertThat" that appear troughout the tests.
For example in the class EmployeeRepositoryTest, EmployeeRestControllerIT, etc..

Is is a very useful library that makes the tests more readable. See the documentation in here : https://assertj.github.io/doc/

## b)Identify an example in which you mock the behavior of the repository (and avoid involving a database). 

Imagine that we are using an API and making some changes to it, and we don't own the repository code. In this case, it is better to mock the behavior of the repository and simulate the expected results. Other example in which we mock the behavior is for example using and Top-Down approach like we are going to see in the next exercise. This allow us to do unit tests on Service class.

## c)What is the difference between standard @Mock and @MockBean?

We have seen in previous lessons what Mockito is, and what it is used for. The annotation @Mock is no more than a shorthand for the Mockito.mock() method. We have seen in previous lessons that we can use this to simulate the behaviour of an API, or some class that we want to test, predefining some values and by doing this we are able to simulate the flow of a certain method contacting an Api.
Altough @MockBean annotation having a similar name, it has some differences. This is used to add mock objects to the spring application context, replacing any existing bean of the same type. If there is no bean, a new one will be added. We can see that this annotation is similar to mock, but used for Spring applications, and it usefull to use in integration tests where a particular bean needs to be mocked, like an external API.

## d)What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

If the scope of the test is the repository and the database and the access to it, and not test the other classes, we can use the annotation @DataJpaTest that does the slicing so we can test only the "data". This is very useful so we can focus on that. But this is done to do a unit test to this class. When we want to do an integration test of the whole API it can be done using an in-memory db that is used just for tests, or use a real database. To use the first one we must use the annotation @AutoConfigureTestDatabase that prepares everything and sets everything up to the tests. After the tests are done, the data will be flushed. If we want to use a persistent database, we must use the annotation @TestPropertySource that accesses the file application-integrationtest.properties, and see all the connections that need to be done to do the integration test.