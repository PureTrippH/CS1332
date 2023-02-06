import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MyTest {
    public static void main(String[] args) {
        ArrayQueue<String> test = new ArrayQueue();
        test.enqueue("00");
        test.enqueue("01");
        test.enqueue("02");
        test.enqueue("03");
        test.enqueue("04");
        test.enqueue("05");
        test.enqueue("06");
        test.enqueue("07");
        test.enqueue("08");
        test.enqueue("09");
        test.enqueue("10");
        System.out.println(test.toString());
    }
}
