First step is create a maven project and get correct dependecies needed for the tests (it will be needed junit and mock)
If you are not familiarized with these subjects:
Junit: https://junit.org/junit5/docs/current/user-guide/
Mock: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#1

So in this particular exercise, i assumed that you have already read the assignment, lets imagine that we want to test the method getTotalValue(). We need to contact the API to get the value of the stocks. Whats the problem with these is that we are dependent of the API to get the response, so these tests stop being unitary. Well if we think in this way, all the unit tests are runned when the maven project is executed so this process can be very time consuming (API may not respond as well). We can see that there are a lot of issues in this particular case, and that's where Mockito can help us. Instead of creating a class that would be only useful for tests, mockito simulates this class and lets us predefine values to simulate the values from the API.

I've been saying only good things about Mockito but when should it be used and when it shouldn't:
## Mockito should be used:
    -supplies non-deterministic results e.g like the current temperature
    -has states that are difficult to create or reproduce
    -is slow e.g the latency of the network
    -module does not yet exist
    -would have to include information and methods exclusively for testing purposes (like the example on the assignment)
## Mockito should not be used:
    -Data/value classes
    -"Everywhere" because this is anti-pattern
    -When we don't have control over the API (they can change and then the tests despite they don't fail, they are not working properly and testing what needs to be tested)

1a/
In a) write the classes. There's a diagram in the assigment. Check my code, its very simple
In b) we have to create the test for getTotalValue(). As a guideline:
1.Prepare a mock to substitute the remote service (@Mock annotation)
2.Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
3.Load the mock with the proper expectations (when...thenReturn)
4.Execute the test (use the service in the SuT)
5.Verify the result (assert) and the use of the mock (verify)

This was very simple, we just use the annotation Mock to create the object and then we use the annotation @InjectMocks to do the Dependency Injection. This are the first 2 points. To do the point 3 we have to use "when" providede by Mockito library. What this does is predifine some situations that mock will "learn". If this values that mock has learned are requested, then mock already knows how to answer them. In this problem we predefine some stocks and provide to mock what he has to answer when they are requested. 
In point 4 we just add some new stocks to simulate this request talked before.
In point 5 we assert the expected value. We must also use verify from Mockito library that will verify some tests that we can define directed to the object created in @Mock. In this case we will verify how many times the method getPrice() was called.

1b/
hamcrest library: https://www.baeldung.com/java-junit-hamcrest-guide
Instead of using Junit asserts, this library provides more readable assertions that can be used to do basically the same thing than Junit assertions.