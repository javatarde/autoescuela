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
public class Alumno extends Persona {
  private int id;
  private String estado;
  private String comentarios;

  public Alumno() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getComentarios() {
    return comentarios;
  }

  public void setComentarios(String comentarios) {
    this.comentarios = comentarios;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + this.id;
    hash = 89 * hash + Objects.hashCode(this.estado);
    hash = 89 * hash + Objects.hashCode(this.comentarios);
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
    final Alumno other = (Alumno) obj;
    if (this.id != other.id) {
      return false;
    }
    if (!estado.equals(other.estado)) {
      return false;
    }
    if (!comentarios.equals(other.estado)) {
      return false;
    }
    
    /*
    if (!Objects.equals(this.estado, other.estado)) {
      return false;
    }
    if (!Objects.equals(this.comentarios, other.comentarios)) {
      return false;
    }
    */
    return true;
  }
  
  @Override
  public boolean validar() {
    return super.validar();
  }
  
  public void mostrar() {
    System.out.printf("%-5s%-12s%-20s%-10s%-12s%-10s\n",
      getId(),
      getNombre(),
      getApellidos(),
      getDni(),
      getTelefono(),
      getEstado()
    );
  }
}
