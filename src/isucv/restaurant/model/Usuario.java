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
    
    private String user;
    private String pass;
    private int task;   
    
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

//Metodos get 
public String GetUser() {return user;}
public String GetPass() {return pass;}
public int GetTask() {return task;}

}