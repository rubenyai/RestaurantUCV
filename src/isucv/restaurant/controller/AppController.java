/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.controller;

// Importar el Paquete "view" y "model" completo
import isucv.restaurant.model.*;
import isucv.restaurant.view.*;
import java.util.AbstractQueue;
//Import necesario para manejar arraylist
import java.util.ArrayList;
import java.util.Queue;

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
 * @author KDERazorback
 */
public class AppController {
    // Instancia del Controlador Principal de la Aplicacion
    public static AppController Instance;

    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    // Ventana de Inicio de Sesion
    public WndLogin LoginWindow;
        
    // Ventana de Rol o Subrol Activa
    public JFrame ActiveWindow;
    
    // Almacena el Nombre de usuario Actual (luego de Iniciar Sesion)
    public String ActiveUsername;
    
    //Almacena las ordenes sin pagar
    public ArrayList<Pedido> UnpaidOrders;
    
     //Almacena las ordenes sin pagar
    public ArrayList<Pedido> PendingOrders;
    
    //Cola de despacho de ordenes
    public Queue<Pedido> OrdersReady;
    
    //Almacena la cartelera
    public Cartelera Billboard;
    
    //Almacena Billboard
      
    /*//////////////
    //   METODOS  //
    *///////////////
    
    // Punto de Entrada Principal de la Aplicacion
    public static void main(String[] args)
    {
        // Crear una nueva instancia de la clase Controller
        Instance = new AppController();
        
        // Crear una nueva instancia de la Cartelera
        Instance.Billboard = new Cartelera();
        Instance.Billboard.Specialities = new ArrayList<>();
        Instance.Billboard.Sides = new ArrayList<>();
        
        // Crear una nueva instancia de WndLogin
        Instance.LoginWindow = new WndLogin();
        Instance.LoginWindow.setLocationRelativeTo(null); // Centrar Ventana
        
        // Inicializar las Colas y Listas de Pedidos
        Instance.UnpaidOrders = new ArrayList<>();
        Instance.PendingOrders = new ArrayList<>();
                
        // Mostrar la ventana de Login siempre que no haya otra ventana activa
        while (true)
        {
            // Pausar la ejecucion del hilo por 500ms y reiniciar el ciclo
            // cuando haya otra ventana activa
            if (Instance.ActiveWindow != null && Instance.ActiveWindow.isVisible())
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                }
                continue;
            }
            
            // Mostrar la ventana de Login
            Instance.LoginWindow.setVisible(true);

