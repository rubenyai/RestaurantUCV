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
    private WndLogin loginwindow;
        
    // Ventana de Rol o Subrol Activa
    private  JFrame activewindow;
    
    // Almacena el Nombre de usuario Actual (luego de Iniciar Sesion)
    private  String activeusername;
    
    //Almacena las ordenes sin pagar
    private  ArrayList<Pedido> unpaidorders;
    
     //Almacena las ordenes sin pagar
    private  ArrayList<Pedido> pendingorders;
    
    //Cola de despacho de ordenes
    private  Queue<Pedido> ordersready;
    
    //Almacena la cartelera
    private Cartelera billboard;
    
    public WndLogin GetLoginWindow(){
            return loginwindow;
    }
    
    public JFrame GetActiveWindow(){
            return activewindow;
    }
    
    public String GetActiveUsername(){
            return activeusername;
    }
    
    public ArrayList<Pedido> GetUnpaidOrders(){
            return this.unpaidorders;
    }
    
    public ArrayList<Pedido> GetPendingOrders(){
            return this.pendingorders;
    }
    
    public Queue<Pedido> GetOrdersReady(){
            return this.ordersready;
    }
    
    public Cartelera GetBillboard(){
            return this.billboard;
    } 
    
    public void SetActiveWindow(JFrame wnd){
        activewindow=wnd;
    }
      
    /*//////////////
    //   METODOS  //
    *///////////////
    
    // Punto de Entrada Principal de la Aplicacion
    public static void main(String[] args)
    {
    
        
        // Crear una nueva instancia de la clase Controller
        Instance = new AppController();
        
        // Crear una nueva instancia de la Cartelera
        Instance.billboard = new Cartelera();
        Instance.billboard.SetSpecialities(new ArrayList<>());
        Instance.billboard.SetSides(new ArrayList<>());
        
        // Crear una nueva instancia de WndLogin
        Instance.loginwindow = new WndLogin();
        Instance.loginwindow.setLocationRelativeTo(null); // Centrar Ventana
        
        // Inicializar las Colas y Listas de Pedidos
        Instance.unpaidorders = new ArrayList<>();
        Instance.pendingorders = new ArrayList<>();
                
        // Mostrar la ventana de Login siempre que no haya otra ventana activa
        while (true)
        {
            // Pausar la ejecucion del hilo por 500ms y reiniciar el ciclo
            // cuando haya otra ventana activa
            if (Instance.activewindow != null && Instance.activewindow.isVisible())
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                }
                continue;
            }
            
            // Mostrar la ventana de Login
            Instance.loginwindow.setVisible(true);

            // Esperar a que la ventana se cierre
            while (Instance.loginwindow.isVisible())
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Determinar si se debe cerrar la aplicacion
            // Cierre por parte del usuario de la ventana Login
            if (Instance.loginwindow.getCloseApp())
                break;
        }
        
        // Cerrar la aplicacion
        Instance.loginwindow.dispose();
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
            Instance.activeusername = username;
            
            switch (taskId)
            {
                case 1: // Chef
                    WndSelectorTareas taskSelector = new WndSelectorTareas();
                    taskSelector.setLocationRelativeTo(null);
                    taskSelector.setUsername(username);
                    Instance.activewindow = taskSelector;
                    break;
                case 2: // Caja
                    WndCaja caja = new WndCaja();
                    caja.setLocationRelativeTo(null);
                    Instance.activewindow = caja;
                    break;
                case 3: // Mesonero
                    WndDespachoPedidos mesonero = new WndDespachoPedidos();
                    mesonero.setLocationRelativeTo(null);
                    Instance.activewindow = mesonero;
                    break;
            }
        }
        else if(username==null && password==null)
        {
            WndGestorPedido gestor = new WndGestorPedido();
            gestor.setLocationRelativeTo(null);
            Instance.activewindow = gestor;
        }
        else
        {
            // Mostrar ventana de Error de Inicio de Sesion
            WndLoginFallido loginError = new WndLoginFallido();
            Instance.activewindow = loginError;
        }
        
        // Mostrar la Ventana Activa y ocultar la Ventana de Inicio de Sesion
        Instance.activewindow.setVisible(true);
        Instance.loginwindow.setVisible(false);
    }
    
    // Permite abrir una sub-ventana de rol para la instancia actual
    // del selector de tareas
    public void OpenSubTask(int subTask)
    {
        JFrame wnd;
        
        switch (subTask)
        {
            case 1: // Regresar al selector de Tareas
                wnd = new WndSelectorTareas(Instance.activeusername);
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
        JFrame prevWnd = Instance.activewindow; // Almacenar la ventana actual
        Instance.activewindow = wnd; // Establecer la nueva ventana como Activa
        
        // Ocultar y desechar la ventana anterior
        prevWnd.setVisible(false);
        prevWnd.dispose();
    }
               
    public ArrayList<ContadorContorno> ChooseSides(int TotalSides,ArrayList<ContadorContorno> SelectedSides)
    {
        //TODO here       
        return null; 
    }
    
    //Retorno arraylist de especialidades de la cartelera
    public ArrayList<Especialidad> GetBillboardSpecialities()
    {
        return billboard.GetSpecialities();
    }
    
    //Retorno arraylist de contornos de la cartelera
    public ArrayList<Contorno> GetBillboardSides()
    {
        return billboard.GetSides();
    }
    
    //Se actualiza el arraylist de especialidades de la cartelera
    public void SetBillboardSpecialities(ArrayList<Especialidad> Specialities)
    {
        Cartelera cart;
        cart = new Cartelera();
        cart.UpdateSpecialities(Specialities);     
    }
    
    //Se actualiza el arraylist de contornos de la cartelera
     public void SetBillboardSides(ArrayList<Contorno> Sides)
    {
        Cartelera cart;
        cart = new Cartelera();
        cart.UpdateSides(Sides);
    }
    
    public void GenerateOrder(ArrayList<ContadorEspecialidad> Specialities,ArrayList<ContadorContorno> Sides)
    {
        //TODO here
    }
    
    //Busca un Order en los Arrays UnpaidOrders y PendingOrders y lo retorna
    public Pedido FindOrder(Integer ID)
    {
        for( int i = 0 ; i  < unpaidorders.size(); i++)
        {
            if(unpaidorders.get(i).GetId()==ID) 
            {
                return (unpaidorders.get(i));
            }
        }
        
        for (int i = 0; i < pendingorders.size(); i++)
        {
            if(pendingorders.get(i).GetId()==ID) 
            {
                return (pendingorders.get(i));
            } 
        }
        return null; 
    }
    
    //Obtiene datos del cliente y crea factura en la ventana caja
    public void PayOrder(String Name,int ID,String ClientID, String BillingAdr, String PhoneNumber)
    {
       Pedido ActualOrder;
       ActualOrder = AppController.Instance.FindOrder(ID);
       //Falta validar
       Cliente Datos=new Cliente(Name,ClientID,BillingAdr,PhoneNumber);
       WndCaja Caja = (WndCaja) activewindow;
       Factura f= new Factura(ActualOrder,Datos, Caja.GetTotal());
    }
    
   /* public ArrayList<Pedido> GetPendingOrders()
    {
        return GetPendingOrders();
    }*/
    
    //Mueve el pedido de PendingdOrders a OrdersReady
    //Pedidos de chef a mesonero
    public void RequestDelivery(int ID)
    {
        for( int i = 0 ; i  < unpaidorders.size(); i++){
            if(pendingorders.get(i).GetId()==ID) {
                    //Añadimos el pedido a la cola de despacho
                    ordersready.add(pendingorders.get(i));
                    //Borramos ya que movimos la orden a la cola de despacho
                    pendingorders.remove(i);
            } 
        } 
    }
    
    //Verifica si existe alguna orden en OrdersReady
    public boolean IsNextPendingOrderAvalaible()
    {
        if(ordersready.isEmpty()==false)
        {
            return true;
        }
        return false;
    }
    
    //Obtiene la proxima orden por despachar por el mesonero
    //Desencolando el pedido de OrdersReady
    public Pedido GetNextOrderReady()
    {
        return ordersready.poll();
    }
    
    public void RemoveOrder(int ID)
    {
        for( int i = 0 ; i  < unpaidorders.size(); i++){
            if(unpaidorders.get(i).GetId()==ID) {
                   unpaidorders.remove(i);
            }
        } 
        
        for( int i = 0 ; i  < pendingorders.size(); i++){
            if(pendingorders.get(i).GetId()==ID) {
                       pendingorders.remove(i);
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
    //Vuelve a crear el arraylist de 0
    public void ResetStats()
    {
        Estadisticas statistics;
        statistics = new Estadisticas();
        statistics.Reset();
    }
}
