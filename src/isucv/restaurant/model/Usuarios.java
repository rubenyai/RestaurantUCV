/*
 * Copyright (C) 2015
 *  Fabian Ramos
 *  Ruben Maza
 *  David Contreras
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
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
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */

public class Usuarios 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private ArrayList <Usuario> users = null;
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    // Comprueba los credenciales proporcionados y retorna un valor indicando si son validos
    public boolean CheckLogIn(String User, String Pass){
        for (Usuario user : users) {
            if (user.GetUser().equals(User) && user.GetPass().equals(Pass)) {
                return true;
            }
        }
        
        return false; 
    }
    
    // Carga el archivo de credenciales desde disco
    public void LoadFile(){
        //Creamos el objetos con new array list de usuario con los datos
        users=new ArrayList<>();
        
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
    
    // Obtiene el tipo de rol asociado con el usuario
    public int GetTaskForUser(String User){
        int tsk=0;
        
        for( int i = 0 ; i  < users.size(); i++){
            if(users.get(i).GetUser().equals(User)) {
                tsk=users.get(i).GetTask();
            }                        
        }
         
        return tsk;
    }
}
