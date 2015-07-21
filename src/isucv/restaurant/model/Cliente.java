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
public class Cliente 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private String Name;
    private String ID;
    private String BillingAddr;
    private String PhoneNumber;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Cliente(String N, String ID, String Addr, String P)
    {
        Name = N;
        this.ID = ID;
        BillingAddr = Addr;
        PhoneNumber = P;
    }
    
    public String GetName(){
            return this.Name;
    }
        
    public String GetID(){
            return this.ID;
    }
    
    public String GetBillingAddr(){
            return this.BillingAddr;
    }
    
    public String GetPhoneNumber(){
            return this.PhoneNumber;
    }
}


