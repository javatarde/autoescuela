package autoescuela;

import autoescuela.Menu.Opcion;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

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
    private Menu menuA = null;
    private Menu menuM = null;
    private Alumno alumno = null;
    private DAOAlumno daoAlumno = new DAOAlumno();
    private final String cadenaDatosAlumno = "\nid   Nombre      Apellidos           DNI       Telefono    Estado"
                                           + "\n--   ------      ---------           ---       --------    ------";


    public MenuPrincipal() {
    
      Menu m = new Menu();
      
      final Opcion opcion1 = m.new Opcion("Crear nuevo alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del nuevo alumno: ");
            // Nota: el id se genera automaticamente en la bbdd al insertar el nuevo alumno
            alumno = new Alumno();
            alumno.setNombre(Utilidades.getCadena("Nombre"));
            alumno.setApellidos(Utilidades.getCadena("Apellidos"));
            alumno.setDni(Utilidades.getCadena("DNI"));
            alumno.setTelefono(Utilidades.getCadena("Telefono"));
            alumno.setComentarios(Utilidades.getCadena("Comentarios"));
            alumno.setEstado(Utilidades.getCadena("Estado"));
            // Comprobar si se han introducido todos los campos obligatorios
            if (alumno.validarAlumno()){            
              List <Alumno> listaAlumnos = daoAlumno.leer(alumno.getNombre(), alumno.getApellidos());
              // Comprobar si el alumno ya existe
              if (listaAlumnos.size()>0){
                  Utilidades.showCadena("ERROR: El alumno ya existe en la base de datos");
              }else{
                  boolean resultado = daoAlumno.crear(alumno);
              }
            }else{
                Utilidades.showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                           "El nuevo alumno no se guardara");
            }
            return menuA;
        }
      });
    
    
      final Opcion opcion2 = m.new Opcion("Borrar alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del alumno a borrar: ");
            int id = Utilidades.getEntero("id");
            // Comprobar si el alumno existe
            List <Alumno> listaAlumnos = daoAlumno.leer(id);
            if (listaAlumnos.size()>0){
                // Mostrar datos del alumno antes de borrarlo
                alumno = listaAlumnos.get(0);
                mostrarAlumo(alumno);
                String cadena = Utilidades.getCadena("¿Desea eliminar al alumno? (si/no) ");
                if (cadena.toLowerCase().equals("si")){
                    boolean resultado = daoAlumno.eliminar(id);
                }
            }else{
                Utilidades.showCadena("ERROR: El alumno no existe en la base de datos");
            }
            return menuA;
        }
      });
    
    
      final Opcion opcion3 = m.new Opcion("Modificar alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del alumno a modificar: ");
            int id = Utilidades.getEntero("id");
            // Comprobar si el alumno existe
            List <Alumno> listaAlumnos = daoAlumno.leer(id);
            if (listaAlumnos.size()>0){
                // Ir al menu Modificar datos del alumno
                alumno = listaAlumnos.get(0);
                return menuM;
            }else{
                Utilidades.showCadena("ERROR: El alumno no existe en la base de datos");
            }
            return menuA;
        }
      });
    
    
      final Opcion opcion4 = m.new Opcion("Buscar alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del alumno a buscar: ");
            Utilidades.showCadena(" (Con id=0, se muestran todos los alumnos)");
            int id = Utilidades.getEntero("id");
// nota: ampliar la busqueda con otros campos
            
            // Comprobar si el alumno existe
            List <Alumno> listaAlumnos = daoAlumno.leer(id);
            if (listaAlumnos.size()>0){
                // Mostrar datos del alumno
                // Si hay varios alumnos que se ajustan a esa busqueda, mostrarlos todos
                Utilidades.showCadena(cadenaDatosAlumno);
                for (Alumno alumnoI : listaAlumnos) {
                    // Mostrar los datos de cada alumno en una sola linea                
                    mostrarAlumnoLinea(alumnoI);
                }
            }else{
                Utilidades.showCadena("ERROR: No existen resultados para esa busqueda");
            }
            return menuA;
        }
      });
      
      // Esta opcion se ha definido con una funcion lambda
      final Opcion opcion5 = m.new Opcion("Salir", (Accion) () -> {
          Utilidades.showCadena("\n Fin del programa");
          exit(0);
          return null;
      });
