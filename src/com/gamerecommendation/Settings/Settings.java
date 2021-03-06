/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamerecommendation.Settings;

import com.gamerecommendation.Home.Home;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Settings extends javax.swing.JFrame {
    
    private String location;
    private String unitDegree;
    
    /**
     * Creates new form Settings
     */
    public Settings() {
        initComponents();
        location = "Santiago";
        unitDegree = "c";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SaveBt = new javax.swing.JLabel();
        City = new javax.swing.JComboBox();
        Celsius = new javax.swing.JRadioButton();
        Fahrenheit = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(690, 450));
        setMinimumSize(new java.awt.Dimension(690, 450));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(690, 450));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SaveBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/saveBtn.png"))); // NOI18N
        SaveBt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveBtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SaveBtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SaveBtMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SaveBtMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SaveBtMouseReleased(evt);
            }
        });
        getContentPane().add(SaveBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 210, -1));

        City.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        City.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Santiago", "Moca", "Santo Domingo" }));
        getContentPane().add(City, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 280, 60));

        Celsius.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/Cel.png"))); // NOI18N
        Celsius.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CelsiusMouseClicked(evt);
            }
        });
        getContentPane().add(Celsius, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        Fahrenheit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/Far.png"))); // NOI18N
        Fahrenheit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FahrenheitMouseClicked(evt);
            }
        });
        getContentPane().add(Fahrenheit, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/closeBtn.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/minimizeBtn.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/window.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 702, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void CelsiusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CelsiusMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Celsius Option Selected", "Option Clicked", JOptionPane.WARNING_MESSAGE);
        this.unitDegree = "c";
    }//GEN-LAST:event_CelsiusMouseClicked

    private void FahrenheitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FahrenheitMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Fahrenheit Option Selected", "Option Clicked", JOptionPane.WARNING_MESSAGE);
        this.unitDegree = "f";
    }//GEN-LAST:event_FahrenheitMouseClicked

    private void SaveBtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveBtMouseEntered
        // TODO add your handling code here:
        SaveBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/saveBtnHover.png")));
    }//GEN-LAST:event_SaveBtMouseEntered

    private void SaveBtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveBtMouseExited
        // TODO add your handling code here:
        SaveBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/saveBtn.png")));
    }//GEN-LAST:event_SaveBtMouseExited

    private void SaveBtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveBtMousePressed
        // TODO add your handling code here:
        SaveBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/saveBtnPressed.png")));
    }//GEN-LAST:event_SaveBtMousePressed

    private void SaveBtMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveBtMouseReleased
        // TODO add your handling code here:
        SaveBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gamerecommendation/Settings/saveBtn.png")));
    }//GEN-LAST:event_SaveBtMouseReleased

    private void SaveBtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveBtMouseClicked
        // TODO add your handling code here:
        this.location = City.getSelectedItem().toString();
        JOptionPane.showMessageDialog(null, "Settings are now updated.");
        Home home = new Home();
        home.changeLocation(this.location);
        home.changeUnitDegrees(this.unitDegree);
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SaveBtMouseClicked

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
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Settings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Celsius;
    private javax.swing.JComboBox City;
    private javax.swing.JRadioButton Fahrenheit;
    private javax.swing.JLabel SaveBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
