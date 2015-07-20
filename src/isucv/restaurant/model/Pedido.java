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
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    public Integer ID;
    private String Status;
    //CAMBIAR ATRIBUTOS A PRIVATE
    public ArrayList<ContadorEspecialidad> Specialities;
    public ArrayList<ContadorContorno> Sides;
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
  /*  public Factura GenerateInvoice(Cliente Datos)
    {
        FALTA EL VALOR DE RETORNO APARTE DEL CODIGO
        return (  );
    }
    */
    public void SetStatus(String Status)
    {
        this.Status = Status;
    }
 }
