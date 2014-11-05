/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela.vista;

import autoescuela.modelo.Matricula;
import java.util.List;

/**
 *
 * @author Formacion
 */
public class ComponenteMatricula implements Componente<Matricula>{
    private Matricula matricula;
    private static final String sino = " si/no ";
    private static final String cadenaDatosMatricula =  
        "Id   Id. del alumno   Id. del permiso   Tipo de matricula    Fecha de alta    Fecha de baja   Motivo de baja"
     +"\n--   --------------   ---------------   -----------------    -------------    -------------   --------------";
    
    public ComponenteMatricula (){
        matricula = new Matricula();
    }

    @Override
    public Matricula get() {
        // Obtener los datos del matricula pidiendoselos al usuario por consola
        matricula = new Matricula();
        matricula.setIdAlumno(Utilidades.getEntero("Id. del alumno"));
        matricula.setIdPermiso(Utilidades.getEntero("Id. del permiso"));
        matricula.setIdTipoMatricula(Utilidades.getEntero("id. tipo de matricula"));
// Nota: la fecha de baja y el motivo no se inicializan, ya que suponemos que no se saben
// la fecha de alta se coge la del sistema
        return matricula;
    }
    
    @Override
    public Matricula update(Matricula matriculaOriginal) {
        // Devolver un clon del original
        try{
            matricula = (Matricula) matriculaOriginal.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("Se ha producido un errror al clonar");
            e.getStackTrace();
        }
        // Mostrar los datos actuales
        set(matricula);
        // Mostrar y actualizar los datos del matricula
        if (Utilidades.getCadena("¿Desea cambiar el Id. del alumno?"+sino).equals("si")){
            matricula.setIdAlumno(Utilidades.getEntero("Id. del alumno"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el Id. del permiso?"+sino).equals("si")){
            matricula.setIdPermiso(Utilidades.getEntero("Id. del permiso"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el Id. del tipo de matricula?"+sino).equals("si")){
            matricula.setIdTipoMatricula(Utilidades.getEntero("Id. del tipo de matricula"));
        }
        if (Utilidades.getCadena("¿Desea cambiar la fecha de alta?"+sino).equals("si")){
            matricula.setFechaAlta(Utilidades.getFecha("Fecha de alta (dd-mm-aaaa)"));
        }
        if (Utilidades.getCadena("¿Desea cambiar la fecha de baja?"+sino).equals("si")){
            matricula.setFechaBaja(Utilidades.getFecha("Fecha de baja (dd-mm-aaaa)"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el motivo de baja?"+sino).equals("si")){
            matricula.setMotivoBaja(Utilidades.getCadena("Motivo de baja"));
        }
        return matricula;
    }
    
    @Override
    public void set(Matricula matricula) {
        // Mostrar todos los campos de matricula en una linea
        Utilidades.showCadena(cadenaDatosMatricula);
        mostrarMatricula(matricula);
    }
    
    @Override
    public void set(List <Matricula> lista) {
    // Mostrar todos los campos de matricula en una linea
        Utilidades.showCadena(cadenaDatosMatricula);
        for (Matricula matriculaI : lista) {
            // Mostrar los datos de cada matricula en una sola linea                
            mostrarMatricula(matriculaI);
        }
    }
    
    private void mostrarMatricula(Matricula m) {
        // Mostrar todos los campos de matricula en una linea
        Utilidades.showCadena(
            new Integer(m.getId()).toString() + "  " +
            new Integer(m.getIdAlumno()).toString() + "  " +
            new Integer(m.getIdPermiso()).toString() + "  " +
            new Integer(m.getIdTipoMatricula()).toString() + "  " +
            m.getFechaAlta().toString() + "  " +
            m.getFechaBaja().toString() + "  " +
            m.getMotivoBaja()
        );
    }
    
}
