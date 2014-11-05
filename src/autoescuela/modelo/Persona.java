/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela.modelo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Formacion
 */
public abstract class Persona implements Serializable, Cloneable {
  private static final long serialVersionUID = 3798673479082438008L;
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

  @Override
  public Persona clone() throws CloneNotSupportedException {
    //escribimos en la matriz de bytes

    try {
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      ObjectOutputStream out;
      out = new ObjectOutputStream(bout);
      out.writeObject(this);
      out.close();
      //leemos en la matriz de bytes
      ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
      ObjectInputStream in = new ObjectInputStream(bin);
      Persona ret;

      ret = (Persona) in.readObject();

      in.close();
      return ret;
    } catch (ClassNotFoundException | IOException ex) {
        return null;
    }   //To change body of generated methods, choose Tools | Templates.
  }
  
  // MODIFICACION DEL MECANISMO DE SERIALIZACION PREDETERMINADO
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.nombre=in.readUTF();
        this.apellidos=in.readUTF();
        this.dni=in.readUTF();
        this.telefono=in.readUTF();
    }

    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
        out.defaultWriteObject();
        out.writeUTF(nombre);
        out.writeUTF(apellidos);
        out.writeUTF(dni);
        out.writeUTF(telefono);
    }
}
