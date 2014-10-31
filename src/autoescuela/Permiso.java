/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela;

import java.util.Objects;

/**
 *
 * @author Formacion
 */
public class Permiso {
    private int id = -1;
    private String valor = null;
    private String descripcion = null;

    public Permiso() {
    }

    public Permiso(int id, String valor, String descripcion) {
        this.id = id;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.valor);
        hash = 71 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Permiso other = (Permiso) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }
    
    public boolean validar() {
      if (getId()<0 
          || getValor()==null || getValor().isEmpty()
          || getDescripcion()==null || getDescripcion().isEmpty()) {
          return false;
      }else{
          return true;
      }
    }
    
    // Devolver todos los campos del permiso en una linea
    @Override
    public String toString(){
        return ("ID: "+new Integer(getId()).toString()+
                " | Permiso: "+getValor()+
                " | Descripcion: "+getDescripcion());
    }
    
    
}
