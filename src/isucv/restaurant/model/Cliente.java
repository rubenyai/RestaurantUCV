/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class Cliente 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private String name;
    private String id;
    private String billingaddr;
    private String phonenumber;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Cliente(String N, String ID, String Addr, String P)
    {
        name = N;
        this.id = id;
        billingaddr = Addr;
        phonenumber = P;
    }
    
    public String GetName(){
            return this.name;
    }
        
    public String GetID(){
            return this.id;
    }
    
    public String GetBillingAddr(){
            return this.billingaddr;
    }
    
    public String GetPhoneNumber(){
            return this.phonenumber;
    }
}


