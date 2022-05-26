public class Main {
    public static void main(String[] args) {
        MyHashTable<String, Integer> map = new MyHashTable<>();
        BST<Double, Integer> tree = new BST<>();

        map.put("Depp", 10);
        map.put("Jada", 10);
        map.put("Will", 10);
        map.put("Cena", 10);
        map.print();



    }
}