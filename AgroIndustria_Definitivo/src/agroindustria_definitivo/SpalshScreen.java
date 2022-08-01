/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agroindustria_definitivo;

import agroindustria_definitivo.Tela_Cadastro;
import agroindustria_definitivo.Tela_Login2;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author RoXen
 */
public class SpalshScreen extends javax.swing.JFrame {

    /**
     * Creates new form SpalshScreen
     */
    public SpalshScreen() {
        initComponents();
         Random r = new Random();
        new Thread() {
            public void run() {
                for (int i = 0; i < 1000; i = i + (Math.abs(r.nextInt()) % 25) + 1) {
                    try {
                        sleep(25);
                        jProgressBar3.setValue(i / 10);

                        if (jProgressBar3.getValue() <= 50) {
                            
                        } else if (jProgressBar1.getValue() <= 100) {
                            
                        }

                    } catch (InterruptedException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
                dispose();
                new Tela_Login2().setVisible(true);

            }
        }.start();
    }
   
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        loadingnumber = new javax.swing.JLabel();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(550, 280));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loadingnumber.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        loadingnumber.setForeground(new java.awt.Color(51, 51, 51));
        loadingnumber.setText("99%");
        jPanel1.add(loadingnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, -1));

        jProgressBar3.setForeground(new java.awt.Color(10, 150, 20));
        jProgressBar3.setToolTipText("");
        jPanel1.add(jProgressBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 370, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/agro3.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 230));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(SpalshScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpalshScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpalshScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpalshScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SpalshScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    public javax.swing.JLabel loadingnumber;
    // End of variables declaration//GEN-END:variables
}
