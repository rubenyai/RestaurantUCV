/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.view;

import isucv.restaurant.controller.Controller;
import isucv.restaurant.model.Pedido;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class WndDespachoPedidos extends javax.swing.JFrame {

    private final static int ORDER_COLUMN_QUANTITY = 0;
    private final static int ORDER_COLUMN_DESCRIPTION = 1;
    
    /**
     * Creates new form WndDespachoPedidos
     */
    public WndDespachoPedidos() {
        initComponents();
        
        // Center Column text for both JTables
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        Table.getColumnModel().getColumn(ORDER_COLUMN_QUANTITY).setCellRenderer(centerRenderer);
        
        // Setup Column Width for both JTables
        Table.getColumnModel().getColumn(ORDER_COLUMN_QUANTITY).setPreferredWidth(40);
        Table.getColumnModel().getColumn(ORDER_COLUMN_QUANTITY).setMaxWidth(80);
        Table.getColumnModel().getColumn(ORDER_COLUMN_DESCRIPTION).setPreferredWidth(360);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblOrderID = new javax.swing.JLabel();
        cmdDiscard = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Despacho de Pedidos");
        setMinimumSize(new java.awt.Dimension(586, 443));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Pedido");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pedido");

        lblOrderID.setFont(new java.awt.Font("Tahoma", 0, 62)); // NOI18N
        lblOrderID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderID.setText("2456");
        lblOrderID.setToolTipText("");

        cmdDiscard.setText("Cerrar");
        cmdDiscard.setToolTipText("");
        cmdDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDiscardActionPerformed(evt);
            }
        });

        Table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOrderID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 148, Short.MAX_VALUE)
                        .addComponent(cmdDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Codigo al iniciar ventana
        QueryNextOrder();
    }//GEN-LAST:event_formWindowOpened

    private void cmdDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDiscardActionPerformed
        QueryNextOrder(); // Cargar el siguiente pedido listo (si existe)
        if("No hay pedidos".equals(lblOrderID.getText()))
        {
            this.setVisible(false);
        }
    }//GEN-LAST:event_cmdDiscardActionPerformed

    // Busca en la cola de Pedidos Listos si existe algun pedido pendiente.
    private void QueryNextOrder()
    {
        // Codigo cerrar, esto actualizara la vaina
        if(Controller.IsNextPendingOrderAvalaible()){
            Pedido despacho;
            despacho=Controller.GetNextOrderReady();
            lblOrderID.setText(Integer.toString(despacho.GetId()));
            
            DefaultTableModel md = (DefaultTableModel) Table.getModel();
            md.setRowCount(0); // Eliminar la tabla
            //
            String Descripcion;
            int Cantidad;
            Object[] Nuevo = new Object[2];

            for (int i = 0; i < despacho.GetSpecialities().size(); i++)
            {
                Descripcion = despacho.GetSpecialities().get(i).GetSpeciality().GetName();
                Cantidad = despacho.GetSpecialities().get(i).GetCount();
                Nuevo = new Object[] {Cantidad, Descripcion};
                md.addRow(Nuevo);
                //Se añaden los contornos del plato al table
                for (int j = 0; j < despacho.GetSpecialities().get(i).GetSides().size(); j++)
                {
                    Descripcion = despacho.GetSpecialities().get(i).GetSides().get(j).GetSide().GetName();
                    Cantidad = despacho.GetSpecialities().get(i).GetSides().get(j).GetCount();
                    if (Cantidad == 1)
                    {
                        Nuevo = new Object[] {null, Descripcion};
                    }
                    else
                    {
                        Nuevo = new Object[] {Cantidad, Descripcion};
                    }
                    md.addRow(Nuevo);
                }
            } 
            Cantidad = 0;
            Descripcion = "";
            Nuevo = new Object[] {"", ""};
            for (int i = 0; i < despacho.GetSides().size(); i++)
            {
                Descripcion = despacho.GetSides().get(i).GetSide().GetName();
                Cantidad=despacho.GetSides().get(i).GetCount();
                Nuevo = new Object[] {Cantidad, Descripcion};
                md.addRow(Nuevo);

            }
            Cantidad = 0;
            Descripcion = "";
            Nuevo = new Object[] {"", ""};
        }
        else
        {
            lblOrderID.setText("No hay pedidos");
            DefaultTableModel model1 = (DefaultTableModel)this.Table.getModel();
            model1.setRowCount(0);
            //Codigo Cerrar ventana
        }
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.JButton cmdDiscard;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblOrderID;
    // End of variables declaration//GEN-END:variables
}
