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
import isucv.restaurant.model.Especialidad;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */

public class IGestorPedidos extends javax.swing.JFrame {
        
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    // Contiene el siguiente identificador de pedido libre
    private static int nextOrderId = 1;
    
    // Contiene los Contornos adicionales seleccionados actualmente
    ArrayList<ContadorContorno> addedSides;
    
    // Contiene todas las especialidades visibles en cartelera.
    // Este arreglo permite realizar el enlace con los botones de "Agregar"
    ArrayList<Especialidad> baseSpecialities;
    
    // Contiene las Especialidades seleccionadas actualmente
    ArrayList<ContadorEspecialidad> addedSpecialities;
    
    // Constantes para acceder a las columnas de la tabla por Nombre
    private final static int COLUMN_COUNT = 0;
    private final static int COLUMN_DESCRIPTION = 1;
    private final static int COLUMN_SIDES = 2;
    private final static int COLUMN_PRICE = 3;
    
    // Variable global a la clase para poder ser accesible por metodos Lambda
    private Integer maxSides = 0;
       
    private int pageIndex; // Indice de la pagina actual en Base 1
    
    /**  Contiene todos los Controles de la ventana ordenados por indice logico a los contornos 
     * Lista de Controles en el ArrayList:
     * 
     *    JLabel     JLabel     JLabel     JButton     JButton
     *    lblTitleX  lblPriceX  lblCountX  cmdAddX     cmdRemoveX
    **/ 
    private final ArrayList<JComponent[]> controlMap;
    
    /**
     * Mapa de Filas en el JTable para enlazar cada una de ellas inequivocamente
     * con las especialidades y los contornos adicionales agregados al pedido.
     * 
     * Solo se registran las especialidades y contornos adicionales en el Mapa.
     * Las referencias a los contornos incluidos en una especialidad no se almacenan
     * y sus indices en el mapa se establecen a null.
     * Esto no deberia causar problema ya que el boton Eliminar siempre esta deshabilitado
     * para filas Contorno incluidas.
     * 
     * El tipo de objeto debe deducirse por el valor de la columna "Contorno" perteneciente
     * a la fila que se accesa en el metodo, para poder hacer la conversion de tipo de dato
     * apropiada para el objeto. Es decir, el mapa no hace ninguna distincion explicita sobre
     * si el elemento referenciado es una Especialidad o un Contorno. Corresponde al codigo
     * del metodo en cuestion deducir el tipo de objeto dependiendo de los demas valores de las celdas
     */
    private ArrayList<Integer> tableRowMap; // Contiene todas las especialidades y Contornos adicionales agregados por indice real a la tabla
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
    
    // Obtiene el identificador del siguiente pedido disponible para creacion
    public int GetNextOrderId() { return nextOrderId; }
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public IGestorPedidos() {
        initComponents();
        this.setSize(this.getWidth() + 10, this.getHeight() + 10); // Incrementar el tamaño de la ventana
                
        // Crear las listas de especialidades y contornos
        addedSides = new ArrayList<>();
        addedSpecialities = new ArrayList<>();
        baseSpecialities = new ArrayList<>();
        
        //boton finalizar pedido disabled hasta q haya algo q mandar
        cmdGenerate.setEnabled(false);
        cmdDiscard.setEnabled(false);
        cmdDeleteAll.setEnabled(false);
        
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
        ArrayList<Especialidad> billboardSpecialities = Controller.GetBillboardSpecialities();
        for (i = 0; i < billboardSpecialities.size(); i++)
        {
            // Agregar el contorno al cache
            if (billboardSpecialities.get(i).GetVisible())
            {                
                baseSpecialities.add(billboardSpecialities.get(i));
            }
        }       
        pageIndex = 1; // Comenzar desde la Pagina 1
        UpdateButtonLayout(); // Actualizar botones de seleccion
        UpdateTable();
    }
    
    // Agrega una nueva especialidad basandose en el indice del boton Agregar
    private void SpecialityUpdateActionDispatcher(int index) {
        int finalIndex = index + ((pageIndex - 1) * 6);
        finalIndex--; // Indice real de la especialidad a agregar
        
        ContadorEspecialidad ce = new ContadorEspecialidad(baseSpecialities.get(finalIndex));
        ce.SetCount(1);
        
        // Agregar la Especialidad
        addedSpecialities.add(ce);
                
        // Refrescar la tabla de especialidades seleccionados
        UpdateTable(); 
    }
    
