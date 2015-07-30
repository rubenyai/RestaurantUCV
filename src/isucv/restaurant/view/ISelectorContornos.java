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
import isucv.restaurant.model.Contorno;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */

public class ISelectorContornos extends javax.swing.JFrame {

    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private final static int COLUMN_COUNT = 0;
    private final static int COLUMN_DESCRIPTION = 1;
    
    private int pageIndex; // Indice de la pagina actual en Base 1
    private int availableSides; // Cantidad de Contornos disponibles para seleccion
    private int selectedSides; // Cantidad de Contornos seleccionados actualmente
    
    /** Lista de Controles en el ArrayList:
     * 
     *    JLabel     JLabel     JLabel     JButton     JButton
     *    lblTitleX  lblPriceX  lblCountX  cmdAddX     cmdRemoveX
    **/ 
    private ArrayList<JComponent[]> controlMap; // Contiene todos los Controles de la ventana ordenados por indice logico a los contornos
    
    // Copia local del arreglo de Contornos disponibles y seleccionados
    private ArrayList<ContadorContorno> sidesLocalCache;
    private ArrayList<ContadorContorno> initialSideSelection;
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public ISelectorContornos() { this(null, 0); }
    
    /**
     * Creates new form WndSelectorContornos with preselected Sides
     * @param baseSides
     * @param maxSides
     */
    public ISelectorContornos(ArrayList<ContadorContorno> baseSides, int maxSides) {
        initComponents();
        
        this.setLocationRelativeTo(null); // Centrar ventana
        
        availableSides = maxSides;
        if (maxSides < 1)
        {
            lblAvailableSides.setVisible(false);
            lblAvailableSidesTitle.setVisible(false);
        }
        else
            lblAvailableSides.setText(String.valueOf(maxSides));
        initialSideSelection = baseSides;
        
        // Construir mapa de Controles
        controlMap = new ArrayList<>();
        controlMap.add(new JComponent[] {lblTitle1, lblPrice1, lblCount1, cmdAdd1, cmdRemove1});
        controlMap.add(new JComponent[] {lblTitle2, lblPrice2, lblCount2, cmdAdd2, cmdRemove2});
        controlMap.add(new JComponent[] {lblTitle3, lblPrice3, lblCount3, cmdAdd3, cmdRemove3});
        controlMap.add(new JComponent[] {lblTitle4, lblPrice4, lblCount4, cmdAdd4, cmdRemove4});
        controlMap.add(new JComponent[] {lblTitle5, lblPrice5, lblCount5, cmdAdd5, cmdRemove5});
        controlMap.add(new JComponent[] {lblTitle6, lblPrice6, lblCount6, cmdAdd6, cmdRemove6});
        controlMap.add(new JComponent[] {lblTitle7, lblPrice7, lblCount7, cmdAdd7, cmdRemove7});
        controlMap.add(new JComponent[] {lblTitle8, lblPrice8, lblCount8, cmdAdd8, cmdRemove8});
        controlMap.add(new JComponent[] {lblTitle9, lblPrice9, lblCount9, cmdAdd9, cmdRemove9});
        
        // Ajustar el ancho de las columnas de la tabla
        Table.getColumnModel().getColumn(COLUMN_COUNT).setPreferredWidth(40);
        Table.getColumnModel().getColumn(COLUMN_DESCRIPTION).setPreferredWidth(240);
        
        // Inicializar la copia local (cache) de contornos
        sidesLocalCache = new ArrayList<>();
        
        // Copiar contornos actuales (solo visibles)
        int i;
        ArrayList<Contorno> billboardSides = Controller.GetBillboardSides();
        for (i = 0; i < billboardSides.size(); i++)
        {
            // Agregar el contorno al cache
            if (billboardSides.get(i).GetVisible())
            {
                ContadorContorno cc = new ContadorContorno(billboardSides.get(i));
                cc.SetCount(0);
                
                sidesLocalCache.add(cc);
            }
        }
        
        // Aplicar la seleccion actual pasada al constructor a la copia cache de contornos
        selectedSides = 0;
        if (baseSides != null && baseSides.size() > 0)
        {
            // Por cada contorno pasado por parametro...
            for (i = 0; i < baseSides.size(); i++)
            {
                int j;
                // Por cada contorno existente en cache...
                for (j = 0; j < sidesLocalCache.size(); j++)
                {
                    // Comparacion de punteros detras de bambalinas
                    if (sidesLocalCache.get(j).GetSide() == baseSides.get(i).GetSide())
                    {
                        // Actualizar cantidad
                        sidesLocalCache.get(j).SetCount(baseSides.get(i).GetCount());
                        selectedSides += baseSides.get(i).GetCount();
                        break; // Cortocircuitar ciclo
                    }
                }
            }
        }
        
        pageIndex = 1; // Comenzar desde la Pagina 1
        UpdateButtonLayout(); // Actualizar botones de seleccion
        UpdateSelectionTable(); // Actualizar tabla de seleccion y estadisticas
    }

