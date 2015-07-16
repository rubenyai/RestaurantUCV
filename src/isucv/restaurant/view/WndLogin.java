/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.view;

import isucv.restaurant.controller.AppController;
import java.util.Arrays;

/**
 *
 * @author KDERazorback
 */
public class WndLogin extends javax.swing.JFrame {

    // Indica si se ha cerrado la ventana de login y la app debe terminar
    // De ser falso, se debe iniciar una subventana dependiendo del rol activo
    private boolean CloseApp = true;
    
    /**
     * Creates new form WndLogin
     */
    public WndLogin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        cmdClientTask = new javax.swing.JButton();
        cmdLogin = new javax.swing.JButton();
        cmdDebugWnd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicio de Sesion");

        txtUsername.setText("Angel Lozano");
        txtUsername.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nombre de Usuario");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Contraseña");

        txtPassword.setText("password");

        cmdClientTask.setText("Cliente");
        cmdClientTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClientTaskActionPerformed(evt);
            }
        });

        cmdLogin.setText("Iniciar Sesion");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });

        cmdDebugWnd.setText("DEBUG");
        cmdDebugWnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDebugWndActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdClientTask)
                        .addGap(43, 43, 43)
                        .addComponent(cmdDebugWnd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdClientTask)
                    .addComponent(cmdLogin)
                    .addComponent(cmdDebugWnd))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Abrir ventana de Depuracion
    private void cmdDebugWndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDebugWndActionPerformed
        // Crear una nueva ventana de Debug Launcher
        Dbg___WndLauncher launcher = new Dbg___WndLauncher();
        launcher.setVisible(true);
        CloseApp = false;
        
        // Establecer el Launcher como ventana activa
        AppController.Instance.ActiveWindow = launcher;
        
        // Ocultar ventana de Inicio de Sesion
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cmdDebugWndActionPerformed

    // Iniciar sesion como Cliente
    private void cmdClientTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClientTaskActionPerformed
        // Eliminar los campos de Usuario y Contraseña para iniciar el rol
        txtUsername.setText("");
        txtPassword.setText("");
        CloseApp = false;
        
        // Solicitar un inicio de sesion como Cliente
        AppController.Instance.Login(null, null);
    }//GEN-LAST:event_cmdClientTaskActionPerformed

    // Iniciar sesion con los credenciales proporcionados
    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
        // Iniciar sesion con los credenciales proporcionados
        CloseApp = false;     
        
        String password = String.copyValueOf(txtPassword.getPassword());
        
        AppController.Instance.Login(txtUsername.getText(), password);
    }//GEN-LAST:event_cmdLoginActionPerformed

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
            java.util.logging.Logger.getLogger(WndLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WndLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WndLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WndLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WndLogin().setVisible(true);
            }
        });
    }
    
    // Retorna el valor de CloseApp
    public boolean getCloseApp()
    {
        return CloseApp;
    }
    
    // Permite establecer el valor de CloseApp en true al mostrar la ventana
    @Override
    public void setVisible(boolean value)
    {
        if (value)
            CloseApp = true;
        
        super.setVisible(value);
    }
    
    // Obtiene el nombre de usuario escrito en el TextField
    public String getUsername()
    {
        return this.txtUsername.getText();
    }
    
    // Obtiene la contraseña escrita en el TextField
    public String getPassword()
    {
        return String.copyValueOf(this.txtPassword.getPassword());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClientTask;
    private javax.swing.JButton cmdDebugWnd;
    private javax.swing.JButton cmdLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}