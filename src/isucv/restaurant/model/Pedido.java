/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

import java.util.ArrayList;

/**
 *
 * @author Flemidra
 */
public class Pedido 
{
    private Integer ID;
    private String Status;
    private ArrayList<ContadorEspecialidad> Specialities;
    private ArrayList<ContadorContorno> Sides;
    
  /*  public Factura GenerateInvoice(Cliente Datos)
    {
        FALTA EL VALOR DE RETORNO APARTE DEL CODIGO
    }
    */
    public void SetStatus(String Status)
    {
        this.Status = Status;
    }
}
