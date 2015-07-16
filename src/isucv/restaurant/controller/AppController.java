/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.controller;

// Importar el Paquete "view" completo
import isucv.restaurant.view.*;
//Import necesario para manejar arraylist
import java.util.ArrayList;

// Imports Adicionales
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    
    // Ventana de Rol Active
    public JFrame ActiveWindow;
    
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
        // TODO: Enlazar con la clase Usuarios y comprobar credenciales!
        boolean result = true; // Eliminar inicializacion!
        
        if (result)
        {
            // TODO: Enlazar con la clase Usuarios y obtener el Rol
            int taskId = 1; // Eliminar inicializacion! [CHEF]
            
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
                    
                default: // Caso de error para le ventana del cliente
                    WndGestorPedido gestor = new WndGestorPedido();
                    gestor.setLocationRelativeTo(null);
                    Instance.ActiveWindow = gestor;
            }
        }
        else
        {
            // Mostrar ventana de Error de Inicio de Sesion
            WndLoginFallido loginError = new WndLoginFallido();
            Instance.ActiveWindow = loginError;
        }
        
        // Mostrar a Ventana Activa y ocultar la Ventana de Inicio de Sesion
        Instance.ActiveWindow.setVisible(true);
        Instance.LoginWindow.setVisible(false);
    }
}
