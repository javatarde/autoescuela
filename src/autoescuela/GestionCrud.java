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
 * @param <T>
 */
public interface GestionCrud<T>{ 

  //CREAR
  boolean crear(T elemento);  

  //ACTUALIZAR
  boolean actualizar(T elemento);
  
  //LEER  
  List<T> leer(int id);
  List<T> leer(T elemento);
  void mostrarTodos();
  
  //ELIMINAR
  boolean eliminar(int id);
}
