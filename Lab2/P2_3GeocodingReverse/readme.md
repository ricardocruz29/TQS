# Exercicio2
First of all in this question we need to head up to this link and get the starting project : https://gitlab.com/ico_gl/ua_tqs_gs20
Then we should analyse all the classes and what each and everyone is doing.
Basically the problem is that we have a class AdressResolver that given coordinates it returns and adress. We want to implement tests to test the method that is doing this that is findAdressForLocation(). We can see that if we are giving coordinates and it returns an adress, surely it needs to access an API to get the results. So like in the last problem, we will use mock to simulate this process. We can also see in the diagram that in AdressResolver constructor there's a HttpClient object that it needs which leads us to the question

## Which is the service to mock?
Well like it was talked before, AdressResolver is waiting an HttpClient object, so the service to mock is HttpClient and it injects the mock on AdressResolver. This can be done using the annotatins @Mock and @InjectMocks like it was used in the last question (it is also need to put in the code the @ExtendWith(MockitoExtension.class) like it was needed in the previous question).

But in this assignment we have a problem. How do we simulate the response that the API would give? Well first of all check my code and you can get a clue. Head up to this website https://developer.mapquest.com/documentation/open/geocoding-api/reverse/get/ that is the MapQuest we are going to use.
In the test whenGoodAlboiGps_returnAddress() I did the same asserts with 2 different examples, and I know it might be overkill but because I was not familiarized with the API i wanted to make sure that the examples i was using were okay. I used this link http://open.mapquestapi.com/geocoding/v1/reverse?key=KEY&location=30.333472,-81.470448&includeRoadMetadata=true&includeNearestIntersection=true with the KEY i had in config.properties and then with this example: http://open.mapquestapi.com/geocoding/v1/reverse?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&location=40.6318,-8.658&includeRoadMetadata=true. The rest of the process might be the most important that is doing the tests, and i think the code is self-explanatory and very similar to what we did in the previous question.
But well just a quick note that altough it seems we are really not testing anything, it is not true. This is a bit dubious but in this case we have no interest in testing the API, so we are testing that the method works properly. We are testing if it returns the correct values for certain given coordinates, as well as seeing if the invocation to the URL works properly, we also check if the method does the parsing.
It was done 1 more test to test to values that have no corresponding Adress if it works properly.

# Exercicio3
In this exercise we have to get out of the context unit tests and do integration test. This test will be done with the participation of the remote API. The tests will be exactly the same but how we set it up will be the different. Mocks will be no longer need. We need a real implementation. For that we should use the class TqsBassicHttpClient. Check the code that is very self-exaplanatory. When the tests are executed we can see that the tests take longer than the unit tests.
We can also see that when we do mvn test and mvn install the unit tests are runned but the integration tests are not. This because this tests usually take longer and are not to be tested as often as unit tests. To test the integration tests trough maven we can use the plugin failsafe. 
When we execute mvn installfailsafe:integration-test we can see that the integration tests were now executed. 

Important note: Tests that end in ...Test will be assumed as unit test and will always run. The tests that end in IT will be assumed as integration test and will be only run when we specify that they need to run.