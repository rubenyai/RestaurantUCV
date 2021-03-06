/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.view;

import isucv.restaurant.controller.Controller;
import isucv.restaurant.model.Contorno;
import isucv.restaurant.model.Especialidad;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class Dbg___WndBillboardDebugger extends javax.swing.JFrame {

    /**
     * Creates new form Dbg___WndBillboardDebugger
     */
    public Dbg___WndBillboardDebugger() {
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

        cmdEditSides = new javax.swing.JButton();
        cmdEditSpecialities = new javax.swing.JButton();
        cmdBreak = new javax.swing.JButton();
        cmdRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstSpecialities = new javax.swing.JList<String>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstSides = new javax.swing.JList<String>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmdDetachFromAppController = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Billboard Debugger");
        setResizable(false);

        cmdEditSides.setText("Edit Sides");
        cmdEditSides.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditSidesActionPerformed(evt);
            }
        });

        cmdEditSpecialities.setText("Edit Specialities");
        cmdEditSpecialities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditSpecialitiesActionPerformed(evt);
            }
        });

        cmdBreak.setText("Break into Debugger");
        cmdBreak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBreakActionPerformed(evt);
            }
        });

        cmdRefresh.setText("Refresh");
        cmdRefresh.setToolTipText("");
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

        lstSpecialities.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        lstSpecialities.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstSpecialities);

        lstSides.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        lstSides.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstSides);

        jLabel1.setText("Current Sides");

        jLabel2.setText("Current Specialities");

        cmdDetachFromAppController.setText("Detach From AppC");
        cmdDetachFromAppController.setToolTipText("");
        cmdDetachFromAppController.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDetachFromAppControllerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 424, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdBreak, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdEditSpecialities, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdEditSides, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdDetachFromAppController, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdEditSides)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdEditSpecialities)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdBreak, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDetachFromAppController, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdEditSidesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditSidesActionPerformed
        IEditorContornos wnd = new IEditorContornos();
        wnd.setLocationRelativeTo(null);
        wnd.setVisible(true);
    }//GEN-LAST:event_cmdEditSidesActionPerformed

    private void cmdEditSpecialitiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditSpecialitiesActionPerformed
        IEditorPlatos wnd = new IEditorPlatos();
        wnd.setLocationRelativeTo(null);
        wnd.setVisible(true);
    }//GEN-LAST:event_cmdEditSpecialitiesActionPerformed

    private void cmdBreakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBreakActionPerformed
        ArrayList<Especialidad> k_backingField__Specialities = Controller.GetBillboardSpecialities();
        ArrayList<Contorno> k_backingField__Sides = Controller.GetBillboardSides();
        
        int i = 0;
        i = 0 + 0;
    }//GEN-LAST:event_cmdBreakActionPerformed
    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        // Specialities
        DefaultListModel<String> contents = new DefaultListModel<>();
        
        int i;
        String line;
        for (i = 0; i < Controller.GetBillboardSpecialities().size(); i++)
        {
            Especialidad e = Controller.GetBillboardSpecialities().get(i);
            line = (e.GetVisible()? "V " : "  ");
            line += e.GetTotalSides() + "  [";
            line += e.GetTime() + " min]  ";
            line += e.GetName() + "  (";
            line += Float.toString(e.GetPrice()) + " BsF)";
            
            contents.addElement(line);
        }
        
        lstSpecialities.setModel(contents);
        
        // Sides
        contents = new DefaultListModel<>();

        for (i = 0; i < Controller.GetBillboardSides().size(); i++)
        {
            Contorno e = Controller.GetBillboardSides().get(i);
            line = (e.GetVisible()? "V  " : "   ");
            line += e.GetName() + "  (";
            line += Float.toString(e.GetPrice()) + " BsF)";
            
            contents.addElement(line);
        }
        
        lstSides.setModel(contents);
    }//GEN-LAST:event_cmdRefreshActionPerformed

    private void cmdDetachFromAppControllerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDetachFromAppControllerActionPerformed
        Controller.SetActiveWindow(null);
        this.cmdDetachFromAppController.setEnabled(false);
    }//GEN-LAST:event_cmdDetachFromAppControllerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdBreak;
    private javax.swing.JButton cmdDetachFromAppController;
    private javax.swing.JButton cmdEditSides;
    private javax.swing.JButton cmdEditSpecialities;
    private javax.swing.JButton cmdRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstSides;
    private javax.swing.JList<String> lstSpecialities;
    // End of variables declaration//GEN-END:variables
}
