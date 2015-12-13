package selfcheckoutsystem;

/**

 @author martinjose
 */
public class Patron
{
   //Nodes used for linked list keeping track of items checked out.
   private static class Node 
   {
      private int info;
      private Node next;
      public Node(int i, Node p){info = i; next = p;}
   }
   
   private int id;
   private Node items = null;
   
   //Basic constructor, no items only a patron ID.
   public Patron (int newId)
   {
      id = newId; //Set patron ID.
   }
   
   //Constructor, single item and a patron ID.
   public Patron (int newId, int newItem)
   {
      id = newId; //Set patron ID.
      items = new Node(newItem, null); //Create first node in the items list.
   }
   
   //Constructor, array of items and a patron ID.
   public Patron (int newId, Integer newItems[])
   {
      id = newId; //Set patron ID.
      
      //Create first Node in the items list.
      if (newItems.length > 0)
         items = new Node(newItems[0], null);
      
      //Add all items past first - if any - to the linked list.
      for (int i = 1; i<newItems.length; i++)
      {
         Node temp = new Node(newItems[i], items);
         items = temp;
      }
      
   }
   
   /**
    * Copy constructor
    * @param orig - Thing we're copying from
    */
   public Patron (Patron orig)
   {
      id = orig.getID();
      Integer[] newItems = orig.getList();
      //Create first Node in the items list.
      if (newItems.length > 0)
         items = new Node(newItems[0], null);
      
      //Add all items past first - if any - to the linked list.
      for (int i = 1; i<newItems.length; i++)
      {
         Node temp = new Node(newItems[i], items);
         items = temp;
      }
   }
   
   /**
   Returns patron's ID.
   @return integer patron's id
   */
   public int getID()
   {
      return id;
   }
   
   /**
   Add new item to the list of items checked-out.
   @param newItem integer ID of new checked-out item.
   */
   public void addItem(int newItem)
   {
      if (items == null)
      {
         items = new Node(newItem, null);
      }
      else
      {
         Node temp = new Node(newItem, items);
         items = temp;
      }
   }
  
   /**
   Add an array of items to the list of items checked-out.
   @param newItem integer array of IDs of new checked-out items.
   */
   public void addItem(Integer newItem[])
   {
      for (int i = 0; i < newItem.length; i++)
      {
         if (items == null)
            items = new Node(newItem[i], null);
         else
         {
            Node temp = new Node(newItem[i], items);
            items = temp;
         }
      }
   }
   
   /**
   Counts total items in the list of checked-out items and returns the result.
   @return integer count of items checked-out.
   */
   public int countItems()
   {
      if (items == null)
         return 0;
      else
      {
         int count = 1;
         Node p = items;
         
         while(p.next != null)
         {
            p = p.next;
            count++;
         }
         
         return count;
      }
   }
   
   /**
   Get the list of items checked out in array form.
   @return Integer array containing all elements of the linked list.
   */
   public Integer[] getList()
   {
      int count = countItems();
      Integer [] retList = new Integer[count];
      
      Node p = items;
      //if (p == null)
      //   retList = null;
      for(int i = count-1; i >= 0; i--)
      {
         retList[i] = p.info;
         p = p.next;
      }
      
      return retList;
      
   }
   
   //Test main.
   public static void main(String args[])
   {
      Integer [] testVals = {1,2,3,4,5};
      Patron p1 = new Patron(1, testVals);
      
      System.out.print("Output number should be 5: ");
      System.out.println(p1.countItems());
      
      Patron p2 = new Patron(2);
      
      System.out.print("Output number should be 0; ");
      System.out.println(p2.countItems());
      
      System.out.print("Outputting patron IDs... should be 1 and 2: ");
      System.out.println(p1.getID() + " " + p2.getID());
      
      System.out.print("Outputting first list... should be 1-5: ");
      Integer [] p1List = p1.getList();
      for (int i = 0; i < p1.countItems(); i++)
         System.out.print(p1List[i] + " ");
      System.out.println();
      
      System.out.print("Outputting second list... should be null: ");
      Integer [] p2List = p2.getList();
      System.out.println(p2List);
      
      System.out.print("Adding an array to the 2nd list then outputting... ");
      System.out.print("should be 1-5: ");
      p2.addItem(testVals);
      p2List = p2.getList();
      for (int i = 0; i < p2.countItems(); i++)
         System.out.print(p2List[i] + " ");
   }
}
