2a) We need to do the necessary tests to Class Dip and all necessary changes into the Class

Changes made in class Dip:
    - In the constructor i did the verification if the numbers and stars are between their bounds (e.g A number can not be greather than 50 and less then or equal 0: A star can not be greater than 10 and less or equal than 0)
    - In method format the format of the stars being printed we can see in the tests that are expect to be the same as the numbers. Change %d to %3d
    - Magic numbers erased which be talked in 2c)

Changes made in the tests:
    - Changing the test "testConstructorFromBadArrays()" which will verify if the number of any of the arrays (numbers or stars) is with the incorrect size (Must be 5 numbers and 2 stars)

Notes:
@Test defines a test method
@BeforeEache Runs before each test
@DisplayName is the name of the test to be displayed
@AssertX Depending on the X it does different things. But it asserts the statement in a certain way e.q equals to something, or is true, false, expection a Exception

2b) Changes to class Set of Naturals Class and Test

A set of naturals isn't necessarily a class to this Euromillion problem. It works more like a Java library that can be used across multiple projects.

Notes: The class SetOfNaturals represents a set (no duplicates should be allowed) ofintegers,in the range [1, +∞].Some basic operations are available (add element, find the intersection...).

Changes made in Class SetOfNaturals:
    - Creation of method intersects that verifies if a subset intersects the current set. This is done by verifying if any numbers of the subset is present at the current set
      Returns true in case there is, and false in case there isn't

Changes made in tests (Will enumerate just a few of them -> Check them out in the code that it is easiear and more self-explanatory):
    - Verifying if we are adding duplicate or negative(or zero) number(s) to a set (From 3 different sources -> FromArray which transforms an array into a set
                                                                                                             -> Single number being added
                                                                                                             -> Adding an array to an existing set)
    - Test the false-positives and positives-falses for intersection, i.e verifying if there is a intersection and the result is that it isn't and vice-versa
    - Same thing for method contains
    - Testing size after and addition to the set being it from 3 different sources

    This is very resumed, but check the code out ( I think the code is very self-explanatory)
    
2c) Talked in 2a) -> Refactoring of the code to eliminate "Magic Numbers". Check what Magic Numbers are : https://refactoring.guru/replace-magic-number-with-symbolic-constant
    Creation of this constants to be used troughout the class:
        static int MAX_NUMBERS = 50;
        static int MIN_NUMBERS = 0;
        static int MAX_STARS = 10;
        static int MIN_STARS = 0;
        static int ARRAY_SIZE_NUMBERS = 5;
        static int ARRAY_SIZE_STARS = 2;

2d) Use Jacoco : https://www.baeldung.com/jacoco to get analysis