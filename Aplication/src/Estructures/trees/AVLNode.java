package Estructures.trees;

public class AVLNode<K extends Comparable<K>,V>{


    private K key;
    private V value;
    private AVLNode<K, V> parent;
    private AVLNode<K, V> left;
    private AVLNode<K, V> right;
    int height;
    int rHeight;
    int lHeight;

    public AVLNode(K key, V value) {
        this.key = key;
        this.value = value;
        left=null;
        right=null;
        parent = null;
        height = 0;
        rHeight = 0;
        lHeight = 0;
    }




    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public AVLNode<K, V> getParent() {
        return parent;
    }

    public void setParent(AVLNode<K, V> parent) {
        this.parent = parent;
    }

    public AVLNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<K, V> left) {
        this.left = left;
    }

    public AVLNode<K, V> getRight() {
        return right;
    }

    public void setRight(AVLNode<K, V> right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return (left==null && right==null);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getrHeight() {
        return rHeight;
    }

    public void setrHeight(int rHeight) {
        this.rHeight = rHeight;
    }

    public int getlHeight() {
        return lHeight;
    }

    public void setlHeight(int lHeight) {
        this.lHeight = lHeight;
    }
}
