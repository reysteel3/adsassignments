import java.util.ArrayList;
import java.util.Objects;

public class MyHashTable <K, V>{
    private class HashNode<K, V>{
        private K key;
        private V val;
        private HashNode<K, V> next;

        final int hashCode;

        public HashNode(){
            hashCode = 0;
            next = null;
        }
        public HashNode(K key, V val, int hashCode){
            this.key = key;
            this.val = val;
            this.hashCode = hashCode;
        }

        @Override
        public String toString() {
            return "{" + key + " " + val + "}";
        }
    }

    private ArrayList<HashNode<K,V>> chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new ArrayList<>();
        size = 0;
        for (int i = 0; i < M; i++){
            chainArray.add(null);
        }
    }

    public MyHashTable(int M) {
        chainArray = new ArrayList<>();
        this.M = M;
        size = 0;
        for (int i = 0; i < M; i++){
            chainArray.add(null);
        }
    }

    private int hash(K key){
        return Objects.hashCode(key);
    }

    private int getIndex(K key){
        int hash = hash(key);
        int index = hash % M;
        if (index < 0) index *= -1;
        return index;
    }

    public void put(K key, V val){
        int index = getIndex(key);
        int hash = hash(key);


        HashNode<K, V> head = chainArray.get(index);

        while(head != null){
            if (head.key.equals(key) && head.hashCode == hash){
                head.val = val;
                return;
            }
            head = head.next;
        }
        size++;
        head = chainArray.get(index);
        HashNode<K, V> newNode = new HashNode<>(key, val, hash);
        newNode.next = head;
        chainArray.set(index, newNode);


        if ( (double) (size / M) > 0.6){
            System.out.println("LOAD FACTOR " + key + " " + val);
            M *= 2;
            ArrayList<HashNode<K, V>> oldList = chainArray;
            chainArray = new ArrayList<>();
            size = 0;
            for (int i = 0; i < M; i ++){
                chainArray.add(null);
            }
            for (int i = 0; i < oldList.size(); i++){
                HashNode<K, V> start = oldList.get(i);
                while(start != null){
                    put(start.key, start.val);
                    start = start.next;
                }
            }
        }

    }

    public void print(){
        for (int i = 0; i < M; i ++){
            HashNode<K, V> head = chainArray.get(i);
            if (head != null){
                while(head != null){
                    System.out.print("{" + head.key + "," + head.val + "}  ");
                    head = head.next;
                }
                System.out.println();
            }
        }
    }

    public V get(K key){
        int index = getIndex(key);
        int hash = hash(key);
        HashNode<K, V> head = chainArray.get(index);

        while(head != null){
            if (head.key.equals(key) && head.hashCode == hash){return head.val;}
            head = head.next;
        }
        return null;
    }

    public V remove(K key){
        int index = getIndex(key);
        int hash = hash(key);
        HashNode<K, V> head = chainArray.get(index);
        HashNode<K, V> prev = null;
        while(head != null){
            if (head.key.equals(key) && hash == head.hashCode) break;
            prev = head;
            head = head.next;
        }
        if (head == null) return null;

        size--;

        if(prev != null) prev.next = head.next;
        else chainArray.set(index, head.next);
        return head.val;
    }

    public boolean contains(V value){
        for (int i = 0; i < M; i ++){
            if (chainArray.get(i) != null){
                HashNode<K, V> head = chainArray.get(i);
                while(head != null){
                    if(head.val.equals(value)){return true;}
                    head = head.next;
                }
            }
        }
        return false;
    }

    public K getKey(V val){
        return null;
    }

    public int size(){
        return size;
    }

}
