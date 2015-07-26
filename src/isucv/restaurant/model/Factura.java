/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

import isucv.restaurant.controller.Controller;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class Factura 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private Pedido order;
    private Cliente client;
    private Float grandtotal;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Factura(Pedido O, Cliente C, Float T)
    {
        order = O;
        client = C;
        grandtotal = T;
        boolean comp;
        int cont;
             //Agregamos los platos y contornos adicionales a estadisticas
            for(int i=0;i<order.GetSpecialities().size();i++)
            {
                comp=false;
                cont=0;
                for(int j=0;j<Controller.GetStats().GetTopSpecialities().size();j++)
                {
                    if(Controller.GetStats().GetTopSpecialities().get(j).GetSpeciality().GetName().equals(order.GetSpecialities().get(i).GetSpeciality().GetName()))
                    {
                        comp=true;
                        cont=Controller.GetStats().GetTopSpecialities().get(j).GetCount();
                        cont=cont+order.GetSpecialities().get(i).GetCount();
                        Controller.GetStats().GetTopSpecialities().get(j).SetCount(cont);
                    }
                }
                if(comp==false)
                {
                    Controller.GetStats().AddSpeciality(order.GetSpecialities().get(i).GetSpeciality(), order.GetSpecialities().get(i).GetCount());
                }
            }
            for(int i=0;i<order.GetSides().size();i++)
            {
                comp=false;
                cont=0;
                for(int j=0;j<Controller.GetStats().GetTopSides().size();j++)
                {
                    if(Controller.GetStats().GetTopSides().get(j).GetSide().GetName().equals(order.GetSides().get(i).GetSide().GetName()))
                    {
                        comp=true;
                        cont=Controller.GetStats().GetTopSides().get(j).GetCount();
                        cont=cont+order.GetSides().get(i).GetCount();
                        Controller.GetStats().GetTopSides().get(j).SetCount(cont);
                    }
                }
                if(comp==false)
                {
                    Controller.GetStats().AddSides(order.GetSides().get(i).GetSide(), order.GetSides().get(i).GetCount());
                }
            }
    }
    
    public Pedido GetOrder(){
            return this.order;
    }
    
    public Cliente GetClient(){
            return this.client;
    }
    
    public Float GetGrandTotal(){
            return this.grandtotal;
    }
}
