/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela.modelo;

import autoescuela.vista.GestorFechas;
import static autoescuela.vista.GestorFechas.*;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Formacion
 */
public class Matricula {
    private int id = -1;
    private int idAlumno = -1;
    private int idPermiso = -1;
    private int idTipoMatricula = -1;
    private Date fechaAlta = null;
    private Date fechaBaja = null;
    private String motivoBaja = "";

    public Matricula() {
    }

    public Matricula(int id, int idAlumno, int idPermiso, int idTipoMatricula, 
                     Date fechaAlta, Date fechaBaja, String motivoBaja) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idPermiso = idPermiso;
        this.idTipoMatricula = idTipoMatricula;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.motivoBaja = motivoBaja;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdTipoMatricula() {
        return idTipoMatricula;
    }

    public void setIdTipoMatricula(int idTipoMatricula) {
        this.idTipoMatricula = idTipoMatricula;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + this.idAlumno;
        hash = 41 * hash + this.idPermiso;
        hash = 41 * hash + this.idTipoMatricula;
        hash = 41 * hash + Objects.hashCode(this.fechaAlta);
        hash = 41 * hash + Objects.hashCode(this.fechaBaja);
        hash = 41 * hash + Objects.hashCode(this.motivoBaja);
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
        final Matricula other = (Matricula) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idAlumno != other.idAlumno) {
            return false;
        }
        if (this.idPermiso != other.idPermiso) {
            return false;
        }
        if (this.idTipoMatricula != other.idTipoMatricula) {
            return false;
        }
        if (!Objects.equals(this.fechaAlta, other.fechaAlta)) {
            return false;
        }
        if (!Objects.equals(this.fechaBaja, other.fechaBaja)) {
            return false;
        }
        if (!Objects.equals(this.motivoBaja, other.motivoBaja)) {
            return false;
        }
        return true;
    }

    // Comprobar campos no vacios y si hay fechas, que fecha de alta no sea anterior a la de baja
    public boolean validar() {
      if (getIdAlumno()<0 || getIdPermiso()<0 || getIdTipoMatricula()<0 || 
          (getFechaBaja()!=null && getFechaAlta()!=null && diferenciasDeFechas(getFechaAlta(),getFechaBaja())<0) ) {
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
