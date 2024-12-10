package collections;
import java.util.ArrayList;
import java.util.Collections;


// array list can store element dynamically
public class MyArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        // this is how internally memory allocate for this list object
//        size = n
//        when this n reach next size will assign like this
//        n = n + n/2 +1;

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // added at specific index and it will not replace the element at that position
        list.add(0, 1000);
//        System.out.println(list);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
// to change
        list.set(0, 200);
        System.out.println(list.get(0));
        Collections.sort(list);
        System.out.println(list);
//        methods
     /*
     .add()
     .set()
     .remove
     .clear
     .size
      */

    }
}
