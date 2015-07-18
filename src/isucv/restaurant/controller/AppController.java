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
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    // Punto de Entrada Principal de la Aplicacion
    public static void main(String[] args)
    {
        // Crear una nueva instancia de la clase Controller
        Instance = new AppController();
        
        // Crear una nueva instancia de WndLogin
        Instance.LoginWindow = new WndLogin();
        Instance.LoginWindow.setLocationRelativeTo(null); // Centrar Ventana
                
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
    
    public Estadisticas GetStats()
    {
        Estadisticas statistics;
        statistics = new Estadisticas();
        
        return null;
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
