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
public class LanguageSelectionForm extends javax.swing.JFrame {

    /**
     * Creates new form LanguageSelectionForm
     */
    public LanguageSelectionForm() {
        initComponents();
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

      language1Button = new javax.swing.JButton();
      language2Button = new javax.swing.JButton();
      language3Button = new javax.swing.JButton();
      language4Button = new javax.swing.JButton();
      language1Label = new javax.swing.JLabel();
      language2Label = new javax.swing.JLabel();
      language3Label = new javax.swing.JLabel();
      language4Label = new javax.swing.JLabel();
      language5Button = new javax.swing.JButton();
      language5Label = new javax.swing.JLabel();
      language6Button = new javax.swing.JButton();
      language6Label = new javax.swing.JLabel();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setMinimumSize(new java.awt.Dimension(640, 480));
      setPreferredSize(new java.awt.Dimension(640, 480));

      language1Button.setText("Language 1");
      language1Button.setMaximumSize(new java.awt.Dimension(240, 88));
      language1Button.setMinimumSize(new java.awt.Dimension(240, 88));
      language1Button.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            language1ButtonActionPerformed(evt);
         }
      });

      language2Button.setText("Language 2");
      language2Button.setMaximumSize(new java.awt.Dimension(240, 88));
      language2Button.setMinimumSize(new java.awt.Dimension(240, 88));
      language2Button.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            language2ButtonActionPerformed(evt);
         }
      });

      language3Button.setText("Language 3");
      language3Button.setMaximumSize(new java.awt.Dimension(240, 88));
      language3Button.setMinimumSize(new java.awt.Dimension(240, 88));
      language3Button.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            language3ButtonActionPerformed(evt);
         }
      });

      language4Button.setText("Language 4");
      language4Button.setMaximumSize(new java.awt.Dimension(240, 88));
      language4Button.setMinimumSize(new java.awt.Dimension(240, 88));
      language4Button.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            language4ButtonActionPerformed(evt);
         }
      });

      language1Label.setText("Language 1");

      language2Label.setText("Language 2");

      language3Label.setText("Language 3");

      language4Label.setText("Language 4");

      language5Button.setText("Language 5");
      language5Button.setMaximumSize(new java.awt.Dimension(240, 88));
      language5Button.setMinimumSize(new java.awt.Dimension(240, 88));
      language5Button.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            language5ButtonActionPerformed(evt);
         }
      });

      language5Label.setText("Language 5");

      language6Button.setText("Language 6");
      language6Button.setMaximumSize(new java.awt.Dimension(240, 88));
      language6Button.setMinimumSize(new java.awt.Dimension(240, 88));
      language6Button.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            language6ButtonActionPerformed(evt);
         }
      });

      language6Label.setText("Language 6");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(language5Button, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(language6Button, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(language3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(language4Button, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(language1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(language1Label)
                     .addComponent(language3Label))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(language4Label)
                     .addComponent(language2Label)
                     .addComponent(language2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(language5Label)
                  .addGap(178, 178, 178)
                  .addComponent(language6Label)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(34, 34, 34)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(language1Label)
               .addComponent(language2Label))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(language1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(language2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(language3Label)
               .addComponent(language4Label))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(language4Button, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(language3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(language5Label)
               .addComponent(language6Label))
            .addGap(2, 2, 2)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(language5Button, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(language6Button, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(30, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void language1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_language1ButtonActionPerformed
        languageNum = 1;
        this.setVisible(false);
    }//GEN-LAST:event_language1ButtonActionPerformed

    private void language2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_language2ButtonActionPerformed
        languageNum = 2;
        this.setVisible(false);
    }//GEN-LAST:event_language2ButtonActionPerformed

    private void language3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_language3ButtonActionPerformed
        languageNum = 3;
        this.setVisible(false);
    }//GEN-LAST:event_language3ButtonActionPerformed

    private void language4ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_language4ButtonActionPerformed
        languageNum = 4;
        this.setVisible(false);
    }//GEN-LAST:event_language4ButtonActionPerformed

   private void language5ButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_language5ButtonActionPerformed
   {//GEN-HEADEREND:event_language5ButtonActionPerformed
        languageNum = 5;
        this.setVisible(false);
   }//GEN-LAST:event_language5ButtonActionPerformed

   private void language6ButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_language6ButtonActionPerformed
   {//GEN-HEADEREND:event_language6ButtonActionPerformed
        languageNum = 6;
        this.setVisible(false);
   }//GEN-LAST:event_language6ButtonActionPerformed

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
            java.util.logging.Logger.getLogger(LanguageSelectionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LanguageSelectionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LanguageSelectionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LanguageSelectionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LanguageSelectionForm().setVisible(true);
            }
        });
    }
    
    public void updateLang1(String text, String filename)
    {
        language1Label.setText(text);
        URL fileurl = LanguageSelectionForm.class.getResource("/selfcheckoutsystem/images/" + filename);
        if( fileurl != null )
            language1Button.setIcon(new javax.swing.ImageIcon(fileurl)); // NOI18N
    }
    
    public void updateLang2(String text, String filename)
    {
        language2Label.setText(text);
        URL fileurl = LanguageSelectionForm.class.getResource("/selfcheckoutsystem/images/" + filename);
        if( fileurl != null )
            language2Button.setIcon(new javax.swing.ImageIcon(fileurl)); // NOI18N
    }

    public void updateLang3(String text, String filename)
    {
        language3Label.setText(text);
        URL fileurl = LanguageSelectionForm.class.getResource("/selfcheckoutsystem/images/" + filename);
        if( fileurl != null )
            language3Button.setIcon(new javax.swing.ImageIcon(fileurl)); // NOI18N
    }

    public void updateLang4(String text, String filename)
    {
        language4Label.setText(text);
        URL fileurl = LanguageSelectionForm.class.getResource("/selfcheckoutsystem/images/" + filename);
        if( fileurl != null )
            language4Button.setIcon(new javax.swing.ImageIcon(fileurl)); // NOI18N
    }
    
    public void updateLang5(String text, String filename)
    {
        language5Label.setText(text);
        URL fileurl = LanguageSelectionForm.class.getResource("/selfcheckoutsystem/images/" + filename);
        if( fileurl != null )
            language5Button.setIcon(new javax.swing.ImageIcon(fileurl)); // NOI18N
    }
        
    public void updateLang6(String text, String filename)
    {
        language6Label.setText(text);
        URL fileurl = LanguageSelectionForm.class.getResource("/selfcheckoutsystem/images/" + filename);
        if( fileurl != null )
            language6Button.setIcon(new javax.swing.ImageIcon(fileurl)); // NOI18N
    }
    
    public int getLanguageNum()
    {
        return languageNum;
    }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton language1Button;
   private javax.swing.JLabel language1Label;
   private javax.swing.JButton language2Button;
   private javax.swing.JLabel language2Label;
   private javax.swing.JButton language3Button;
   private javax.swing.JLabel language3Label;
   private javax.swing.JButton language4Button;
   private javax.swing.JLabel language4Label;
   private javax.swing.JButton language5Button;
   private javax.swing.JLabel language5Label;
   private javax.swing.JButton language6Button;
   private javax.swing.JLabel language6Label;
   // End of variables declaration//GEN-END:variables
    private int languageNum;
}
