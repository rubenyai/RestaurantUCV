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
    
    private Pedido order;
    private Cliente client;
    private Float grandtotal;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Factura(Pedido O, Cliente C, Float T)
    {
        order = O;
        client = C;
        grandtotal = T;
    }
    
    public Pedido GetOrder(){
            return this.order;
    }
    
    public Cliente GetClient(){
            return this.client;
    }
    
    public Float GetGrandTotal(){
            return this.grandtotal;
    }
}