            // Esperar a que la ventana se cierre
            while (Instance.LoginWindow.isVisible())
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Determinar si se debe cerrar la aplicacion
            // Cierre por parte del usuario de la ventana Login
            if (Instance.LoginWindow.getCloseApp())
                break;
        }
        
        // Cerrar la aplicacion
        Instance.LoginWindow.dispose();
        Instance = null;
    }
   
    // Comprueba los credenciales para un inicio de Sesion y abre la ventana
    // correspondiente
    public void Login(String username, String password)
    {
        Usuarios users;
        users = new Usuarios();
        users.LoadFile();
        boolean result = users.CheckLogIn(username, password);
        if (result)
        {
            int taskId = users.GetTaskForUser(username);
         
            // Almacenar el nombre de usuario Actual
            Instance.ActiveUsername = username;
            
            switch (taskId)
            {
                case 1: // Chef
                    WndSelectorTareas taskSelector = new WndSelectorTareas();
                    taskSelector.setLocationRelativeTo(null);
                    taskSelector.setUsername(username);
                    Instance.ActiveWindow = taskSelector;
                    break;
                case 2: // Caja
                    WndCaja caja = new WndCaja();
                    caja.setLocationRelativeTo(null);
                    Instance.ActiveWindow = caja;
                    break;
                case 3: // Mesonero
                    WndDespachoPedidos mesonero = new WndDespachoPedidos();
                    mesonero.setLocationRelativeTo(null);
                    Instance.ActiveWindow = mesonero;
                    break;
            }
        }
        else if(username==null && password==null)
        {
            WndGestorPedido gestor = new WndGestorPedido();
            gestor.setLocationRelativeTo(null);
            Instance.ActiveWindow = gestor;
        }
        else
        {
            // Mostrar ventana de Error de Inicio de Sesion
            WndLoginFallido loginError = new WndLoginFallido();
            Instance.ActiveWindow = loginError;
        }
        
        // Mostrar la Ventana Activa y ocultar la Ventana de Inicio de Sesion
        Instance.ActiveWindow.setVisible(true);
        Instance.LoginWindow.setVisible(false);
    }
    
    // Permite abrir una sub-ventana de rol para la instancia actual
    // del selector de tareas
    public void OpenSubTask(int subTask)
    {
        JFrame wnd;
        
        switch (subTask)
        {
            case 1: // Regresar al selector de Tareas
                wnd = new WndSelectorTareas(Instance.ActiveUsername);
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
        JFrame prevWnd = Instance.ActiveWindow; // Almacenar la ventana actual
        Instance.ActiveWindow = wnd; // Establecer la nueva ventana como Activa
        
        // Ocultar y desechar la ventana anterior
        prevWnd.setVisible(false);
        prevWnd.dispose();
    }
               
    public ArrayList<ContadorContorno> ChooseSides(int TotalSides,ArrayList<ContadorContorno> SelectedSides)
    {
        //TODO here       
        return null; 
    }
    
    public ArrayList<Especialidad> GetBillboardSpecialities()
    {
        //TODO here
        return null;
    }
    
    public ArrayList<Contorno> GetBillboardSides()
    {
        //TODO here
        return null;
    }
    
    public void SetBillboardSpecialities(ArrayList<Especialidad> Specialities)
    {
        //TODO here
    }
    
     public void SetBillboardSides(ArrayList<Contorno> Sides)
    {
        //TODO here
    }
    
    public void GenerateOrder(ArrayList<ContadorEspecialidad> Specialities,ArrayList<ContadorContorno> Sides)
    {
        //TODO here
    }
        
    public Pedido FindOrder(Integer ID)
    {
        for( int i = 0 ; i  < UnpaidOrders.size(); i++){
                            if(UnpaidOrders.get(i).ID.equals(ID)) {
                                return (UnpaidOrders.get(i));
                            }
                            if(PendingOrders.get(i).ID.equals(ID)) {
                                return (PendingOrders.get(i));
                            } 
                       }        
        return null; 
    }
            
    public void PayOrder(String Name,int ID,String ClientID, String BillingAdr, String PhoneNumber)
    {
       Cliente client=new Cliente(Name,ClientID,BillingAdr,PhoneNumber);
       WndCaja Caja = (WndCaja) ActiveWindow;
       Factura f= new Factura(AppController.Instance.FindOrder(ID),client,Caja.GetTotal());
    }
    
    public ArrayList<Pedido> GetPendingOrders()
    {
        return GetPendingOrders();
    }
    //Mueve el pedido de PendingdOrders a OrdersReady
    //Pedidos de chef a mesonero
    public void RequestDelivery(int ID)
    {
        for( int i = 0 ; i  < UnpaidOrders.size(); i++){
            if(PendingOrders.get(i).ID.equals(ID)) {
                    //Añadimos el pedido a la cola de despacho
                    OrdersReady.add(PendingOrders.get(i));
                    //Borramos ya que movimos la orden a la cola de despacho
                    PendingOrders.remove(i);
            } 
        } 
    }
    //Verifica si existe alguna orden en OrdersReady
    public boolean IsNextPendingOrderAvalaible()
    {
        if(OrdersReady.isEmpty()==false)
        {
            return true;
        }
        return false;
    }
    
    public Pedido GetNextOrderReady()
    {
        return OrdersReady.poll();
    }
    
    public void RemoveOrder(int ID)
    {
        for( int i = 0 ; i  < UnpaidOrders.size(); i++){
            if(UnpaidOrders.get(i).ID.equals(ID)) {
                   UnpaidOrders.remove(i);
            }
            if(PendingOrders.get(i).ID.equals(ID)) {
                   PendingOrders.remove(i);
            } 
        }               
    }
    
    public Estadisticas GetStats()
    {
        Estadisticas statistics;
        statistics = new Estadisticas();
        
        return statistics;
    }
    
    //Reinicia las estadisticas
    //Hace .clear en los arraylist topspecialities y topsides
    public void ResetStats()
    {
        Estadisticas statistics;
        statistics = new Estadisticas();
        statistics.Reset();
    }
}
