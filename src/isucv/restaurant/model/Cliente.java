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

/**
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */

public class Cliente 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private final String name;
    private final String id;
    private final String billingaddr;
    private final String phonenumber;
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
    
    public String GetName() { return this.name; } 
    public String GetID() { return this.id; }
    public String GetBillingAddr() { return this.billingaddr; }
    public String GetPhoneNumber() { return this.phonenumber; }
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Cliente(String N, String ID, String Addr, String P)
    {
        name = N;
        this.id = ID;
        billingaddr = Addr;
        phonenumber = P;
    }
}


