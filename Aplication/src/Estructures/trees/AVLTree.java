package Estructures.trees;


import java.io.Serializable;
import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V>  implements MyBinaryTree<K, V>, Serializable {

    private static final long serialVersionUID = 1L;

    protected AVLNode<K, V> root;

    public AVLTree() {
        root = null;
    }

    public AVLNode<K, V> getRoot() {
        return root;
    }

    @Override
    public K getMin() {
        return root == null ? null : getMin(root).getKey();
    }

    private AVLNode<K, V> getMin(AVLNode<K, V> x) {
        if (x.getLeft() == null)
            return x;
        return getMin(x.getLeft());
    }

    @Override
    public K getMax() {
        return root == null ? null : getMax(root).getKey();
    }

    private AVLNode<K, V> getMax(AVLNode<K, V> x) {
        if (x.getRight() == null)
            return x;
        return getMax(x.getRight());
    }

    @Override
    public K getPredecessor(K key) {
        return search(root, key) != null ? getPredecessor(search(root, key)).getKey() : null;
    }

    private AVLNode<K, V> getPredecessor(AVLNode<K, V> x) {
        if (x.getLeft() != null)
            return getMax(x.getLeft());
        AVLNode<K, V> y = x.getParent();
        while (y != null && x == y.getLeft()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    @Override
    public K getSuccessor(K key) {
        return search(root, key) != null ? getSuccessor(search(root, key)).getKey() : null;
    }

    private AVLNode<K, V> getSuccessor(AVLNode<K, V> x) {
        if (x.getRight() != null)
            return getMin(x.getRight());
        AVLNode<K, V> y = x.getParent();
        while (y != null && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    public int getWeight() {
        if (root != null)
            return getWeight(root);
        return 0;
    }

    public int getWeight(AVLNode<K, V> x) {
        int l = (x.getLeft() != null) ? getWeight(x.getLeft()) : 0;
        int r = (x.getRight() != null) ? getWeight(x.getRight()) : 0;
        return l + r + 1;
    }

    @Override
    public V search(K key) {
        AVLNode<K, V> node = search(root, key);
        return node == null ? null : node.getValue();
    }

    @Override
    public boolean isInTree(K key) {
        return search(root, key) != null;
    }

    private AVLNode<K, V> search(AVLNode<K, V> node, K key) {
        if (key == null)
            return null;
        if (node == null || key.equals(node.getKey()))
            return node;
        if (key.compareTo(node.getKey()) <= 0)
            return search(node.getLeft(), key);
        return search(node.getRight(), key);
    }

    public V search(K key, V value) {
        AVLNode<K, V> node = search(root, key, value);
        return node == null ? null : node.getValue();
    }

    private AVLNode<K,V> search(AVLNode<K,V> node, K key, V value){
        if (key == null)
            return null;
        if (node == null || key.equals(node.getKey()) && value.equals(node.getValue()))
            return node;
        if (key.compareTo(node.getKey()) <= 0)
            return search(node.getLeft(), key, value);
        return search(node.getRight(), key, value);
    }

    @Override
    public void insert(K key, V value) {
        AVLNode<K, V> node = new AVLNode<>(key, value);
        insert(node);
    }

    protected void insert(AVLNode<K, V> z) {
        AVLNode<K, V> y = null;
        AVLNode<K, V> x = root;
        while (x != null) {
            y = x;
            if (z.getKey().compareTo(x.getKey()) <= 0)
                x = x.getLeft();
            else
                x = x.getRight();
        }
        z.setParent(y);
        if (y == null)
            root = z;
        else if (z.getKey().compareTo(y.getKey()) <= 0)
            y.setLeft(z);
        else
            y.setRight(z);


        rebalance(z);
    }

    @Override
    public V delete(K key) {
        AVLNode<K, V> node = search(root, key);
        return node != null ? delete(node).getValue() : null;
    }

    @Override
    public V delete(K key, V value) {
        AVLNode<K,V> node = search(root, key, value);
        return node!=null ? delete(node).getValue() : null;
    }

    protected AVLNode<K, V> delete(AVLNode<K, V> z) {
        AVLNode<K, V> x = null;
        AVLNode<K, V> y = null;
        if (z.getLeft() == null || z.getRight() == null)
            y = z;
        else
            y = getSuccessor(z);
        if (y.getLeft() != null)
            x = y.getLeft();
        else
            x = y.getRight();
        if (x != null)
            x.setParent(y.getParent());
        if (y.getParent() == null)
            root = x;
        else if (y == y.getParent().getLeft())
            y.getParent().setLeft(x);
        else
            y.getParent().setRight(x);
        if (y != z) {
            z.setKey(y.getKey());
            z.setValue(y.getValue());
        }

        rebalance(z);

        return y;
    }

    @Override
    public ArrayList<V> searchEqualTo(K key) {
        ArrayList<V> array = new ArrayList<V>();
        if (isInTree(key)) {
            AVLNode<K, V> node = search(root, key);
            while (node != null && node.getKey().compareTo(key) == 0) {
                array.add(node.getValue());
                node = getPredecessor(node);
            }
        }
        return array;
    }

    @Override
    public ArrayList<V> searchLowerOrEqualTo(K key) {
        ArrayList<V> array = new ArrayList<V>();

        AVLNode<K, V> node = getMin(root);

        while (node != null && node.getKey().compareTo(key) <= 0) {
            array.add(node.getValue());
            node = getSuccessor(node);
        }

        return array;
    }

    @Override
    public ArrayList<V> searchLowerTo(K key) {
        ArrayList<V> array = new ArrayList<V>();

        AVLNode<K, V> node = getMin(root);

        while (node != null && node.getKey().compareTo(key) < 0) {
            array.add(node.getValue());
            node = getSuccessor(node);
        }

        return array;
    }

    @Override
    public ArrayList<V> searchBiggerOrEqualThan(K key) {
        ArrayList<V> array = new ArrayList<V>();

        AVLNode<K, V> node = getMax(root);

        while (node != null && node.getKey().compareTo(key) >= 0) {
            array.add(node.getValue());
            node = getPredecessor(node);
        }

        return array;
    }

    @Override
    public ArrayList<V> searchBiggerThan(K key) {
        ArrayList<V> array = new ArrayList<V>();

        AVLNode<K, V> node = getMax(root);

        while (node != null && node.getKey().compareTo(key) > 0) {
            array.add(node.getValue());
            node = getPredecessor(node);
        }

        return array;
    }

        private int height(AVLNode<K, V> x ,int keep) {
            if (x == null){
                return -1;
            }else {
                if(keep>0) {
                    x.setlHeight(height(x.getLeft(),keep-1));
                    x.setrHeight(height(x.getRight(),keep-1));
                    x.setHeight(Math.max(x.getlHeight() , x.getrHeight()) + 1);
                    return x.getHeight();
                }else{
                    return x.getHeight();
                }
            }
        }



        private void leftRotate(AVLNode<K, V> x) {
            AVLNode<K, V> y = x.getRight(); // set y
            x.setRight(y.getLeft()); // turn y’s left subtree into x’s right subtree
            if (y.getLeft() != null)
                y.getLeft().setParent(x);
            y.setParent(x.getParent()); // link x’s parent to y
            if (x.getParent() == null)
                root = y;
            else if (x == x.getParent().getLeft())
                x.getParent().setLeft(y);
            else
                x.getParent().setRight(y);
            y.setLeft(x); // put x on y’s left
            x.setParent(y);
        }

        private void rightRotate(AVLNode<K, V> x) {
            AVLNode<K, V> y = x.getLeft(); // set y
            x.setLeft(y.getRight()); // turn y’s right subtree into x’s left subtree
            if (y.getRight() != null)
                y.getRight().setParent(x);
            y.setParent(x.getParent()); // link x’s parent to y
            if (x.getParent() == null)
                root = y;
            else if (x == x.getParent().getRight())
                x.getParent().setRight(y);
            else
                x.getParent().setLeft(y);
            y.setRight(x); // put x on y’s right
            x.setParent(y);
        }

        private void rebalance(AVLNode<K, V> node) {

            while (node != null) {

                height(node,2);
                if (node.getlHeight() >= 2 + node.getrHeight()) {

                    if (node.getLeft().getHeight() >= node.getLeft().getrHeight()) {
                        rightRotate(node);
                    } else {
                        leftRotate(node.getLeft());
                        rightRotate(node);
                    }
                } else if (node.getlHeight()+2 <=  node.getrHeight()) {

                    if (node.getRight().getrHeight() >= node.getRight().getlHeight()) {
                        leftRotate(node);
                    } else {
                        rightRotate(node.getRight());
                        leftRotate(node);
                    }
                }
                node = node.getParent();
            }
        }




}

