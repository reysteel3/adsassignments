

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BST <K extends Comparable<K>, V>{
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;

        }
    }

    public BST(){
        root = null;
    }

    public BST(K key, V val){
        root = new Node(key, val);
    }




    private Node insert(Node root, K key, V val){
        if (root == null){
            root = new Node(key, val);
            return root;
        }
        if (key.compareTo(root.key) < 0){
            root.left = insert(root.left, key, val);
        }
        else{
            root.right = insert(root.right, key, val);
        }
        return root;
    }

    public void put(K key, V val) {root = insert(root, key, val);}

    public V get(K key) { return get(root, key);}

    private V get(Node root, K key){
        if (root == null) {return null;}
        if (root.key.compareTo(key) == 0) {return root.val;}
        if (root.key.compareTo(key) > 0) return get(root.left, key);
        return get(root.right, key);
    }

    public void delete(K key) {delete(root, key);}

    private Node delete(Node root, K key){
        if (root == null) return root;

        if (key.compareTo(root.key) < 0){root.left = delete(root.left, key);}
        else if (key.compareTo(root.key) > 0){root.right = delete(root.right, key);}
        else{
            if (root.left == null){return root.right;}
            else if (root.right == null){return root.left;}

            root.right = delete(root.right, root.key);
        }
        return root;
    }
}
