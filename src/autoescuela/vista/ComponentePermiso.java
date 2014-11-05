/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela.vista;

import autoescuela.modelo.Permiso;
import java.util.List;

/**
 *
 * @author Formacion
 */
public class ComponentePermiso implements Componente<Permiso>{
    private Permiso permiso;
    private static final String sino = " si/no ";
    private static final String cadenaDatosPermiso = "****--Consulta de Permisos de conducir disponibles--****" + 
                                                   "\nid   Permiso      Descripcion" + 
                                                   "\n--   -------      -----------";
    
    public ComponentePermiso (){
        permiso = new Permiso();
    }

    @Override
    public Permiso get() {
        permiso = new Permiso();
        // Obtener los datos del permiso pidiendoselos al usuario por consola
        permiso.setValor(Utilidades.getCadena("Permiso"));
        permiso.setDescripcion(Utilidades.getCadena("Descripcion"));
        return permiso;
    }
    
    @Override
    public Permiso update(Permiso permisoOriginal) {
        // Devolver un clon del original
        try{
            permiso = (Permiso) permisoOriginal.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("Se ha producido un errror al clonar");
            e.getStackTrace();
        }
        // Mostrar los datos actuales
        set(permiso);
        // Mostrar y actualizar los datos del permiso
        if (Utilidades.getCadena("¿Desea cambiar el valor?"+sino).equals("si")){
            permiso.setValor(Utilidades.getCadena("Permiso"));
        }
        if (Utilidades.getCadena("¿Desea cambiar la descripcion?"+sino).equals("si")){
            permiso.setDescripcion(Utilidades.getCadena("Descripcion"));
        }
        // Validar los nuevos datos introducidos
        if (!permiso.validar()){
            Utilidades.showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                                  "Las modificaciones realizadas no se guardaran");
            return permisoOriginal;
        }else{
            return permiso;
        }
    }
    
    @Override
    public void set(Permiso permiso) {
        // Mostrar todos los campos del permiso en una linea
        Utilidades.showCadena(cadenaDatosPermiso);
        mostrarPermiso(permiso);
    }
    
    @Override
    public void set(List <Permiso> lista) {
    // Mostrar todos los campos del permiso en una linea
        Utilidades.showCadena(cadenaDatosPermiso);
        for (Permiso permisoI : lista) {
            // Mostrar los datos de cada permiso en una sola linea                
            mostrarPermiso(permisoI);
        }
    }
    
    private void mostrarPermiso(Permiso p) {
        // Mostrar todos los campos del permiso en una linea
        Utilidades.showCadena("ID: "+new Integer(p.getId()).toString()+
                                " | Permiso: "+p.getValor()+
                                " | Descripcion: "+p.getDescripcion());
    }
  
}
