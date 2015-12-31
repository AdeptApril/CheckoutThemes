/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selfcheckoutsystem;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;
import static java.lang.Thread.sleep;

/**
 * This class controls the running of the system, and thus has relatively loose
 * cohesion; it's running through the various things needed for checking out
 *
 * @author
 */
public class SelfCheckoutSystem
{
   //TODO: 
   //-Sounds sometimes cut off
   //-Have items have names and not just numbers.
   //-The cart list is just the patron's list of currently-checked out items
   //-Possibly update the databases with the items that change during checkout

   private final static int MAX_LANGUAGES = 4;
   private final static String shutdownUserID = "1234";

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) throws InterruptedException
   {
      initPatronList();
      initItemList();
      //Now the system runs the self-checkout, never to stop.
      //...unless the shutdown user ID is entered.
      while (true)
      {
         //Load language options
         loadLanguageOptions();
         Language lang1 = new Language(langOptions.get(0));
         Language lang2 = new Language(langOptions.get(1));
         Language lang3 = new Language(langOptions.get(2));
         Language lang4 = new Language(langOptions.get(3));
         Language lang5 = new Language(langOptions.get(4));
         Language lang6 = new Language(langOptions.get(5));
         //Load the LanguageSelectionForm
         LanguageSelectionForm lang = new LanguageSelectionForm();
         lang.updateLang1(lang1.name(), lang1.icon());
         lang.updateLang2(lang2.name(), lang2.icon());
         lang.updateLang3(lang3.name(), lang3.icon());
         lang.updateLang4(lang4.name(), lang4.icon());
         lang.updateLang5(lang5.name(), lang5.icon());
         lang.updateLang6(lang6.name(), lang6.icon());
         lang.setVisible(true);
         //That will choose which set of language text we'll use.
         while (lang.isVisible())
         {
            sleep(500);
         }
         languageNum = lang.getLanguageNum();
         System.out.println("Using language #" + languageNum);
         Language chosenLang = new Language(langOptions.get(languageNum - 1));

         //Load the login screen
         LoginForm login = new LoginForm();
         login.setBackground(chosenLang.scanCardImage());
         login.setText(chosenLang.scanCardText());
         login.setVisible(true);
         playSound(chosenLang.scanCardSound());
         while (login.isVisible())
         {
            sleep(500);
         }
         userID = login.getUserID();
         System.out.println("User ID: " + userID);
         if (userID.equals(shutdownUserID))
         {
            System.exit(0); //The program should end now.
         }

         //Check the login info
         currPatron = findPatron(userID);
         //If login info bad
         if (currPatron == null)
         {
            //Load badCard screen
            StandardForm badCard = new StandardForm();
            badCard.setBackground(chosenLang.badCardImage());
            badCard.setText(chosenLang.badCardText());
            badCard.setVisible(true);
            playSound(chosenLang.badCardSound());
            sleep(8000);
            badCard.setVisible(false);
            continue; //Go back to language selection
         }
         //If login info good (If we get here, it should be)
         cartPatron = new Patron(currPatron.getID()); //This should be an itemless Patron
         //Load the EnterItem screen
         //Should loop back here until "finish" is chosen
         boolean finished = false;
         while (!finished)
         {
            EnterItemForm item = new EnterItemForm();
            item.setBackground(chosenLang.scanItemImage());
            item.setText(chosenLang.scanItemText());
            item.setCartText("Items checked out: \n");
            for(Integer i: cartPatron.getList() )
               item.setCartText(i.toString() + "\n");

            item.setVisible(true);
            playSound(chosenLang.scanItemSound());
            while (item.isVisible())
            {
               sleep(500);
            }
            String itemID = item.getItemID();
            if (itemID == null)
            {
               finished = true;
            } else
            {
               System.out.println("Item ID: " + itemID);
               currItem = findItem(itemID);
               if (currItem == null || (!currItem.isAvailable() && !(Arrays.asList(cartPatron.getList()).contains(Integer.parseInt(itemID.trim())) ) ))
               {
                  //If item number bad (bad type, unavailable, any other reason)
                  //Load badItem screen
                  StandardForm badItem = new StandardForm();
                  badItem.setBackground(chosenLang.badItemImage());
                  badItem.setText(chosenLang.badItemText());
                  badItem.setVisible(true);
                  playSound(chosenLang.badItemSound());
                  sleep(3000);
                  badItem.setVisible(false);
                  badItem.dispose();
               } else //If item number bad (already checked out)
                if (Arrays.asList(cartPatron.getList()).contains(Integer.parseInt(itemID.trim())))
                  {
                     //Load itemAlreadyAttempted screen
                     StandardForm itemAlreadyAttempted = new StandardForm();
                     itemAlreadyAttempted.setBackground(chosenLang.itemAlreadyAttemptedImage());
                     itemAlreadyAttempted.setText(chosenLang.itemAlreadyAttemptedText());
                     itemAlreadyAttempted.setVisible(true);
                     playSound(chosenLang.itemAlreadyAttemptedSound());
                     sleep(3000);
                     itemAlreadyAttempted.setVisible(false);
                     itemAlreadyAttempted.dispose();
                  } else
                  {
                     //If item number good
                     //Load goodItem screen
                     StandardForm goodItem = new StandardForm();
                     goodItem.setBackground(chosenLang.goodItemImage());
                     goodItem.setText(chosenLang.goodItemText());
                     goodItem.setVisible(true);
                     playSound(chosenLang.goodItemSound());
                     sleep(3000);
                     currPatron.addItem(Integer.parseInt(itemID.trim()));
                     cartPatron.addItem(Integer.parseInt(itemID.trim()));
                     findItem(itemID.trim()).setAvailable(false);
                     goodItem.setVisible(false);
                     goodItem.dispose();
                  }
            }
            item.dispose();
         } //end of item while loop.

         //If "finished" pushed (or timer runs out)
         //Load goodbye screen
         StandardForm goodbye = new StandardForm();
         goodbye.setBackground(chosenLang.takeReceiptImage());
         goodbye.setText(chosenLang.takeReceiptText());
         goodbye.setVisible(true);
         playSound(chosenLang.takeReceiptSound());
         sleep(10000);
         goodbye.setVisible(false);

         //Dispose of all frames - could be done at other times.
         lang.dispose();
         login.dispose();
         //item.dispose(); //done elsewhere.
         goodbye.dispose();
         //badCard.dispose(); //done elsewhere
         //goodItem.dispose(); //done elsewhere
         //itemAlreadyAttempted.dispose(); //done elsewhere
         //badItem.dispose(); //done elsewhere
      } //end of checkout while loop
   }

   private static Patron findPatron(String idString)
   {
      int id;
      try
      {
         id = Integer.parseInt(idString.trim());
      } catch (Exception ex)
      {
         System.out.println("Something wrong with the patronID: " + ex);
         return null;
      }
      //Attempt to find the patron in the patron list, and return if found
      int patronListSize = patronList.size();
      for (int i = 0; i < patronListSize; i++)
      {
         if (patronList.get(i).getID() == id)
         {
            return patronList.get(i);
         }
      }
      return null; //Nothing found? Return null
   }

   private static Item findItem(String idString)
   {
      int id;
      try
      {
         id = Integer.parseInt(idString.trim());
      } catch (Exception ex)
      {
         System.out.println("Something wrong with the itemID: " + ex);
         return null;
      }
      //Attempt to find the patron in the patron list, and return if found
      int itemListSize = itemList.size();
      for (int i = 0; i < itemListSize; i++)
      {
         if (itemList.get(i).getID() == id)
         {
            return itemList.get(i);
         }
      }
      return null; //Nothing found? Return null
   }

   //Taken from http://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
   public static synchronized void playSound(final String filename)
   {
      try
      {
         Clip clip = AudioSystem.getClip();
         AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                 SelfCheckoutSystem.class.getClass().getResourceAsStream("/selfcheckoutsystem/sounds/" + filename));
         clip.open(inputStream);
         clip.start();
      } catch (Exception e)
      {
         System.out.println(e.getMessage());
      }
   }

   private static void loadLanguageOptions()
   {
      String filename = "languageOptions.txt"; //Where the language list resides
      URL fileurl = SelfCheckoutSystem.class.getClass().getResource("/selfcheckoutsystem/configuration/" + filename);
      String line = null;
      try
      { //Probably a more-compact way of doing this with Scanner
         int i = 0;
         FileReader fileReader = new FileReader(new File(fileurl.getFile()));
         BufferedReader themeReader = new BufferedReader(fileReader);
         while ((line = themeReader.readLine()) != null)
         {
            langOptions.add(line);
            i++;
         }
         themeReader.close();
      } catch (IOException ex)
      {
         System.out.println("IOException: " + ex);
      }
   }

   private static void initPatronList()
   {
      String filename = "patrons.txt"; //Where the patrons list resides
      URL fileurl = SelfCheckoutSystem.class.getClass().getResource("/selfcheckoutsystem/configuration/" + filename);
      String line = null;
      try
      { //Probably a more-compact way of doing this with Scanner
         //TODO: Make it so it actually reads in the list of items a patron has checked out
         //Currently it just takes the patron ID bit and ignores the rest.
         int i = 0;
         FileReader fileReader = new FileReader(new File(fileurl.getFile()));
         BufferedReader patronReader = new BufferedReader(fileReader);
         patronReader.readLine(); //First line is ignored
         while ((line = patronReader.readLine()) != null)
         {
            int id;
            String[] parts = line.split(" ");
            try
            {
               id = Integer.parseInt(parts[0].trim());
            } catch (Exception ex)
            {
               System.out.println("Something wrong with the patronID: " + ex);
               continue;
            }
            patronList.add(new Patron(id));
            i++;
         }
         patronReader.close();
      } catch (IOException ex)
      {
         System.out.println("IOException: " + ex);
      }
   }

   /**
    * Initializes the item list. Assumes that it's a text file with the first
    * line ignored, then every line after that is in the format of: ItemID type
    * status All three should be there. Space delimited.
    */
   private static void initItemList()
   {
      String filename = "items.txt"; //Where the items list resides
      URL fileurl = SelfCheckoutSystem.class.getClass().getResource("/selfcheckoutsystem/configuration/" + filename);
      String line = null;
      try
      { //Probably a more-compact way of doing this with Scanner
         int i = 0;
         FileReader fileReader = new FileReader(new File(fileurl.getFile()));
         BufferedReader itemReader = new BufferedReader(fileReader);
         itemReader.readLine(); //First line is ignored
         while ((line = itemReader.readLine()) != null)
         {
            int id;
            boolean status;
            String[] parts = line.split(" ");
            try
            {
               id = Integer.parseInt(parts[0].trim());
               status = Boolean.parseBoolean(parts[2].trim());
            } catch (Exception ex)
            {
               System.out.println("Something wrong with the item construction: " + ex);
               continue;
            }
            itemList.add(new Item(id, parts[1].trim(), status));
            i++;
         }
         itemReader.close();
      } catch (IOException ex)
      {
         System.out.println("IOException: " + ex);
      }
   }

   private static int languageNum;
   private static String userID;
   private static Patron currPatron;
   private static Patron cartPatron;
   private static Item currItem;
   private static ArrayList<String> langOptions = new ArrayList<String>();
   private static ArrayList<Patron> patronList = new ArrayList<Patron>();
   private static ArrayList<Item> itemList = new ArrayList<Item>();
}
