public class RedBlackTree<E extends Comparable<E>> {
    private static boolean RED = false;
    private static boolean BLACK = true;
private  Node Root = null;



   public static class Node <E extends Comparable<E> >
    {
       protected Node<E> Right= null, Left = null, Parent = null;
        protected    E Data ;
        protected  boolean color = RED;

        Node(E Data)
        {
            this.Data = Data;
        }
    }
    public void inorderTraversal()
    {
        inorderTraversal(this.Root);
    }
    private void  inorderTraversal(Node<E> root)
    {
        if (root != null)
        {
            inorderTraversal(root.Left);
            System.out.println(root.Data);
            inorderTraversal(root.Right);
        }
    }

    public void insert (E Key)
    {
         Node<E> freshNode = new Node<E>(Key);
         if(this.Root == null)
         {
             freshNode.color = BLACK;
             this.Root = freshNode;
         }
         else {
             this.Root = BuildTree(this.Root, freshNode);
             fixTree(FindNode(Key));
         }


    }

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

    private void rightRotation(Node<E> node)
    {
        System.out.println("Rotating Right"+ node.Data);
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

    private  void leftRotation(Node<E> node)
    {
        System.out.println("Rotating Left" + node.Data);
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

      public Node<E> FindNode(E key)
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



      public void preOrderTraversal()
      {
          preOrderTraversal(this.Root);
      }



         public  void preOrderTraversal(Node<E> root)
         {
             if(root != null) {

                 if(root.color ==RED)
                 System.out.println(root.Data + " red");
                 else
                     System.out.println(root.Data +" black");
                 preOrderTraversal(root.Left);
                 preOrderTraversal(root.Right);
             }

         }


         public void fixTree(Node<E> node)
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

                 if( uncle == null || uncle.color == BLACK )
                 {
                     fixTree(restructureTree(node,grandParent,parent));
                 }
                 else if( uncle != null && uncle.color == RED)
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
                     fixTree(grandParent);

                 }

             }

         }

    public Node<E> restructureTree(Node<E> node,Node<E> grandParent, Node<E> parent)
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





    public Node<E> FindUncle( Node<E> node)// returns the uncle of node
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
