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

package isucv.restaurant.controller;

// Importar el Paquete "view" y "model" completo
import isucv.restaurant.model.*;
import isucv.restaurant.view.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Import necesario para manejar ArrayList
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

// Imports Adicionales
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/*
    Roles Principales Disponibles para Usuarios
        1. Chef (Selector de Tareas)
        2. Caja
        3. Mesonero

    Sub-roles Disponibles para el Chef (metodo OpenSubTask(...))

        4. Editor de Especialidades
        5. Editor de Contornos
        6. Visualizador de Estadisticas
        7. Preparacion de Pedidos
*/

/**
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */

public final class Controller {

    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    // Ventana de Inicio de Sesion
    private static ILogIn loginWindow;
        
    // Ventana de Rol o Subrol Activa
    private static JFrame activeWindow;
    
    // Almacena el Nombre de usuario Actual (luego de Iniciar Sesion)
    private static String activeUsername;
    
    //Almacena las ordenes sin pagar
    private static ArrayList<Pedido> unpaidOrders;
    
     //Almacena las ordenes pagadas
    private static ArrayList<Pedido> pendingOrders;
    
    //Cola de despacho de ordenes
    private static Queue<Pedido> ordersReady;
    
    //Almacena la cartelera
    private static Cartelera billboard;
    
    //Almacena las Estadisticas
    private static Estadisticas stats;
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
    
    public static ILogIn GetLoginWindow(){ return loginWindow; }
    public static void SetLoginWindow(ILogIn newWnd) { loginWindow = newWnd; }
    public static JFrame GetActiveWindow(){ return activeWindow; }
    public static void SetActiveWindow(JFrame wnd){ activeWindow = wnd; }
    public static String GetActiveUsername(){ return activeUsername; }
    public static ArrayList<Pedido> GetUnpaidOrders(){ return unpaidOrders; }
    public static ArrayList<Pedido> GetPendingOrders(){ return pendingOrders; }
    public static Queue<Pedido> GetOrdersReady(){ return ordersReady; }
    public static ArrayList<Especialidad> GetBillboardSpecialities() { return billboard.GetSpecialities(); }
    public static void SetBillboardSpecialities(ArrayList<Especialidad> specialities) { billboard.SetSpecialities(specialities); }
    public static ArrayList<Contorno> GetBillboardSides() { return billboard.GetSides(); }
    public static void SetBillboardSides(ArrayList<Contorno> sides) { billboard.SetSides(sides); }
    public static Estadisticas GetStats() { return stats; }
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    // Punto de Entrada Principal de la Aplicacion
    public static void main(String[] args)
    {
        // Crear una nueva instancia de la Cartelera
        billboard = new Cartelera();
        billboard.SetSpecialities(new ArrayList<>());
        billboard.SetSides(new ArrayList<>());
        
        //Cargamos la cartelera
        LoadBillboard();
        
        // Crear una nueva instancia de ILogIn
        loginWindow = new ILogIn();
        loginWindow.setLocationRelativeTo(null); // Centrar Ventana
        
        // Inicializar las Colas y Listas de Pedidos
        unpaidOrders = new ArrayList<>();
        pendingOrders = new ArrayList<>();
        ordersReady = new LinkedList<>();
        
        // Crear la clase Estadisticas
        stats = new Estadisticas();
                
        // Mostrar la ventana de Login siempre que no haya otra ventana activa
        while (true)
        {
            // Pausar la ejecucion del hilo por 500ms y reiniciar el ciclo
            // cuando haya otra ventana activa
            if (activeWindow != null && activeWindow.isVisible())
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                continue;
            }
            
            // Mostrar la ventana de Login
            loginWindow.setVisible(true);

            // Esperar a que la ventana se cierre
            while (loginWindow.isVisible())
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Determinar si se debe cerrar la aplicacion
            // Cierre por parte del usuario de la ventana Login
            if (loginWindow.GetCloseApp())
            {
                //Se guarda la billboard antes de cerrar la aplicacion
                SaveBillboard();
                break;
            }
        }
        
