/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela.modelo;

import java.util.Objects;

/**
 *
 * @author Formacion
 */
public class TipoMatricula implements Cloneable{
    private int id = -1;
    private String valor = null;

    public TipoMatricula() {
    }

    public TipoMatricula(int id, String valor) {
        this.id = id;
        this.valor = valor;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.valor);
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
        final TipoMatricula other = (TipoMatricula) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.valor.equals(other.valor)) {
            return false;
        }
        return true;
    }
    
    public boolean validar() {
      if (getValor()==null || getValor().isEmpty()) {
          return false;
      }else{
          return true;
      }
    }
    
  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone(); //To change body of generated methods, choose Tools | Templates.
  }
  
}
