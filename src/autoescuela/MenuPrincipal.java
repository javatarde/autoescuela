package autoescuela;

import autoescuela.Menu.Opcion;
import static java.lang.System.exit;
import java.util.ArrayList;
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
public class MenuPrincipal{
    private Menu menuP = null;
    private DAOAlumno daoAlumno = new DAOAlumno();
    private final String cadenaDatosAlumno = "\nid   Nombre      Apellidos           DNI       Telefono    Estado"
                                           + "\n--   ------      ---------           ---       --------    ------";


    public MenuPrincipal() {
    
      Menu m = new Menu();
      
      final Opcion opcion1 = m.new Opcion("Crear nuevo alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            showCadena("Introduzca los siguientes datos del nuevo alumno: ");
            // Nota: el id se genera automaticamente en la bbdd al insertar el nuevo alumno
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
                  showCadena("ERROR: El alumno ya existe en la base de datos");
              }else{
                  boolean resultado = daoAlumno.crear(alumno);
              }
            }else{
                showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                           "El nuevo alumno no se guardara");
            }
            return menuP;
        }
      });
    
    
      final Opcion opcion2 = m.new Opcion("Borrar alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            showCadena("Introduzca los siguientes datos del alumno a borrar: ");
            int id = getEntero("id");
            // Comprobar si el alumno existe
            List <Alumno> listaAlumnos = daoAlumno.leer(id);
            if (listaAlumnos.size()>0){
                // Mostrar datos del alumno antes de borrarlo
                Alumno alumno = listaAlumnos.get(0);
                mostrarAlumo(alumno);
                String cadena = getCadena("¿Desea eliminar al alumno? (si/no) ");
                if (cadena.toLowerCase().equals("si")){
                    boolean resultado = daoAlumno.eliminar(id);
                }
            }else{
                showCadena("ERROR: El alumno no existe en la base de datos");
            }
            return menuP;
        }
      });
    
    
      final Opcion opcion3 = m.new Opcion("Modificar alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            showCadena("Introduzca los siguientes datos del alumno a modificar: ");
            int id = getEntero("id");
            // Comprobar si el alumno existe
            List <Alumno> listaAlumnos = daoAlumno.leer(id);
            if (listaAlumnos.size()>0){
                // Mostrar datos del alumno
                Alumno alumno = listaAlumnos.get(0);
                boolean seguir = true;
                do{
                    // Elegir los campos a modificar
                    mostrarAlumo(alumno);
                    showCadena("Campos a modificar:");
                    showCadena(" 1) Nombre");
                    showCadena(" 2) Apellidos");
                    showCadena(" 3) DNI");
                    showCadena(" 4) Telefono");
                    showCadena(" 5) Comentarios");
                    showCadena(" 6) Estado");
                    showCadena(" 7) Guardar cambios");
                    showCadena(" 8) Volver al menu principal");
                    String opcion = getCadena(" Elige un campo a modificar");
                    switch (opcion){
                        case "1":
                            alumno.setNombre(getCadena("Nombre"));
                        break;
                        case "2":
                            alumno.setApellidos(getCadena("Apellidos"));
                        break;
                        case "3":
                            alumno.setDni(getCadena("DNI"));
                        break;
                        case "4":
                            alumno.setTelefono(getCadena("Telefono"));
                        break;
                        case "5":
                            alumno.setComentarios(getCadena("Comentarios"));
                        break;
                        case "6":
                            alumno.setEstado(getCadena("Estado"));
                        break;
                        case "7":
                            if (!alumno.validarAlumno()){
                                showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                                           "Las modificaciones realizadas no se guardaran");
                            }else{
                                seguir = false;
                                boolean resultado = daoAlumno.actualizar(alumno);
                            }
                        break;
                        case "8":
                                seguir = false;
                        }
                }while (seguir);
            }else{
                showCadena("ERROR: El alumno no existe en la base de datos");
            }
            return menuP;
        }
      });
    
    
      final Opcion opcion4 = m.new Opcion("Buscar alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            showCadena("Introduzca los siguientes datos del alumno a buscar: ");
            showCadena(" (Con id=0, se muestran todos los alumnos)");
            int id = getEntero("id");
// nota: ampliar la busqueda con otros campos
            
            // Comprobar si el alumno existe
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
                showCadena("ERROR: No existen resultados para esa busqueda");
            }
            return menuP;
        }
      });
      
      // Esta opcion se ha definido con una funcion lambda
      final Opcion opcion5 = m.new Opcion("Salir", (Accion) () -> {
          showCadena("\n Fin del programa");
          exit(0);
          return null;
      });