    // Actualiza los textos de los Botones de Agregar Especialidad
    private void UpdateButtonLayout()
    {
        // Actualiza la disposicion y el texto de los controles de Adicion/Substraccion de Contornos
        // agregando soporte para Paginacion y Selecciones Dinamicas
        
        int realIndex = 6 * (pageIndex - 1);
        int logicalIndex;
        
        for (logicalIndex = 0; logicalIndex < 6; logicalIndex++)
        {
            JLabel title = (JLabel) controlMap.get(logicalIndex)[0];
            JLabel price = (JLabel) controlMap.get(logicalIndex)[1];
            JLabel cont = (JLabel) controlMap.get(logicalIndex)[2];
            JLabel time = (JLabel) controlMap.get(logicalIndex)[3];
            JButton addButton = (JButton) controlMap.get(logicalIndex)[4];
            
            if (realIndex < baseSpecialities.size())
            {
                // Existe un elemento para el control
                Especialidad c = baseSpecialities.get(realIndex);
                
                title.setText(c.GetName());
                title.setToolTipText(c.GetName());
                price.setText(String.format("%.2f", c.GetPrice()) + " BsF ");
                cont.setText(String.format(c.GetTotalSides() + " Contornos "));
                time.setText(String.format(c.GetTime() + " Min "));
                
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
        cmdNextPage.setEnabled(realIndex < baseSpecialities.size());
        cmdPreviousPage.setEnabled(realIndex > 6);
    }
    
    // Obtiene una lista de los Contornos seleccionados
    public ArrayList<ContadorEspecialidad> GetSelectedSides()
    {
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
    
    // Actualiza la Tabla de Resumen de Pedido
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
        int contSpecialities=0, contSides=0;
        
        // Crear mapa de Especialidades y Contornos contra Filas del Table
        tableRowMap = new ArrayList<>();
        
        // Mostrar Especialidades
        for (int i = 0; i < addedSpecialities.size(); i++)
        {
            // Sincronizar la tabla con su mapa de filas
            while (tableRowMap.size() < md.getRowCount())
                tableRowMap.add(null);
            
            // Agregar especialidad
            ContadorEspecialidad e = addedSpecialities.get(i);
            //Mostramos solo las especialidades que tienen un count mayor a 0 (que han sido pedidos)
            //En finalizar orden removemos estos mismos que tienen count igual a 0 para tener el array solo con ordenes pedidas
            if(e.GetCount() > 0)
            {
                tableRowMap.add(i); // Agregar una entrada al mapa de filas apuntando a la Especialidad
                md.addRow(new Object[] {e.GetCount(), e.GetSpeciality().GetName(),e.GetSpeciality().GetTotalSides(), e.GetSpeciality().GetPrice()});
                contSpecialities+=e.GetCount();
                
                // Agregar contornos incluidos
                if (e.GetSides() == null)
                    continue;

                for (int sub = 0; sub < e.GetSides().size(); sub++)
                {
                    ContadorContorno c = e.GetSides().get(sub);

                    md.addRow(new Object[] {c.GetCount(), "          " + c.GetSide().GetName(), null, null});
                }
            }
        }
        
        // Mostrar Contornos Adicionales
        for (int a = 0; a < addedSides.size(); a++)
        {
            // Sincronizar la tabla con su mapa de filas
            while (tableRowMap.size() < md.getRowCount())
                tableRowMap.add(null);
            
            // Agregar Contorno
            ContadorContorno aux = addedSides.get(a);
            tableRowMap.add(a); // Agregar una entrada al mapa de filas apuntando al Contorno
            md.addRow(new Object[] {aux.GetCount(), aux.GetSide().GetName(), null, aux.GetSide().GetPrice()});
            contSides+=aux.GetCount();
        }
        
        //Buscamos la cantidad de contornos adicionales y platos para mostrarlos en el indicador
        lblSelectedSpecialities.setText(Integer.toString(contSpecialities));
        lblSelectedSidesAditionals.setText(Integer.toString(contSides)); 
        //Deshabilitar boton eliminar todo si no hay mas filas
        cmdDeleteAll.setEnabled(md.getRowCount() > 0);
    }
    
    // Incrementa el identificador del siguiente pedido disponible
    public void IncrementNextOrderId() { nextOrderId++; }
    
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
        lblTextPlatos = new javax.swing.JLabel();
        lblTextContornosadd = new javax.swing.JLabel();
        lblSelectedSidesAditionals = new javax.swing.JLabel();
        cmdDelete = new javax.swing.JButton();
        cmdEditSides = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 530, 280));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Platos Seleccionados");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        cmdDeleteAll.setText("Eliminar Todo");
        cmdDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteAllActionPerformed(evt);
            }
        });
        getContentPane().add(cmdDeleteAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 420, 140, -1));

        cmdAddAdditionalSide.setText("<html>Agregar Contorno<br>Adicional");
        cmdAddAdditionalSide.setToolTipText("");
        cmdAddAdditionalSide.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdAddAdditionalSide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddAdditionalSideActionPerformed(evt);
            }
        });
        getContentPane().add(cmdAddAdditionalSide, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 140, -1));

        cmdDiscard.setText("Descartar Pedido");
        cmdDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDiscardActionPerformed(evt);
            }
        });
        getContentPane().add(cmdDiscard, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 590, 169, 43));

        cmdGenerate.setText("Finalizar Pedido");
        cmdGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGenerateActionPerformed(evt);
            }
        });
        getContentPane().add(cmdGenerate, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 590, 169, 43));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menú del dia - Platos Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle1.setText("Pizza Margarita");
        jPanel1.add(lblTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 160, -1));

        lblCont1.setText("S/C");
        jPanel1.add(lblCont1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        lblTime1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTime1.setText("25 Min");
        jPanel1.add(lblTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        lblPrice1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice1.setText("580,40 BsF");
        jPanel1.add(lblPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 160, -1));

        cmdAdd1.setText("Agregar");
        cmdAdd1.setName("1"); // NOI18N
        cmdAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 160, -1));

        lblTitle2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle2.setText("Pizza Vegetariana");
        jPanel1.add(lblTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 160, -1));

        lblCont2.setText("S/C");
        jPanel1.add(lblCont2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        lblTime2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTime2.setText("25 Min");
        jPanel1.add(lblTime2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        lblPrice2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice2.setText("590,00 BsF");
        jPanel1.add(lblPrice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 160, -1));

        cmdAdd2.setText("Agregar");
        cmdAdd2.setName("2"); // NOI18N
        cmdAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 160, -1));

        lblTitle3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle3.setText("Pizza 4 Estaciones");
        jPanel1.add(lblTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 160, -1));

        lblCont3.setText("S/C");
        jPanel1.add(lblCont3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, -1));

        lblTime3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTime3.setText("25 Min");
        jPanel1.add(lblTime3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, -1, -1));

        lblPrice3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice3.setText("759,90 BsF");
        jPanel1.add(lblPrice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 160, -1));

        cmdAdd3.setText("Agregar");
        cmdAdd3.setName("3"); // NOI18N
        cmdAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 160, -1));

        lblTitle5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle5.setText("Haburguesa de Carne");
        jPanel1.add(lblTitle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 160, -1));

        lblCont5.setText("1 Contornos");
        jPanel1.add(lblCont5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        lblTime5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTime5.setText("10 Min");
        jPanel1.add(lblTime5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

        lblPrice5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice5.setText("483,80 BsF");
        jPanel1.add(lblPrice5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 160, -1));

        cmdAdd5.setText("Agregar");
        cmdAdd5.setName("5"); // NOI18N
        cmdAdd5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 160, -1));

        lblTitle4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle4.setText("Pollo a la Canasta");
        jPanel1.add(lblTitle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 160, -1));

        lblTitle6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle6.setText("Hamburguesa de Pollo");
        jPanel1.add(lblTitle6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 160, -1));

        lblCont4.setText("2 Contornos");
        jPanel1.add(lblCont4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        lblCont6.setText("1 Contornos");
        jPanel1.add(lblCont6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 80, -1));

        lblTime6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTime6.setText("10 Min");
        jPanel1.add(lblTime6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, -1, -1));

        lblTime4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblTime4.setText("15 Min");
        jPanel1.add(lblTime4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        lblPrice6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice6.setText("410,00 BsF");
        jPanel1.add(lblPrice6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 160, -1));

        lblPrice4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrice4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice4.setText("580,40 BsF");
        jPanel1.add(lblPrice4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 160, -1));

        cmdAdd6.setText("Agregar");
        cmdAdd6.setName("6"); // NOI18N
        cmdAdd6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 160, -1));

        cmdAdd4.setText("Agregar");
        cmdAdd4.setName("4"); // NOI18N
        cmdAdd4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 160, -1));

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
        jPanel1.add(cmdNextPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 50, 210));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 680, 250));

        lblSelectedSpecialities.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSelectedSpecialities.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectedSpecialities.setText("4");
        getContentPane().add(lblSelectedSpecialities, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 480, 140, -1));

        lblTextPlatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTextPlatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextPlatos.setText("Platos");
        getContentPane().add(lblTextPlatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 460, 140, -1));

        lblTextContornosadd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTextContornosadd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextContornosadd.setText("<html>Contornos<br>Adicionales");
        getContentPane().add(lblTextContornosadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 510, 140, 40));

        lblSelectedSidesAditionals.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSelectedSidesAditionals.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectedSidesAditionals.setText("4");
        getContentPane().add(lblSelectedSidesAditionals, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 550, 140, -1));

        cmdDelete.setText("Eliminar");
        cmdDelete.setToolTipText("");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(cmdDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, 140, -1));

        cmdEditSides.setText("<html>Modificar<br>Contornos");
        cmdEditSides.setToolTipText("");
        cmdEditSides.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdEditSides.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditSidesActionPerformed(evt);
            }
        });
        getContentPane().add(cmdEditSides, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, 140, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteAllActionPerformed
        //Boton eliminar todo
        cmdGenerate.setEnabled(false);
        cmdDiscard.setEnabled(false);
        cmdDeleteAll.setEnabled(false);
        addedSides = new ArrayList<>();
        addedSpecialities = new ArrayList<>();

        lblSelectedSpecialities.setText("0");
        lblSelectedSidesAditionals.setText("0");
        UpdateTable();
    }//GEN-LAST:event_cmdDeleteAllActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        // Boton Eliminar 
        // Elimina uno o varios elementos (filas) seleccionados de la tabla      
        // independientemente si son Especialidad o Contorno
        
        DefaultTableModel md = (DefaultTableModel) table.getModel();
        
        if (md.getRowCount() < 1)
            return;
        
        // Obtener la fila seleccionada
        int selectedIndex = table.getSelectedRow();
        
        if (selectedIndex < 0)
            return;
        
        // Obtener el indice del elemento a eliminar. Local al ArrayList correspondiente
        Integer index = tableRowMap.get(selectedIndex);
        
        if (index == null)
            return;
        
        // Si la fila es una Especialidad removerla del ArrayList
        if (md.getValueAt(selectedIndex, COLUMN_SIDES) != null)
        {
            addedSpecialities.remove(index.intValue());
        }
        // Si la fila es un Contorno Adicional (sides=null, price!=null)
        else if (md.getValueAt(selectedIndex, COLUMN_SIDES) == null &&
                md.getValueAt(selectedIndex, COLUMN_COUNT) != null)
            addedSides.remove(index.intValue());
        // La fila seleccionada es un contorno incluido en una especialidad. No borrarla
        else
            return;

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
        cmdDiscard.setEnabled(false);
        cmdDeleteAll.setEnabled(false);
        addedSides = new ArrayList<>();
        addedSpecialities = new ArrayList<>();
        int i;
        ArrayList<Especialidad> billboardSpecialities = Controller.GetBillboardSpecialities();
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
        cmdDiscard.setEnabled(true);
        // Preparar un hilo nuevo para ejecutar el metodo ChooseSides de manera asincronica
        Thread t = new Thread(() -> {
            ArrayList<ContadorContorno> sides = Controller.chooseSides(0, null);
            
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
        
        //Averiguamos si el contorno elegido es propio de una especialidad o es un contorno adicional
        DefaultTableModel md = (DefaultTableModel) table.getModel();
        
        if (md.getRowCount() < 1)
            return;
        
        int selectedIndex = table.getSelectedRow();
        
        if (selectedIndex < 0)
            return;
        
        //si esta vacio en price, es un contorno de una especialidad
        //al contrario, es un contorno adicional
        if(table.getValueAt(selectedIndex, COLUMN_PRICE) == null)
        {
            //Buscamos la especialidad a la que pertenece este contorno
            while (selectedIndex >= 0)
            {
                selectedIndex--;
                
                // Ubicar una celda superior que contenga precio
                if (table.getValueAt(selectedIndex, COLUMN_PRICE) != null)
                    break;
            }
            
            if (selectedIndex < 0)
                return;
        }
        
        //Buscamos la cantidad de contornos que puede tener esta especialidad
        maxSides = (Integer)(table.getValueAt(selectedIndex, COLUMN_SIDES));
        
        // Comprobar si la fila tiene un valor de null en la columna "Contornos"
        // Es decir, si no es realmente una especialidad. Y adicionalmente si puede
        // tener contornos (mayor a 0)
        if (maxSides == null || maxSides < 1)
        {
            return;
        }

        // Resolvemos el indice del contorno desde el mapa de filas
        ContadorEspecialidad ce = addedSpecialities.get(tableRowMap.get(selectedIndex));

        // Preparar un hilo nuevo para ejecutar el metodo ChooseSides de manera asincronica
        Thread w = new Thread(() -> {
            //falta implementar
            ArrayList<ContadorContorno> sides1 = Controller.chooseSides(maxSides, ce.GetSides());
            if (sides1 == null)
                return;
            
            ce.SetSides(sides1);
            
            UpdateTable();
        });
        
        // Ejecutar el hilo
        w.start();
    }//GEN-LAST:event_cmdEditSidesActionPerformed

    private void cmdGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGenerateActionPerformed
        // Boton finalizar pedido.
        // Generar un nuevo pedido y almacenarlo en la lista de Pedidos sin Pagar
        
        //Generate Order a unpaid
        Controller.GenerateOrder(addedSpecialities, addedSides);
        cmdGenerate.setEnabled(false);
        cmdDiscard.setEnabled(false);
        
        // Reiniciar las listas internas
        addedSides = new ArrayList<>();
        addedSpecialities = new ArrayList<>();
        
        UpdateTable();
    }//GEN-LAST:event_cmdGenerateActionPerformed
 
    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
         // Recibe los eventos de Adicion de contornos, y los redirije al metodo
        // de incremento de seleccion
        cmdGenerate.setEnabled(true);
        cmdDiscard.setEnabled(true);
        cmdDeleteAll.setEnabled(true);
        JButton source = (JButton) evt.getSource(); // Obtener el control primario que genero este evento
        Integer specialityIndex = Integer.parseInt(source.getName()); // Obtener el indice del elemento en esta sub-pagina
 
        SpecialityUpdateActionDispatcher(specialityIndex); // Llamar al metodo principal de adicion
    }//GEN-LAST:event_cmdAddActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // Actualizar los botones de edicion basandose en la seleccion actual
        DefaultTableModel md = (DefaultTableModel) table.getModel();
        
        if (md.getRowCount() < 1)
            return;
        
        cmdGenerate.setEnabled(md.getRowCount() > 0);
        
        // Obtener la fila seleccionada
        int selectedIndex = table.getSelectedRow();
        
        // Comprobacion multiple:
        /**
         * Se comprueba si el indice seleccionado actualmente es >= 0.
         * De ser asi entonces existe una fila seleccionada.
         * Luego comprobar si se debe habilitar el boton de Editar Contornos
         * 
         * Este boton solo sebe estar desactivado cuando:
         *    COLUMN_PRICE != null   y   COLUMN_SIDES == null       [Contorno Adicional seleccionado]
         *    o si:   COLUMN_SIDES < 1
         */
        boolean baseConditional = (selectedIndex >= 0);
        boolean enableSideEdit = (md.getValueAt(selectedIndex, COLUMN_SIDES) != null && (int)md.getValueAt(selectedIndex, COLUMN_SIDES) > 0);
        enableSideEdit = enableSideEdit || (md.getValueAt(selectedIndex, COLUMN_PRICE) == null);
        
        // Comprobacion adicional. La celda price debe ser distinta de null para ser eliminable
        // Esto permite la seleccion y eliminacion de filas con Contornos Adicionales que poseen la celda
        // de "Contornos" en null
        cmdDelete.setEnabled(baseConditional && md.getValueAt(selectedIndex, COLUMN_PRICE) != null);
        // Comprobacion adicional. La celda "Contornos" debe ser distinta a null y contener un numero > 0
        cmdEditSides.setEnabled(baseConditional && enableSideEdit);
    }//GEN-LAST:event_tableMouseClicked
 
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
    private javax.swing.JLabel lblTextContornosadd;
    private javax.swing.JLabel lblTextPlatos;
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
