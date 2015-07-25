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
public class ContadorContorno 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private Integer count;
    private Contorno side;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public ContadorContorno(Contorno Side)
    {
        this.side = Side;
        count = 0;
    }
    
    public void AddCount(Integer Count)
    {
        this.count += Count;
    }
      
    public int GetCount(){
            return this.count;
    }
    
    public Contorno GetSide(){
            return this.side;
    }
    
    public void SetCount(int cou){
            count=cou;
    }
}
