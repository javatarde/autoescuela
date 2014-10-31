/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela;

/**
 *
 * @author Formacion
 */
public class VistaAlumno {
    public Alumno getAlumno() {
        Alumno alumno = new Alumno();
        alumno.setNombre(Utilidades.getCadena("Nombre"));
        alumno.setApellidos(Utilidades.getCadena("Apellidos"));
        alumno.setDni(Utilidades.getCadena("DNI"));
        alumno.setTelefono(Utilidades.getCadena("Telefono"));
        alumno.setComentarios(Utilidades.getCadena("Comentarios"));
        alumno.setEstado(Utilidades.getCadena("Estado"));
        return alumno;
    }
}
