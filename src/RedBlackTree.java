import java.util.ArrayList;
import java.util.List;

/** Noel Romero
 * Nxr170030
 * 3345.004
 */

/**
 * Class Red black Tree
 * This program will perform insertion and rebalancing based and abide by the rules of a red black tree
 *  This program will also take in any object that extends comparable
 * @param <E>
 */

public class RedBlackTree<E extends Comparable<E>> {
    private static boolean RED = false;// the boolean field RED represents the redblack tree's node color withe it being set to false
    private static boolean BLACK = true;// the boolean field Black represents the redblack tree's node color withe it being set to true
    private  Node Root = null;

    /**
     * @Class Node
     * @ Description: This class will take an a data type that extends comparable
     * These will be the nodes of being inseted into the redBlack tree
     *
     * @param <E>
     */

    public static class Node <E extends Comparable<E> >
    {
        private Node<E> Right= null, Left = null, Parent = null;
        private     E Data ;
        private boolean color = RED;

        Node(E Data)
        {
            this.Data = Data;
        }
    }

    /**
     * @Function insert
     * @description This function takes in a Key from the data and checks to see if the value exists in the tree if
     * the key is already in the tree then the insert function does not call the appropriate functions to insert the key value
     * into the tree if the key does not already exist in the tree the insert function calls the appropriate functions to enter into the tree
     * @param Key takes in the a key token of data type E
     * @return returns true if the insertion was successful
     * @throws IllegalArgumentException
     */

    public boolean insert (E Key)throws NullPointerException
    {
        try {
            if (!contains(Key)) // if key does not exist in RB Tree condition is met
            {
                Node<E> freshNode = new Node<E>(Key);
                if (this.Root == null) // checks to see if the tree is currently empty
                {
                    freshNode.color = BLACK; // sets the nodes color to black
                    this.Root = freshNode; // sets the root to fresh node
                } else // if tree not empty
                {
                    this.Root = BuildTree(this.Root, freshNode); // first insert the node according Binary search tree rules
                    fixTree(FindNode(Key)); // fix the tree to meet the requirements of a redBlack Tree
                }

                return true; //return true if insertion is met
            } else {
                return false; // return false if no insertion was done
            }
        }
        catch (NullPointerException e) // catch null exceptions
        {
            throw  new NullPointerException("Invalid Object Type"); //
        }

    }

    /**
     * Function : Build Tree
     * Description: This function inserts the node into the appropriate place according to the rules of a Binary search tree
     * and then using recursion returns the first node in the parameter  "root" which is the new version of the tree with the addition of the node being inserted
     * @param root
     * @param node
     * @return Node root
     */
    private  Node<E> BuildTree(Node<E> root, Node<E> node)
    {
        if (root == null)
        {
            return node;
        }


        if (root.Data.compareTo(node.Data)> 0)
        {
            root.Left = BuildTree(root.Left, node);
            root.Left.Parent = root;
        }
        else if( root.Data.compareTo(node.Data) < 0)
        {
            root.Right = BuildTree(root.Right, node);
            root.Right.Parent = root;
        }
        else
        {
            return null;
        }


        return root;

    }

    /**
     * @method rightRotation
     * @descriotion Takes in a node and moves its left child node in its place
     * and becomes the right child of that ex left child node. That ex left child node also gets the parant of the node
     * in the parameter
     * @param node
     */

    private void rightRotation(Node<E> node)
    {

        Node<E> tempLeft = node.Left;
        node.Left = tempLeft.Right;

        if(node.Left != null)
        {
            node.Left.Parent = node;
        }
        tempLeft.Parent = node.Parent;

        if(tempLeft.Parent == null)
        {
            this.Root =tempLeft;

        }
        else if( node == node.Parent.Left)
        {
            node.Parent.Left = tempLeft;
        }
        else
        {
            node.Parent.Right = tempLeft;

        }

        tempLeft.Right = node;
        node.Parent = tempLeft;


    }

    /**
     * @method: leftRotation
     * @description Takes in a node and moves its Right child node in its place
     *      * and becomes the right child of that ex Right child node. That ex Right child node also gets the parent of the node
     *      * in the parameter
     * @param node
     */
    private  void leftRotation(Node<E> node)
    {

        Node<E> tempRight = node.Right;
        node.Right = tempRight.Left;

        if(node.Right != null)
        {
            node.Right.Parent = node;
        }
        tempRight.Parent = node.Parent;

        if(tempRight.Parent == null)
        {
            this.Root = tempRight;
        }
        else if( node == node.Parent.Right)
        {
            node.Parent.Right = tempRight;
        }
        else
        {
            node.Parent.Left = tempRight;
        }
        tempRight.Left = node;
        node.Parent = tempRight;


    }


    /**
     * @method recolor
     * @description takes in the newly rotated node that was previously a child node now parent node and recolors
     * itself and its children by the rules of a RB tree
     * @param tempLeft
     */

    private void recolor(Node<E> tempLeft)
    {
        tempLeft.color= BLACK ;

        if(tempLeft.Left != null && tempLeft.Right != null)
        {

            tempLeft.Right.color = RED;
            tempLeft.Left.color = RED;
        }
        else if (tempLeft.Left != null && tempLeft.Right == null)
        {
            tempLeft.Left.color = RED;
        }
        else if(tempLeft.Left == null && tempLeft.Right != null)
        {
            tempLeft.Right.color = RED;
        }
    }

