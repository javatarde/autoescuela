/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela.modelo;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Formacion
 */
public class Matricula {
    private int id;
    private int idAlumno;
    private int idPermiso;
    private int idTipoMatricula;
    private Date fechaAlta;
    private Date fechaBaja;
    private String motivoBaja;

    public Matricula() {
    }

    public Matricula(int id, int idAlumno, int idPermiso, int idTipoMatricula, Date fechaAlta, Date fechaBaja, String motivoBaja) {
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

    public boolean validar() {
      if (false) {
          return false;
      }else{
          return true;
      }
    }

}
