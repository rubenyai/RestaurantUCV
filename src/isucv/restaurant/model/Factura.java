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

import isucv.restaurant.controller.Controller;

/**
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */

public class Factura 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private final Pedido order;
    private final Cliente client;
    private final Float grandtotal;
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
    
    public Pedido GetOrder() { return this.order; }
    public Cliente GetClient() { return this.client; }
    public Float GetGrandTotal() { return this.grandtotal; }
    
    
    
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
}
