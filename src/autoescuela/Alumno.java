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
public class Alumno extends Persona implements Cloneable {
  private static final long serialVersionUID = -847759666675389221L;
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
    return true;
  }
  
  @Override
  public boolean validar() {
    if (!super.validar() || this.getEstado()==null || this.getEstado().isEmpty()
        || this.getComentarios()==null || this.getComentarios().isEmpty()
       ) {
      return false;
    }
    return true;
  }
  
  @Override
  public Persona clone() throws CloneNotSupportedException {
    return super.clone(); //To change body of generated methods, choose Tools | Templates.
  }
  
}