/*      
      // Version anterior (hecha con una clase anonima interna):
      final Opcion opcion5 = m.new Opcion("Salir", new Accion(){
        @Override
        public Menu ejecutar() {
            showCadena("\n Fin del programa");
            exit(0);
            return null;
        }
      });
*/      
      
      // Incluir las opciones en el menu
      List <Opcion> listaOpcionesMenu1 = new ArrayList<>();
      listaOpcionesMenu1.add(opcion1);
      listaOpcionesMenu1.add(opcion2);
      listaOpcionesMenu1.add(opcion3);
      listaOpcionesMenu1.add(opcion4);
      listaOpcionesMenu1.add(opcion5);
      menuP = new Menu(listaOpcionesMenu1);
    }
    
    
    // Mostrar todas las opciones del menuActual y ejecuta la elegida por el usuario
    public void mostrarMenu() {
        Menu menuActual = menuP;
        do{
            showCadena("\n------------------------------------------------------");
            showCadena("Autoescuela FORINEMAS. Software de gestion de alumnos:  ");
            menuActual.mostrarOpciones();
            int opcion = getEntero("\n Elija una opcion del menu");
            if ((opcion>0) && (opcion <=menuActual.getNumAcciones())){
                showCadena(" ");
                menuActual = menuActual.getAccion(opcion-1).ejecutar();
            }else{
                System.out.println("\n Opcion incorrecta, introduzca un numero "+
                                   "entre 1 y "+menuActual.getNumAcciones());
            }
        }while (true);
    }
    
    
    // Mostrar todos los campos del alumno, uno en cada linea
    private static void mostrarAlumo(Alumno alumno){
        showCadena("id: ", new Integer(alumno.getId()).toString());
        showCadena("Nombre: ",alumno.getNombre());
        showCadena("Apellidos: ",alumno.getApellidos());
        showCadena("DNI: ",alumno.getDni());
        showCadena("Telefono: : ",alumno.getTelefono());
        showCadena("Comentarios: ",alumno.getComentarios());
        showCadena("Estado: ",alumno.getEstado());
    }
    
    // Mostrar todos los campos del alumno en una linea
    private static void mostrarAlumnoLinea(Alumno alumno){
//    showCadena(cadenaDatosAlumno);
      
      System.out.printf("%-5s%-12s%-20s%-10s%-12s%-10s\n",
              alumno.getId(),
              alumno.getNombre(),
              alumno.getApellidos(),
              alumno.getDni(),
              alumno.getTelefono(),
              alumno.getEstado()
      );
      
      /*
              showCadena(new Integer(alumno.getId()).toString() + " " +
                   alumno.getNombre() + " " +
                   alumno.getApellidos() + " " +
                   alumno.getDni() + " " +
                   alumno.getTelefono() + " " +
                   alumno.getEstado());
      */
// nota: El campo comentarios no se muestra porque puede tener varias lineas        
//        showCadena("Comentarios: ",alumno.getComentarios());
    }
    
    // Mostrar por pantalla una cadena y devolver otra (no vacia) leida por consola
    private static String getCadena(String cadena){
        String leido = null;
        do{
            System.out.print(cadena+": ");
            leido = new Scanner(System.in).nextLine();
        }while (leido.isEmpty());
        return leido;
    }
    
    // Mostrar por pantalla una cadena y devolver un entero leido por consola
    private static int getEntero(String cadena){
        String leido = null;
        do{
            try{
                System.out.print(cadena+": ");
                leido = new Scanner(System.in).nextLine();
                return new Integer(leido);
            }catch(NumberFormatException e){
                System.out.println(" ERROR: Debe introducir un numero entero");
            }
        }while (true);
    }
    
    private static void showCadena(String cadena){
      System.out.println(cadena);
    }
    
    private static void showCadena(String cadena, String cadenaOpcional){
      if (cadenaOpcional == null){
          showCadena(cadena);
      }else{
          showCadena(cadena + cadenaOpcional);
      }
    }
    
}
