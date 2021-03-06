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
import static isucv.restaurant.controller.Controller.FindOrder;
import static isucv.restaurant.controller.Controller.GetPendingOrders;
import isucv.restaurant.model.ContadorContorno;
import isucv.restaurant.model.ContadorEspecialidad;
import isucv.restaurant.model.Pedido;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */

public class ICocina extends javax.swing.JFrame {

    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private final static int ORDERS_COLUMN_ID = 0;
    private final static int ORDERS_COLUMN_DISHES = 1;
    private final static int ORDERS_COLUMN_SIDES = 2;
    private final static int ORDERS_COLUMN_TIME = 3;
    
    private final static int ORDER_COLUMN_QUANTITY = 0;
    private final static int ORDER_COLUMN_DESCRIPTION = 1;

    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public ICocina() {
        initComponents();
        
        // Center Column text for both JTables
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tableColaPedidos.getColumnModel().getColumn(ORDERS_COLUMN_ID).setCellRenderer(centerRenderer);
        tableColaPedidos.getColumnModel().getColumn(ORDERS_COLUMN_DISHES).setCellRenderer(centerRenderer);
        tableColaPedidos.getColumnModel().getColumn(ORDERS_COLUMN_SIDES).setCellRenderer(centerRenderer);
        tableColaPedidos.getColumnModel().getColumn(ORDERS_COLUMN_TIME).setCellRenderer(centerRenderer);
        tablePedido.getColumnModel().getColumn(ORDER_COLUMN_QUANTITY).setCellRenderer(centerRenderer);
        
        // Setup Column Width for both JTables
        tableColaPedidos.getColumnModel().getColumn(ORDERS_COLUMN_ID).setPreferredWidth(80);
        tableColaPedidos.getColumnModel().getColumn(ORDERS_COLUMN_ID).setMaxWidth(120);
        tableColaPedidos.getColumnModel().getColumn(ORDERS_COLUMN_DISHES).setPreferredWidth(80);
        tableColaPedidos.getColumnModel().getColumn(ORDERS_COLUMN_SIDES).setPreferredWidth(80);
        tableColaPedidos.getColumnModel().getColumn(ORDERS_COLUMN_TIME).setPreferredWidth(80);
        
        tablePedido.getColumnModel().getColumn(ORDER_COLUMN_QUANTITY).setPreferredWidth(40);
        tablePedido.getColumnModel().getColumn(ORDER_COLUMN_QUANTITY).setMaxWidth(80);
        tablePedido.getColumnModel().getColumn(ORDER_COLUMN_DESCRIPTION).setPreferredWidth(360);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tableColaPedidos = new javax.swing.JTable();
        lblTextColadePedidos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePedido = new javax.swing.JTable();
        cmdDespacharPedido = new javax.swing.JButton();
        lblIdPedido = new javax.swing.JLabel();
        lblTextPedido = new javax.swing.JLabel();
        lblCantPlatos = new javax.swing.JLabel();
        lblAdditionalSides = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Preparación de Pedidos");
        setMinimumSize(new java.awt.Dimension(572, 469));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tableColaPedidos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableColaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(2454),  new Integer(1),  new Integer(0),  new Float(15.0)},
                { new Integer(2455),  new Integer(1),  new Integer(1),  new Float(17.0)},
                { new Integer(2456),  new Integer(8),  new Integer(3),  new Float(35.0)},
                { new Integer(2471),  new Integer(1),  new Integer(1),  new Float(7.0)},
                { new Integer(2472),  new Integer(2),  new Integer(4),  new Float(38.0)},
                { new Integer(2473),  new Integer(2),  new Integer(0),  new Float(30.0)},
                { new Integer(2474),  new Integer(0),  new Integer(4),  new Float(8.0)}
            },
            new String [] {
                "Id Pedido", "Total de Platos", "Total de Contornos", "Tiempo Total (minutos)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableColaPedidos.getTableHeader().setReorderingAllowed(false);
        tableColaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableColaPedidosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableColaPedidos);

        lblTextColadePedidos.setText("Cola de Pedidos por Preparar");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Pedido");
        jLabel3.setToolTipText("");

        tablePedido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "Pollo a la Canasta"},
                {null, "Arroz Blanco"},
                {null, "Yuca Frita"},
                { new Integer(2), "Pizza Margarita"},
                { new Integer(1), "Hamburguesa de carne"},
                {null, "Papas Fritas"},
                { new Integer(3), "Papas Fritas"},
                { new Integer(1), "Nestea Durazno"}
            },
            new String [] {
                "Cantidad", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
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
        jScrollPane1.setViewportView(tablePedido);

        cmdDespacharPedido.setText("Despachar Pedido");
        cmdDespacharPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDespacharPedidoActionPerformed(evt);
            }
        });

        lblIdPedido.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblIdPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdPedido.setText("2456");
        lblIdPedido.setToolTipText("");

        lblTextPedido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTextPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextPedido.setText("Pedido");

        lblCantPlatos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCantPlatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantPlatos.setText("8 Platos");
        lblCantPlatos.setToolTipText("");

        lblAdditionalSides.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAdditionalSides.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdditionalSides.setText("<html>3 Contornos<br>Adicionales");
        lblAdditionalSides.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblTextColadePedidos)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmdDespacharPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                        .addComponent(lblIdPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTextPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(2, 2, 2))
                                .addComponent(lblCantPlatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAdditionalSides))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTextColadePedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdDespacharPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(lblTextPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantPlatos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAdditionalSides, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     private void UpdateTable(int id)
    {
        // Actualiza la tabla con la informacion de Especialidades y Contornos seleccionados
        DefaultTableModel md = (DefaultTableModel) tablePedido.getModel();
        md.setRowCount(0); // Eliminar el contenido actual de la tabla
        
        //Contadores que se mostraran
        int contSpecialities=0,contSides=0;
        
        //conseguir el pedido con ID
        Pedido ped;
        ped=FindOrder(id);
        // Mostrar Especialidades
        for (int i = 0; i < ped.GetSpecialities().size(); i++)
        {
            // Agregar especialidad
            ContadorEspecialidad e = ped.GetSpecialities().get(i);
            //Mostramos solo las especialidades que tienen un count mayor a 0 (que han sido pedidos)
            //En finalizar orden removemos estos mismos que tienen count igual a 0 para tener el array solo con ordenes pedidas
            if(e.GetCount()>0)
            {
                md.addRow(new Object[] {e.GetCount(), e.GetSpeciality().GetName()});
                contSpecialities+=e.GetCount();
            }
            // Agregar contornos incluidos
            if (e.GetSides() == null || e.GetSides().isEmpty())
                continue;

            for (int sub = 0; sub < ped.GetSpecialities().get(i).GetSides().size(); sub++)
            {
                ContadorContorno c = e.GetSides().get(sub);
                md.addRow(new Object[] {c.GetCount(), "          " + c.GetSide().GetName(), null, null});
             }
        }
        // Mostrar Contornos Adicionales
        for (int a = 0; a < ped.GetSides().size(); a++)
        {
            // Agregar Contorno
            ContadorContorno aux = ped.GetSides().get(a);
            md.addRow(new Object[] {aux.GetCount(), aux.GetSide().GetName()});
            contSides+=aux.GetCount();
        } 
        lblCantPlatos.setText(String.format(Integer.toString(contSpecialities) + " Platos "));
        lblAdditionalSides.setText("<html>" + String.format(Integer.toString(contSides) + " Contornos<br>Adicionales")); 
    }
    
    public void UpdateTablePedidos(){
        //Boton despachar pedido deshabilitado hasta q se seleccione un pedido
        cmdDespacharPedido.setEnabled(false);
        DefaultTableModel md1 = (DefaultTableModel) tableColaPedidos.getModel();
            md1.setRowCount(0); // Eliminar la tabla
            
            DefaultTableModel md2 = (DefaultTableModel) tablePedido.getModel();
            md2.setRowCount(0); // Eliminar la tabla
        //Condicion, si no existen pedidos, no mostramos nada
        if(GetPendingOrders().isEmpty()==false)
        {
            int tim=0;
            int id;
            int CantContorno;
            int CantEspecialidad;
            //Borramos todo para cargar
                lblIdPedido.setText(" ");
                lblCantPlatos.setText("0 Platos");
                lblAdditionalSides.setText("<html>0 Contornos<br>Adicionales");

                //Cargamos desde pendingorders
                for(int i=0;i < GetPendingOrders().size();i++)
                {
                    CantEspecialidad=0;
                    //cantidad platos
                    CantContorno=0;
                    //ID
                    id=GetPendingOrders().get(i).GetId();
                    //Cantidad platos
                    CantEspecialidad+=GetPendingOrders().get(i).GetSpecialities().size();
                    //cantidad platos
                    for(int j=0;j<GetPendingOrders().get(i).GetSides().size();j++)
                    {
                        CantContorno+=GetPendingOrders().get(i).GetSides().get(j).GetCount();
                    }
                    //Calculo total time
                    //Suma de tiempos, si size nos devuelve q tiene elementos
                    for(int j=0;j < GetPendingOrders().get(i).GetSpecialities().size();j++)
                    {
                            tim+=GetPendingOrders().get(i).GetSpecialities().get(j).GetSpeciality().GetTime();
                    }

                    //Suma de tiempos, cantidad de contornos adiciones
                    //Multiplicamos por 2, ya que cada contorno adicional dura 2 min cocinandose
                    tim+=(2)*CantContorno;
                    
                    //Suma cantidad de especialidades y contornos adicionales para mostrar en array
                    
                    //Codigo llenado de table
                      md1.addRow(new Object[] {id,CantEspecialidad,CantContorno,tim});   
                }
        }else{
                lblIdPedido.setText("---");
                lblCantPlatos.setText("<html>No Existen<br>Pedidos");
                lblAdditionalSides.setText("");
        }
    }
    
    // Permite retornar a la ventana de seleccion de tarea al cerrar
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        UpdateTablePedidos();
    }//GEN-LAST:event_formWindowOpened

    private void cmdDespacharPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDespacharPedidoActionPerformed
        int idped=0;
        //Movemos la orden elegida a la cola de despacho de mesonero
        DefaultTableModel md = (DefaultTableModel) tableColaPedidos.getModel();
        if (md.getRowCount() < 1)
            return;

        for (int i = md.getRowCount() - 1; i >= 0; i--)
            {
                if (tableColaPedidos.isRowSelected(i))
                {
                    //Encontramos la fila para obtener el num de pedido
                    idped=Integer.parseInt(tableColaPedidos.getValueAt(i,0).toString());
                }
            }
        Pedido p;
        p=FindOrder(idped);
        
        //Movemos el pedido al mesonero ordersready y borramos el pedido de pendingorders
        Controller.GetOrdersReady().add(p);
        for(int y=0;y<Controller.GetPendingOrders().size();y++)
        {
            if(Controller.GetPendingOrders().get(y).GetId()==idped)
            {
                    Controller.GetPendingOrders().remove(y);
            }
        }
        //Borramos la 2da table
        DefaultTableModel md2 = (DefaultTableModel) tablePedido.getModel();
        md2.setRowCount(0);
        //Se actualiza nuevamente la table de pedidos
        UpdateTablePedidos();
        //Se deshabilita boton de despacho hasta q se seleccione otra opcion
        cmdDespacharPedido.setEnabled(false);
    }//GEN-LAST:event_cmdDespacharPedidoActionPerformed

    private void tableColaPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableColaPedidosMouseClicked
        int id=0;
        DefaultTableModel md = (DefaultTableModel) tableColaPedidos.getModel();
        if (md.getRowCount() < 1)
            return;
        
        for (int i = md.getRowCount() - 1; i >= 0; i--)
        {
            if (tableColaPedidos.isRowSelected(i))
            {
                //Habilitar boton Despachar pedido
                cmdDespacharPedido.setEnabled(true);
                id=Integer.parseInt(tableColaPedidos.getValueAt(i,0).toString());
                lblIdPedido.setText(Integer.toString(id));     
            }
        }
        UpdateTable(id);
    }//GEN-LAST:event_tableColaPedidosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdDespacharPedido;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdditionalSides;
    private javax.swing.JLabel lblCantPlatos;
    private javax.swing.JLabel lblIdPedido;
    private javax.swing.JLabel lblTextColadePedidos;
    private javax.swing.JLabel lblTextPedido;
    private javax.swing.JTable tableColaPedidos;
    private javax.swing.JTable tablePedido;
    // End of variables declaration//GEN-END:variables
}
