Create Maven based project -> Used Intellij
Add required dependencies to run Junit tests. 
Juni documentation : https://junit.org/junit5/docs/current/user-guide/#running-tests-build-maven
Create the skeleton. The Java classes should be in main/java/.../file.Java
                     The test classes should be in test/java/.../test_file.java
Important link for unit test : https://martinfowler.com/articles/workflowsOfRefactoring/#tdd

Tips for this exercise:
    -The java class must be a stack (if you dont know what is a stack : https://en.wikipedia.org/wiki/Stack_(abstract_data_type) ) which contains methods like:
        -push(x): add an item on the top
        -pop: remove the item at the top
        -peek: return the item at the top (without removing it)
        -size: return the number of items in the stack
        -isEmpty: return whether the stack has no items
    -All of this methods must be tested. Unit tests will be used, which basically test a single unit (there are other test like integration and all system which require integration between units. It will be talked later).
    -This tests will be done using Junit: https://www.tutorialspoint.com/junit/junit_overview.htm
    -So what should be texted?
        a)A stack is empty on construction.
        b)A stack has size 0 on construction.
        c)After n pushes to an empty stack, n > 0, the stack is not empty and its size is n
        d)If one pushes x then pops, the value popped is x.
        e)If one pushes x then peeks, the value returned is x, but the size stays the same
        f)If the size is n, then after n pops, the stack is empty and has a size 0
        g)Popping from an empty stack does throw a NoSuchElementException [You should test for the Exception occurrence]
        h)Peeking into an empty stack does throw a NoSuchElementException
        i)For bounded stacks only, pushing onto a full stack does throw an IllegalStateException

All this tests are done in folder test/java/stack/TqsStackTest.java and there Stack class is implemented in main/java/TqsStack.java
