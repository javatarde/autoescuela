package autoescuela;

import static java.lang.System.exit;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Formacion
 */
public class MenuPrincipal implements Menu{
    private MenuPrincipal menu;
    private DAOAlumno daoAlumno = new DAOAlumno();
    private final String cadenaDatosAlumno = "id  -   Nombre  -   Apellidos  -  DNI  -  Telefono -  Estado ";


    @Override
    public Menu ejecutar() {
        // algo
        return null;
    }
    
    
    public MenuPrincipal() {
        // nota: usar una interfaz para hacer las opciones del menu
        
    }
    
    public MenuPrincipal MenuPrincipal(){
        showCadena("Autoescuela FORINEMAS: ");
        showCadena(" 1) Crear nuevo alumno");
        showCadena(" 2) Borrar alumno");
        showCadena(" 3) Modificar alumno");
        showCadena(" 4) Buscar alumno");
        showCadena(" 5) Salir");
        String opcion = getCadena("\n Elige una opcion del menu");
        switch (opcion){
            case "1":
MenuAltaAlumo();
                break;
            case "2":
MenuBajaAlumo();
                break;
            case "3":
MenuModificaAlumo();
                break;
            case "4":
MenuBuscaAlumo();
                break;
            case "5":
                exit(0);
        }
        return menu;
    }
    
    
    public MenuPrincipal MenuAltaAlumo(){
        showCadena("Introduce los siguientes datos del nuevo alumno: ");
        // Nota: el id lo genera automaticamente la bbdd al insertar el nuevo alumno
        Alumno alumno = new Alumno();
        alumno.setNombre(getCadena("Nombre"));
        alumno.setApellidos(getCadena("Apellidos"));
        alumno.setDni(getCadena("DNI"));
        alumno.setTelefono(getCadena("Telefono"));
        alumno.setComentarios(getCadena("Comentarios"));
        alumno.setEstado(getCadena("Estado"));
        // Comprobar si se han introducido todos los campos obligatorios
        if (alumno.validarAlumno()){            
          List <Alumno> listaAlumnos = daoAlumno.leer(alumno.getNombre(), alumno.getApellidos());
          // Comprobar si el alumno ya existe
          if (listaAlumnos.size()>0){
              showCadena("Error: El alumno ya existe en la base de datos");
          }else{   
              boolean resultado = daoAlumno.crear(alumno);
          }
        }else{
            showCadena("Error: No se han introducido todos los campos obligatorios");
        }
        return menu;
    }
    
    
    public MenuPrincipal MenuBajaAlumo(){
        showCadena("Introduce los siguientes datos del alumno a borrar: ");
        int id = new Integer(getCadena("id: "));
        // Comprobar si el alumno existe
        List <Alumno> listaAlumnos = daoAlumno.leer(id);
        if (listaAlumnos.size()>0){
        // Mostrar datos del alumno
            Alumno alumno = listaAlumnos.get(1);
            mostrarAlumo(alumno);
            String cadena = getCadena("¿Desea eliminar al alumno? (si/no) ");
            if (cadena.equals("si")){
                boolean resultado = daoAlumno.eliminar(id);
            }
        }else{
            showCadena("Error: El alumno no existe en la base de datos");
        }
        return menu;
    }
    
    
    public MenuPrincipal MenuModificaAlumo(){
        showCadena("Introduce los siguientes datos del alumno a modificar: ");
        int id = new Integer(getCadena("id: "));
        // Comprobar si el alumno existe
        List <Alumno> listaAlumnos = daoAlumno.leer(id);
        if (listaAlumnos.size()>0){
            // Mostrar datos del alumno
            Alumno alumno = listaAlumnos.get(1);
            boolean seguir = true;
            do{
                // Elegir los campos a modificar
                mostrarAlumo(alumno);
                showCadena("Campos a modificar:");
                showCadena(" 1) Nombre");
                showCadena(" x) Guardar cambios");
                String opcion = getCadena(" Elige un campo a modificar: ");
                switch (opcion){
                    case "1":
                        alumno.setNombre(getCadena("Nombre"));
                    break;
// faltan resto de campos        
                    case "x":
                        if (!alumno.validarAlumno()){
                            showCadena("Error: No se han introducido todos los campos obligatorios. "+
                                       "Las modificaciones realizadas no se guardaran");
                        }else{
                            seguir = false;
                            boolean resultado = daoAlumno.actualizar(alumno);
                        }}
            }while (seguir);

        }else{
            showCadena("Error: El alumno no existe en la base de datos");
        }
        return menu;
    }
    
    
    public MenuPrincipal MenuBuscaAlumo(){
        showCadena("Introduce los siguientes datos del alumno a buscar: ");
        int id = new Integer(getCadena("id: "));
        // Comprobar si el alumno existe
        
// nota: ampliar la busqueda a otros campos
        
        List <Alumno> listaAlumnos = daoAlumno.leer(id);
        if (listaAlumnos.size()>0){
            // Mostrar datos del alumno
            // Si hay varios alumnos que se ajustan a esa busqueda, mostrarlos todos
            showCadena(cadenaDatosAlumno);
            for (Alumno alumnoI : listaAlumnos) {
                // Mostrar los datos de cada alumno en una sola linea                
                mostrarAlumnoLinea(alumnoI);
            }
        }else{
            showCadena("Error: El alumno no existe en la base de datos");
        }
        return menu;
    }    
    
    
// crear metodo validarAlumno    
// private boolean comprobarAlumo(Alumno alumno){return false;};

// daoAlumno: leer y otras operaciones recibiendo un alumno
// devolviendo booleano y mostrando por pantalla el resultado


    public static void main(String[] args) {
        MenuPrincipal m = new MenuPrincipal();
//        Persona p = new Alumno();
//        m.showCadena("a", p.getNombre());
        while (true){
            m.MenuPrincipal();
        }
    }



    
    private void mostrarAlumo(Alumno alumno){
        showCadena("id: ", new Integer(alumno.getId()).toString());
        showCadena("Nombre: ",alumno.getNombre());
        showCadena("Apellidos: ",alumno.getApellidos());
        showCadena("DNI: ",alumno.getDni());
        showCadena("Telefono: : ",alumno.getTelefono());
        showCadena("Comentarios: ",alumno.getComentarios());
        showCadena("Estado: ",alumno.getEstado());
    }
    
    private void mostrarAlumnoLinea(Alumno alumno){
//        showCadena(cadenaDatosAlumno);
        showCadena(new Integer(alumno.getId()).toString() + 
                   alumno.getNombre() + 
                   alumno.getApellidos() + 
                   alumno.getDni() + 
                   alumno.getTelefono() + 
                   alumno.getEstado());
// el campo comentarios no se muestra porque puede tener muchos campos        
//        showCadena("Comentarios: ",alumno.getComentarios());
    }
    
    
    private String getCadena(String cadena){
        String leido = null;
        do{
            System.out.print(cadena+": ");
            leido = new Scanner(System.in).nextLine();
        }while (leido.isEmpty());
        return leido;
    }
    
    private void showCadena(String cadena){
        System.out.println(cadena);
    }
    
    private void showCadena(String cadena, String cadenaOpcional){
        if (cadenaOpcional == null){
            showCadena(cadena);
        }else{
            showCadena(cadena + cadenaOpcional);
        }
    }
    
}
