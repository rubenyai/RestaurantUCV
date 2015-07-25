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
import javax.swing.JOptionPane;
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
        
        //boton finalizar pedido disabled hasta q haya algo q mandar
        cmdGenerate.setEnabled(false);
        
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
                
                addedSpecialities.add(ce);
            }
        }       
        pageIndex = 1; // Comenzar desde la Pagina 1
        UpdateButtonLayout(); // Actualizar botones de seleccion
        UpdateTable();
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
        //Boton modificar contorno deshabilitado
        cmdEditSides.setEnabled(false);
        //Boton eliminar deshabilitado
        cmdDelete.setEnabled(false);
        // Actualiza la tabla con la informacion de Especialidades y Contornos seleccionados
        DefaultTableModel md = (DefaultTableModel) table.getModel();
        md.setRowCount(0); // Eliminar el contenido actual de la tabla
        
        //contadores para mostrarlos en los labels cantidad de platos y contornos adicionales
        int contSpecialities=0,contSides=0;
        
        // Mostrar Especialidades
        for (int i = 0; i < addedSpecialities.size(); i++)
        {
            // Agregar especialidad
            ContadorEspecialidad e = addedSpecialities.get(i);
            //Mostramos solo las especialidades que tienen un count mayor a 0 (que han sido pedidos)
            //En finalizar orden removemos estos mismos que tienen count igual a 0 para tener el array solo con ordenes pedidas
            if(e.GetCount()>0)
            {
            md.addRow(new Object[] {e.GetCount(), e.GetSpeciality().GetName(),e.GetSpeciality().GetTotalSides(), e.GetSpeciality().GetPrice()});
            contSpecialities+=e.GetCount();
            }
            // Agregar contornos incluidos
            //int sub;
            if (e.GetSides() == null)
                continue;
            
            for (int sub = 0; sub < e.GetSides().size(); sub++)
            {
                ContadorContorno c = e.GetSides().get(sub);
                int repeat;
                for (repeat = 0; repeat < c.GetCount(); repeat++)
                    md.addRow(new Object[] {null, c.GetSide().GetName(),"",""});
            }
        }
        // Mostrar Contornos Adicionales
        for (int a = 0; a < addedSides.size(); a++)
        {
            // Agregar Contorno
            ContadorContorno aux = addedSides.get(a);
            md.addRow(new Object[] {aux.GetCount(), aux.GetSide().GetName(),"", aux.GetSide().GetPrice()});
            contSides+=aux.GetCount();
        }
        //Buscamos la cantidad de contornos adicionales y platos para mostrarlos en el indicador
        lblSelectedSpecialities.setText(Integer.toString(contSpecialities));
        lblSelectedSidesAditionals.setText(Integer.toString(contSides)); 
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor de Pedidos");
        setMinimumSize(new java.awt.Dimension(620, 633));
        setResizable(false);
        setSize(new java.awt.Dimension(620, 633));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableMouseEntered(evt);
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
        cmdAdd1.setName("1"); // NOI18N
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
        cmdAdd2.setName("2"); // NOI18N
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
        cmdAdd3.setName("3"); // NOI18N
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
        cmdAdd5.setName("5"); // NOI18N
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
        cmdAdd6.setName("6"); // NOI18N
        cmdAdd6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 127, -1));

        cmdAdd4.setText("Agregar");
        cmdAdd4.setName("4"); // NOI18N
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteAllActionPerformed
        //Boton eliminar todo
        cmdGenerate.setEnabled(false);
        addedSides = new ArrayList<>();
        addedSpecialities = new ArrayList<>();
        int i;
        ArrayList<Especialidad> billboardSpecialities = Controller.GetBillboard().GetSpecialities();
        for (i = 0; i < billboardSpecialities.size(); i++)
        {
            // Agregar el contorno al cache
            if (billboardSpecialities.get(i).GetVisible())
            {
                ContadorEspecialidad ce = new ContadorEspecialidad(billboardSpecialities.get(i));
                ce.SetCount(0);
                
                addedSpecialities.add(ce);
            }
        }
        lblSelectedSpecialities.setText("0");
        lblSelectedSidesAditionals.setText("0");
        UpdateTable();
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
            {
                if(Integer.parseInt(table.getValueAt(i, 0).toString())>0)
                {
                    //Decrementamos cont -1 pero aun asi se muestra en la table
                    //Para esto buscamos con el nombre cual es el objeto
                    String name=table.getValueAt(i, 1).toString();
                    for(int j=0;j<addedSpecialities.size();j++)
                    {
                        if(addedSpecialities.get(j).GetSpeciality().GetName()==name)
                        {
                            addedSpecialities.get(j).SetCount( ((addedSpecialities.get(j).GetCount()))-1);
                        }
                    }
                }
            }
        }
     UpdateTable();
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Borramos la table
        DefaultTableModel model1 = (DefaultTableModel)this.table.getModel();
        model1.setRowCount(0);
    }//GEN-LAST:event_formWindowOpened

    private void cmdDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDiscardActionPerformed
        //Boton Descartar pedido
        //Borramos la table
        cmdGenerate.setEnabled(false);
        addedSides = new ArrayList<>();
        addedSpecialities = new ArrayList<>();
        int i;
        ArrayList<Especialidad> billboardSpecialities = Controller.GetBillboard().GetSpecialities();
        for (i = 0; i < billboardSpecialities.size(); i++)
        {
            // Agregar el contorno al cache
            if (billboardSpecialities.get(i).GetVisible())
            {
                ContadorEspecialidad ce = new ContadorEspecialidad(billboardSpecialities.get(i));
                ce.SetCount(0);
                
                addedSpecialities.add(ce);
            }
        }
        lblSelectedSpecialities.setText("0");
        lblSelectedSidesAditionals.setText("0");
        UpdateTable();   
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
        cmdGenerate.setEnabled(true);
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
        
        //Variables que pasaremos
        ArrayList<ContadorContorno> baseSides=new ArrayList<>();
        int maxSides=0;
        
        //Averiguamos si el contorno elegido es propio de una especialidad o es un contorno adicional
        DefaultTableModel md = (DefaultTableModel) table.getModel();
        if (md.getRowCount() < 1)
            return;
        
        int i;
        for (i = md.getRowCount() - 1; i >= 0; i--)
        {
            if (table.isRowSelected(i))
            {
                //si esta vacio en price, es un contorno de una especialidad
                //al contrario, es un contorno adicional
                if(table.getValueAt(i, 3)=="")
                {
                    //Buscamos el array de contornos de esta especialidad
                    
                    //Buscamos la cantidad de contornos que tiene esta especialidad
                    maxSides=Integer.parseInt(table.getValueAt(i, 2).toString());
                }
                else
                {
                    //Buscamos el array de contornos de esta especialidad
                    baseSides=addedSides;
                    //max sides le asignamos 0, no hay limite
                    maxSides=0;
                }
            }
        }
        // Preparar un hilo nuevo para ejecutar el metodo ChooseSides de manera asincronica
        Thread w = new Thread(() -> {
            //////////////////////////////
            ////////////////////////////
            //ArrayList<ContadorContorno> sides1 = Controller.ChooseSides(maxSides, baseSides);
            ////////////////////////// Da error npi why
            //////////////////////////////////////////
            ArrayList<ContadorContorno> sides1 =addedSides; ///esta linea no sirve para nada, es para que no explote
            if (sides1 == null)
                return;
            
            // Combinar los arreglos addedSides y sides
            int si, ti;
            // Por cada contorno adicional seleccionado
            for (si = 0; si < sides1.size(); si++)
            {
                // Por cada contorno adicional ya existente
                boolean found = false;
                for (ti = 0; ti < addedSides.size(); ti++)
                {
                    // Comparacion de punteros detras de bambalinas
                    if (addedSides.get(ti).GetSide() == sides1.get(si).GetSide())
                    {
                        found = true;
                        addedSides.get(ti).AddCount(sides1.get(si).GetCount()); // Agregar Cantidad
                        break; // Cortocircuitar ciclo
                    }
                }
                
                if (!found)
                    addedSides.add(sides1.get(si)); // Agregar nuevo contorno
            }
            
            // Actualizar tabla de pedido
            UpdateTable();
        });
        
        // Ejecutar el hilo
        w.start();
    }//GEN-LAST:event_cmdEditSidesActionPerformed

    private void cmdGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGenerateActionPerformed
        // Boton finalizar pedido
        //Borramos ordenes sin count
        for(int i=0;i<addedSpecialities.size();i++)
        {
            if(addedSpecialities.get(i).GetCount()==0)
            {
                addedSpecialities.remove(i);
            }
        }
       //Generate Order a unpaid
       if(addedSpecialities.isEmpty()!=false || addedSides.isEmpty()!=false)
       {
           Controller.GenerateOrder(addedSpecialities, addedSides);
       }
        cmdGenerate.setEnabled(false);
        addedSides = new ArrayList<>();
        addedSpecialities = new ArrayList<>();
        int i;
        ArrayList<Especialidad> billboardSpecialities = Controller.GetBillboard().GetSpecialities();
        for (i = 0; i < billboardSpecialities.size(); i++)
        {
            // Agregar el contorno al cache
            if (billboardSpecialities.get(i).GetVisible())
            {
                ContadorEspecialidad ce = new ContadorEspecialidad(billboardSpecialities.get(i));
                ce.SetCount(0);
                
                addedSpecialities.add(ce);
            }
        }
        lblSelectedSpecialities.setText("0");
        lblSelectedSidesAditionals.setText("0");
        UpdateTable();
    }//GEN-LAST:event_cmdGenerateActionPerformed
 
    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
         // Recibe los eventos de Adicion de contornos, y los redirije al metodo
        // de incremento de seleccion
        cmdGenerate.setEnabled(true);
        JButton source = (JButton) evt.getSource(); // Obtener el control primario que genero este evento
        Integer specialityIndex = Integer.parseInt(source.getName()); // Obtener el indice del elemento en esta sub-pagina
 
        SpecialityUpdateActionDispatcher(specialityIndex, true); // Llamar al metodo principal de adicion
    }//GEN-LAST:event_cmdAddActionPerformed

    private void tableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseEntered

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel md = (DefaultTableModel) table.getModel();
        if (md.getRowCount() < 1)
            return;
        if (md.getRowCount() > 1)
            cmdGenerate.setEnabled(true);
        int i;
        for (i = md.getRowCount() - 1; i >= 0; i--)
        {
            if (table.isRowSelected(i))
            {
                //Ver si es un contorno, activa boton modificar
                if(table.getValueAt(i, 2)=="")
                {
                    cmdEditSides.setEnabled(true);
                }
                else
                {
                    cmdEditSides.setEnabled(false);
                }
                //Ver si es una especialidad, activar boton eliminar
                if(table.getValueAt(i, 2)!="")
                {
                    cmdDelete.setEnabled(true);
                }
                else
                {
                    cmdDelete.setEnabled(false);
                } 
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

       // Incrementa en 1 el contorno descrito pot el indice especificado
    // de la sub-pagina visible actualmente
    private void SpecialityUpdateActionDispatcher(int index, boolean increment) {
        int finalIndex = index + ((pageIndex - 1) * 6);
        finalIndex--; // Indice real del contorno a actualizar
        
        // Actualizar el indice en cache
        addedSpecialities.get(finalIndex).AddCount(increment ? 1 : -1);
                
        // Refrescar la tabla de contornos seleccionados
        UpdateTable();
        
        // Actualizar contadores de seleccion en los Botones
        UpdateButtonLayout(); 
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
            
            if (realIndex < addedSpecialities.size())
            {
                // Existe un elemento para el control
                ContadorEspecialidad c = addedSpecialities.get(realIndex);
                
                title.setText(c.GetSpeciality().GetName());
                price.setText(String.format("%.2f", c.GetSpeciality().GetPrice()) + " BsF ");
                cont.setText(String.format(c.GetSpeciality().GetTotalSides() + " Contornos "));
                time.setText(String.format(c.GetSpeciality().GetTime() + " Min "));
                
                title.setVisible(true);
                price.setVisible(true);
                cont.setVisible(true);
                time.setVisible(true);
                addButton.setVisible(true);
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
        cmdNextPage.setEnabled(realIndex < addedSpecialities.size());
        cmdPreviousPage.setEnabled(realIndex > 6);
    }
    
    public ArrayList<ContadorEspecialidad> GetSelectedSides()
    {
        // Obtiene una lista de los Contornos seleccionados
        // La lista es pre-procesada desde el cache eliminando los items no seleccionados
        
        if (addedSpecialities == null)
            return null;
        
        ArrayList<ContadorEspecialidad> output = new ArrayList<>();
        
        int i;
        for (i = 0; i < addedSpecialities.size(); i++) 
        {
            if (addedSpecialities.get(i).GetCount() > 0)
                output.add(addedSpecialities.get(i));   
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
