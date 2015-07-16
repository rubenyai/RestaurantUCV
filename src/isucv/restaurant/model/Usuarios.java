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
                 for( int i = 0 ; i  < Users.size(); i++){
                            if(Users.get(i).getuser()==User && Users.get(i).getpass()==Pass){
                                return true;
                            }                        
                       }
        return false; 
    }
    
    public void LoadFile(){
        //Creamos el objetos con new array list de usuario con los datos
        Users=new ArrayList <Usuario> ();
         try (BufferedReader br = new BufferedReader(new FileReader("C:\\users.txt")))
		{
			String sCurrentLine;
                        String usr;
                        String psw;
                        char aux;
                        int tsk = 0;
                        int inicio,fin;
                        //Mientras que no se llegue al final del archivo, movemos la linea a scurrenline como string
			while ((sCurrentLine = br.readLine()) != null) {
				//procedemos a buscar usr,psw y tsk que estan separados por ","
                                //buscamos psw
                                inicio = sCurrentLine.indexOf(",");
                                fin = sCurrentLine.indexOf(",", inicio + 1);
                                psw=sCurrentLine.substring(inicio + 1, fin);
                                //Buscamos usr 
                                inicio = sCurrentLine.indexOf(",",fin+1);
                                fin = sCurrentLine.indexOf(",", inicio + 1);
                                usr=sCurrentLine.substring(inicio + 1, fin);
                                //buscamos task
                                inicio=sCurrentLine.length();
                                aux=sCurrentLine.charAt(inicio-1);
                                if(aux=='1')
                                    {tsk=1;}    
                                else if(aux=='2')
                                    {tsk=2;}
                                else if (aux=='3')
                                    {tsk=3;}
                                //Creamos un nuevo objeto al arraylist con los datos
                                Users.add(new Usuario(usr,psw,tsk)); 
			}
		} catch (IOException e) {
                        throw new RuntimeException(e);
		}
    }
    
    public int GetTaskForUser(String User){
        int tsk=0;
         for( int i = 0 ; i  < Users.size(); i++){
                            if(Users.get(i).getuser()==User){
                                tsk=Users.get(i).gettask();
                            }                        
                       }
        return tsk;
    }
}
