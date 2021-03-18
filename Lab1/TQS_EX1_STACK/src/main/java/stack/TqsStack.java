package stack;

import java.util.ArrayList;
import java.util.NoSuchElementException;

//Stack assuming that the top of the stack is index 0 and the tail is in stack.size()-1
public class TqsStack<T> {
    //Attributes
    private Integer bound;
    private ArrayList<T> stack;

    //Constructors
    //Default stack
    public TqsStack(){
        this.stack = new ArrayList<>();
    }
    //Stack with bound
    public TqsStack(int bound){
        this.stack = new ArrayList<>();
        this.bound = bound;
    }

    //returns whether the stack is empty or not (False or True)
    public boolean isEmpty(){
        return this.stack.size() == 0;
    }

    //return the size of the Stack
    public int size() {
        return this.stack.size();
    }

    //Pushes an element T into the top of the stack -> Can be of any type
    public void push(T element) {
        //In case there's a bound to the stack
        if (this.bound != null){
            if (stack.size() + 1 > this.bound){
                throw new IllegalStateException();
            } else{
                this.stack.add(0, element);
            }
        //In case there's not a bound to the stack
        } else {
            this.stack.add(0, element);
        }
    }

    //Returns the value at the top of the stack without removing it
    public T peek() {
        if (this.stack.size() == 0){
            throw new NoSuchElementException();
        } else{
            return this.stack.get(0);
        }
    }

    //remove the item at the top of the stack and returns its value
    public T pop() {
        if (this.stack.size() == 0){
            throw new NoSuchElementException();
        }
        else{
            T element = this.stack.get(0);
            this.stack.remove(0);
            return element;
        }
    }
}
