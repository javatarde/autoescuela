/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela;

import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author Formacion
 */
public class Matricula {
    private int id;
    private int idAlumno;
    private int idTipoMatricula;
    private Calendar fechaAlta;
    private Calendar fechaBaja;
    private String motivoBaja;

    public Matricula() {
    }

    public Matricula(int id, int idAlumno, int idTipoMatricula, Calendar fechaAlta, Calendar fechaBaja, String motivoBaja) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idTipoMatricula = idTipoMatricula;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.motivoBaja = motivoBaja;
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

    public Calendar getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Calendar getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Calendar fechaBaja) {
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
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + this.idAlumno;
        hash = 83 * hash + this.idTipoMatricula;
        hash = 83 * hash + Objects.hashCode(this.fechaAlta);
        hash = 83 * hash + Objects.hashCode(this.fechaBaja);
        hash = 83 * hash + Objects.hashCode(this.motivoBaja);
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

    

}
