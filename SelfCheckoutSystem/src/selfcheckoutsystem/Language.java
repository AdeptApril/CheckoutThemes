/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selfcheckoutsystem;

import java.net.URL;
import java.util.*;
import java.io.*;
import java.awt.Font;

/**
 *
 * @author mintlaptop
 */
public class Language
{

   /**
    * Constructor. Needs to know which language to use.
    *
    * @param filename - name of the language file to use.
    */
   public Language(String filename)
   {
      loadLanguageFile(filename);
      setNameAndIcon();
      setScanCard();
      setScanItem();
      setGoodItem();
      setTakeReceipt();
      setBadCard();
      setBadItem();
      setItemAlreadyAttempted();
   }

   /**
    * Loads the specified ini file into a container for later use
    *
    * @param filename - name of the language ini file
    */
   private void loadLanguageFile(String filename)
   {
      //Read language file into iniList
      //TODO: catch filenames not leading to actual files
      // -fileurl will be null in that case, but what to do with that?
      URL fileurl = Language.class.getClass().getResource("/selfcheckoutsystem/themes/" + filename);
      try
      {
         Scanner scanner = new Scanner(new File(fileurl.getFile()));
         while (scanner.hasNextLine())
         {
            iniList.add(scanner.nextLine());
         }

      } catch (Exception ex)
      {
         System.out.println("Exception: " + ex);
      }
   }

   /*
   Set themeName and icon
    */
   private void setNameAndIcon()
   {
      int index = iniList.indexOf("[Languages]");
      for (int i = index + 1; i < index + 5; i++)
      { //Maybe not elegant, but I want to run at most five times
         String line = iniList.get(i);
         if (line.contains("Name="))
         {
            themeName = line.replace("Name=", "");
         }
         if (line.contains("Icon="))
         {
            themeIcon = line.replace("Icon=", "");
         }
      }
   }

   /*
   Sets all the needed bits for the Scan Card screen
    */
   private void setScanCard()
   {
      //Set ScanCard (Enter Patron ID)
      //[ScanCard]
      int index = iniList.indexOf("[ScanCard]");
      for (int i = index + 1; i < index + 8; i++)
      {
         String line = iniList.get(i);
         if (line.contains("MessageText="))
         {
            scanCardText = line.replace("MessageText=", "");
         }
         if (line.contains("MessageTextSize="))
         {
            String scanCardFontSize = line.replace("MessageTextSize=", "");
            //Could read the font type, but it's unlikely that the font will match up.
            scanCardFont = new Font("DejaVu Sans", Font.PLAIN, (int)((Integer.parseInt(scanCardFontSize))*1) );
         }
         if (line.contains("PictureFile="))
         {
            scanCardImage = line.replace("PictureFile=", "");
         }
         if (line.contains("Sound="))
         {
            scanCardSound = line.replace("Sound=", "");
         }
      }
      scanCardText = scanCardText.replaceAll("\\{CRLF}", "\\\n");
   }

   /**
    * Sets all the needed bits for the Scan Item screen
    */
   private void setScanItem()
   {
      //[ScanItem]
      int index = iniList.indexOf("[ScanItem]");
      for (int i = index + 1; i < index + 8; i++)
      {
         String line = iniList.get(i);
         if (line.contains("MessageText="))
         {
            scanItemText = line.replace("MessageText=", "");
         }
         if (line.contains("PictureFile="))
         {
            scanItemImage = line.replace("PictureFile=", "");
         }
         if (line.contains("Sound="))
         {
            scanItemSound = line.replace("Sound=", "");
         }
      }
      scanItemText = scanItemText.replaceAll("\\{CRLF}", "\\\n");
   }

   /**
    * Sets all the needed bits for the GoodItem screen
    */
   private void setGoodItem()
   {
      int index = iniList.indexOf("[GoodItem]");
      for (int i = index + 1; i < index + 6; i++)
      {
         String line = iniList.get(i);
         if (line.contains("MessageText="))
         {
            goodItemText = line.replace("MessageText=", "");
         }
         if (line.contains("PictureFile="))
         {
            goodItemImage = line.replace("PictureFile=", "");
         }
         if (line.contains("Sound="))
         {
            goodItemSound = line.replace("Sound=", "");
         }
      }
      goodItemText = goodItemText.replaceAll("\\{CRLF}", "\\\n");
   }

   /**
    * Sets all the needed bits for the Take Receipt (Goodbye) screen
    */
   private void setTakeReceipt()
   {
      int index = iniList.indexOf("[TakeReceipt]");
      for (int i = index + 1; i < index + 6; i++)
      {
         String line = iniList.get(i);
         if (line.contains("MessageText="))
         {
            takeReceiptText = line.replace("MessageText=", "");
         }
         if (line.contains("PictureFile="))
         {
            takeReceiptImage = line.replace("PictureFile=", "");
         }
         if (line.contains("Sound="))
         {
            takeReceiptSound = line.replace("Sound=", "");
         }
      }
      takeReceiptText = takeReceiptText.replaceAll("\\{CRLF}", "\\\n");
   }

