/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.controller;

// Importar el Paquete "view" y "model" completo
import isucv.restaurant.model.*;
import isucv.restaurant.view.*;
//Import necesario para manejar arraylist
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

// Imports Adicionales
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public final class Controller {

    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    // Ventana de Inicio de Sesion
    private static WndLogin loginWindow;
        
    // Ventana de Rol o Subrol Activa
    private static JFrame activeWindow;
    
    // Almacena el Nombre de usuario Actual (luego de Iniciar Sesion)
    private static String activeUsername;
    
    //Almacena las ordenes sin pagar
    private static ArrayList<Pedido> unpaidOrders;
    
     //Almacena las ordenes pagas
    private static ArrayList<Pedido> pendingOrders;
    
    //Cola de despacho de ordenes
    private static Queue<Pedido> ordersReady;
    
    //Almacena la cartelera
    private static Cartelera billboard;
    
    //Almacena las Estadisticas
    private static Estadisticas stats;
    
    /*////////////////////////////////
    //    GET y SETS ELEMENTALES    //
    */////////////////////////////////
    
    public static WndLogin GetLoginWindow(){
            return loginWindow;
    }
    
    public static void SetLoginWindow(WndLogin newWnd) {
        loginWindow = newWnd;
    }
    
    public static JFrame GetActiveWindow(){
            return activeWindow;
    }
    
    public static String GetActiveUsername(){
            return activeUsername;
    }
    
    public static ArrayList<Pedido> GetUnpaidOrders(){
            return unpaidOrders;
    }
    
    public static ArrayList<Pedido> GetPendingOrders(){
            return pendingOrders;
    }
    
    public static Queue<Pedido> GetOrdersReady(){
            return ordersReady;
    }
    
    public static Cartelera GetBillboard(){
            return billboard;
    } 
    
    public static void SetActiveWindow(JFrame wnd){
        activeWindow = wnd;
    }
      
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
        billboard.LoadBillboard();
        
        // Crear una nueva instancia de WndLogin
        loginWindow = new WndLogin();
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
            if (loginWindow.getCloseApp())
            {
                //Se guarda la billboard antes de cerrar la aplicacion
                billboard.SaveBillboard();
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
        users.LoadFile();
        boolean result = users.CheckLogIn(username, password);
        if (result)
        {
            int taskId = users.GetTaskForUser(username);
         
            // Almacenar el nombre de usuario Actual
            activeUsername = username;
            
            switch (taskId)
            {
                case 1: // Chef
                    WndSelectorTareas taskSelector = new WndSelectorTareas();
                    taskSelector.setLocationRelativeTo(null);
                    taskSelector.setUsername(username);
                    activeWindow = taskSelector;
                    break;
                case 2: // Caja
                    WndCaja caja = new WndCaja();
                    caja.setLocationRelativeTo(null);
                    activeWindow = caja;
                    break;
                case 3: // Mesonero
                    WndDespachoPedidos mesonero = new WndDespachoPedidos();
                    mesonero.setLocationRelativeTo(null);
                    activeWindow = mesonero;
                    break;
            }
        }
        else if(username==null && password==null)
        {
            WndGestorPedido gestor = new WndGestorPedido();
            gestor.setLocationRelativeTo(null);
            activeWindow = gestor;
        }
        else
        {
            // Mostrar ventana de Error de Inicio de Sesion
            WndLoginFallido loginError = new WndLoginFallido();
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
                wnd = new WndSelectorTareas(activeUsername);
                break;
            case 4: // Especialidades
                wnd = new WndEditorPlatos();
                break;
            case 5: // Contornos
                wnd = new WndEditorContornos();
                break;
            case 6: // Estadisticas
                wnd = new WndEstadisticas();
                break;
            case 7: // Preparacion de Pedidos
                wnd = new WndCocinaPedidos();
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
               
    public static ArrayList<ContadorContorno> ChooseSides(int TotalSides,ArrayList<ContadorContorno> SelectedSides)
    {
        // Permite abrir la ventana de seleccion de Contornos y retornar una lista de Contornos seleccionados
        //ADVERTENCIA!!: ESTE METODO DEBE SER LLAMADO DE MANERA ASINCRONICA UNICAMENTE
        //               PARA EVITAR CUELGUES DEL THREAD DE UI DE JAVA
        WndSelectorContornos wnd = new WndSelectorContornos(SelectedSides, TotalSides);
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
    
    //Retorno arraylist de especialidades de la cartelera
    public static ArrayList<Especialidad> GetBillboardSpecialities()
    {
        return billboard.GetSpecialities();
    }
    
    //Retorno arraylist de contornos de la cartelera
    public static ArrayList<Contorno> GetBillboardSides()
    {
        return billboard.GetSides();
    }
    
    //Se actualiza el arraylist de especialidades de la cartelera
    public static void SetBillboardSpecialities(ArrayList<Especialidad> Specialities)
    {
        Cartelera cart;
        cart = new Cartelera();
        cart.UpdateSpecialities(Specialities);     
    }
    
    //Se actualiza el arraylist de contornos de la cartelera
     public static void SetBillboardSides(ArrayList<Contorno> Sides)
    {
        Cartelera cart;
        cart = new Cartelera();
        cart.UpdateSides(Sides);
    }
    
    public static void GenerateOrder(ArrayList<ContadorEspecialidad> Specialities,ArrayList<ContadorContorno> Sides)
    {
        // Crear un nuevo pedido basandose en el ultimo Identificador disponible
        // Mostrar la interfaz de confirmacion de pedido y añadirlo a la cola de Pedidos Sin Pagar
        
        WndGestorPedido gestor = (WndGestorPedido) activeWindow;
        
        int id = gestor.GetNextOrderId();
        gestor.IncrementNextOrderId();
        
        // Crear el nuevo pedido
        Pedido p = new Pedido();
        p.SetId(id);
        p.SetSpecialities(Specialities);
        p.SetSides(Sides);
        p.SetStatus("Generado");
        
        // Agregar el pedido a la Lista de Pedidos sin Pagar
        GetUnpaidOrders().add(p);
                
        //se muestra el pop up con el nro de pedido
        WndConfirmacionPedido confirmacion = new WndConfirmacionPedido();
        confirmacion.setLocationRelativeTo(null);
        activeWindow = confirmacion;
        confirmacion.IDPedido(id);
        activeWindow.setVisible(true);
        activeWindow= gestor;
    }
    
    //Busca un Order en los Arrays UnpaidOrders y PendingOrders y lo retorna
    public static Pedido FindOrder(Integer ID)
    {
        for( int i = 0 ; i  < unpaidOrders.size(); i++)
        {
            if(unpaidOrders.get(i).GetId() == ID) 
            {
                return (unpaidOrders.get(i));
            }
        }
        
        for (int i = 0; i < pendingOrders.size(); i++)
        {
            if(pendingOrders.get(i).GetId() == ID) 
            {
                return (pendingOrders.get(i));
            } 
        }
        return null; 
    }
    
    //Obtiene datos del cliente y crea factura en la ventana caja
    public static void PayOrder(String Name,int ID,String ClientID, String BillingAdr, String PhoneNumber)
    {
       Pedido ActualOrder;
       ActualOrder = FindOrder(ID);
       //Falta validar
            ActualOrder.SetStatus("Pagado");
            Cliente Datos=new Cliente(Name,ClientID,BillingAdr,PhoneNumber);
            WndCaja Caja = (WndCaja) activeWindow;
            Factura f= new Factura(ActualOrder,Datos, Caja.GetTotal());
            //Remove the actual order of unpaid
            RemoveOrder(ID);
            //Agregamos a la lista del chef
            pendingOrders.add(ActualOrder);
    }
        
    //Mueve el pedido de PendingdOrders a OrdersReady
    //Pedidos de chef a mesonero
    public static void RequestDelivery(int ID)
    {
        for( int i = 0 ; i  < unpaidOrders.size(); i++){
            if(pendingOrders.get(i).GetId() == ID) {
                    //Añadimos el pedido a la cola de despacho
                    ordersReady.add(pendingOrders.get(i));
                    //Borramos ya que movimos la orden a la cola de despacho
                    pendingOrders.remove(i);
            } 
        } 
    }
    
    //Verifica si existe alguna orden en OrdersReady
    public static boolean IsNextPendingOrderAvalaible()
    {
        return ordersReady.isEmpty()==false;
    }
    
    //Obtiene la proxima orden por despachar por el mesonero
    //Desencolando el pedido de OrdersReady
    public static Pedido GetNextOrderReady()
    {
        return ordersReady.poll();
    }
    
    public static void RemoveOrder(int ID)
    {
        for( int i = 0 ; i  < unpaidOrders.size(); i++){
            if(unpaidOrders.get(i).GetId() == ID) {
                   unpaidOrders.remove(i);
            }
        } 
        
        for( int i = 0 ; i  < pendingOrders.size(); i++){
            if(pendingOrders.get(i).GetId() == ID) {
                       pendingOrders.remove(i);
            }
        }
    }
     
    // Obtiene las Estadisticas de la sesion actual
    public static Estadisticas GetStats()
    {
        return stats;
    }
    
    //Reinicia las estadisticas
    //Vuelve a crear el arraylist de 0
    public static void ResetStats()
    {
        stats.Reset();
    }
}
