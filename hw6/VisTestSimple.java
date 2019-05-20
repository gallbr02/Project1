/** CS216 Data Structures
 * Scrollable Flexible Tree Visualizer
 *
 * Sunny Kim (skim@gettysburg.edu)
 *
 * --1.  takes in a tree as an array, and the degree (order) of the tree
 *       and visualizes the tree from root to leaf
 * --2.  takes in a tree as a treenode (to root) and visualize the tree
 *
 *
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

// to capture screen shots
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class VisTestSimple {

    /**
     * tests a simple binary tree built by calling BTNode constructors repeatedly to
     * link children nodes to parent.
     */
    public static void sample1() {
        BTNode<Integer> n1  = new BTNode<Integer>(1, null, null);
        BTNode<Integer> n7  = new BTNode<Integer>(7, null, null);
        BTNode<Integer> n15 = new BTNode<Integer>(15, null, null);
        BTNode<Integer> n25 = new BTNode<Integer>(25, null, null);
        BTNode<Integer> n40 = new BTNode<Integer>(40, null, null);

        BTNode<Integer> n5  = new BTNode<Integer>(5, n1, n7);
        BTNode<Integer> n10 = new BTNode<Integer>(10, n5, n15);
        BTNode<Integer> n30 = new BTNode<Integer>(30, n25, n40);
        BTNode<Integer> n20 = new BTNode<Integer>(20, n10, n30);

        TreeVis<Integer> btvis = new TreeVis<Integer>(n20, "binary tree example");
        btvis.vis();
        btvis.capture("binary_tree");   // screen capture image is saved

        try {
            Thread.sleep(2000);
        }
        catch (Exception ee) {
            System.err.println("Error thread sleeping - should never happen: " + ee);    
        }

        //btvis.dispose();   // uncomment to automatically close the window
    }

    /**
     * tests binary search tree add/remove algorithms.
     */
    public static void sample2() {
        int[] data1 = { 40, 2, 17,  9, 38, 20, 17, 2, 93,  8, 100,  95, 5};       
        int[] data2 = { 5, 20, 93, 17,  9, 40, 38, 2,  2, 17,  95, 100, 8};
        
        // create a binary search tree (of Integer)
        BSTree<Integer> bst = new BSTree<Integer>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2);
            }
        });
        
        // test 'add' algorithm
        
        String entries = "";                                      // for debugging
        String action = "";
        int entry;

        TreeVis<Integer> treeVis;
        for (int i = 0; i < data1.length; ++i) {
            entry  = data1[i];
            bst.add(entry);                                       // add to the tree
            
            action =  "after_" + entry + "_added"
                   + ((i < data1.length-1) ? "_NEXT_ADD_" + data1[i+1] : "_NEXT_REMOVE_"
                   + data2[0]);
            treeVis = new TreeVis<Integer>(bst.getRoot(), action);  
            treeVis.vis();
            treeVis.capture();
            
            entries += entry + " ";                                
            
            try {
                Thread.sleep(600);
            }
            catch (Exception ee) {
                System.err.println("Error thread sleeping - should never happen: " + ee);    
            }

            treeVis.dispose();
        }
        System.err.println("BST add order: " + entries);         

       
        // now test 'remove' algorithm
        entries = "";
        
        

    }
    public static void sample3() {
        int[] data1 = { 40, 2, 17,  9, 38, 20, 17, 2, 93,  8, 100,  95};       
        int[] data2 = { 5, 20, 93, 17,  9, 40, 38, 2,  2, 17,  95, 100, 8};
        
        // create a binary search tree (of Integer)
        BSTree<Integer> bst = new BSTree<Integer>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2);
            }
        });
        
        // test 'add' algorithm
        
        String entries = "";                                      // for debugging
        String action = "";
        int entry;

        TreeVis<Integer> treeVis;
        for (int i = 0; i < data1.length; ++i) {
            entry  = data1[i];
            bst.add(entry);                                       // add to the tree
            
            action =  "after_" + entry + "_added"
                   + ((i < data1.length-1) ? "_NEXT_ADD_" + data1[i+1] : "_NEXT_REMOVE_"
                   + data2[0]);
            treeVis = new TreeVis<Integer>(bst.getRoot(), action);  
            treeVis.vis();
            treeVis.capture();
            
            entries += entry + " ";                                
            
            try {
                Thread.sleep(2000);
            }
            catch (Exception ee) {
                System.err.println("Error thread sleeping - should never happen: " + ee);    
            }

            treeVis.dispose();
        }
        System.err.println("BST add order: " + entries);         

       
        // now test 'remove' algorithm
        entries = "";
        
        

    }
    
    // an example usage
    public static void main(String[] args) {
//    	BSTree<Integer> BSTreeTest = new BSTree<Integer>();
//    	BSTreeTest.add(11);
//    	BSTreeTest.add(40);
//    	BSTreeTest.add(15);
//    	BSTreeTest.add(52);
//    	BSTreeTest.add(2);
//    	BSTreeTest.add(27);
//    	BSTreeTest.add(99);
//    	BSTreeTest.add(52);
//    	BSTreeTest.add(11);
//    	BSTreeTest.add(12);
//    	BSTreeTest.add(17);
//    	BSTreeTest.add(3);
    	
    	//sample3();
        sample2();   // BSTree add/remove methods
       // sample1();
    }
}