/*      
      // Version anterior (hecha con una clase anonima interna):
      final Opcion opcion5 = m.new Opcion("Salir", new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("\n Fin del programa");
            exit(0);
            return null;
        }
      });
*/      
      
      // Opciones del Menu Modificar alumno
      final Opcion opcionM1 = m.new Opcion("Nombre", (Accion) () -> {
          mostrarAlumo(alumno);
          Utilidades.showCadena(" ");
          alumno.setNombre(Utilidades.getCadena("Nombre"));
          return menuM;
      });      
      
      final Opcion opcionM2 = m.new Opcion("Apellidos", (Accion) () -> {
        mostrarAlumo(alumno);
        Utilidades.showCadena(" ");
        alumno.setApellidos(Utilidades.getCadena("Apellidos"));
        return menuM;
      });
      
      final Opcion opcionM3 = m.new Opcion("DNI", (Accion) () -> {
        mostrarAlumo(alumno);
        Utilidades.showCadena(" ");
        alumno.setDni(Utilidades.getCadena("DNI"));
        return menuM;
      });
      
      final Opcion opcionM4 = m.new Opcion("Telefono", (Accion) () -> {
        mostrarAlumo(alumno);
        Utilidades.showCadena(" ");
        alumno.setTelefono(Utilidades.getCadena("Telefono"));
        return menuM;
      });
      
      final Opcion opcionM5 = m.new Opcion("Comentarios", (Accion) () -> {
        mostrarAlumo(alumno);
        Utilidades.showCadena(" ");
        alumno.setComentarios(Utilidades.getCadena("Comentarios"));
        return menuM;
      });

      final Opcion opcionM6 = m.new Opcion("Estado", (Accion) () -> {
        mostrarAlumo(alumno);
        Utilidades.showCadena(" ");
        alumno.setEstado(Utilidades.getCadena("Estado"));
        return menuM;
      });
      
      final Opcion opcionM7 = m.new Opcion("Mostrar todos los campos", (Accion) () -> {
        Utilidades.showCadena(" ");
        mostrarAlumo(alumno);
        return menuM;
      });
      
      final Opcion opcionM8 = m.new Opcion("Guardar cambios", (Accion) () -> {
        if (!alumno.validarAlumno()){
            Utilidades.showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                                  "Las modificaciones realizadas no se guardaran");
        }else{
            Utilidades.showCadena(" ");
            mostrarAlumo(alumno);
            boolean resultado = daoAlumno.actualizar(alumno);
        }
        return menuM.getAnterior();
      });
      
      final Opcion opcionM9 = m.new Opcion("Volver al menu anterior", (Accion) () -> {
        return menuM.getAnterior();
      });
      
      // Incluir las opciones en el menu de Alumnos
      List <Opcion> listaOpcionesMenuA = new ArrayList<>();
      listaOpcionesMenuA.add(opcion1);
      listaOpcionesMenuA.add(opcion2);
      listaOpcionesMenuA.add(opcion3);
      listaOpcionesMenuA.add(opcion4);
      listaOpcionesMenuA.add(opcion5);
      menuA = new Menu(listaOpcionesMenuA);
      menuA.setRotuloMenu("Menu de alumnos");

      // Incluir las opciones en el menu de Modificar Alumno
      List <Opcion> listaOpcionesMenuM = new ArrayList<>();
      listaOpcionesMenuM.add(opcionM1);
      listaOpcionesMenuM.add(opcionM2);
      listaOpcionesMenuM.add(opcionM3);
      listaOpcionesMenuM.add(opcionM4);
      listaOpcionesMenuM.add(opcionM5);
      listaOpcionesMenuM.add(opcionM6);
      listaOpcionesMenuM.add(opcionM7);
      listaOpcionesMenuM.add(opcionM8);
      listaOpcionesMenuM.add(opcionM9);
      menuM = new Menu(listaOpcionesMenuM);
      menuM.setRotuloMenu("Elija un campo del alumno a modificar");
      menuM.setAnterior(menuA);
      
    }
    
    
    // Mostrar todas las opciones del menuActual y ejecuta la elegida por el usuario
    public void mostrarMenu() {
        Menu menuActual = menuA;
        do{
            Utilidades.showCadena("\n------------------------------------------------------");
            Utilidades.showCadena("Autoescuela FORINEMAS. Software de gestion de alumnos:  ");
            Utilidades.showCadena(menuActual.getRotuloMenu());
            menuActual.mostrarOpciones();
            int opcion = Utilidades.getEntero("\n Elija una opcion del menu");
            if ((opcion>0) && (opcion <=menuActual.getNumAcciones())){
                Utilidades.showCadena(" ");
                menuActual = menuActual.getAccion(opcion-1).ejecutar();
            }else{
                System.out.println("\n Opcion incorrecta, introduzca un numero "+
                                   "entre 1 y "+menuActual.getNumAcciones());
            }
        }while (true);
    }
    
    
    // Mostrar todos los campos del alumno, uno en cada linea
    private static void mostrarAlumo(Alumno alumno){
        Utilidades.showCadena("id: ", new Integer(alumno.getId()).toString());
        Utilidades.showCadena("Nombre: ",alumno.getNombre());
        Utilidades.showCadena("Apellidos: ",alumno.getApellidos());
        Utilidades.showCadena("DNI: ",alumno.getDni());
        Utilidades.showCadena("Telefono: : ",alumno.getTelefono());
        Utilidades.showCadena("Comentarios: ",alumno.getComentarios());
        Utilidades.showCadena("Estado: ",alumno.getEstado());
    }
    
    // Mostrar todos los campos del alumno en una linea
    private static void mostrarAlumnoLinea(Alumno alumno){
        System.out.printf("%-5s%-12s%-20s%-10s%-12s%-10s\n",
                        alumno.getId(),
                        alumno.getNombre(),
                        alumno.getApellidos(),
                        alumno.getDni(),
                        alumno.getTelefono(),
                        alumno.getEstado()
// nota: El campo comentarios no se muestra porque puede tener varias lineas        
//        showCadena("Comentarios: ",alumno.getComentarios());
        );
    }

}
