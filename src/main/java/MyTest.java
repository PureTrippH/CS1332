import org.junit.Before;

public class MyTest {

    public static void main(String[] args) {
        CircularSinglyLinkedList<String> list = new CircularSinglyLinkedList<>();
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        System.out.println(list.toString());
        list.removeFromFront();
        System.out.println(list.toString());
        list.removeFromBack();
        System.out.println(list.toString());
        list.removeFromFront();
    }
}