    // Incrementa en 1 el contorno descrito pot el indice especificado
    // de la sub-pagina visible actualmente
    private void SideUpdateActionDispatcher(int index, boolean increment) {
        int finalIndex = index + ((pageIndex - 1) * 9);
        finalIndex--; // Indice real del contorno a actualizar
        
        // Actualizar el indice en cache
        sidesLocalCache.get(finalIndex).AddCount(increment ? 1 : -1);
        
        // Refrescar la tabla de contornos seleccionados
        UpdateSelectionTable();
        
        // Actualizar contadores de seleccion en los Botones
        UpdateButtonLayout(); 
    }
    
    private void UpdateSelectionTable()
    {
        // Actualiza la Tabla de contornos seleccionados con la informacion en Cache
        
        int i;
        DefaultTableModel md = (DefaultTableModel) Table.getModel();
        md.setRowCount(0); // Eliminar el contenido de la Tabla
        int selected = 0;
        
        // Agregar contornos si su cantidad es mayor a 1
        for (i = 0; i < sidesLocalCache.size(); i++)
        {
            if (sidesLocalCache.get(i).GetCount() > 0)
            {
                md.addRow(new Object[] {sidesLocalCache.get(i).GetCount(),
                    sidesLocalCache.get(i).GetSide().GetName()});
                selected += sidesLocalCache.get(i).GetCount();
            }
        }
        
        // Actualizar estadisticas
        lblSelectedSides.setText(String.valueOf(selected));
        selectedSides = selected;
        
        lblNoSelection.setVisible(md.getRowCount() < 1);
    }
    
    private void UpdateButtonLayout()
    {
        // Actualiza la disposicion y el texto de los controles de Adicion/Substraccion de Contornos
        // agregando soporte para Paginacion y Selecciones Dinamicas
        
        int realIndex = 9 * (pageIndex - 1);
        int logicalIndex;
        
        for (logicalIndex = 0; logicalIndex < 9; logicalIndex++)
        {
            JLabel title = (JLabel) controlMap.get(logicalIndex)[0];
            JLabel price = (JLabel) controlMap.get(logicalIndex)[1];
            JLabel count = (JLabel) controlMap.get(logicalIndex)[2];
            JButton addButton = (JButton) controlMap.get(logicalIndex)[3];
            JButton removeButton = (JButton) controlMap.get(logicalIndex)[4];
            
            if (realIndex < sidesLocalCache.size())
            {
                // Existe un elemento para el control
                ContadorContorno c = sidesLocalCache.get(realIndex);
                
                title.setText(c.GetSide().GetName());
                price.setText(String.format("%.2f", c.GetSide().GetPrice()) + " BsF ");
                count.setText(String.valueOf(c.GetCount()));
                
                title.setVisible(true);
                price.setVisible(true);
                count.setVisible(true);
                addButton.setVisible(true);
                removeButton.setVisible(true);
                
                addButton.setEnabled(selectedSides < availableSides || availableSides == 0);
                removeButton.setEnabled(c.GetCount() > 0);
            }
            else
            {
                // No existe un contorno para el control. Ocultar todos sus componentes
                title.setVisible(false);
                price.setVisible(false);
                count.setVisible(false);
                addButton.setVisible(false);
                removeButton.setVisible(false);
            }
                
            realIndex++;
        }
        
        // Comprobacion de limite para realIndex
        // (Comprobar si existen mas paginas a la derecha/izquierda)
        cmdNextPage.setEnabled(realIndex < sidesLocalCache.size());
        cmdPreviousPage.setEnabled(realIndex > 9);
    }
    
