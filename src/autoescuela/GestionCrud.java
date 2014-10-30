/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela;

import java.util.List;

/**
 *
 * @author Formacion
 */
public interface GestionCrud<T>{ 

  //CREAR
  boolean crear(List<T> a);  

  //ACTUALIZAR
  boolean actualizar(List<T> a);
  
  //LEER
  List<T> leer();  
  List<T> leer(int id);
  List<T> leer(String nombre, String apellidos);
  
  //ELIMINAR
  boolean eliminar(int id);
}
