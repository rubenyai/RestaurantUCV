/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

/**
 *
 * @author Flemidra
 */
public class Factura 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private Pedido Order;
    private Cliente Client;
    private Float GrandTotal;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Factura(Pedido O, Cliente C, Float T)
    {
        Order = O;
        Client = C;
        GrandTotal = T;
    }
    
    public Pedido GetOrder(){
            return this.Order;
    }
    
    public Cliente GetClient(){
            return this.Client;
    }
    
    public Float GetGrandTotal(){
            return this.GrandTotal;
    }
}
