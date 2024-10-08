package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyAVLTree {


    // instance variable
    public Node root;
    // USE RECURSION


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // constructor for initialise the root to null BYDEFAULT
    public MyAVLTree() {
        this.root = null;
    }

    private int x;
    // insert method to insert the new Data
    // used insert method from my BST project
    // INSERT MUST CHECK IF IT NEEDS A ROTATION
    public void insert(int data){ // Mr. U said it would be ok to add element to the constructor
        if(root == null){
            root = new Node(data); // previous is automatically set to null
            System.out.println(root.getData() + " ADDED");
        }
        else{
            insertHelper(data, root, null); // then it will recursively call within the method until it returns true or false
            // insertHelper handles all of the balancing
        }
    }

    // done
    public void insertHelper(int data, Node node, Node previous){
        Node newNode = new Node(data);
        if(data < node.getData()){
            if(node.getLeft() != null){
                insertHelper(data, node.getLeft(), node);
            }
            else{
                node.setLeft(newNode);
                System.out.println(newNode.getData() + " ADDED");
                //return true;
                //node.setRightNode(element);
                //return true;
            }
        }
        else if(data > node.getData()){
            if(node.getRight() != null){
                insertHelper(data, node.getRight(), node);
            }
            else{
                node.setRight(newNode);
                System.out.println(newNode.getData() + " ADDED");
                //node.setLeftNode(element);
                //return true;
            }
        }
        else if(data == node.getData()){
            System.out.println("The value provided already exists in the tree.");
            //return false;
        }



        // it's recursion so each call down the line of recursion has to reach this line before finishing the method and moving back up
        // working its way up the tree starting at the node above the one just added and fixing all balancing

        if(node == null){
            return;
        }


        if(Math.abs(getBalanceFactor(node)) > 1){
            System.out.println("Imbalance found!");
            System.out.println(node.getData() + " bf: " + getBalanceFactor(node) + " - new node " + newNode.getData());

            // there is an unbalance at the node
            // check if previousNode is null (only need previous node for the rotations themselves)
            int rotationNeeded = determineRotationNeeded(node);
            System.out.println("rotation needed: " + rotationNeeded);
            Node z;
            Node y;
            Node x;
            if(rotationNeeded == 0){
                System.out.println("here: " + node.getRight());
                // right-right case
                z = node;
                y = node.getRight();
                x = y.getRight();

                leftRotate(z, previous);
            }
            else if(rotationNeeded == 1){
                // left-right case
                z = node;
                y = node.getLeft();
                x = y.getRight();

                leftRotate(y, z);
                rightRotate(z, previous);
            }
            else if(rotationNeeded == 2){
                // left-left case
                z = node;
                y = node.getLeft();
                x = y.getLeft();

                rightRotate(z, previous);
            }
            else if(rotationNeeded == 3){
                // right-left case
                z = node;
                y = node.getRight();
                x = y.getLeft();

                rightRotate(y, z);
                leftRotate(z, previous);
            }
            /*
            Return values:
                0 - Left Rotation
                1 - Left-Right Rotation
                2 - Right Rotation
                3 - Right-Left Rotation
         */
        }

        // it's ok that it returns if it is added because the added node won't cause an imbalance on the one before it (removal can only do that)
        //return false;
    }


    // PROBLEM WITH BALANCE FACTOR
    public Integer getBalanceFactor(Node node) {
        int numRight = preorderRight(node, node, 0, 0); // adding a start node makes sure the method knows when to stop and not go to the right of that start node (since the start is no longer always the root)
        int numLeft = preorderLeft(node, node, 0,0);
        //System.out.println(node.getData()+ " Right: " + numRight);
        //System.out.println(node.getData() + " Left: " + numLeft);
        int bf = numLeft - numRight;
        System.out.println(node.getData() + " balance factor: " + bf);
        return numLeft - numRight;
    }



    public int preorderLeft() {
        return preorderLeft(root, root, 0, 0);
    }

    public int preorderLeft(Node start, Node current, int currentLevel, int deepestLevel) {
        if (current == null ) {
            return deepestLevel;
        }
        //ystem.out.println(current.getData() + " ");
        if(currentLevel > deepestLevel){
            deepestLevel = currentLevel;
        }
        int x = preorderLeft(start, current.getLeft(), currentLevel + 1, deepestLevel);

        int y = 0;
        if(current != start){
            if(currentLevel > deepestLevel){
                deepestLevel = currentLevel;
            }
            y = preorderLeft(start, current.getRight(), currentLevel + 1, deepestLevel);
        }
        //System.out.println("x left: " + x);
        //System.out.println("y left: " + y);
        //System.out.println();
        if(x > y){
           return x;
        }
        else{
            return y;
        }
    }

    public int preorderRight() {
        return preorderRight(root, root, 0, 0);
    }

    public int preorderRight(Node start, Node current, int currentLevel, int deepestLevel) {
        if (current == null) {
            return deepestLevel;
        }
        //System.out.println(current.getData() + " ");
        if(currentLevel > deepestLevel){
            deepestLevel = currentLevel;
        }
        int x = preorderRight(start, current.getRight(), currentLevel + 1, deepestLevel);

        int y = 0;
        if(current != start){
            if(currentLevel > deepestLevel){
                deepestLevel = currentLevel;
            }
            y = preorderRight(start, current.getLeft(), currentLevel + 1, deepestLevel);
        }

        //System.out.println("x right: " + x);
        //System.out.println("y right: " + y);
        if(x > y){
            return x;
        }
        else{
            return y;
        }
    }


    public int rightHeightBalancedHelper(Node current, int currentLevel, int deepestLevel, Node start){
        currentLevel++;
        if(current.getLeft() == null && current.getRight() == null){
            if(currentLevel > deepestLevel){
                deepestLevel = currentLevel;
            }
            return deepestLevel;
        }
        if(current.getRight() != null){
            currentLevel--;
            deepestLevel = rightHeightBalancedHelper(current.getRight(), currentLevel, deepestLevel, start);
        }
        if(current != start){
            if(current.getLeft() != null){
                currentLevel--;
                deepestLevel = rightHeightBalancedHelper(current.getLeft(), currentLevel, deepestLevel, start);
            }
        }
        else{
            return deepestLevel;
        }
        System.out.println("deepest right: " + deepestLevel);

        return deepestLevel;
    }

    public int leftHeightBalancedHelper(Node current, int currentLevel, int deepestLevel, Node start){
        currentLevel++;
        if(current.getLeft() == null && current.getRight() == null){
            if(currentLevel > deepestLevel){
                deepestLevel = currentLevel;
            }
            return deepestLevel;
        }
        if(current.getLeft() != null){
            currentLevel--;
            deepestLevel = leftHeightBalancedHelper(current.getLeft(), currentLevel, deepestLevel, start);
        }
        if(current != start){
            if(current.getRight() != null){
                currentLevel--;
                deepestLevel = leftHeightBalancedHelper(current.getRight(), currentLevel, deepestLevel, start);

            }
        }
        else if(current == start){
            return deepestLevel;
        }
        System.out.println("deepest left: " + deepestLevel);
        return deepestLevel;
    }

    public int determineRotationNeeded(Node node) {
        Integer balanceFactor = getBalanceFactor(node);

        if(balanceFactor > 1){ // problem is to the left
            if(node.getLeft().getRight() != null && (node.getLeft().getRight().getLeft() != null || node.getLeft().getRight().getRight() != null)){
                // left-right case --> left-right rotation
                return 1;
            }
            else if(node.getLeft().getLeft() != null){
                // left-left case --> need right rotation
                // it's fine to check the left first because the node before the problem node can only have 1 node attached to it (bc it balances as soon as an additional level is added) --> can happen with removal though because could add the 2 and then lessen the level of the other side which makes the balance off
                return 2;
            }
        }
        else if(balanceFactor < 1){ // problem is to the right
            if(node.getRight().getLeft() != null && (node.getRight().getLeft().getRight() != null || node.getRight().getLeft().getLeft() != null)){ // 2nd part of and statement won't run if the first part is false (short circuit)
                // right-left case --> right-left rotation
                return 3;
            }
            else if(node.getRight().getRight() != null){
                // right-right case --> left rotation
                return 0;
            }
        }

        /*
            Return values:
                0 - Left Rotation
                1 - Left-Right Rotation
                2 - Right Rotation
                3 - Right-Left Rotation
         */
        return 0;
    }


    // Done - ROTATIONS -- NEED TO CHECK IF THE NODE IS THE ROOT / IF THE PREVIOUS IS NULL!!!
    public void leftRotate(Node node, Node previous) {
        if(node == root){
            root = node.getRight(); // it's ok to do node.getRight(), it has to exist based on the other code
            Node leftNode = root.getLeft(); // the current left
            root.setLeft(node);
            node.setRight(leftNode);
        }
        else{
            Node rightNode = node.getRight();

            if(previous.getRight() == node){
                previous.setRight(rightNode);
            }
            else if(previous.getLeft() == node){
                previous.setLeft(rightNode);
            }

            Node currentLeft = rightNode.getLeft();
            rightNode.setLeft(node);
            node.setRight(currentLeft);
            // leave whatever rightNode has to the right because it can keep it
        }
    }

    public void rightRotate(Node node, Node previous) {
        if(node == root){
            root = node.getLeft();
            Node rightNode = root.getRight();
            root.setRight(node);
            node.setLeft(rightNode);
        }
        else{
            Node leftNode = node.getLeft();

            if(previous.getRight() == node){
                previous.setRight(leftNode);
            }
            else if(previous.getLeft() == node){
                previous.setLeft(leftNode);
            }

            Node currentRight = leftNode.getRight();
            leftNode.setRight(node);

            node.setLeft(currentRight);
        }
    }




    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.print(" " + node.getData());
            if (node.getLeft() != null) {
                nodes.add(node.getLeft());
            }
            if (node.getRight() != null) {
                nodes.add(node.getRight());
            }
        }
        System.out.println();
    }





    public class Node {
        //instance variable of Node class
        public int data;
        public Node left;
        public Node right;




        //constructor
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }






}