    public ArrayList<ContadorContorno> GetSelectedSides()
    {
        // Obtiene una lista de los Contornos seleccionados
        // La lista es pre-procesada desde el cache eliminando los items no seleccionados
        
        if (sidesLocalCache == null)
            return null;
        
        ArrayList<ContadorContorno> output = new ArrayList<>();
        
        int i;
        for (i = 0; i < sidesLocalCache.size(); i++) 
        {
            if (sidesLocalCache.get(i).GetCount() > 0)
                output.add(sidesLocalCache.get(i));   
        }
        
        if (output.size() > 0)
            return output;
        else
            return null;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmdPreviousPage = new javax.swing.JButton();
        cmdNextPage = new javax.swing.JButton();
        lblTitle1 = new javax.swing.JLabel();
        lblPrice1 = new javax.swing.JLabel();
        cmdRemove1 = new javax.swing.JButton();
        cmdAdd1 = new javax.swing.JButton();
        lblCount1 = new javax.swing.JLabel();
        lblTitle4 = new javax.swing.JLabel();
        lblPrice4 = new javax.swing.JLabel();
        cmdRemove4 = new javax.swing.JButton();
        cmdAdd4 = new javax.swing.JButton();
        lblCount4 = new javax.swing.JLabel();
        lblTitle7 = new javax.swing.JLabel();
        lblPrice7 = new javax.swing.JLabel();
        cmdRemove7 = new javax.swing.JButton();
        cmdAdd7 = new javax.swing.JButton();
        lblCount7 = new javax.swing.JLabel();
        lblTitle2 = new javax.swing.JLabel();
        lblPrice2 = new javax.swing.JLabel();
        cmdRemove2 = new javax.swing.JButton();
        cmdAdd2 = new javax.swing.JButton();
        lblCount2 = new javax.swing.JLabel();
        lblTitle5 = new javax.swing.JLabel();
        lblPrice5 = new javax.swing.JLabel();
        cmdRemove5 = new javax.swing.JButton();
        cmdAdd5 = new javax.swing.JButton();
        lblCount5 = new javax.swing.JLabel();
        lblTitle8 = new javax.swing.JLabel();
        lblPrice8 = new javax.swing.JLabel();
        cmdRemove8 = new javax.swing.JButton();
        cmdAdd8 = new javax.swing.JButton();
        lblCount8 = new javax.swing.JLabel();
        lblTitle3 = new javax.swing.JLabel();
        lblPrice3 = new javax.swing.JLabel();
        cmdRemove3 = new javax.swing.JButton();
        cmdAdd3 = new javax.swing.JButton();
        lblCount3 = new javax.swing.JLabel();
        lblTitle6 = new javax.swing.JLabel();
        lblPrice6 = new javax.swing.JLabel();
        cmdRemove6 = new javax.swing.JButton();
        cmdAdd6 = new javax.swing.JButton();
        lblCount6 = new javax.swing.JLabel();
        lblTitle9 = new javax.swing.JLabel();
        lblPrice9 = new javax.swing.JLabel();
        cmdRemove9 = new javax.swing.JButton();
        cmdAdd9 = new javax.swing.JButton();
        lblCount9 = new javax.swing.JLabel();
        lblNoSelection = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        lblTextResumenContornosSel = new javax.swing.JLabel();
        lblAvailableSidesTitle = new javax.swing.JLabel();
        lblAvailableSides = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblSelectedSides = new javax.swing.JLabel();
        cmdCancel = new javax.swing.JButton();
        cmdOk = new javax.swing.JButton();

        setTitle("Selector de Contornos");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contornos del dia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdPreviousPage.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        cmdPreviousPage.setText("<");
        cmdPreviousPage.setEnabled(false);
        cmdPreviousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPreviousPageActionPerformed(evt);
            }
        });
        jPanel1.add(cmdPreviousPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 260));

        cmdNextPage.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        cmdNextPage.setText(">");
        cmdNextPage.setEnabled(false);
        cmdNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextPageActionPerformed(evt);
            }
        });
        jPanel1.add(cmdNextPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 50, 260));

        lblTitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle1.setText("Yuca Frita");
        jPanel1.add(lblTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 110, -1));

        lblPrice1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice1.setText("110,50 BsF");
        jPanel1.add(lblPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 110, -1));

        cmdRemove1.setText("-");
        cmdRemove1.setName("1"); // NOI18N
        cmdRemove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(cmdRemove1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 40, -1));

        cmdAdd1.setText("+");
        cmdAdd1.setName("1"); // NOI18N
        cmdAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

        lblCount1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCount1.setText("1");
        jPanel1.add(lblCount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 30, 20));

        lblTitle4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle4.setText("Papas Fritas");
        jPanel1.add(lblTitle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 110, -1));

        lblPrice4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice4.setText("210,70 BsF");
        jPanel1.add(lblPrice4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 110, -1));

        cmdRemove4.setText("-");
        cmdRemove4.setName("4"); // NOI18N
        cmdRemove4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(cmdRemove4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 40, -1));

        cmdAdd4.setText("+");
        cmdAdd4.setName("4"); // NOI18N
        cmdAdd4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        lblCount4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCount4.setText("0");
        jPanel1.add(lblCount4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 30, 20));

        lblTitle7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle7.setText("Papas al Vapor");
        jPanel1.add(lblTitle7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 110, -1));

        lblPrice7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice7.setText("180,40 BsF");
        jPanel1.add(lblPrice7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 110, -1));

        cmdRemove7.setText("-");
        cmdRemove7.setName("7"); // NOI18N
        cmdRemove7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(cmdRemove7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 40, -1));

        cmdAdd7.setText("+");
        cmdAdd7.setName("7"); // NOI18N
        cmdAdd7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        lblCount7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCount7.setText("0");
        jPanel1.add(lblCount7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 30, 20));

        lblTitle2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle2.setText("Arroz Blanco");
        jPanel1.add(lblTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 110, -1));

        lblPrice2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice2.setText("70,00 BsF");
        jPanel1.add(lblPrice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 110, -1));

        cmdRemove2.setText("-");
        cmdRemove2.setName("2"); // NOI18N
        cmdRemove2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(cmdRemove2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 40, -1));

        cmdAdd2.setText("+");
        cmdAdd2.setName("2"); // NOI18N
        cmdAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        lblCount2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCount2.setText("0");
        jPanel1.add(lblCount2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 30, 20));

        lblTitle5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle5.setText("Arroz Frito");
        jPanel1.add(lblTitle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 110, -1));

        lblPrice5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice5.setText("70,00 BsF");
        lblPrice5.setToolTipText("");
        jPanel1.add(lblPrice5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 110, -1));

        cmdRemove5.setText("-");
        cmdRemove5.setName("5"); // NOI18N
        cmdRemove5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(cmdRemove5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 40, -1));

        cmdAdd5.setText("+");
        cmdAdd5.setName("5"); // NOI18N
        cmdAdd5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        lblCount5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCount5.setText("0");
        jPanel1.add(lblCount5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 30, 20));

        lblTitle8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle8.setText("Ensalada Rallada");
        jPanel1.add(lblTitle8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 110, -1));

        lblPrice8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice8.setText("110,00 BsF");
        jPanel1.add(lblPrice8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 110, -1));

        cmdRemove8.setText("-");
        cmdRemove8.setName("8"); // NOI18N
        cmdRemove8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(cmdRemove8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 40, -1));

        cmdAdd8.setText("+");
        cmdAdd8.setName("8"); // NOI18N
        cmdAdd8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, -1));

        lblCount8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCount8.setText("0");
        jPanel1.add(lblCount8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 30, 20));

        lblTitle3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle3.setText("Tajadas");
        jPanel1.add(lblTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 110, -1));

        lblPrice3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice3.setText("90,00 BsF");
        jPanel1.add(lblPrice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 110, -1));

        cmdRemove3.setText("-");
        cmdRemove3.setName("3"); // NOI18N
        cmdRemove3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(cmdRemove3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 40, -1));

        cmdAdd3.setText("+");
        cmdAdd3.setName("3"); // NOI18N
        cmdAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, -1, -1));

        lblCount3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCount3.setText("0");
        jPanel1.add(lblCount3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 30, 20));

        lblTitle6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle6.setText("Nestea Durazno");
        jPanel1.add(lblTitle6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 110, -1));

        lblPrice6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice6.setText("90,00 BsF");
        jPanel1.add(lblPrice6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 110, -1));

        cmdRemove6.setText("-");
        cmdRemove6.setName("6"); // NOI18N
        cmdRemove6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(cmdRemove6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 40, -1));

        cmdAdd6.setText("+");
        cmdAdd6.setName("6"); // NOI18N
        cmdAdd6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));

        lblCount6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCount6.setText("1");
        lblCount6.setToolTipText("");
        jPanel1.add(lblCount6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 30, 20));

        lblTitle9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitle9.setText("Nestea Limon");
        jPanel1.add(lblTitle9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 110, -1));

        lblPrice9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrice9.setText("90,00 BsF");
        jPanel1.add(lblPrice9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 110, -1));

        cmdRemove9.setText("-");
        cmdRemove9.setName("9"); // NOI18N
        cmdRemove9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(cmdRemove9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 40, -1));

        cmdAdd9.setText("+");
        cmdAdd9.setName("9"); // NOI18N
        cmdAdd9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(cmdAdd9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, -1, -1));

        lblCount9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCount9.setText("0");
        jPanel1.add(lblCount9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 30, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 550, 290));

        lblNoSelection.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblNoSelection.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSelection.setText("-- No hay Contornos Seleccionados --");
        getContentPane().add(lblNoSelection, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 340, 80));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "Yuca Frita"},
                { new Integer(1), "Nestea Durazno"}
            },
            new String [] {
                "Cant", "Descripcion"
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 360, 210));

        lblTextResumenContornosSel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTextResumenContornosSel.setText("Contornos Seleccionados Actualmente");
        getContentPane().add(lblTextResumenContornosSel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        lblAvailableSidesTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAvailableSidesTitle.setText("<html>Contornos disponibles para el plato");
        getContentPane().add(lblAvailableSidesTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 100, 60));

        lblAvailableSides.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAvailableSides.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvailableSides.setText("2");
        getContentPane().add(lblAvailableSides, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 334, 80, 50));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("<html>Contornos seleccionados");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 100, 60));

        lblSelectedSides.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSelectedSides.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectedSides.setText("2");
        getContentPane().add(lblSelectedSides, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 80, 50));

        cmdCancel.setText("Cancelar");
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });
        getContentPane().add(cmdCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, 180, 40));

        cmdOk.setText("Aceptar");
        cmdOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOkActionPerformed(evt);
            }
        });
        getContentPane().add(cmdOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 460, 180, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        //Boton cancelar
        // Se restaura la cache antes de cerrar la ventana
        // Con lo que sea que se haya pasado al constructor de la clase
        
        sidesLocalCache = initialSideSelection;
        this.setVisible(false);
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOkActionPerformed
        // Boton aceptar
        // Solo se oculta la ventana
        
        this.setVisible(false);
    }//GEN-LAST:event_cmdOkActionPerformed

    private void cmdPreviousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPreviousPageActionPerformed
        // Boton flecha izquierda
        pageIndex--;
        UpdateButtonLayout();
    }//GEN-LAST:event_cmdPreviousPageActionPerformed

    private void cmdNextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNextPageActionPerformed
        // Boton flecha derecha
        pageIndex++;
        UpdateButtonLayout();
    }//GEN-LAST:event_cmdNextPageActionPerformed

    private void ButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddActionPerformed
        // Recibe los eventos de Adicion de contornos, y los redirije al metodo
        // de incremento de seleccion
        
        JButton source = (JButton) evt.getSource(); // Obtener el control primario que genero este evento
        Integer sideIndex = Integer.parseInt(source.getName()); // Obtener el indice del elemento en esta sub-pagina
        
        SideUpdateActionDispatcher(sideIndex, true); // Llamar al metodo principal de adicion
    }//GEN-LAST:event_ButtonAddActionPerformed

    private void ButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRemoveActionPerformed
        // Recibe los eventos de Substraccion de contornos, y los redirije al metodo
        // de substraccion de seleccion
        
        JButton source = (JButton) evt.getSource(); // Obtener el control primario que genero este evento
        Integer sideIndex = Integer.parseInt(source.getName()); // Obtener el indice del elemento en esta sub-pagina
        
        SideUpdateActionDispatcher(sideIndex, false); // Llamar al metodo principal de substraccion
    }//GEN-LAST:event_ButtonRemoveActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.JButton cmdAdd1;
    private javax.swing.JButton cmdAdd2;
    private javax.swing.JButton cmdAdd3;
    private javax.swing.JButton cmdAdd4;
    private javax.swing.JButton cmdAdd5;
    private javax.swing.JButton cmdAdd6;
    private javax.swing.JButton cmdAdd7;
    private javax.swing.JButton cmdAdd8;
    private javax.swing.JButton cmdAdd9;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdNextPage;
    private javax.swing.JButton cmdOk;
    private javax.swing.JButton cmdPreviousPage;
    private javax.swing.JButton cmdRemove1;
    private javax.swing.JButton cmdRemove2;
    private javax.swing.JButton cmdRemove3;
    private javax.swing.JButton cmdRemove4;
    private javax.swing.JButton cmdRemove5;
    private javax.swing.JButton cmdRemove6;
    private javax.swing.JButton cmdRemove7;
    private javax.swing.JButton cmdRemove8;
    private javax.swing.JButton cmdRemove9;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvailableSides;
    private javax.swing.JLabel lblAvailableSidesTitle;
    private javax.swing.JLabel lblCount1;
    private javax.swing.JLabel lblCount2;
    private javax.swing.JLabel lblCount3;
    private javax.swing.JLabel lblCount4;
    private javax.swing.JLabel lblCount5;
    private javax.swing.JLabel lblCount6;
    private javax.swing.JLabel lblCount7;
    private javax.swing.JLabel lblCount8;
    private javax.swing.JLabel lblCount9;
    private javax.swing.JLabel lblNoSelection;
    private javax.swing.JLabel lblPrice1;
    private javax.swing.JLabel lblPrice2;
    private javax.swing.JLabel lblPrice3;
    private javax.swing.JLabel lblPrice4;
    private javax.swing.JLabel lblPrice5;
    private javax.swing.JLabel lblPrice6;
    private javax.swing.JLabel lblPrice7;
    private javax.swing.JLabel lblPrice8;
    private javax.swing.JLabel lblPrice9;
    private javax.swing.JLabel lblSelectedSides;
    private javax.swing.JLabel lblTextResumenContornosSel;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lblTitle2;
    private javax.swing.JLabel lblTitle3;
    private javax.swing.JLabel lblTitle4;
    private javax.swing.JLabel lblTitle5;
    private javax.swing.JLabel lblTitle6;
    private javax.swing.JLabel lblTitle7;
    private javax.swing.JLabel lblTitle8;
    private javax.swing.JLabel lblTitle9;
    // End of variables declaration//GEN-END:variables
}