    /**
     * @method  FindNode
     * @description  returns a node with the match data that correspounds with the key
     * in the parameter
     * @param key
     * @return
     */
    private Node<E> FindNode(E key)
    {


        return FindNode( this.Root, key);
    }

    private  Node<E> FindNode (Node<E> root, E key)
    {
        if(root == null)
            return null;

        if(root.Data.compareTo(key)== 0)
        {
            return  root;
        }
        else if(root.Data.compareTo(key)> 0)
        {
            return FindNode(root.Left,key);
        }
        else if(root.Data.compareTo(key) < 0)
        {
            return  FindNode(root.Right,key);
        }
        else {
            return null;
        }
    }

    /**
     * @method
     * @description returns true if the key is found within the tree and false if it is not found
     * @param key
     * @return
     */
    public boolean contains(E key)
    {
        return contains(this.Root,key);
    }




    private boolean contains(Node<E> root, E key )
    {
        if(root == null)
        {
            return false;
        }
        if(root.Data.compareTo(key)==0)
        {
            return true;
        }
        else if(root.Data.compareTo(key) >0)
        {
            return  contains(root.Left,key);
        }
        else if(root.Data.compareTo(key)< 0)
        {
            return  contains(root.Right,key);
        }
        else
        {
            return false;
        }

    }


    /**
     * @method toString
     * @description  returns the contents of the tree in a preOrder fashion in a tree
     * @return
     */
    public String toString()
    {
        ArrayList<String> preOrder = new ArrayList<>(10);
        toString(preOrder,this.Root);
        String str = "";
        for(String x: preOrder)
        {
            str += x;
        }
        return str;
    }



    private   void toString(List<String> preOrderList, Node<E> root)
    {
        if(root != null) {

            if(root.color ==RED) {
                preOrderList.add("*");
                if(root.Data instanceof String)
                    preOrderList.add((String) root.Data);
                else
                    preOrderList.add(Integer.toString((Integer) root.Data));
                preOrderList.add(" ");
            }
            else {
                if(root.Data instanceof String)
                    preOrderList.add((String) root.Data);
                else
                    preOrderList.add(Integer.toString((Integer) root.Data));
                preOrderList.add(" ");

            }
            toString(preOrderList,root.Left);
            toString(preOrderList,root.Right);
        }

    }

    /**
     * @method fixTree
     * @description  corrects the tree by following the rules of a red black tree
     *  node double reds and same black depth
     * calling the appropriate  function to perform just that and recursively fix the tree where needed
     * @param node
     */
    private void fixTree(Node<E> node)
    {

        if(node.Parent == null || node.Parent.color == BLACK ||(node.color == BLACK && node.Parent.color==RED))
        {
            return;
        }
        else
        {
            Node<E> grandParent = node.Parent.Parent;
            Node<E> parent = node .Parent;
            Node<E> uncle = FindUncle(node);

            if( uncle == null || uncle.color == BLACK ) // case where rotation is needed
            {
                fixTree(restructureTree(node,grandParent,parent)); // call the restructure function then recursively call the fix function to fix other nodes that violated the RB tree rules after  rotations
            }
            else if( uncle != null && uncle.color == RED) // case where uncle grandparent and parent need to be recolored
            {
                uncle.color = BLACK;
                parent.color = BLACK;
                if(grandParent == this.Root)
                {
                    grandParent.color = BLACK;
                }
                else
                {
                    grandParent.color = RED;
                }
                fixTree(grandParent); // check if there are any double red cases after recoloring

            }

        }

    }

    /**
     * @method restructureTree
     * @description  restructure  the tree according to what rotation case is needed and then call the re color function
     * return the node that was recently a child and got eleveted to parent
     * @param node
     * @param grandParent
     * @param parent
     * @return
     */

    private Node<E> restructureTree(Node<E> node,Node<E> grandParent, Node<E> parent)
    {
        if((node == parent.Left) && (parent== grandParent.Left)) //Left Left Case
        {
            rightRotation(grandParent);
            recolor(parent);
            return parent;
        }
        else if( (node == parent.Right) && (parent == grandParent.Left)) // Left Right case
        {
            leftRotation(parent);
            rightRotation(grandParent);
            recolor(node);
            return node;
        }
        else if ((node == parent.Right) && (parent== grandParent.Right) ) //Right Right case
        {
            leftRotation(grandParent);
            recolor(parent);
            return parent;

        }
        else if((node == parent.Left) && (parent== grandParent.Right)) //Right Left Case
        {
            rightRotation(parent);
            leftRotation(grandParent);
            recolor(node);
            return node;
        }

        return null;
    }


    /**
     * @method FindUncle
     * @description  find the uncle node of the node passed in the parameter
     * and return that uncle node
     * @param node
     * @return
     */

    private Node<E> FindUncle( Node<E> node)// returns the uncle of node
    {
        if (node.Parent == node.Parent.Parent.Left)
        {
            return node.Parent.Parent.Right;
        }
        else
        {
            return node.Parent.Parent.Left;
        }
    }


}
