# 1

In this exercise let's start to download geckoDriver if you use Firefox or Chrome driver if you use Google Chrome.

Geckodriver: https://github.com/mozilla/geckodriver/releases/
Chromedriver: https://sites.google.com/a/chromium.org/chromedriver/

You can add the file to System path so it is not necessary to always define the path in the tests.
To do this follow this tutorial:
https://www.selenium.dev/documentation/en/webdriver/driver_requirements/

It will be also need Selenium IDE browser plugin to simulate web tests that are more interactive:
Head up to https://www.selenium.dev/selenium-ide/ to download it.


In this exercise we will use selenium web driver that offers a concise programming interface to drive a web browser, simulating as if a real user is operating the browser

In this example we can see a simple test done with selenium
https://saucelabs.com/resources/articles/getting-started-with-webdriver-selenium-for-java-in-eclipse

You need to have the browser installed locally as well as the correspondent driver.


1a) Copy the example and see how it works. We can see that when we run the test, the browser is opened, and the test is simulated as if a real user was doing the test.

1b) Usin @BeforeEach and @Aftereach we have to refactor code. Note that we can intialize the driver in @BeforeEach and then close it in @AfterEach.

# 2

Selenium IDE let's us take advantage of things like locators in the browser, e.g input id, select box's, etc. With this plugin we can interactively record a test of choosing different paramenters and then define the target values to check if it is working as expected. 

To use this plugin, we just need to define the URL that we are going to operate, and then start recording, and just doing some steps in the website we can test a lot of things. When we tested and clicked everything we wanted, we just need to stop recording. 
When the test is done, just save it and export with junit and test it in an IDE like intellij. Note that it might still use an older version of junit and be aware to do the necessary changes.

Note: In this exercise i had an bug because of the test could not found a <tr> element, and because of that i used a provided example by the teacher. 
This errors might occur and are hard of debugging, so be careful what steps you might do.