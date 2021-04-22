In this exercise it is done a brief introduction to cucumber.

To understand cucumber go to this link: 

Cucumber framework: https://cucumber.io/docs
O'Reilly Cucumber in a nutshell: https://learning.oreilly.com/library/view/mastering-software-testing/9781787285736/6e1c1a32-79a7-492e-a452-894c9f646bfd.xhtml

IN this book there is also a example that is what is what is implemented in this exercise.

Cucumber let us do tests more human readable with properties like Scenario where u define one, Background that works like a BeforeEach, and properties like Given, And, Then that are used to describe the tests. This is done in folder resources in a test with the extension .feature. To define how this tests work, in the folder tests, u need to define one class with annotation @Cucumber that might be empty, and in another class u have to define the steps for the test. FOr instance if u defined in feature that the test is Given something Then do something, in the steps test, u have to define this 2 steps. The code is easy to understand because it is very human readable.