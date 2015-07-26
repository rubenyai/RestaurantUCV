/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.view;

import isucv.restaurant.controller.Controller;
import isucv.restaurant.model.ContadorContorno;
import isucv.restaurant.model.ContadorEspecialidad;
import isucv.restaurant.model.Contorno;
import isucv.restaurant.model.Especialidad;
import isucv.restaurant.model.Pedido;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class Dbg___WndLauncher extends javax.swing.JFrame {

    private ArrayList<ContadorContorno> sideSelectorCache = null;
    
    /**
     * Creates new form Dbg___WndLauncher
     */
    public Dbg___WndLauncher() {
        initComponents();
        this.setLocationRelativeTo(null); // Center Window
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdDebugUsers = new javax.swing.JButton();
        cmdDebugSideSelector = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cmdBillboardLoadFromFile = new javax.swing.JButton();
        cmdBillboardSaveToFile = new javax.swing.JButton();
        cmdCreateDebugOrder = new javax.swing.JButton();
        txtOrderId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmdSelectSides = new javax.swing.JButton();
        cmdClearSelectionCache = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSideMaxCount = new javax.swing.JTextField();
        cmdArrayListViewer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lanzador de Ventanas");
        setResizable(false);

        cmdDebugUsers.setText("Debug Users");
        cmdDebugUsers.setToolTipText("");
        cmdDebugUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDebugUsersActionPerformed(evt);
            }
        });

        cmdDebugSideSelector.setText("Debug Side Selector");
        cmdDebugSideSelector.setToolTipText("");
        cmdDebugSideSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDebugSideSelectorActionPerformed(evt);
            }
        });

        jButton1.setText("Billboard Debugger");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmdBillboardLoadFromFile.setText("LFF");
        cmdBillboardLoadFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBillboardLoadFromFileActionPerformed(evt);
            }
        });

        cmdBillboardSaveToFile.setText("STF");
        cmdBillboardSaveToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBillboardSaveToFileActionPerformed(evt);
            }
        });

        cmdCreateDebugOrder.setText("Create Debug Order");
        cmdCreateDebugOrder.setEnabled(false);
        cmdCreateDebugOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCreateDebugOrderActionPerformed(evt);
            }
        });

        txtOrderId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOrderId.setText("2456");

        jLabel1.setText("Order ID");

        cmdSelectSides.setText("Select Sides");
        cmdSelectSides.setEnabled(false);
        cmdSelectSides.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSelectSidesActionPerformed(evt);
            }
        });

        cmdClearSelectionCache.setText("Clear Cache");
        cmdClearSelectionCache.setEnabled(false);
        cmdClearSelectionCache.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearSelectionCacheActionPerformed(evt);
            }
        });

        jLabel2.setText("Max Count");

        txtSideMaxCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSideMaxCount.setText("6");

        cmdArrayListViewer.setText("OrderList Viewer");
        cmdArrayListViewer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdArrayListViewerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmdDebugSideSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(cmdDebugUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdCreateDebugOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cmdBillboardLoadFromFile))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdBillboardSaveToFile)
                            .addComponent(txtOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdSelectSides, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdClearSelectionCache, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSideMaxCount, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdArrayListViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdDebugUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdDebugSideSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdBillboardLoadFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdBillboardSaveToFile, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCreateDebugOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdSelectSides, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdClearSelectionCache, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtSideMaxCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdArrayListViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdDebugUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDebugUsersActionPerformed
        Dbg___WndUserDebugger wnd = new Dbg___WndUserDebugger();
        Controller.SetActiveWindow(wnd);
        Controller.GetActiveWindow().setVisible(true);

        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cmdDebugUsersActionPerformed

    private void cmdDebugSideSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDebugSideSelectorActionPerformed
        Dbg___WndSideSelectorDebugger wnd = new Dbg___WndSideSelectorDebugger();
        Controller.SetActiveWindow(wnd);
        Controller.GetActiveWindow().setVisible(true);

        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cmdDebugSideSelectorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Dbg___WndBillboardDebugger wnd = new Dbg___WndBillboardDebugger();
        Controller.SetActiveWindow(wnd);
        Controller.GetActiveWindow().setVisible(true);
        wnd.setLocationRelativeTo(null); // Center Window

        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmdBillboardLoadFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBillboardLoadFromFileActionPerformed
        Controller.GetBillboard().LoadBillboard();
        cmdCreateDebugOrder.setEnabled(true);
    }//GEN-LAST:event_cmdBillboardLoadFromFileActionPerformed

    private void cmdBillboardSaveToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBillboardSaveToFileActionPerformed
        Controller.GetBillboard().SaveBillboard();
    }//GEN-LAST:event_cmdBillboardSaveToFileActionPerformed

    private void cmdCreateDebugOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCreateDebugOrderActionPerformed
        cmdSelectSides.setEnabled(true);
        // Order
        Pedido p = new Pedido();
        p.SetId(Integer.parseInt(txtOrderId.getText()));
        
        // Speciality=Pizza 4 Estaciones (x2)
        ContadorEspecialidad sp = new ContadorEspecialidad(Controller.GetBillboard().GetSpecialities().get(2));
        sp.SetCount(2);
        p.GetSpecialities().add(sp);
        
        // Speciality=Pizza Margarita (x1)
        sp = new ContadorEspecialidad(Controller.GetBillboard().GetSpecialities().get(0));
        sp.SetCount(1);
        p.GetSpecialities().add(sp);
        
        // Speciality=Pollo a la Canasta (x1) {Yuca Frita, Papas al Vapor}
        ArrayList<ContadorContorno> sides = new ArrayList<>();
        sp = new ContadorEspecialidad(Controller.GetBillboard().GetSpecialities().get(3));
        sp.AddCount(1);
        ContadorContorno cont = new ContadorContorno(Controller.GetBillboard().GetSides().get(2));
        cont.SetCount(1);
        sides.add(cont);
        cont = new ContadorContorno(Controller.GetBillboard().GetSides().get(1));
        cont.SetCount(1);
        sides.add(cont);
        sp.SetSides(sides);
        p.GetSpecialities().add(sp);
                
        // Speciality=Pollo a la Canasta (x1) {Papas al Vapor (x2)}
        sides = new ArrayList<>();
        sp = new ContadorEspecialidad(Controller.GetBillboard().GetSpecialities().get(3));
        sp.AddCount(1);
        cont = new ContadorContorno(Controller.GetBillboard().GetSides().get(1));
        cont.SetCount(2);
        sides.add(cont);
        sp.SetSides(sides);
        p.GetSpecialities().add(sp);
        
        // Additional Side=Papas al Vapor (x1)
        cont = new ContadorContorno(Controller.GetBillboard().GetSides().get(1));
        cont.SetCount(1);
        p.GetSides().add(cont);
        
        // Add Order to UnpaidOrders
        Controller.GetUnpaidOrders().add(p);
    }//GEN-LAST:event_cmdCreateDebugOrderActionPerformed

    private void cmdSelectSidesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSelectSidesActionPerformed
        // Seleccionar contornos
        WndSelectorContornos wnd = new WndSelectorContornos(sideSelectorCache, Integer.parseInt(txtSideMaxCount.getText()));
        wnd.setVisible(true);
        
        // Lambda Expression: New Background Thread for Callback
        /*
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    WaitForSideSelection_Background(wnd);
                }
            });
        */
        Thread t = new Thread(() -> {
            WaitForSideSelection_Background(wnd);
        });
                
        t.start();
    }//GEN-LAST:event_cmdSelectSidesActionPerformed

    private void WaitForSideSelection_Background(WndSelectorContornos wnd)
    {
        // Seleccionar contornos (Internal Thread)
        while (wnd.isVisible())
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dbg___WndLauncher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        sideSelectorCache = wnd.GetSelectedSides();
        cmdClearSelectionCache.setEnabled(sideSelectorCache != null);
        wnd.dispose();
    }
    
    private void cmdClearSelectionCacheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearSelectionCacheActionPerformed
        // Clear Side Selector Cache
        sideSelectorCache = null;
        cmdClearSelectionCache.setEnabled(false);
    }//GEN-LAST:event_cmdClearSelectionCacheActionPerformed

    private void cmdArrayListViewerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdArrayListViewerActionPerformed
        Dbg___OrderListDebugger wnd = new Dbg___OrderListDebugger();
        Controller.SetActiveWindow(wnd);
        Controller.GetActiveWindow().setVisible(true);
        wnd.setLocationRelativeTo(null); // Center Window

        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cmdArrayListViewerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdArrayListViewer;
    private javax.swing.JButton cmdBillboardLoadFromFile;
    private javax.swing.JButton cmdBillboardSaveToFile;
    private javax.swing.JButton cmdClearSelectionCache;
    private javax.swing.JButton cmdCreateDebugOrder;
    private javax.swing.JButton cmdDebugSideSelector;
    private javax.swing.JButton cmdDebugUsers;
    private javax.swing.JButton cmdSelectSides;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtOrderId;
    private javax.swing.JTextField txtSideMaxCount;
    // End of variables declaration//GEN-END:variables
}
