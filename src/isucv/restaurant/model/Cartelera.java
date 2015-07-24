/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

import isucv.restaurant.controller.Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class Cartelera 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private ArrayList<Especialidad> specialities;
    private ArrayList<Contorno> sides;
    private Integer totalspecialities;
    private Integer totalsides;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public void UpdateSpecialities(ArrayList<Especialidad> esp)
    {
        //Se actualizan las especialidades pasandole por parametro un ArrayList con las especialidades completas
        specialities = esp;
        //Se totaliza el numero de especialidades dentro de la cartelera
        totalspecialities = specialities.size();
    }
   
    public void UpdateSides(ArrayList<Contorno> con)
    {
        //Se actualizan los contornos pasandole por parametro un ArrayList con los contornos completos
        sides = con;
        //Se totaliza el numero de contornos dentro de la cartelera
        totalsides = sides.size();
    }
    
    public void LoadBillboard()
    {
        Controller.GetBillboard().SetSpecialities(new ArrayList<>());
        Controller.GetBillboard().SetSides(new ArrayList<>());
        
         try (BufferedReader br = new BufferedReader(new FileReader(".//billboard.txt")))
		{
			String line;
                        String[] segments;
			while ((line = br.readLine()) != null) {
                                // Comprobar si la linea es un comentario o esta vacia o no contiene comas
                                if (line.length() < 5 || line.charAt(0) == '$' || !line.contains(","))
                                    continue;
                            
				segments = line.split(",");
                                
                                if (segments == null || segments.length < 1)
                                    continue;
                                
                                int i;
                                for (i = 0; i < segments.length; i++)
                                    segments[i] = segments[i].trim();
                                
                                if (segments.length == 5)
                                {
                                    // Especialidad
                                    // Nombre, Precio, Contornos, Tiempo, Visible
                                    Especialidad n = new Especialidad(segments[0], Float.parseFloat(segments[1]), Integer.parseInt(segments[2]), Integer.parseInt(segments[3]), (segments[4].equalsIgnoreCase("true")));
                                    Controller.GetBillboard().GetSpecialities().add(n);
                                }
                                else if (segments.length == 3)
                                {
                                    // Contorno
                                    // Nombre, Precio, Visible
                                    Contorno n = new Contorno(segments[0], Float.parseFloat(segments[1]), (segments[2].equalsIgnoreCase("true")));
                                    Controller.GetBillboard().GetSides().add(n);
                                }
			}
		} catch (IOException e) {
                        throw new RuntimeException(e);
		}
     }
    
    public void SaveBillboard()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(".//billboard.txt")))
		{
                    // Header
                    bw.write("$ Archivo de Estado de Cartelera\n");
                    bw.write("$ Aqui se almacena el ultimo estado guardado de la Cartelera de la Aplicacion\n");
                    bw.write("\n\n");
                            
                    // Specialities Header
                    bw.write("$ ESPECIALIDADES\n");
                    bw.write("$  Sintaxis: Nombre, Precio, Contornos, Tiempo, Visible\n\n");
                    int i;
                    
                    // Specialities
                    for (i = 0; i < Controller.GetBillboard().GetSpecialities().size(); i++)
                    {
                        Especialidad d = Controller.GetBillboard().GetSpecialities().get(i);
                        bw.write(d.GetName());
                        bw.write(", ");
                        bw.write(d.GetPrice().toString());
                        bw.write(", ");
                        bw.write(Integer.toString(d.GetTotalSides()));
                        bw.write(", ");
                        bw.write(Integer.toString(d.GetTime()));
                        bw.write(", ");
                        bw.write(d.GetVisible().toString());
                        bw.write("\n");
                    }
                    
                    // Sides Header
                    bw.write("\n\n$ CONTORNOS\n");
                    bw.write("$  Sintaxis: Nombre, Precio, Visible\n\n");
                    
                    // Sides
                    for (i = 0; i < Controller.GetBillboard().GetSides().size(); i++)
                    {
                        Contorno d = Controller.GetBillboard().GetSides().get(i);
                        bw.write(d.GetName());
                        bw.write(", ");
                        bw.write(d.GetPrice().toString());
                        //bw.write(", ");
                        //bw.write(d.Time.toString());
                        bw.write(", ");
                        bw.write(d.GetVisible().toString());
                        bw.write("\n");
                    }
                    
                    bw.flush(); // Sincronizar con el sistema de archivos
		} catch (IOException e) {
                        throw new RuntimeException(e);
		}
    }
    
    public ArrayList<Especialidad> GetSpecialities(){
            return this.specialities;
    }
    
    public ArrayList<Contorno> GetSides(){
            return this.sides;
    }
        
    public int GetTotalSpecialities(){
            return this.totalspecialities;
    }
    
    public int GetTotalSides(){
            return this.totalsides;
    }
    
    public void SetSpecialities(ArrayList<Especialidad> spec){
            this.specialities=spec;
    }
    
    public void SetSides(ArrayList<Contorno> con){
            this.sides=con;
    }
    
    public void SetTotalSpecialities(int totalspe){
            this.totalspecialities=totalspe;
    }
    
    public void SetTotalSides(int totalcon){
            this.totalsides=totalcon;
    }
}
