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

/*
   Numeros de Rol Disponibles para Usuarios dentro del Archivo de Credenciales
     1. Chef
     2. Caja
     3. Mesonero
*/

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class Usuarios 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private ArrayList <Usuario> users=null;
    private boolean fileloaded=false;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public boolean CheckLogIn(String User, String Pass){
                 for( int i = 0 ; i  < users.size(); i++){
                            if(users.get(i).GetUser().equals(User) && users.get(i).GetPass().equals(Pass)){
                                return true;
                            }                        
                       }
        return false; 
    }
    
    public void LoadFile(){
        //Creamos el objetos con new array list de usuario con los datos
        users=new ArrayList <Usuario> ();
         try (BufferedReader br = new BufferedReader(new FileReader(".//users.txt")))
		{
			String sCurrentLine;
                        String usr;
                        String psw;
                        char aux;
                        int tsk = 0;
                        int inicio,fin;
                        //Mientras que no se llegue al final del archivo, movemos la linea a scurrenline como string
			while ((sCurrentLine = br.readLine()) != null) {
                                // Comprobar si la linea es un comentario o esta vacia
                                if (sCurrentLine.length() < 5 || sCurrentLine.charAt(0) == '$')
                                    continue;
                            
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
                                users.add(new Usuario(usr,psw,tsk)); 
			}
		} catch (IOException e) {
                        throw new RuntimeException(e);
		}
    }
    
    public int GetTaskForUser(String User){
        int tsk=0;
         for( int i = 0 ; i  < users.size(); i++){
                            if(users.get(i).GetUser().equals(User)) {
                                tsk=users.get(i).GetTask();
                            }                        
                       }
        return tsk;
    }
    
    public int DBG__GetTotalUsersLoaded()
    {
        if (users == null)
            return 0;
        else
            return users.size();
    }
}
