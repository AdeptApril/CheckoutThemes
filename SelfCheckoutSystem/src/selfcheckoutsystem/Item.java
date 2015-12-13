package selfcheckoutsystem;

/**

 @author martinjose
 */
public class Item
{
   private int id; // Serial number to identify item
   private String type; // Type of item
   private boolean available; // Checked out or available
   
   public Item (int newId, String newType, boolean status)
   {
      id = newId; 
      type = newType; 
      available = status;
   }
   
   /**
   Returns the id # of the item.
   @return int value of item which invoked method.
   */
   public int getID()
   {
      return id;
   }
   
   /**
   Returns the type of the item.
   @return string type of the the item which invoked the method.
   */
   public String getType()
   {
      return type;
   }
   
   /**
   Returns the state of availability of the item.
   @return boolean availability of the the item which invoked the method.
   */
   public boolean isAvailable()
   {
      return available;
   }
   
   /**
   changes the state of availability to the input state.
   @param status boolean state to be set.
   */
   public void setAvailable(boolean status)
   {
      available = status;
   }
}
