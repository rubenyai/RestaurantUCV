/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.prototipo.interfaz;

/**
 *
 * @author KDERazorback
 * @author rubenyai
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        WndEditorPlatos platos = new WndEditorPlatos();        
        WndEditorContornos contornos = new WndEditorContornos();
        WndDespachoPedidos despacho = new WndDespachoPedidos();
        WndCocinaPedidos cocina = new WndCocinaPedidos();
        WndCaja caja = new WndCaja();
        WndGestorPedido gestor = new WndGestorPedido();
        WndConfirmacionPedido pedido = new WndConfirmacionPedido();
        WndLogin login = new WndLogin();
        WndLoginFallido loginFallido = new WndLoginFallido();
        WndSelectorTareas selectorChef = new WndSelectorTareas();
        WndSelectorContornos selectorContornos = new WndSelectorContornos();
        //WndEditorCredencialesObsolete credenciales = new WndEditorCredencialesObsolete();
        WndEstadisticas estadisticas = new WndEstadisticas();
        
        platos.setVisible(true);
        contornos.setVisible(true);
        despacho.setVisible(true);
        cocina.setVisible(true);
        caja.setVisible(true);
        gestor.setVisible(true);
        pedido.setVisible(true);
        login.setVisible(true);
        loginFallido.setVisible(true);
        selectorChef.setVisible(true);
        selectorContornos.setVisible(true);
        //credenciales.setVisible(true);
        estadisticas.setVisible(true);
    }
    
}