   //[BadCard]
   /**
    * Sets all the needed bits for the Bad Card screen
    */
   private void setBadCard()
   {
      int index = iniList.indexOf("[BadCard]");
      for (int i = index + 1; i < index + 6; i++)
      {
         String line = iniList.get(i);
         if (line.contains("MessageText="))
         {
            badCardText = line.replace("MessageText=", "");
         }
         if (line.contains("PictureFile="))
         {
            badCardImage = line.replace("PictureFile=", "");
         }
         if (line.contains("Sound="))
         {
            badCardSound = line.replace("Sound=", "");
         }
      }
      badCardText = badCardText.replaceAll("\\{CRLF}", "\\\n");
   }

   //[BadItem]
   /**
    * Sets all the needed bits for the Take Receipt (Goodbye) screen
    */
   private void setBadItem()
   {
      int index = iniList.indexOf("[BadItem]");
      for (int i = index + 1; i < index + 6; i++)
      {
         String line = iniList.get(i);
         if (line.contains("MessageText="))
         {
            badItemText = line.replace("MessageText=", "");
         }
         if (line.contains("PictureFile="))
         {
            badItemImage = line.replace("PictureFile=", "");
         }
         if (line.contains("Sound="))
         {
            badItemSound = line.replace("Sound=", "");
         }
      }
      badItemText = badItemText.replaceAll("\\{CRLF}", "\\\n");
   }

   //[ItemAlreadyAttempted]
   /**
    * Sets all the needed bits for the Item Already Attempted screen
    */
   private void setItemAlreadyAttempted()
   {
      int index = iniList.indexOf("[ItemAlreadyAttempted]");
      for (int i = index + 1; i < index + 6; i++)
      {
         String line = iniList.get(i);
         if (line.contains("MessageText="))
         {
            itemAlreadyAttemptedText = line.replace("MessageText=", "");
         }
         if (line.contains("PictureFile="))
         {
            itemAlreadyAttemptedImage = line.replace("PictureFile=", "");
         }
         if (line.contains("Sound="))
         {
            itemAlreadyAttemptedSound = line.replace("Sound=", "");
         }
      }
      itemAlreadyAttemptedText = itemAlreadyAttemptedText.replaceAll("\\{CRLF}", "\\\n");
   }

   public String name()
   {
      return themeName;
   }

   public String icon()
   {
      return themeIcon;
   }

   public String scanCardText()
   {
      return scanCardText;
   }
   
   public Font scanCardFont()
   {
      return scanCardFont;
   }

   public String scanCardImage()
   {
      return scanCardImage;
   }

   public String scanCardSound()
   {
      return scanCardSound;
   }

   public String scanItemText()
   {
      return scanItemText;
   }

   public String scanItemImage()
   {
      return scanItemImage;
   }

   public String scanItemSound()
   {
      return scanItemSound;
   }

   public String goodItemText()
   {
      return goodItemText;
   }

   public String goodItemImage()
   {
      return goodItemImage;
   }

   public String goodItemSound()
   {
      return goodItemSound;
   }

   public String takeReceiptText()
   {
      return takeReceiptText;
   }

   public String takeReceiptImage()
   {
      return takeReceiptImage;
   }

   public String takeReceiptSound()
   {
      return takeReceiptSound;
   }

   public String badCardText()
   {
      return badCardText;
   }

   public String badCardImage()
   {
      return badCardImage;
   }

   public String badCardSound()
   {
      return badCardSound;
   }

   public String badItemText()
   {
      return badItemText;
   }

   public String badItemImage()
   {
      return badItemImage;
   }

   public String badItemSound()
   {
      return badItemSound;
   }

   public String itemAlreadyAttemptedText()
   {
      return itemAlreadyAttemptedText;
   }

   public String itemAlreadyAttemptedImage()
   {
      return itemAlreadyAttemptedImage;
   }

   public String itemAlreadyAttemptedSound()
   {
      return itemAlreadyAttemptedSound;
   }

   //private String iniName;
   private String themeName = null;
   private String themeIcon = null;
   private String scanCardText = null;
   private String scanCardImage = null;
   private String scanCardSound = null;
   private String scanItemText = null;
   private Font scanCardFont = null;
   private String scanItemImage = null;
   private String scanItemSound = null;
   private String goodItemText = null;
   private String goodItemImage = null;
   private String goodItemSound = null;
   private String takeReceiptText = null;
   private String takeReceiptImage = null;
   private String takeReceiptSound = null;
   private String badCardText = null;
   private String badCardImage = null;
   private String badCardSound = null;
   private String badItemText = null;
   private String badItemImage = null;
   private String badItemSound = null;
   private String itemAlreadyAttemptedText = null;
   private String itemAlreadyAttemptedImage = null;
   private String itemAlreadyAttemptedSound = null;
   List<String> iniList = new ArrayList<>(); //The read-in text file
}
