public class MyTest {
    public static void main(String[] args) {
        ArrayQueue<Integer> test = new ArrayQueue<>();
        for (int i = 0; i < 9; i++) {
            test.enqueue(i);
        }
        System.out.println(test.toString());
        test.dequeue();
        test.dequeue();
        System.out.println(test.toString());
        test.enqueue(61365);
        test.enqueue(12);
        System.out.println(test.toString());

        test.dequeue();
        System.out.println(test.toString());
        test.enqueue(13);
        System.out.println(test.toString());
        test.enqueue(14);
        System.out.println(test.toString());
    }
}
