package collections;

import java.util.ListIterator;
import java.util.Stack;

public class MyStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        //show top element
        System.out.println(stack.peek());
        // remove top element
//        stack.pop();
        System.out.println(stack.empty());
        System.out.println(stack.search(4));
        System.out.println(stack);
//        System.out.println(stack.size());
//        for(Integer i : stack) {
//            System.out.println(i);
//        }

        System.out.println("--------");
        ListIterator<Integer> iterator = stack.listIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
