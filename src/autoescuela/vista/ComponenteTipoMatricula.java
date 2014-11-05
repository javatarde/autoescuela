/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela.vista;

import autoescuela.modelo.TipoMatricula;
import java.util.List;

/**
 *
 * @author Formacion
 */
public class ComponenteTipoMatricula implements Componente<TipoMatricula>{
    private TipoMatricula tipoMatricula;
    private static final String sino = " si/no ";
    private static final String cadenaDatosTipoMatricula = "****--Consulta de tipo de matriculas disponibles--****" + 
                                                           "\nid   Valor" + 
                                                           "\n--   -----";
    
    public ComponenteTipoMatricula (){
        tipoMatricula = new TipoMatricula();
    }

    @Override
    public TipoMatricula get() {
        tipoMatricula = new TipoMatricula();
        // Obtener los datos del tipoMatricula pidiendoselos al usuario por consola
        tipoMatricula.setValor(Utilidades.getCadena("Valor"));
        return tipoMatricula;
    }
    
    @Override
    public TipoMatricula update(TipoMatricula tipoMatriculaOriginal) {
        // Devolver un clon del original
        try{
            tipoMatricula = (TipoMatricula) tipoMatriculaOriginal.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("Se ha producido un errror al clonar");
            e.getStackTrace();
        }
        // Mostrar los datos actuales
        set(tipoMatricula);
        // Mostrar y actualizar los datos del tipoMatricula
        if (Utilidades.getCadena("¿Desea cambiar el valor?"+sino).equals("si")){
            tipoMatricula.setValor(Utilidades.getCadena("Valor"));
        }
        // Validar los nuevos datos introducidos
        if (!tipoMatricula.validar()){
            Utilidades.showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                                  "Las modificaciones realizadas no se guardaran");
            return tipoMatriculaOriginal;
        }else{
            return tipoMatricula;
        }
    }
    
    @Override
    public void set(TipoMatricula tipoMatricula) {
        // Mostrar todos los campos del tipoMatricula en una linea
        Utilidades.showCadena(cadenaDatosTipoMatricula);
        mostrarTipoMatricula(tipoMatricula);
    }
    
    @Override
    public void set(List <TipoMatricula> lista) {
    // Mostrar todos los campos del tipoMatricula en una linea
        Utilidades.showCadena(cadenaDatosTipoMatricula);
        for (TipoMatricula tipoMatriculaI : lista) {
            // Mostrar los datos de cada tipoMatricula en una sola linea                
            mostrarTipoMatricula(tipoMatriculaI);
        }
    }
    
    private void mostrarTipoMatricula(TipoMatricula p) {
        // Mostrar todos los campos del tipoMatricula en una linea
        Utilidades.showCadena("ID: "+new Integer(p.getId()).toString()+
                              " | TipoMatricula: "+p.getValor());
    }
  
}
