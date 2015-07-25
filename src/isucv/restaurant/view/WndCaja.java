/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.view;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import isucv.restaurant.controller.Controller;
import isucv.restaurant.model.Pedido;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class WndCaja extends javax.swing.JFrame {

    private final static int ORDER_COLUMN_QUANTITY = 0;
    private final static int ORDER_COLUMN_DESCRIPTION = 1;
    private final static int ORDER_COLUMN_PRICE = 2;
    // ATRIBUTOS INTERNOS
    Integer ID;
    float Total = 0;
    float Costo, Balance = 0;
    int Cantidad;
    String Descripcion;
    /**
     * Creates new form WndCaja
     */
    public WndCaja() {
        initComponents();
        // Center Column text for the JTable
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(ORDER_COLUMN_QUANTITY).setCellRenderer(centerRenderer);
        
        // Setup Column Width for the JTable
        jTable1.getColumnModel().getColumn(ORDER_COLUMN_QUANTITY).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(ORDER_COLUMN_QUANTITY).setMaxWidth(80);
        jTable1.getColumnModel().getColumn(ORDER_COLUMN_DESCRIPTION).setPreferredWidth(360);
        jTable1.getColumnModel().getColumn(ORDER_COLUMN_PRICE).setPreferredWidth(120);
        
        jTextField1.setText("");
        ClearFiles(); //Se elimina el contenido de los Files y jTable
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
        jTextField1 = new javax.swing.JTextField();
        cmdSearchOrder = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmdGenerateOrder = new javax.swing.JButton();
        cmdDiscardOrder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pago de Pedidos");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Identificador de Pedido");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("2456");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        cmdSearchOrder.setText("Buscar");
        cmdSearchOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchOrderActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Datos de Cliente");

        jLabel3.setText("Nombre o Razon Social");

        jTextField2.setText("Paulo Perez");

        jLabel4.setText("Cedula o RIF");

        jTextField3.setText("V-14.587.655");

        jLabel5.setText("Direccion");

        jLabel6.setText("Teléfono");

        jTextField4.setText("0416-7172772");

        jTextField5.setText("Universidad Central de Venezuela");

        jTextField6.setText("Distrito Capital, Zona Postal 1050");

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "Pollo a la Canasta",  new Float(580.4)},
                {null, "Arroz Blanco",  new Float(0.0)},
                {null, "Yuca Frita",  new Float(0.0)},
                { new Integer(2), "Pizza Margarita",  new Float(685.4)},
                { new Integer(1), "Hamburguesa de carne",  new Float(483.8)},
                {null, "Papas Fritas",  new Float(0.0)},
                { new Integer(3), "Papas Fritas",  new Float(210.7)},
                { new Integer(1), "Nestea Durazno",  new Float(90.0)}
            },
            new String [] {
                "Cantidad", "Descripcion", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Resumen de Pedido");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel8.setText("Subtotal");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("3157.10 BsF");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel10.setText("IVA (12%)");

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("378.85 BsF");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel12.setText("Total a Pagar");

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("3535.95 BsF");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        cmdGenerateOrder.setText("Generar Pedido");
        cmdGenerateOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGenerateOrderActionPerformed(evt);
            }
        });

        cmdDiscardOrder.setText("Descartar Pedido");
        cmdDiscardOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDiscardOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cmdSearchOrder)
                        .addGap(52, 234, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jTextField6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdGenerateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdDiscardOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(10, 10, 10)
                                .addComponent(jTextField4))
                            .addComponent(jTextField2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdSearchOrder))
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdGenerateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cmdDiscardOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Boton BUSCAR
    private void cmdSearchOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchOrderActionPerformed
            
        //Verificamos si ID esta lleno con valores validos
        if(!"".equals(jTextField1.getText()))
        {
            //Se busca el pedido utilizando el ID
            Integer i, j;
            //VARIABLES PARA SER USADAS CUANDO SAQUES LOS DATOS DEL ARRAYLIST
            String Descripcion;
            Pedido ActualOrder;
            ID = Integer.parseInt(jTextField1.getText());
            ActualOrder = Controller.FindOrder(ID);
            jTextField1.setBackground(Color.white);
            if (Controller.FindOrder(ID) != null)
            {
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(true);
                jTextField4.setEnabled(true);
                jTextField5.setEnabled(true);
                jTextField6.setEnabled(true);
                cmdGenerateOrder.setEnabled(true);
                cmdDiscardOrder.setEnabled(true);
                Object[] Nuevo = new Object[3];
                //Se empieza a hacer el llenado del jTable1 que contiene el Resumen del Pedido
                //Aqui sacamos los datos de especialidades
                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                modelo.setNumRows(0); // Se elimina el contenido del Table
                for (i = 0; i < ActualOrder.GetSpecialities().size(); i++)
                {
                    Descripcion = ActualOrder.GetSpecialities().get(i).GetSpeciality().GetName();
                    Costo = ActualOrder.GetSpecialities().get(i).GetSpeciality().GetPrice();
                    Cantidad = ActualOrder.GetSpecialities().get(i).GetCount();
                    Balance = (Balance + (Cantidad * Costo));
                    Nuevo = new Object[] {Cantidad, Descripcion, Costo};
                    modelo.addRow(Nuevo);
                    ClearVariables(); //Se resetean las variables a status inciales
                    //Se añaden los contornos del plato al table
                    for (j = 0; j < ActualOrder.GetSpecialities().get(i).GetSides().size(); j++)
                    {
                        Descripcion = ActualOrder.GetSpecialities().get(i).GetSides().get(j).GetSide().GetName();
                        Cantidad = ActualOrder.GetSpecialities().get(i).GetSides().get(j).GetCount();
                        if (Cantidad == 1)
                            Nuevo = new Object[] {null, Descripcion, null};
                        else
                        Nuevo = new Object[] {Cantidad, Descripcion, null};

                        modelo.addRow(Nuevo);
                        ClearVariables();
                    }
                    ClearVariables();
                } 
                //Aqui la de los contornos ADICIONALES
                for (i = 0; i < ActualOrder.GetSides().size(); i++)
                {
                    Descripcion = ActualOrder.GetSides().get(i).GetSide().GetName();
                    Costo=ActualOrder.GetSides().get(i).GetSide().GetPrice();
                    Cantidad=ActualOrder.GetSides().get(i).GetCount();
                    Balance = (Balance + (Cantidad * Costo));
                    Nuevo = new Object[] {Cantidad, Descripcion, Costo};
                    modelo.addRow(Nuevo);
                    ClearVariables(); ////Se resetean las variables a status inciales
                }

                CalculateAmount(Balance);
            }
            else
            {
                jTextField1.setBackground(Color.red);
                ClearFiles();
            }
        }else{
            jTextField1.setBackground(Color.red);
        }
    }//GEN-LAST:event_cmdSearchOrderActionPerformed

    private void cmdDiscardOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDiscardOrderActionPerformed
        if(!"".equals(jTextField1.getText()))
        {
            Controller.RemoveOrder(Integer.parseInt(jTextField1.getText()));
        }
        ClearFiles();
    }//GEN-LAST:event_cmdDiscardOrderActionPerformed

    private void cmdGenerateOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGenerateOrderActionPerformed
        if(!"".equals(jTextField2.getText()) && !"".equals(jTextField3.getText()) && !"".equals(jTextField4.getText()) && !"".equals(jTextField5.getText()) && !"0 BsF ".equals(jLabel13.getText()))
        {
            PayForOrder();
            ClearFiles();
            jTextField1.setText("");
        //Mostrar datos faltantes coloreados en rojo
        }else{
            if("".equals(jTextField2.getText()))
            {
                jTextField2.setBackground(Color.red);
            }
            if("".equals(jTextField3.getText()))
            {
                jTextField3.setBackground(Color.red);
            }
            if("".equals(jTextField4.getText()))
            {
                jTextField4.setBackground(Color.red);
            }
            if("".equals(jTextField5.getText()))
            {
                jTextField5.setBackground(Color.red);
            }   
        } 
    }//GEN-LAST:event_cmdGenerateOrderActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void CalculateAmount(float Balance)
    {
        //Se encarga de calcular el Subtotal de la orden
        //Falta agregar que sume todas las columnas de precio del jtable
        float aux = 0;
        aux = (float) (Balance * 0.12);
        //Se empiezan a llenar los campos del subtotal, iva y total
        jLabel9.setText(String.format("%.2f", Balance) + " BsF ");
        jLabel11.setText(String.format("%.2f", aux) + " BsF ");
        aux = aux + Balance;
        Total = aux;
        jLabel13.setText(String.format("%.2f", aux) + " BsF ");    
    }
    private void PayForOrder()
    {//Se envian los datos del cliente al metodo payorder para poder generar  la factura
        Controller.PayOrder(jTextField2.getText(), Integer.parseInt(jTextField1.getText()) , jTextField3.getText(), (jTextField5.getText() + " " + jTextField6.getText()), jTextField4.getText());
    }
    public float GetTotal()
    {
        return (Total);
    }
    
    public final void ClearFiles()
    {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField6.setEnabled(false);
        cmdGenerateOrder.setEnabled(false);
        cmdDiscardOrder.setEnabled(false);
        jTextField1.setBackground(Color.WHITE);
        jTextField2.setBackground(Color.WHITE);
        jTextField3.setBackground(Color.WHITE);
        jTextField4.setBackground(Color.WHITE);
        jTextField5.setBackground(Color.WHITE);
        jLabel9.setText("0 BsF ");
        jLabel11.setText("0 BsF ");
        jLabel13.setText("0 BsF ");
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0); // Se elimina el contenido del Table
    }
    
    private void ClearVariables()
    {
        Costo = 0;
        Cantidad = 0;
        Descripcion = "";
        Object Nuevo[]= {"", "", ""};
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdDiscardOrder;
    private javax.swing.JButton cmdGenerateOrder;
    private javax.swing.JButton cmdSearchOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
