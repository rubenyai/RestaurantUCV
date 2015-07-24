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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class WndGestorPedido extends javax.swing.JFrame {

    // Contiene el siguiente identificador de pedido libre
    private static int nextOrderId = 2456;
    
    // Contiene los Contornos adicionales seleccionados actualmente
    ArrayList<ContadorContorno> addedSides;
    
    // Contiene las Especialidades seleccionadas actualmente
    ArrayList<ContadorEspecialidad> addedSpecialities;
    
   private final static int COLUMN_COUNT = 0;
    private final static int COLUMN_DESCRIPTION = 1;
    
    private ArrayList<ContadorEspecialidad> specialitiesLocalCache;
    
    private int availableSpecialities; // Cantidad de Contornos disponibles para seleccion
    private int selectedSpecialities; // Cantidad de Contornos seleccionados actualmente
    private int pageIndex; // Indice de la pagina actual en Base 1
    
    /** Lista de Controles en el ArrayList:
     * 
     *    JLabel     JLabel     JLabel     JButton     JButton
     *    lblTitleX  lblPriceX  lblCountX  cmdAddX     cmdRemoveX
    **/ 
    private ArrayList<JComponent[]> controlMap; // Contiene todos los Controles de la ventana ordenados por indice logico a los contornos
    
    
    /**
     * Creates new form WndGestorPedido
     */
    public WndGestorPedido() {
        initComponents();
        this.setSize(this.getWidth() + 10, this.getHeight() + 10); // Incrementar el tamaño de la ventana
        
        // Crear las listas de especialidades y contornos
        addedSides = new ArrayList<>();
        addedSpecialities = new ArrayList<>();
        
        this.setLocationRelativeTo(null); // Centrar ventana
        
        // Construir mapa de Controles
        controlMap = new ArrayList<>();
        controlMap.add(new JComponent[] {lblTitle1, lblPrice1, lblCont1, lblTime1, cmdAdd1});
        controlMap.add(new JComponent[] {lblTitle2, lblPrice2, lblCont2, lblTime2, cmdAdd2});
        controlMap.add(new JComponent[] {lblTitle3, lblPrice3, lblCont3, lblTime3, cmdAdd3});
        controlMap.add(new JComponent[] {lblTitle4, lblPrice4, lblCont4, lblTime4, cmdAdd4});
        controlMap.add(new JComponent[] {lblTitle5, lblPrice5, lblCont5, lblTime5, cmdAdd5});
        controlMap.add(new JComponent[] {lblTitle6, lblPrice6, lblCont6, lblTime6, cmdAdd6});
        
        // Ajustar el ancho de las columnas de la tabla
        table.getColumnModel().getColumn(COLUMN_COUNT).setPreferredWidth(40);
        table.getColumnModel().getColumn(COLUMN_DESCRIPTION).setPreferredWidth(240);
        
        // Inicializar la copia local (cache) de contornos
        specialitiesLocalCache = new ArrayList<>();
        
        // Copiar contornos actuales (solo visibles)
        int i;
        ArrayList<Especialidad> billboardSpecialities = Controller.GetBillboard().GetSpecialities();
        for (i = 0; i < billboardSpecialities.size(); i++)
        {
            // Agregar el contorno al cache
            if (billboardSpecialities.get(i).GetVisible())
            {
                ContadorEspecialidad ce = new ContadorEspecialidad(billboardSpecialities.get(i));
                ce.SetCount(0);
                
                specialitiesLocalCache.add(ce);
            }
        }
        
        // Aplicar la seleccion actual pasada al constructor a la copia cache de contornos
        selectedSpecialities = 0;
        
        pageIndex = 1; // Comenzar desde la Pagina 1
        UpdateButtonLayout(); // Actualizar botones de seleccion
        UpdateSelectionTable(); // Actualizar tabla de seleccion y estadisticas 
    }
    
    public int GetNextOrderId()
    {
        // Obtiene el identificador del siguiente pedido disponible para creacion
        return nextOrderId;
    }
    
    public void IncrementNextOrderId()
    {
        // Incrementa el identificador del siguiente pedido disponible
        nextOrderId++;
    }
    
    private void UpdateTable()
    {
        // Actualiza la tabla con la informacion de Especialidades y Contornos seleccionados
        DefaultTableModel md = (DefaultTableModel) table.getModel();
        md.setRowCount(0); // Eliminar el contenido actual de la tabla
        
        // Mostrar Especialidades
        int i;
        for (i = 0; i < addedSpecialities.size(); i++)
        {
            // Agregar especialidad
            ContadorEspecialidad e = addedSpecialities.get(i);
            md.addRow(new Object[] {e.GetCount(), e.GetSpeciality().GetName(), e.GetSpeciality().GetPrice()});
            
            // Agregar contornos incluidos
            int sub;
            if (e.GetSides() == null)
                continue;
            
            for (sub = 0; sub < e.GetSides().size(); sub++)
            {
                ContadorContorno c = e.GetSides().get(sub);
                int repeat;
                for (repeat = 0; repeat < c.GetCount(); repeat++)
                    md.addRow(new Object[] {null, c.GetSide().GetName(), 0});
            }
        }
        
        // Mostrar Contornos Adicionales
        for (i = 0; i < addedSides.size(); i++)
        {
            // Agregar Contorno
            ContadorContorno aux = addedSides.get(i);
            md.addRow(new Object[] {aux.GetCount(), aux.GetSide().GetName(), aux.GetSide().GetPrice()});
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

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        cmdDeleteAll = new javax.swing.JButton();
        cmdAddAdditionalSide = new javax.swing.JButton();
        cmdDiscard = new javax.swing.JButton();
        cmdGenerate = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblTitle1 = new javax.swing.JLabel();
        lblCont1 = new javax.swing.JLabel();
        lblTime1 = new javax.swing.JLabel();
        lblPrice1 = new javax.swing.JLabel();
        cmdAdd1 = new javax.swing.JButton();
        lblTitle2 = new javax.swing.JLabel();
        lblCont2 = new javax.swing.JLabel();
        lblTime2 = new javax.swing.JLabel();
        lblPrice2 = new javax.swing.JLabel();
        cmdAdd2 = new javax.swing.JButton();
        lblTitle3 = new javax.swing.JLabel();
        lblCont3 = new javax.swing.JLabel();
        lblTime3 = new javax.swing.JLabel();
        lblPrice3 = new javax.swing.JLabel();
        cmdAdd3 = new javax.swing.JButton();
        lblTitle5 = new javax.swing.JLabel();
        lblCont5 = new javax.swing.JLabel();
        lblTime5 = new javax.swing.JLabel();
        lblPrice5 = new javax.swing.JLabel();
        cmdAdd5 = new javax.swing.JButton();
        lblTitle4 = new javax.swing.JLabel();
        lblTitle6 = new javax.swing.JLabel();
        lblCont4 = new javax.swing.JLabel();
        lblCont6 = new javax.swing.JLabel();
        lblTime6 = new javax.swing.JLabel();
        lblTime4 = new javax.swing.JLabel();
        lblPrice6 = new javax.swing.JLabel();
        lblPrice4 = new javax.swing.JLabel();
        cmdAdd6 = new javax.swing.JButton();
        cmdAdd4 = new javax.swing.JButton();
        cmdPreviousPage = new javax.swing.JButton();
        cmdNextPage = new javax.swing.JButton();
        lblSelectedSpecialities = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblSelectedSidesAditionals = new javax.swing.JLabel();
        cmdDelete = new javax.swing.JButton();
        cmdEditSides = new javax.swing.JButton();
        lblNoSelection = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor de Pedidos");
        setMinimumSize(new java.awt.Dimension(620, 633));
        setResizable(false);
        setSize(new java.awt.Dimension(620, 633));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "Pollo a la Canasta",  new Integer(2),  new Float(580.4)},
                {null, "Arroz Blanco", null, null},
                {null, "Yuca Frita", null, null},
                { new Integer(2), "Pizza Margarita",  new Integer(0),  new Float(685.4)},
                { new Integer(1), "Hamburguesa de Carne",  new Integer(1),  new Float(483.8)},
                {null, "Papas Fritas", null, null},
                { new Integer(3), "Papas Fritas",  new Integer(0),  new Float(210.7)},
                { new Integer(1), "Nestea Durazno",  new Integer(0),  new Float(90.0)}
            },
            new String [] {
                "Cantidad", "Descripción", "Contornos", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 460, 280));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Platos Seleccionados");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        cmdDeleteAll.setText("Eliminar Todo");
        cmdDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteAllActionPerformed(evt);
            }
        });
        getContentPane().add(cmdDeleteAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 140, -1));

        cmdAddAdditionalSide.setText("<html>Agregar Contorno<br>Adicional");
        cmdAddAdditionalSide.setToolTipText("");
        cmdAddAdditionalSide.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdAddAdditionalSide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddAdditionalSideActionPerformed(evt);
            }
        });
        getContentPane().add(cmdAddAdditionalSide, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 140, -1));

        cmdDiscard.setText("Descartar Pedido");
        cmdDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDiscardActionPerformed(evt);
            }
        });
        getContentPane().add(cmdDiscard, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 590, 169, 43));

        cmdGenerate.setText("Finalizar Pedido");
        cmdGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGenerateActionPerformed(evt);
            }
        });
        getContentPane().add(cmdGenerate, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 590, 169, 43));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menú del dia - Platos Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle1.setText("Pizza Margarita");
        jPanel1.add(lblTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        lblCont1.setText("S/C");
        jPanel1.add(lblCont1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        lblTime1.setText("25 Min");
        jPanel1.add(lblTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        lblPrice1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice1.setText("580,40 BsF");
        jPanel1.add(lblPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 127, -1));

        cmdAdd1.setText("Agregar");
        cmdAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 127, -1));

        lblTitle2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle2.setText("Pizza Vegetariana");
        jPanel1.add(lblTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, -1, -1));

        lblCont2.setText("S/C");
        jPanel1.add(lblCont2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        lblTime2.setText("25 Min");
        jPanel1.add(lblTime2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        lblPrice2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice2.setText("590,00 BsF");
        jPanel1.add(lblPrice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 127, -1));

        cmdAdd2.setText("Agregar");
        cmdAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 127, -1));

        lblTitle3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle3.setText("Pizza 4 Estaciones");
        jPanel1.add(lblTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        lblCont3.setText("S/C");
        jPanel1.add(lblCont3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        lblTime3.setText("25 Min");
        jPanel1.add(lblTime3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, -1));

        lblPrice3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice3.setText("759,90 BsF");
        jPanel1.add(lblPrice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 127, -1));

        cmdAdd3.setText("Agregar");
        cmdAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 127, -1));

        lblTitle5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle5.setText("Haburguesa de Carne");
        jPanel1.add(lblTitle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, -1));

        lblCont5.setText("1 Contornos");
        jPanel1.add(lblCont5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        lblTime5.setText("10 Min");
        jPanel1.add(lblTime5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, -1, -1));

        lblPrice5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice5.setText("483,80 BsF");
        jPanel1.add(lblPrice5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 127, -1));

        cmdAdd5.setText("Agregar");
        cmdAdd5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 127, -1));

        lblTitle4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle4.setText("Pollo a la Canasta");
        jPanel1.add(lblTitle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        lblTitle6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle6.setText("Hamburguesa de Pollo");
        jPanel1.add(lblTitle6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));

        lblCont4.setText("2 Contornos");
        jPanel1.add(lblCont4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        lblCont6.setText("1 Contornos");
        jPanel1.add(lblCont6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, -1, -1));

        lblTime6.setText("10 Min");
        jPanel1.add(lblTime6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, -1, -1));

        lblTime4.setText("15 Min");
        jPanel1.add(lblTime4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        lblPrice6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice6.setText("410,00 BsF");
        jPanel1.add(lblPrice6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 127, -1));

        lblPrice4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice4.setText("580,40 BsF");
        jPanel1.add(lblPrice4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 127, -1));

        cmdAdd6.setText("Agregar");
        cmdAdd6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 127, -1));

        cmdAdd4.setText("Agregar");
        cmdAdd4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 127, -1));

        cmdPreviousPage.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        cmdPreviousPage.setText("<");
        cmdPreviousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPreviousPageActionPerformed(evt);
            }
        });
        jPanel1.add(cmdPreviousPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 50, 210));

        cmdNextPage.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        cmdNextPage.setText(">");
        cmdNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextPageActionPerformed(evt);
            }
        });
        jPanel1.add(cmdNextPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 50, 210));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 610, 250));

        lblSelectedSpecialities.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSelectedSpecialities.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectedSpecialities.setText("4");
        getContentPane().add(lblSelectedSpecialities, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, 140, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Platos");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 140, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("<html>Contornos<br>Adicionales");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 510, 140, 40));

        lblSelectedSidesAditionals.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSelectedSidesAditionals.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectedSidesAditionals.setText("4");
        getContentPane().add(lblSelectedSidesAditionals, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 550, 140, -1));

        cmdDelete.setText("Eliminar");
        cmdDelete.setToolTipText("");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(cmdDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 140, -1));

        cmdEditSides.setText("<html>Modificar<br>Contornos");
        cmdEditSides.setToolTipText("");
        cmdEditSides.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdEditSides.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditSidesActionPerformed(evt);
            }
        });
        getContentPane().add(cmdEditSides, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 140, -1));

        lblNoSelection.setText("No Hay Especialidades Seleccionadas");
        getContentPane().add(lblNoSelection, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 200, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteAllActionPerformed
        //Boton eliminar todo
        //Borramos la table
        DefaultTableModel model1 = (DefaultTableModel)this.table.getModel();
        model1.setRowCount(0);
        
    }//GEN-LAST:event_cmdDeleteAllActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
         // Boton eliminar 
        //Elimina uno o varios elementos (filas) seleccionados de la tabla      
        DefaultTableModel md = (DefaultTableModel) table.getModel();
        if (md.getRowCount() < 1)
            return;
        
        int i;
        for (i = md.getRowCount() - 1; i >= 0; i--)
        {
            if (table.isRowSelected(i))
                md.removeRow(i);
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Borramos la table
        DefaultTableModel model1 = (DefaultTableModel)this.table.getModel();
        model1.setRowCount(0);
    }//GEN-LAST:event_formWindowOpened

    private void cmdDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDiscardActionPerformed
        //Boton Descartar pedido
        //Borramos la table
        DefaultTableModel model1 = (DefaultTableModel)this.table.getModel();
        model1.setRowCount(0);
        lblSelectedSpecialities.setText("0");
        lblSelectedSidesAditionals.setText("0");
    }//GEN-LAST:event_cmdDiscardActionPerformed

    private void cmdPreviousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPreviousPageActionPerformed
        // Boton Flecha izquierda:
        pageIndex--;
        UpdateButtonLayout();
    }//GEN-LAST:event_cmdPreviousPageActionPerformed

    private void cmdNextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNextPageActionPerformed
        // Boton Flecha Derecha:
        pageIndex++;
        UpdateButtonLayout();
    }//GEN-LAST:event_cmdNextPageActionPerformed

    private void cmdAddAdditionalSideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddAdditionalSideActionPerformed
        // Boton Agregar contorno adicional
        
        // Preparar un hilo nuevo para ejecutar el metodo ChooseSides de manera asincronica
        Thread t = new Thread(() -> {
            ArrayList<ContadorContorno> sides = Controller.ChooseSides(0, null);
            
            if (sides == null)
                return;
            
            // Combinar los arreglos addedSides y sides
            int si, ti;
            // Por cada contorno adicional seleccionado
            for (si = 0; si < sides.size(); si++)
            {
                // Por cada contorno adicional ya existente
                boolean found = false;
                for (ti = 0; ti < addedSides.size(); ti++)
                {
                    // Comparacion de punteros detras de bambalinas
                    if (addedSides.get(ti).GetSide() == sides.get(si).GetSide())
                    {
                        found = true;
                        addedSides.get(ti).AddCount(sides.get(si).GetCount()); // Agregar Cantidad
                        break; // Cortocircuitar ciclo
                    }
                }
                
                if (!found)
                    addedSides.add(sides.get(si)); // Agregar nuevo contorno
            }
            
            // Actualizar tabla de pedido
            UpdateTable();
        });
        
        // Ejecutar el hilo
        t.start();
    }//GEN-LAST:event_cmdAddAdditionalSideActionPerformed

    private void cmdEditSidesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditSidesActionPerformed
        // Boton modificar contornos
    }//GEN-LAST:event_cmdEditSidesActionPerformed

    private void cmdGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGenerateActionPerformed
        // Boton finalizar pedido
    }//GEN-LAST:event_cmdGenerateActionPerformed
 
    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
         // Recibe los eventos de Adicion de contornos, y los redirije al metodo
        // de incremento de seleccion
        
        JButton source = (JButton) evt.getSource(); // Obtener el control primario que genero este evento
        Integer specialityIndex = Integer.parseInt(source.getName()); // Obtener el indice del elemento en esta sub-pagina
        
        SpecialityUpdateActionDispatcher(specialityIndex, true); // Llamar al metodo principal de adicion
    }//GEN-LAST:event_cmdAddActionPerformed

       // Incrementa en 1 el contorno descrito pot el indice especificado
    // de la sub-pagina visible actualmente
    private void SpecialityUpdateActionDispatcher(int index, boolean increment) {
        int finalIndex = index + ((pageIndex - 1) * 6);
        finalIndex--; // Indice real del contorno a actualizar
        
        // Actualizar el indice en cache
        specialitiesLocalCache.get(finalIndex).AddCount(increment ? 1 : -1);
        
        // Refrescar la tabla de contornos seleccionados
        UpdateSelectionTable();
        
        // Actualizar contadores de seleccion en los Botones
        UpdateButtonLayout(); 
    }
    
    private void UpdateSelectionTable()
    {
        // Actualiza la Tabla de contornos seleccionados con la informacion en Cache
        
        int i;
        DefaultTableModel md = (DefaultTableModel) table.getModel();
        md.setRowCount(0); // Eliminar el contenido de la Tabla
        int selected = 0;
        
        // Agregar contornos si su cantidad es mayor a 1
        for (i = 0; i < specialitiesLocalCache.size(); i++)
        {
            if (specialitiesLocalCache.get(i).GetCount() > 0)
            {
                md.addRow(new Object[] {specialitiesLocalCache.get(i).GetCount(),
                    specialitiesLocalCache.get(i).GetSpeciality().GetName()});
                selected += specialitiesLocalCache.get(i).GetCount();
            }
        }
        
        // Actualizar estadisticas
        lblSelectedSpecialities.setText(String.valueOf(selected));
        selectedSpecialities = selected;
        lblSelectedSidesAditionals.setText("0");
        
        lblNoSelection.setVisible(md.getRowCount() < 1);
    }
    
    private void UpdateButtonLayout()
    {
        // Actualiza la disposicion y el texto de los controles de Adicion/Substraccion de Contornos
        // agregando soporte para Paginacion y Selecciones Dinamicas
        
        int realIndex = 6 * (pageIndex - 1);
        int logicalIndex = 0;
        
        for (logicalIndex = 0; logicalIndex < 6; logicalIndex++)
        {
            JLabel title = (JLabel) controlMap.get(logicalIndex)[0];
            JLabel price = (JLabel) controlMap.get(logicalIndex)[1];
            JLabel cont = (JLabel) controlMap.get(logicalIndex)[2];
            JLabel time = (JLabel) controlMap.get(logicalIndex)[3];
            JButton addButton = (JButton) controlMap.get(logicalIndex)[4];
            
            if (realIndex < specialitiesLocalCache.size())
            {
                // Existe un elemento para el control
                ContadorEspecialidad c = specialitiesLocalCache.get(realIndex);
                
                title.setText(c.GetSpeciality().GetName());
                price.setText(String.format("%.2f", c.GetSpeciality().GetPrice()) + " BsF ");
                cont.setText(String.format(c.GetSpeciality().GetTotalSides() + " Contornos "));
                time.setText(String.format(c.GetSpeciality().GetTime() + " Min "));
                
                title.setVisible(true);
                price.setVisible(true);
                cont.setVisible(true);
                time.setVisible(true);
                addButton.setVisible(true);
                
                addButton.setEnabled(selectedSpecialities < availableSpecialities || availableSpecialities == 0);
            }
            else
            {
                // No existe un contorno para el control. Ocultar todos sus componentes
                title.setVisible(false);
                price.setVisible(false);
                cont.setVisible(false);
                time.setVisible(false);
                addButton.setVisible(false);
            }
                
            realIndex++;
        }
        
        // Comprobacion de limite para realIndex
        // (Comprobar si existen mas paginas a la derecha/izquierda)
        cmdNextPage.setEnabled(realIndex < specialitiesLocalCache.size());
        cmdPreviousPage.setEnabled(realIndex > 6);
    }
    
    public ArrayList<ContadorEspecialidad> GetSelectedSides()
    {
        // Obtiene una lista de los Contornos seleccionados
        // La lista es pre-procesada desde el cache eliminando los items no seleccionados
        
        if (specialitiesLocalCache == null)
            return null;
        
        ArrayList<ContadorEspecialidad> output = new ArrayList<>();
        
        int i;
        for (i = 0; i < specialitiesLocalCache.size(); i++) 
        {
            if (specialitiesLocalCache.get(i).GetCount() > 0)
                output.add(specialitiesLocalCache.get(i));   
        }
        
        if (output.size() > 0)
            return output;
        else
            return null;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAdd1;
    private javax.swing.JButton cmdAdd2;
    private javax.swing.JButton cmdAdd3;
    private javax.swing.JButton cmdAdd4;
    private javax.swing.JButton cmdAdd5;
    private javax.swing.JButton cmdAdd6;
    private javax.swing.JButton cmdAddAdditionalSide;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdDeleteAll;
    private javax.swing.JButton cmdDiscard;
    private javax.swing.JButton cmdEditSides;
    private javax.swing.JButton cmdGenerate;
    private javax.swing.JButton cmdNextPage;
    private javax.swing.JButton cmdPreviousPage;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCont1;
    private javax.swing.JLabel lblCont2;
    private javax.swing.JLabel lblCont3;
    private javax.swing.JLabel lblCont4;
    private javax.swing.JLabel lblCont5;
    private javax.swing.JLabel lblCont6;
    private javax.swing.JLabel lblNoSelection;
    private javax.swing.JLabel lblPrice1;
    private javax.swing.JLabel lblPrice2;
    private javax.swing.JLabel lblPrice3;
    private javax.swing.JLabel lblPrice4;
    private javax.swing.JLabel lblPrice5;
    private javax.swing.JLabel lblPrice6;
    private javax.swing.JLabel lblSelectedSidesAditionals;
    private javax.swing.JLabel lblSelectedSpecialities;
    private javax.swing.JLabel lblTime1;
    private javax.swing.JLabel lblTime2;
    private javax.swing.JLabel lblTime3;
    private javax.swing.JLabel lblTime4;
    private javax.swing.JLabel lblTime5;
    private javax.swing.JLabel lblTime6;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lblTitle2;
    private javax.swing.JLabel lblTitle3;
    private javax.swing.JLabel lblTitle4;
    private javax.swing.JLabel lblTitle5;
    private javax.swing.JLabel lblTitle6;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
