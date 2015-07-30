/*
 * Copyright (C) 2015
 *  Fabian Ramos
 *  Ruben Maza
 *  David Contreras
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package isucv.restaurant.view;

import isucv.restaurant.controller.Controller;
import isucv.restaurant.model.ContadorContorno;
import isucv.restaurant.model.ContadorEspecialidad;
import java.util.ArrayList;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class IEstadisticas extends javax.swing.JFrame {

    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private final static int COLUMN_DESCRIPTION = 0;
    private final static int COLUMN_COUNT = 1;
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public IEstadisticas() {
        initComponents();
        
        // Adjust Table's Column width
        tableTopEspecialidades.getColumnModel().getColumn(COLUMN_DESCRIPTION).setPreferredWidth(160);
        tableTopEspecialidades.getColumnModel().getColumn(COLUMN_COUNT).setPreferredWidth(60);
        
        // Enable Table Auto-sorting for Count column
        // jTable1
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableTopEspecialidades.getModel());
        tableTopEspecialidades.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        
        sortKeys.add(new RowSorter.SortKey(COLUMN_COUNT, SortOrder.DESCENDING));
        
        sorter.setSortKeys(sortKeys);
        sorter.sort();
        
        // jTable2
        sorter = new TableRowSorter<>(tableTopContornos.getModel());
        tableTopContornos.setRowSorter(sorter);
        sortKeys = new ArrayList<>();
        
        sortKeys.add(new RowSorter.SortKey(COLUMN_COUNT, SortOrder.DESCENDING));
        
        sorter.setSortKeys(sortKeys);
        sorter.sort();
        
        //Se elimina el contenido de las jtable
        DefaultTableModel modelo = (DefaultTableModel) tableTopEspecialidades.getModel();
        modelo.setNumRows(0);
        DefaultTableModel modelo2 = (DefaultTableModel) tableTopContornos.getModel();
        modelo2.setNumRows(0);
        
        int i, IndexToEliminate = 0, Count;
        ArrayList<ContadorEspecialidad> TopSpecialitiesCopy = new ArrayList<>(Controller.GetStats().GetTopSpecialities());
        ArrayList<ContadorContorno> TopSidesCopy = new ArrayList<>(Controller.GetStats().GetTopSides());
        Object[] Nuevo;
        
        //Se comprueba que el ArrayList de especialidades contenga algo
        if (!TopSpecialitiesCopy.isEmpty())
        {
            //Mientras que exista mas de un elemento en el ArrayList se procede a comprobar el mayor
            while (!TopSpecialitiesCopy.isEmpty())
            {
                Count = TopSpecialitiesCopy.get(0).GetCount();
                if (!TopSpecialitiesCopy.isEmpty())
                {
                    for (i = 1; i < TopSpecialitiesCopy.size(); i++)
                    {
                        //Se verifica cual es el elemento de mas solicitado
                        if ( TopSpecialitiesCopy.get(i).GetCount() > Count)
                        {
                            Count = TopSpecialitiesCopy.get(i).GetCount();
                            IndexToEliminate = i;
                        }
                    }
                } 
                Nuevo = new Object[] {TopSpecialitiesCopy.get(IndexToEliminate).GetSpeciality().GetName(), TopSpecialitiesCopy.get(IndexToEliminate).GetCount()};
                modelo.addRow(Nuevo);
                TopSpecialitiesCopy.remove(IndexToEliminate);
                IndexToEliminate = 0;
            }
        }
        
        //Se Comprueba que el ArrayList de contornos contenga algo
        if (!TopSidesCopy.isEmpty())
        {
            //Mientras que exista mas de un elemento en el ArrayList se procede a comprobar cual es el mayor
            while (!TopSidesCopy.isEmpty())
            {
                Count = TopSidesCopy.get(0).GetCount();
                if (!TopSidesCopy.isEmpty())
                {
                    for (i = 1; i < TopSidesCopy.size(); i++)
                    {
                        //Se verifica el elemento mas solicitado de la lista
                        if (TopSidesCopy.get(i).GetCount() > Count)
                        {
                            Count = TopSidesCopy.get(i).GetCount();
                            IndexToEliminate = i;
                        }
                    }
                }
                Nuevo = new Object[] {TopSidesCopy.get(IndexToEliminate).GetSide().GetName(), TopSidesCopy.get(IndexToEliminate).GetCount()};
                modelo2.addRow(Nuevo);
                TopSidesCopy.remove(IndexToEliminate);
                IndexToEliminate = 0;
            }
        }    
    }

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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTextEspecialidades = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTopEspecialidades = new javax.swing.JTable();
        lblTextContornos = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableTopContornos = new javax.swing.JTable();
        cmdClose = new javax.swing.JButton();
        cmdResetStats = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estadisticas del Dia");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTextEspecialidades.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTextEspecialidades.setText("Especialidades mas Solicitadas");
        lblTextEspecialidades.setToolTipText("");

        tableTopEspecialidades.setModel(new javax.swing.table.DefaultTableModel(
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
        tableTopEspecialidades.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableTopEspecialidades);

        lblTextContornos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTextContornos.setText("Contornos mas Solicitados");
        lblTextContornos.setToolTipText("");

        tableTopContornos.setModel(new javax.swing.table.DefaultTableModel(
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
        tableTopContornos.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tableTopContornos);

        cmdClose.setText("Cerrar");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

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
                            .addComponent(lblTextEspecialidades, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTextContornos, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 248, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTextEspecialidades)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTextContornos)
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
         this.setVisible(false);
    }//GEN-LAST:event_cmdResetStatsActionPerformed

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        // Codigo al cerrar la ventana
        this.setVisible(false);
    }//GEN-LAST:event_cmdCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdResetStats;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblTextContornos;
    private javax.swing.JLabel lblTextEspecialidades;
    private javax.swing.JTable tableTopContornos;
    private javax.swing.JTable tableTopEspecialidades;
    // End of variables declaration//GEN-END:variables
}
