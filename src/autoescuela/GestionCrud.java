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
  boolean crear(T a);  

  //ACTUALIZAR
  boolean actualizar(T a);
  
  //LEER  
  List<T> leer(int id);
  List<T> leer(String nombre, String apellidos);  
  void mostrarTodos();
  
  //ELIMINAR
  boolean eliminar(int id);
}
