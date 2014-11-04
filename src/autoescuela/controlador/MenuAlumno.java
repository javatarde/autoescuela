/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela.controlador;

import autoescuela.modelo.Alumno;
import autoescuela.modelo.GestionCrud;
import autoescuela.vista.Componente;

/**
 *
 * @author Formacion
 */
public class MenuAlumno extends MenuGenerico<Alumno>{

    public MenuAlumno(Componente<Alumno> componente, GestionCrud<Alumno> dao, 
                      String nombreClase, Menu menuAnt, Menu menuSig) {
        super(componente, dao, nombreClase, menuAnt, menuSig);
    }
    
}