        // Cerrar la aplicacion
        loginWindow.dispose();
        System.exit(0); // Salir de la Aplicacion
    }
   
    // Comprueba los credenciales para un inicio de Sesion y abre la ventana
    // correspondiente
    public static void Login(String username, String password)
    {
        Usuarios users;
        users = new Usuarios();
        
        users.LoadFile(); // Cargar el archivo de credenciales
        
        boolean result = users.CheckLogIn(username, password);
        if (result)
        {
            int taskId = users.GetTaskForUser(username);
         
            // Almacenar el nombre de usuario Actual
            activeUsername = username;
            
            switch (taskId)
            {
                case 1: // Chef
                    ISelectorTarea taskSelector = new ISelectorTarea();
                    taskSelector.setLocationRelativeTo(null);
                    taskSelector.setUsername(username);
                    activeWindow = taskSelector;
                    break;
                case 2: // Caja
                    ICaja caja = new ICaja();
                    caja.setLocationRelativeTo(null);
                    activeWindow = caja;
                    break;
                case 3: // Mesonero
                    IDespachoPedidos mesonero = new IDespachoPedidos();
                    mesonero.setLocationRelativeTo(null);
                    activeWindow = mesonero;
                    break;
            }
        }
        else if(username==null && password==null)
        {
            IGestorPedidos gestor = new IGestorPedidos();
            gestor.setLocationRelativeTo(null);
            activeWindow = gestor;
        }
        else
        {
            // Mostrar ventana de Error de Inicio de Sesion
            IErrorLogIn loginError = new IErrorLogIn();
            loginError.setLocationRelativeTo(null);
            activeWindow = loginError;
        }
        
        // Mostrar la Ventana Activa y ocultar la Ventana de Inicio de Sesion
        activeWindow.setVisible(true);
        loginWindow.setVisible(false);
    }
    
    // Permite abrir una sub-ventana de rol para la instancia actual
    // del selector de tareas
    public static void OpenSubTask(int subTask)
    {
        JFrame wnd;
        
        switch (subTask)
        {
            case 1: // Regresar al selector de Tareas
                wnd = new ISelectorTarea(activeUsername);
                break;
            case 4: // Especialidades
                wnd = new IEditorPlatos();
                break;
            case 5: // Contornos
                wnd = new IEditorContornos();
                break;
            case 6: // Estadisticas
                wnd = new IEstadisticas();
                break;
            case 7: // Preparacion de Pedidos
                wnd = new ICocina();
                break;
            default: // Ignorar
                return;
        }
        
        wnd.setLocationRelativeTo(null); // Centrar sub-ventana
        wnd.setVisible(true); // Mostrar sub-ventana
        JFrame prevWnd = activeWindow; // Almacenar la ventana actual
        activeWindow = wnd; // Establecer la nueva ventana como Activa
        
        // Ocultar y desechar la ventana anterior
        prevWnd.setVisible(false);
        prevWnd.dispose();
    }
               
    public static ArrayList<ContadorContorno> chooseSides(int TotalSides,ArrayList<ContadorContorno> selectedSides)
    {
        // Permite abrir la ventana de seleccion de Contornos y retornar una lista de Contornos seleccionados
        //ADVERTENCIA!!: ESTE METODO DEBE SER LLAMADO DE MANERA ASINCRONICA UNICAMENTE
        //               PARA EVITAR CUELGUES DEL THREAD DE UI DE JAVA
        
        ISelectorContornos wnd = new ISelectorContornos(selectedSides, TotalSides);
        wnd.setVisible(true);
        
        while (wnd.isVisible())
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return wnd.GetSelectedSides();
    }
    
    // Carga los datos guardados de la Cartelera desde un archivo de Cartelera en el disco
    public static void LoadBillboard()
    {
        ArrayList<Especialidad> newSpecialities = new ArrayList<>();
        ArrayList<Contorno> newSides = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(".//billboard.txt")))
        {
            String line;
            String[] segments;
            while ((line = br.readLine()) != null) {
                // Comprobar si la linea es un comentario o esta vacia o no contiene comas
                if (line.length() < 5 || line.charAt(0) == '$' || !line.contains(","))
                    continue;

                segments = line.split(",");

                if (segments == null || segments.length < 1)
                    continue;

                int i;
                for (i = 0; i < segments.length; i++)
                    segments[i] = segments[i].trim();

                if (segments.length == 5)
                {
                    // Especialidad
                    // Nombre, Precio, Contornos, Tiempo, Visible
                    Especialidad n = new Especialidad(segments[0], Float.parseFloat(segments[1]), Integer.parseInt(segments[2]), Integer.parseInt(segments[3]), (segments[4].equalsIgnoreCase("true")));
                    newSpecialities.add(n);
                }
                else if (segments.length == 3)
                {
                    // Contorno
                    // Nombre, Precio, Visible
                    Contorno n = new Contorno(segments[0], Float.parseFloat(segments[1]), (segments[2].equalsIgnoreCase("true")));
                    newSides.add(n);
                }
            }
        } catch (IOException e) {
                    throw new RuntimeException(e);
	}
         
        // Aplicar cambios
         SetBillboardSpecialities(newSpecialities);
         SetBillboardSides(newSides);
     }
    
    // Guarda el estado actual de la Cartelera en un archivo en disco
    public static void SaveBillboard()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(".//billboard.txt")))
        {
            // Header
            bw.write("$ Archivo de Estado de Cartelera\n");
            bw.write("$ Aqui se almacena el ultimo estado guardado de la Cartelera de la Aplicacion\n");
            bw.write("\n\n");

            // Specialities Header
            bw.write("$ ESPECIALIDADES\n");
            bw.write("$  Sintaxis: Nombre, Precio, Contornos, Tiempo, Visible\n\n");
            int i;

            // Specialities
            for (i = 0; i < Controller.GetBillboardSpecialities().size(); i++)
            {
                Especialidad d = Controller.GetBillboardSpecialities().get(i);
                bw.write(d.GetName());
                bw.write(", ");
                bw.write(d.GetPrice().toString());
                bw.write(", ");
                bw.write(Integer.toString(d.GetTotalSides()));
                bw.write(", ");
                bw.write(Integer.toString(d.GetTime()));
                bw.write(", ");
                bw.write(d.GetVisible().toString());
                bw.write("\n");
            }

            // Sides Header
            bw.write("\n\n$ CONTORNOS\n");
            bw.write("$  Sintaxis: Nombre, Precio, Visible\n\n");

            // Sides
            for (i = 0; i < Controller.GetBillboardSides().size(); i++)
            {
                Contorno d = Controller.GetBillboardSides().get(i);
                bw.write(d.GetName());
                bw.write(", ");
                bw.write(d.GetPrice().toString());
                //bw.write(", ");
                //bw.write(d.Time.toString());
                bw.write(", ");
                bw.write(d.GetVisible().toString());
                bw.write("\n");
            }

            bw.flush(); // Sincronizar con el sistema de archivos
        } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }
    
    // Crear un nuevo pedido basandose en el ultimo Identificador disponible     
    public static void GenerateOrder(ArrayList<ContadorEspecialidad> specialities,ArrayList<ContadorContorno> sides)
    {
        // Mostrar la interfaz de confirmacion de pedido y añadirlo a la cola de Pedidos Sin Pagar
        IGestorPedidos gestor = (IGestorPedidos) activeWindow;
        
        int id = gestor.GetNextOrderId();
        gestor.IncrementNextOrderId();
        
        // Crear el nuevo pedido
        Pedido p = new Pedido();
        p.SetId(id);
        p.SetSpecialities(specialities);
        p.SetSides(sides);
        p.SetStatus("Generado");
        
        // Agregar el pedido a la Lista de Pedidos sin Pagar
        GetUnpaidOrders().add(p);
                
        //se muestra el pop up con el nro de pedido
        IPedido confirmacion = new IPedido(id);
        confirmacion.setLocationRelativeTo(null);
        activeWindow = confirmacion;
        activeWindow.setVisible(true);
        activeWindow= gestor;
    }
    
    //Busca un Order en los Arrays UnpaidOrders y PendingOrders y lo retorna
    public static Pedido FindOrder(Integer id)
    {
        // UnpaidOrders
        for (Pedido unpaidOrder : unpaidOrders) {
            if (unpaidOrder.GetId() == id) {
                return unpaidOrder;
            }
        }
        
        // PendingOrders
        for (Pedido pendingOrder : pendingOrders) {
            if (pendingOrder.GetId() == id) {
                return pendingOrder;
            } 
        }
        return null; 
    }
    
    //Obtiene datos del cliente y crea factura en la ventana caja
    public static void PayOrder(String name, int id, String clientID, String billingAdr, String phoneNumber)
    {
        Pedido ActualOrder;
        ActualOrder = FindOrder(id);
       
        ActualOrder.SetStatus("Pagado");
        Cliente Datos=new Cliente(name,clientID,billingAdr,phoneNumber);
        ICaja Caja = (ICaja) activeWindow;
        Factura f= new Factura(ActualOrder,Datos, Caja.GetTotal());
        //Remove the actual order of unpaid
        RemoveOrder(id);
        //Agregamos a la lista del chef
        pendingOrders.add(ActualOrder);
    }
        
    //Mueve el pedido de PendingdOrders a OrdersReady
    //Pedidos de chef a mesonero
    public static void RequestDelivery(int id)
    {
        for( int i = 0 ; i  < unpaidOrders.size(); i++){
            if(pendingOrders.get(i).GetId() == id) {
                    //Añadimos el pedido a la cola de despacho
                    ordersReady.add(pendingOrders.get(i));
                    //Borramos ya que movimos la orden a la cola de despacho
                    pendingOrders.remove(i);
            } 
        } 
    }
    
    //Verifica si existe alguna orden en OrdersReady
    public static boolean IsNextPendingOrderAvalaible() { return !ordersReady.isEmpty(); }
    
    //Obtiene la proxima orden por despachar por el mesonero
    //Desencolando el pedido de OrdersReady
    public static Pedido GetNextOrderReady() { return ordersReady.poll(); }
    
    // Ubica un pedido en la lista UnpaidOrders o PendingOrders y lo elimina
    public static void RemoveOrder(int id)
    {
        // Unpaid Orders
        for( int i = 0 ; i  < unpaidOrders.size(); i++){
            if(unpaidOrders.get(i).GetId() == id) {
                unpaidOrders.remove(i);
                return; // Cortocircuitar ciclo
            }
        } 
        
        // Pending Orders
        for( int i = 0 ; i  < pendingOrders.size(); i++){
            if(pendingOrders.get(i).GetId() == id) {
                pendingOrders.remove(i);
                return; // Cortocircuitar ciclo
            }
        }
    }
     
    //Reinicia las estadisticas
    public static void ResetStats() { stats.Reset(); }
}
