/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela.controlador;

import autoescuela.modelo.GestionCrud;
import autoescuela.modelo.TipoMatricula;
import autoescuela.vista.Componente;

/**
 *
 * @author Formacion
 */
public class MenuTipoMatricula extends MenuGenerico<TipoMatricula>{

    public MenuTipoMatricula(Componente<TipoMatricula> componente, GestionCrud<TipoMatricula> dao, 
                      String nombreClase, Menu menuAnt, Menu menuSig) {
        super(componente, dao, nombreClase, menuAnt, menuSig);
    }
    
}
