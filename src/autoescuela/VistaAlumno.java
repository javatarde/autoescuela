/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela;

import java.util.List;

/**
 *
 * @author Formacion
 */
public class VistaAlumno implements Vista<Alumno>{
    private Alumno alumno;
    private final String cadenaDatosAlumno = "\nid   Nombre      Apellidos           DNI       Telefono    Estado"
                                           + "\n--   ------      ---------           ---       --------    ------";
    
    public VistaAlumno (){
        alumno = new Alumno();
    }

    @Override
    public Alumno get() {
        // Obtener los datos del alumno
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
        String sino = " si/no ";
        try{
            alumno = (Alumno) alumnoOriginal.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("Hay un errror");
            e.getStackTrace();
        }
//Alumno alumno = alumnoOriginal;
        // Mostrar los datos actuales
        show(alumno);
        // Mostrar y actualizar los datos del alumno
        if (Utilidades.getCadena("¿Desea cambiar el nombre?"+sino).equals("si")){
            alumno.setNombre(Utilidades.getCadena("Nombre"));
        }
        if (Utilidades.getCadena("¿Desea cambiar los Apellidos?"+sino).equals("si")){
            alumno.setApellidos(Utilidades.getCadena("Apellidos"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el DNI?"+sino).equals("si")){
            alumno.setDni(Utilidades.getCadena("DNI"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el Telefono?"+sino).equals("si")){
            alumno.setTelefono(Utilidades.getCadena("Telefono"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el Comentarios?"+sino).equals("si")){
            alumno.setComentarios(Utilidades.getCadena("Comentarios"));
        }
        if (Utilidades.getCadena("¿Desea cambiar el Estado?"+sino).equals("si")){
            alumno.setEstado(Utilidades.getCadena("Estado"));
        }
        // Validar los nuevos datos introducidos
        if (!alumno.validar()){
            Utilidades.showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                                  "Las modificaciones realizadas no se guardaran");
            return alumnoOriginal;
        }else{
            return alumno;
        }
    }
    
    @Override
    public void show(Alumno alumno) {
        // Mostrar todos los campos del alumno en una linea
        Utilidades.showCadena(cadenaDatosAlumno);
        mostrarAlumno(alumno);
    }
    
    @Override
    public void show(List <Alumno> lista) {
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
