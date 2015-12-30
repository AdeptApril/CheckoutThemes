/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selfcheckoutsystem;

import java.net.URL;

/**
 *
 * @author mintlaptop
 */
public class EnterItemForm extends javax.swing.JFrame {

    /**
     * Creates new form EnterItemForm
     */
    public EnterItemForm() {
        initComponents();
        enterItemID.requestFocusInWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents()
   {

      jScrollPane2 = new javax.swing.JScrollPane();
      cartTextArea = new javax.swing.JTextArea();
      enterItemID = new javax.swing.JTextField();
      goButton = new javax.swing.JButton();
      finishButton = new javax.swing.JButton();
      EnterItemScreenTextArea = new javax.swing.JTextArea();
      backgroundImage = new javax.swing.JLabel();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setBounds(new java.awt.Rectangle(0, 0, 640, 480));
      setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
      setMinimumSize(new java.awt.Dimension(640, 480));
      getContentPane().setLayout(null);

      cartTextArea.setColumns(20);
      cartTextArea.setRows(5);
      jScrollPane2.setViewportView(cartTextArea);

      getContentPane().add(jScrollPane2);
      jScrollPane2.setBounds(10, 70, 136, 340);
      getContentPane().add(enterItemID);
      enterItemID.setBounds(180, 380, 227, 25);

      goButton.setText("Go");
      goButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            goButtonActionPerformed(evt);
         }
      });
      getContentPane().add(goButton);
      goButton.setBounds(460, 380, 67, 29);

      finishButton.setText("Finish");
      finishButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            finishButtonActionPerformed(evt);
         }
      });
      getContentPane().add(finishButton);
      finishButton.setBounds(420, 190, 165, 29);

      EnterItemScreenTextArea.setEditable(false);
      EnterItemScreenTextArea.setColumns(20);
      EnterItemScreenTextArea.setRows(5);
      EnterItemScreenTextArea.setText("Enter item #");
      getContentPane().add(EnterItemScreenTextArea);
      EnterItemScreenTextArea.setBounds(160, 0, 324, 75);

      backgroundImage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
      backgroundImage.setMaximumSize(new java.awt.Dimension(640, 480));
      backgroundImage.setMinimumSize(new java.awt.Dimension(640, 480));
      getContentPane().add(backgroundImage);
      backgroundImage.setBounds(0, 0, 650, 460);

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        // TODO add your handling code here:
        itemID = null;
        this.setVisible(false);
    }//GEN-LAST:event_finishButtonActionPerformed

   private void goButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_goButtonActionPerformed
   {//GEN-HEADEREND:event_goButtonActionPerformed
        itemID = enterItemID.getText();
        this.setVisible(false);
   }//GEN-LAST:event_goButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EnterItemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnterItemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnterItemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnterItemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnterItemForm().setVisible(true);
            }
        });
    }

   public void setBackground(String filename)
   {
      URL fileurl = LanguageSelectionForm.class.getResource("/selfcheckoutsystem/images/" + filename);
      if (fileurl != null)
      {
         backgroundImage.setIcon(new javax.swing.ImageIcon(fileurl)); // NOI18N
      }
   }
   
   public void setText(String text)
   {
      EnterItemScreenTextArea.setText(text);
   }
   
   public void setCartText(String text)
   {
      cartTextArea.append(text);
   }
   
   public String getItemID()
    {
        return itemID;
    }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JTextArea EnterItemScreenTextArea;
   private javax.swing.JLabel backgroundImage;
   private javax.swing.JTextArea cartTextArea;
   private javax.swing.JTextField enterItemID;
   private javax.swing.JButton finishButton;
   private javax.swing.JButton goButton;
   private javax.swing.JScrollPane jScrollPane2;
   // End of variables declaration//GEN-END:variables
   private String itemID = null;
}
