# 3

Like we have used in Mockito in the previous lesson, there are extensions that can help us with refactoring our code. In this case there are Selenium extensions that allow us to do dependency injection, as well as downloading for us if we don't have the driver needed to the browser. This extensions simplify our code and are easy to use.

To see this extensions head up to : https://bonigarcia.github.io/selenium-jupiter/

This extension has advantages such as:

-transitively import the required Selenium dependencies.[you may just add the selenium-jupiter dependency in POM]
-enabledependency injection with respect to the WebDriver implementation(automates the use of WebDriverManagerto resolve thespecific browser implementation).
-if using dependency injection, it will also ensure that the WebDriver is initializedand closed.
-you do not need to pre-install the WebDriver binaries; they are retrieved on demand.


## 3a)
From this documentation: https://bonigarcia.github.io/selenium-jupiter/
follow the sections "Quick References" and  "Local Browsers". What we need to do is implement our own test, very similar to the tests done in the guide.

## 3b)
An "headless browser" is a browser that doesn't have a interface. I've been saying that we need to have the browser installed to run the tests, but with a headless browser we don't need. This are very advantageous if we want to do a simple test to something.

## 3c)
The code we exported in exercise 2, altough being refactored to Junit 5, there are still some things we can do to make it look better. In this section, we saw some advantages of selenium-jupiter extension and so we can refactor the code to something easir. We can use the extension so we don't need to use BeforeEach and AfterEach  to open and close the driver. Overall the code looks simpler and easir to understand . Check my code cuz it is very similar to Mockito, with dependency injection.

## 3d)
Other way to run a specific browser and not having to install them is using Docker. Trough docker and an anotation we can make docker run a container that has the web browser that we specified and so we don't need to have it installed locally. To do this set up follow this guide:

https://bonigarcia.github.io/selenium-jupiter/#docker-browsers