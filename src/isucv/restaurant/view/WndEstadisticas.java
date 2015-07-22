/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.view;

import isucv.restaurant.controller.Controller;
import isucv.restaurant.model.ContadorContorno;
import isucv.restaurant.model.ContadorEspecialidad;
import isucv.restaurant.model.Estadisticas;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author KDERazorback
 */
public class WndEstadisticas extends javax.swing.JFrame {

    private final static int COLUMN_DESCRIPTION = 0;
    private final static int COLUMN_COUNT = 1;
    
    /**
     * Creates new form WndEstadisticas
     */
    public WndEstadisticas() {
        initComponents();
        
        // Adjust Table's Column width
        jTable1.getColumnModel().getColumn(COLUMN_DESCRIPTION).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(COLUMN_COUNT).setPreferredWidth(60);
        
        // Enable Table Auto-sorting for Count column
        // jTable1
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable1.getModel());
        jTable1.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        
        sortKeys.add(new RowSorter.SortKey(COLUMN_COUNT, SortOrder.DESCENDING));
        
        sorter.setSortKeys(sortKeys);
        sorter.sort();
        
        // jTable2
        sorter = new TableRowSorter<>(jTable2.getModel());
        jTable2.setRowSorter(sorter);
        sortKeys = new ArrayList<>();
        
        sortKeys.add(new RowSorter.SortKey(COLUMN_COUNT, SortOrder.DESCENDING));
        
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        cmdClose = new javax.swing.JButton();
        cmdResetStats = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estadisticas del Dia");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Especialidades mas Solicitadas");
        jLabel1.setToolTipText("");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Pizza Margarita",  new Integer(4)},
                {"Pizza Vegetariana",  new Integer(0)},
                {"Pizza 4 Estaciones",  new Integer(3)},
                {"Pollo a la Canasta",  new Integer(6)},
                {"Hamburguesa de Carne",  new Integer(14)},
                {"Hamburguesa de Pollo",  new Integer(9)},
                {"Bistec al ajillo",  new Integer(1)},
                {"Parrilla Mixta",  new Integer(4)},
                {"Sopa del dia", null}
            },
            new String [] {
                "Descripción", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Contornos mas Solicitados");
        jLabel2.setToolTipText("");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Papas Fritas",  new Integer(11)},
                {"Papas al Vapor",  new Integer(15)},
                {"Yuca Frita",  new Integer(8)},
                {"Arroz Blanco",  new Integer(7)},
                {"Arroz Frito",  new Integer(3)},
                {"Ensalada Rallada",  new Integer(14)},
                {"Tajadas",  new Integer(10)},
                {"Nestea Durazno", null}
            },
            new String [] {
                "Descripción", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable2);

        cmdClose.setText("Cerrar");

        cmdResetStats.setText("Reiniciar Estadisticas");
        cmdResetStats.setToolTipText("");
        cmdResetStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdResetStatsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdResetStats, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 248, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdResetStats, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Permite retornar a la ventana de seleccion de tarea al cerrar
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void cmdResetStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdResetStatsActionPerformed
         Controller.ResetStats();
        //Mostramos las tablas vacias
        DefaultTableModel model1 = (DefaultTableModel)this.jTable1.getModel();
        model1.setRowCount(0);
        
        DefaultTableModel model2 = (DefaultTableModel)this.jTable2.getModel();
        model2.setRowCount(0);
        
    }//GEN-LAST:event_cmdResetStatsActionPerformed
//Aqui codigo al abrir la ventana estadisticas
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       //codigo que vacia las tables
        DefaultTableModel model1 = (DefaultTableModel)this.jTable1.getModel();
        model1.setRowCount(0);
        
        DefaultTableModel model2 = (DefaultTableModel)this.jTable2.getModel();
        model2.setRowCount(0);
        
        Estadisticas statistics;
        statistics = new Estadisticas();
        
        //Nos devuelve nuestros arraylists
        statistics = Controller.GetStats();

        //llenamos los arraylists segun los cont que tengan, si estan vacios, no hacemos nada
        if(statistics.GetTopSpecialities().isEmpty()==false)
        {
            JOptionPane.showMessageDialog(null,"hola1");
        }
        if(statistics.GetTopSides().isEmpty()==false)
        {
             JOptionPane.showMessageDialog(null,"hola2");
        }
    }//GEN-LAST:event_formWindowOpened

    // Almacena la ventana principal que muestra esta ventana
    private JFrame ParentWindow = null;
    
    // Permite intercambiar las ventanas activas del controlador al mostrarse
    // y ocultarse
    @Override
    public void setVisible(boolean value)
    {        
        super.setVisible(value);
        
        if (!value)
        {
            Controller.OpenSubTask(1);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdResetStats;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
