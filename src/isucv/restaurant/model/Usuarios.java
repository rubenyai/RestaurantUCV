/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ruben
 */
public class Usuarios 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private ArrayList <Usuario> Users=null;
    private boolean FileLoaded=false;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public boolean CheckLogIn(String User, String Pass){
       
        return false; 
    }
    
    public void LoadFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\users.txt")))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				//Prueba si esta funcionando bien la lectura
                                System.out.println(sCurrentLine);
                                ///////////////////////////////////////////
                                //Creamos los objetos con new array list de usuario con los datos
                                Users=new ArrayList <Usuario>();
			}
 
		} catch (IOException e) {
                        throw new RuntimeException(e);
		} 
    }
    
    public int GetTaskForUser(String User){
        return 0;
    }
}
