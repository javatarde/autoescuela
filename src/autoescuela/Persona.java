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
public abstract class Persona {
  private String nombre;
  private String apellidos;
  private String dni;
  private String telefono;

  public Persona() {
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + Objects.hashCode(this.nombre);
    hash = 59 * hash + Objects.hashCode(this.apellidos);
    hash = 59 * hash + Objects.hashCode(this.dni);
    hash = 59 * hash + Objects.hashCode(this.telefono);
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
    final Persona other = (Persona) obj;
    
    if (!nombre.equals(other.nombre)) {
      return false;
    }
    if (!apellidos.equals(other.apellidos)) {
      return false;
    }
    if (!dni.equals(other.dni)) {
      return false;
    }
    if (!telefono.equals(other.telefono)) {
      return false;
    }
    
    /*
    if (!Objects.equals(this.nombre, other.nombre)) {
      return false;
    }
    if (!Objects.equals(this.apellidos, other.apellidos)) {
      return false;
    }
    if (!Objects.equals(this.dni, other.dni)) {
      return false;
    }
    if (!Objects.equals(this.telefono, other.telefono)) {
      return false;
    }
    */
    return true;
  }
  
  public boolean validar() {
    if (this.getNombre()==null || this.getNombre().isEmpty()
        || this.getApellidos()==null || this.getApellidos().isEmpty()
        || this.getDni()==null || this.getDni().isEmpty()
        || this.getTelefono()==null || this.getTelefono().isEmpty()
       ) {
      return false;
    }
    return true;
  }
}
