package Estructures;

import Estructures.linear_Structures.HashMap;
import Estructures.linear_Structures.Queue;
import Estructures.linear_Structures.Stack;
import Estructures.trees.AVLTree;
import Estructures.trees.Binary_tree;
import Estructures.trees.RBTree;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class Pruebas_Estructuras {

	@Test
	void testQueue1() {
		Queue<Integer> Q = new Queue<>();
		Q.offer(3);
		Q.offer(4);

		Q.offer(5);
		Q.offer(77);
		if(Q.poll() == 3) {
			assertTrue(true);
		}else {
			fail("Not yet implemented");
		}
		if(Q.poll() == 4) {
			assertTrue(true);
		}else {
			fail("Not yet implemented");
		}
		if(Q.poll() == 5) {
			assertTrue(true);
		}else {
			fail("Not yet implemented");
		}
		if(Q.poll() == 77) {
			assertTrue(true);
		}else {
			fail("Not yet implemented");
		}
		if(Q.poll() == null) {
			assertTrue(true);
		}else {
			fail("LOL");
		}
	}
	
	@Test
	void testStack1() {
		Stack<Integer> S = new Stack<>();
		S.push(3);
		S.push(5);
		S.push(7);
		int a = S.pop();
		System.out.println(a + "S");
		if(a == 7) {
			assertTrue(true);
		}else {
			fail("Therr is no honor");
		}
		
		a = S.pop();
		
		if(a == 5) {
			assertTrue(true);
		}else {
			fail("Therr is no honor");
		}
		S.push(7);
		a = S.pop();
		
		if(a == 7) {
			assertTrue(true);
		}else {
			fail("Therr is no honor");
		}
	}
	
	@Test
	void TestHashMap1() {
		HashMap<Integer, String> HM = new HashMap<>();
		HM.put(2, "LOL");
		HM.put(4, "LOL2");
		HM.put(5, "LOL4");
		System.out.println(HM.get(2));
		if(HM.get(2) == "LOL") {
			assertTrue(true);
		}else {
			fail("Me la jugue");
		}
		if(HM.get(5) == "LOL4") {
			assertTrue(true);
		}else {
			fail("Me la jugue");
		}
	}
	
	Binary_tree<Integer, Integer> tree_t;
	
	void BSTscene() {
		tree_t = new Binary_tree<>();
	}
	
	@Test
	void BST_Test() {
		BSTscene();
		tree_t.insert(5,1);
		tree_t.insert(1,15);
		tree_t.insert(1,3);
		tree_t.search(3,1);
		tree_t.delete(3,1);
		tree_t.delete(15,1);
		tree_t.delete(5,1);
		tree_t.delete(5,1);
		tree_t.delete(5,1);
		tree_t.insert(65,1);
		
		for (int i = 0; i < 500; i++) {
			tree_t.insert((int)(Math.random()*1600),1);
		}
		
		for (int i = 0; i < 500; i++) {
			tree_t.delete((int)(Math.random()*1600));
		}
		
	}

	RBTree<Integer, Integer> RB_tree_t;
	private void RB_tree_scene() {
		RB_tree_t = new RBTree<>();
	}

	@Test
	void RB_tree_Test() {
		RB_tree_scene();
		RB_tree_t.insert(1,5);
		RB_tree_t.insert(1,15);
		RB_tree_t.insert(1,3);
		RB_tree_t.insert(1,6);
		RB_tree_t.insert(1,7);
		RB_tree_t.insert(1,8);
		RB_tree_t.insert(1,9);
        RB_tree_t.insert(1,10);
        RB_tree_t.insert(1,11);
        RB_tree_t.insert(2,11);
        RB_tree_t.insert(3,11);
        RB_tree_t.insert(1,12);
		RB_tree_t.search(3);
		RB_tree_t.delete(3);
		RB_tree_t.delete(15);
		RB_tree_t.delete(5);
		RB_tree_t.delete(5);
        RB_tree_t.delete(9);
        RB_tree_t.delete(7);
        RB_tree_t.delete(8);
        RB_tree_t.delete(10);
        RB_tree_t.delete(11);
        RB_tree_t.delete(12);
        RB_tree_t.delete(13);

		RB_tree_t.insert(1,65);

		for (int i = 0; i < 50000; i++) {
		    RB_tree_t.insert((int)(Math.random()*10000),1);
		}

		for (int i = 0; i < 50000; i++) {
			RB_tree_t.delete((int)(Math.random()*10000));
		}

	}

    AVLTree<Integer, Integer> AVL_tree_t;
    private void AVL_tree_scene() {
        AVL_tree_t = new AVLTree<>();
    }

    @Test
    void AVL_tree_Test() {
        AVL_tree_scene();
        AVL_tree_t.insert(5,1);
        AVL_tree_t.insert(15,1);
        AVL_tree_t.insert(3,1);
        AVL_tree_t.insert(6,1);
        AVL_tree_t.insert(7,1);
        AVL_tree_t.insert(8,1);
        AVL_tree_t.insert(9,1);
        AVL_tree_t.insert(10,1);
        AVL_tree_t.insert(11,1);
        AVL_tree_t.insert(11,1);
        AVL_tree_t.insert(11,1);
        AVL_tree_t.insert(12,1);
        AVL_tree_t.search(3);
        AVL_tree_t.delete(3);
        AVL_tree_t.delete(15);
        AVL_tree_t.delete(5);
        AVL_tree_t.delete(5);
        AVL_tree_t.delete(9);
        AVL_tree_t.delete(7);
        AVL_tree_t.delete(8);
        AVL_tree_t.delete(10);
        AVL_tree_t.delete(11);
        AVL_tree_t.delete(12);
        AVL_tree_t.delete(13);

		AVL_tree_t.insert(5,1);
		AVL_tree_t.insert(15,14);
		AVL_tree_t.insert(3,1);
		AVL_tree_t.insert(6,1);
		AVL_tree_t.insert(7,1);
		AVL_tree_t.insert(8,1);
		AVL_tree_t.insert(9,1);
		AVL_tree_t.insert(10,11);
		AVL_tree_t.insert(11,12);
		AVL_tree_t.insert(11,12);
		AVL_tree_t.insert(11,12);
		AVL_tree_t.insert(12,13);

		System.out.println(Arrays.toString(AVL_tree_t.searchLowerOrEqualTo(15).toArray()));

        AVL_tree_t.insert(1,65);

        for (int i = 0; i < 50000; i++) {
            AVL_tree_t.insert((int)(Math.random()*100),1);
            System.out.println(i);
        }

        for (int i = 0; i < 50000; i++) {
            AVL_tree_t.delete((int)(Math.random()*100));
			System.out.println(i);
        }

    }

}
