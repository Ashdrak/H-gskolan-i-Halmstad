public class Test {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> test1 = new DoublyLinkedList<>();
        System.out.println(test1);
        test1.add(5);
        test1.add(7);
        test1.add(5);
        test1.add(6);
        test1.add(9);
        test1.add(5);
        test1.add(6);
        test1.add(2);
        test1.add(5);
        test1.add(5);
        test1.add(5);
        System.out.println(test1);
        test1.remove(5);
        System.out.println(test1);
    }
}
