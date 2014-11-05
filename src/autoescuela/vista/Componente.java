/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela.vista;

import java.util.List;

/**
 *
 * @author Formacion
 * @param <T>
 */
public interface Componente<T> {
    public T get();
    public T update(T elemento);
    public void set(T elemento);
    public void set(List <T> lista);
    
}
