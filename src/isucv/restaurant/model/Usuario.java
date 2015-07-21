/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

/**
 *
 * @author Ruben
 */

// Clase usuario, que sera objeto de usuarios para crear un array 
public class Usuario 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    public String user;
    public String pass;
    public int task;   
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
//metodo para set de la clase usuario
public Usuario(String usr,String psw, int tsk)
{
    user=usr;
    pass=psw;
    task=tsk;
}

public String GetUser(){
            return this.user;
    }

public String GetPass(){
            return this.pass;
    }

public int GetTask(){
            return this.task;
    }
}