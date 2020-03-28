/*
Noel Romero
Nxr170030
CS 3345
 */
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;
public class Driver {
    public static void main(String[] args)
    {
                int [] Array ={100,20,40,60,80,45,19,30 ,24,22,27  ,63,65,67,33,28,64};       // ,63,65,67,33};
                RedBlackTree tree = new RedBlackTree();
           for(int x: Array)
           {
               tree.insert(x);

           }
tree.preOrderTraversal();
        System.out.println("New databelow*******************");

           int [] Array2 = {45,19,30,24,22,35};
          // insetToTree(Array2,tree);
        System.out.println("New databelow*******************");
        int [] Array3 = {63,65,67};
      //  insetToTree(Array3,tree);
    }
    public static void insetToTree( int [] array, RedBlackTree<Integer> tree )
    {
        for(int x: array)
        {
            tree.insert(x);
        }
        tree.preOrderTraversal();

    }


}
