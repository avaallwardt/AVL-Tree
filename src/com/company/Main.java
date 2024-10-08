package com.company;

public class Main {

    public static void main(String[] args) {
	    MyAVLTree tree = new MyAVLTree();

        tree.insert(11);
        tree.traverseLevelOrder();

        tree.insert(7);
        tree.traverseLevelOrder();

        tree.insert(16);
        tree.traverseLevelOrder();


        tree.insert(6);
        tree.traverseLevelOrder();

        tree.insert(9);
        tree.traverseLevelOrder();

        tree.insert(10);
        tree.traverseLevelOrder();

        /*
        tree.insert(4);
        tree.traverseLevelOrder();
        tree.insert(2);
        tree.traverseLevelOrder();

        tree.insert(10);
        tree.traverseLevelOrder();

        tree.insert(8);
        tree.traverseLevelOrder();

        tree.insert(12);
        tree.traverseLevelOrder();

        tree.insert(7);
        tree.traverseLevelOrder();

        tree.traverseLevelOrder();
        //System.out.println(tree.getRoot().getData());

         */

// change
        /*
        MyAVLTree leftRotation = new MyAVLTree();
        leftRotation.insert(50);
        System.out.println("here");
        leftRotation.insert(25);
        leftRotation.insert(10);


        System.out.println("asdfasdfasfsd");

        BinarySearchTree rightRotation = new BinarySearchTree();
        leftRotation.insert(50);
        leftRotation.insert(75);
        leftRotation.insert(100);

        BinarySearchTree leftRight = new BinarySearchTree(); //also called double left
        leftRight.insert(50);
        leftRight.insert(75);
        leftRight.insert(60);

        BinarySearchTree rightLeft = new BinarySearchTree(); //also called double right
        rightLeft.insert(50);
        rightLeft.insert(25);
        rightLeft.insert(40);

        System.out.println("asdf");

	     */
    }
}
