package stack;

import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {
        private TqsStack stack;

    @BeforeEach
    void setUp() {
        stack = new TqsStack<>();
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Testing Stack empty on construction")
    @Test
    void testStackIsEmpty() {
        assertTrue(stack.isEmpty(), "Stack must be empty on construction");
    }

    @DisplayName("Testing Stack has size 0 on construction")
    @Test
    void testStackSizeZero() {
        assertEquals(0, stack.size(), "Stack must have size 0 on construction");
    }

    @DisplayName("Testing Stack has size N when N pushes are done")
    @Test
    void testStackSizeAfterNPush() {
        // Although we are inserting items to the stack in this method, this doesn't mean the other methods will be influenced by this one.
        // This is considered as an "requirements" for the test, where repeatable should be capable, meaning that one method should not influence antoher one

        //Insert of the top4 of liga NOS -> Testing purposes only
        stack.push("SLB");
        stack.push("FCP");
        stack.push("SCP");
        stack.push("SCB");

        //assert the size of the stack is 4 -> Number of pushes done
        assertEquals(4, stack.size());

        //assert that stack is not empty
        assertFalse(stack.isEmpty(), () -> "stack reports as " +  " empty");
    }


    @DisplayName("Testing Push X and Pop, the value popped is X")
    @Test
    void testStackValuePopped() {
        //Pushed element CDT -> Tondela
        stack.push("CDT");

        //assert the value popped from the stack is CDT -> Last one pushed into the stack
        assertEquals("CDT", stack.pop());
    }

    @DisplayName("Testing the value peeked is the last pushed, and the size remains the same")
    @Test
    void testStackValuePeekedSize(){
        //Pushed element FCF -> Famalicão
        stack.push("FCF");

        //get the value of the size after inserting on element
        int stackSize = stack.size();

        //assert the value peeked is FCF -> Last one pushed into the stack
        assertEquals("FCF", stack.peek());

        //assert the size of the stack remains the same as the start size
        assertEquals(stackSize, stack.size());
    }

    @DisplayName("Testing the size of stack (start size N) is empty and has a size 0 after N pops ")
    @Test
    void testStackIsEmptySizeZeroNPop(){
        //get the value of the size at the start -> size N and do n pops
        int n = stack.size();
        for (int i=0; i<n; i++){
            stack.pop();
        }

        //assert stack size n has size 0 after n pops
        assertEquals(0, stack.size(), "Stack with size n must have size 0 after n pops");

        //assert stack with size n is empty after n pops
        assertTrue(stack.isEmpty(), "Stack with size n must be empty after n pops");
    }

    @DisplayName("Testing pop on empty Stack throws Exception")
    @Test
    void testPopOnEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {stack.pop();});
    }

    @DisplayName("Testing peek on empty Stack throws Exception")
    @Test
    void testPeekOnEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {stack.peek();});
    }

    @DisplayName("Testing push on Stack with bound reached")
    @Test
    void testPushOnFullStack() {
        //override the existence stack,and create one with limit of 3 items and push 3 elements into the stack to reach the limit
        stack = new TqsStack<>(3);
        for (int i=0;i<3;i++){
            stack.push(i);
        }

        //addition of another element when the stack is already full
        assertThrows(IllegalStateException.class, () -> {stack.push(1);});
    }
}