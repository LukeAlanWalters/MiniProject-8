/**
 * BitTree, intended to store mappings from bits to values.
 * @author Luke Walters
 * Worked on this with Kevin Johanson
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;



public class BitTree {

    // +--------+------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * Keeps track of the number of levels in the BitTree
     */
    int level;

    /**
     * Keeps track of what the current node is
     */
    BitTreeNode currentNode;

    /**
     * Keeps track of the root node
     */
    BitTreeNode root;

    // +--------------+------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Creates a binary search tree which has non leaf node values set to null.
     * @param n - The number of levels in the Tree
     */
    BitTree(int n) {
        this.root = new BitTreeNode(null);
        this.level = n;
    }//BitTree(n)


    // +---------+------------------------------------------------
    // | Method |
    // +--------+

    /**
     * Dump the tree to some output location
     * Calls its helper function
     */
    public void dump(PrintWriter pen) {
        String holder = "";
        dump(pen, root, holder);
    } // dump(PrintWriter)



    /**
     * Gets a value when given a string of bits
     * @param bits
     * @return The String at the end of the search
     * @throws Exception - if the bits length is not equal to the tree level size or the bits contains a non 0/1 char
     */
    public String get(String bits) throws Exception {
        this.currentNode = this.root;

        if (bits.length() != this.level) {
            throw new Exception();
        }//if(level)

        for (int i = 0; i < bits.length(); i++) {
            if (bits.charAt(i) == '0') {
                if (this.currentNode.left == null) {
                    throw new Exception();
                }//if(0)
                this.currentNode = this.currentNode.left;
            } else if (bits.charAt(i) == '1') {
                if (this.currentNode.right == null) {
                    throw new Exception();
                }//if(1)
                this.currentNode = this.currentNode.right;
            } else {
                throw new Exception();
            }//else
        }//for()
        return this.currentNode.value;
    }//get()


    /**
     * Sets a node value at the given bit destination
     * @param bits - a string of bits which tells the tree where to set the value
     * @param value - the value to be set
     * @throws Exception - - if the bits length is not equal to the tree level size or the bits contains a non 0/1 char
     */
    public void set(String bits, String value) throws Exception {
        this.currentNode = this.root;

        if (bits.length() != this.level) {
            throw new Exception();
        }//if(level)
        for (int i = 0; i < bits.length(); i++) {
            if (bits.charAt(i) == '0') {
                if (this.currentNode.left == null) {
                    this.currentNode.left = new BitTreeNode(null);
                }//if(0)
                this.currentNode = this.currentNode.left;
            } else if (bits.charAt(i) == '1') {
                if (this.currentNode.right == null) {
                    this.currentNode.right = new BitTreeNode(null);
                }//if(1)
                this.currentNode = this.currentNode.right;
            } else {
                throw new Exception();
            }//else()
        }//for()
        this.currentNode.value = value;
    }//set()


    /**
     * A helper function for dump which printout the tree
     * @param pen - the location to print out the data
     * @param node - the node from which we start routing which should always be root
     * @param holder - the holder string value which stores the route taken to a value
     */
    public void dump(PrintWriter pen, BitTreeNode node, String holder) {

        if (node == null) {
            return;
        }//if(null)

        if ((node.left != null) || (node.right != null)) {
            dump(pen, node.left, holder.concat("0"));
            dump(pen, node.right, holder.concat("1"));
        } else if (node.value != null) {
            pen.print(holder + "," + node.value);
            pen.println();
        }//else if(node.val != null)
    } //dump(helper)

    public void load(InputStream source) throws Exception, IOException{
        String readInHolder = "";
        String[] splitHolder;
        BufferedReader reader = new BufferedReader(new InputStreamReader(source));

        while((readInHolder = reader.readLine())  != null){
            splitHolder = readInHolder.split(",");
            this.set(splitHolder[0], splitHolder[1]);
        }//while(newLine)
    }//load()

}