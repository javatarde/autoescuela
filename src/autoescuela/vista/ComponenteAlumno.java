/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela.vista;

import autoescuela.modelo.Alumno;
import java.util.List;

/**
 *
 * @author Formacion
 */
public class ComponenteAlumno implements Componente<Alumno>{
    private Alumno alumno;
    private static final String sino = " si/no ";
    private static final String cadenaDatosAlumno = "\nid   Nombre      Apellidos           DNI       Telefono    Estado"
                                                  + "\n--   ------      ---------           ---       --------    ------";
    
    public ComponenteAlumno (){
        alumno = new Alumno();
    }

    @Override
    public Alumno get() {
        alumno = new Alumno();
        // Obtener los datos del alumno pidiendoselos al usuario por consola
        alumno.setNombre(Utilidades.getCadena("Nombre"));
        alumno.setApellidos(Utilidades.getCadena("Apellidos"));
        alumno.setDni(Utilidades.getCadena("DNI"));
        alumno.setTelefono(Utilidades.getCadena("Telefono"));
        alumno.setComentarios(Utilidades.getCadena("Comentarios"));
        alumno.setEstado(Utilidades.getCadena("Estado"));
        return alumno;
    }
    
    @Override
    public Alumno update(Alumno alumnoOriginal) {
        // Devolver un clon del original
        try{
            alumno = (Alumno) alumnoOriginal.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("Se ha producido un errror al clonar");
            e.getStackTrace();
        }
        // Mostrar los datos actuales
        set(alumno);
        // Mostrar y actualizar los datos del alumno
        if (Utilidades.getCadena("¿Desea cambiar el nombre?"+sino).equals("si")){
            alumno.setNombre(Utilidades.getCadena("Nombre"));
        }
        if (Utilidades.getCadena("¿Desea cambiar los apellidos?"+sino).equals("si")){
            alumno.setApellidos(Utilidades.getCadena("Apellidos"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el DNI?"+sino).equals("si")){
            alumno.setDni(Utilidades.getCadena("DNI"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el telefono?"+sino).equals("si")){
            alumno.setTelefono(Utilidades.getCadena("Telefono"));
        }
        if (Utilidades.getCadena("¿Desea cambiar los comentarios?"+sino).equals("si")){
            alumno.setComentarios(Utilidades.getCadena("Comentarios"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el estado?"+sino).equals("si")){
            alumno.setEstado(Utilidades.getCadena("Estado"));
        }
        return alumno;
    }
    
    @Override
    public void set(Alumno alumno) {
        // Mostrar todos los campos del alumno en una linea
        Utilidades.showCadena(cadenaDatosAlumno);
        mostrarAlumno(alumno);
    }
    
    @Override
    public void set(List <Alumno> lista) {
    // Mostrar todos los campos del alumno en una linea
        Utilidades.showCadena(cadenaDatosAlumno);
        for (Alumno alumnoI : lista) {
            // Mostrar los datos de cada alumno en una sola linea                
            mostrarAlumno(alumnoI);
        }
    }
    
    private void mostrarAlumno(Alumno a) {
        // Mostrar todos los campos del alumno en una linea
        System.out.printf("%-5s%-12s%-20s%-10s%-12s%-10s\n",
          a.getId(),
          a.getNombre(),
          a.getApellidos(),
          a.getDni(),
          a.getTelefono(),
          a.getEstado()
// nota: El campo comentarios no se muestra porque puede tener varias lineas        
//        showCadena("Comentarios: ",alumno.getComentarios());
        );
    }
  
}
