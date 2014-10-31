package autoescuela;

import autoescuela.Menu.Opcion;
import static java.lang.System.exit;
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
public class MenuGenerico<T,DAO>{
    private Menu menu  = null;
    private T elemento = null;
    private String nombreClase = null;
    private GestionCrud<T>() dao = new GestionCrud<T>();
    private final String cadena = "\nid   Nombre      Apellidos           DNI       Telefono    Estado"
                                + "\n--   ------      ---------           ---       --------    ------";

    public MenuGenerico() {
    
      Menu m = new Menu();
/*      
      // Opciones del Menu principal
      final Opcion opcion1 = m.new Opcion("Gestion de permisos", (Accion) () -> {
          return menuA;
      });
      
      final Opcion opcion2 = m.new Opcion("Gestion de permisos", (Accion) () -> {
          return menuP;
      });
      
      final Opcion opcion3 = m.new Opcion("Salir", (Accion) () -> {
          Utilidades.showCadena("\n Fin del programa");
          exit(0);
          return null;
      });
*/      
      nombreClase = elemento.getClass().getName();
      
      // Opciones del Menu de alumnos
      final Opcion opcionA1 = m.new Opcion("Crear nuevo "+nombreClase, new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del nuevo "+elemento.getClass()+": ");
            // Nota: el id se genera automaticamente en la bbdd al insertar el nuevo alumno
            elemento = new <T>();
            alumno.setNombre(Utilidades.getCadena("Nombre"));
            alumno.setApellidos(Utilidades.getCadena("Apellidos"));
            alumno.setDni(Utilidades.getCadena("DNI"));
            alumno.setTelefono(Utilidades.getCadena("Telefono"));
            alumno.setComentarios(Utilidades.getCadena("Comentarios"));
            alumno.setEstado(Utilidades.getCadena("Estado"));
            // Comprobar si se han introducido todos los campos obligatorios
            if (elemento.validar()){
              List <T> lista = dao.leer(elemento.getNombre(), elemento.getApellidos());
              // Comprobar si el alumno ya existe
              if (!lista.isEmpty()){
                  Utilidades.showCadena("ERROR: Ya existe un alumno con esos valores en la base de datos");
              }else{
                  boolean resultado = dao.crear(elemento);
              }
            }else{
                Utilidades.showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                           "El nuevo "+elemento.getClass()+" no se guardara");
            }
            return menu;
        }
      });
    
    
      final Opcion opcionA2 = m.new Opcion("Borrar alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del alumno a borrar: ");
            int id = Utilidades.getEntero("id");
            // Comprobar si el alumno existe
            List <Alumno> listaAlumnos = daoAlumno.leer(id);
            if (!listaAlumnos.isEmpty()){
                // Mostrar datos del alumno antes de borrarlo
                alumno = listaAlumnos.get(0);
                mostrarAlumno(alumno);
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
    
    
      final Opcion opcionA3 = m.new Opcion("Modificar alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del alumno a modificar: ");
            int id = Utilidades.getEntero("id");
            // Comprobar si el alumno existe
            List <Alumno> listaAlumnos = daoAlumno.leer(id);
            if (listaAlumnos.size()==1){
                // Ir al menu Modificar datos del alumno
                alumno = listaAlumnos.get(0);
                return menuM;
            }else{
                Utilidades.showCadena("ERROR: El alumno no existe en la base de datos");
            }
            return menuA;
        }
      });
    
    
      final Opcion opcionA4 = m.new Opcion("Buscar alumno", new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del alumno a buscar: ");
            int id = Utilidades.getEntero("id");
// nota: se podria ampliar la busqueda con otros campos
            // Comprobar si el alumno existe
            List <Alumno> listaAlumnos = daoAlumno.leer(id);
            if (!listaAlumnos.isEmpty()){
                // Mostrar datos del alumno
                // Si hay varios alumnos que se ajustan a esa busqueda, mostrarlos todos
                Utilidades.showCadena(cadenaDatos);
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
      
      final Opcion opcionA5 = m.new Opcion("Mostrar todos los alumnos", new Accion(){
        @Override
        public Menu ejecutar() {
            // Al buscar con id=0, se devuelven todos los alumnos
            List <Alumno> listaAlumnos = daoAlumno.leer(0);
            if (!listaAlumnos.isEmpty()){
                // Mostrar datos del todos los alumnos
                // Si hay varios alumnos que se ajustan a esa busqueda, mostrarlos todos
                Utilidades.showCadena(cadenaDatosAlumno);
                for (Alumno alumnoI : listaAlumnos) {
                    // Mostrar los datos de cada alumno en una sola linea                
                    mostrarAlumnoLinea(alumnoI);
                }
            }else{
                Utilidades.showCadena("No existen ningun alumno en la base de datos");
            }
            return menuA;
        }
      });
      
      final Opcion opcionA6 = m.new Opcion("Volver al menu principal", (Accion) () -> {
          return menuA.getAnterior();
      });
      
      
        // Opciones del Menu Modificar alumno
        final Opcion opcionM1 = m.new Opcion("Nombre", (Accion) () -> {
            mostrarAlumno(alumno);
            Utilidades.showCadena(" ");
            alumno.setNombre(Utilidades.getCadena("Nombre"));
            return menuM;
        });      

        final Opcion opcionM2 = m.new Opcion("Apellidos", (Accion) () -> {
          mostrarAlumno(alumno);
          Utilidades.showCadena(" ");
          alumno.setApellidos(Utilidades.getCadena("Apellidos"));
          return menuM;
        });

        final Opcion opcionM3 = m.new Opcion("DNI", (Accion) () -> {
          mostrarAlumno(alumno);
          Utilidades.showCadena(" ");
          alumno.setDni(Utilidades.getCadena("DNI"));
          return menuM;
        });

        final Opcion opcionM4 = m.new Opcion("Telefono", (Accion) () -> {
          mostrarAlumno(alumno);
          Utilidades.showCadena(" ");
          alumno.setTelefono(Utilidades.getCadena("Telefono"));
          return menuM;
        });

        final Opcion opcionM5 = m.new Opcion("Comentarios", (Accion) () -> {
          mostrarAlumno(alumno);
          Utilidades.showCadena(" ");
          alumno.setComentarios(Utilidades.getCadena("Comentarios"));
          return menuM;
        });

        final Opcion opcionM6 = m.new Opcion("Estado", (Accion) () -> {
          mostrarAlumno(alumno);
          Utilidades.showCadena(" ");
          alumno.setEstado(Utilidades.getCadena("Estado"));
          return menuM;
        });

        final Opcion opcionM7 = m.new Opcion("Mostrar todos los campos", (Accion) () -> {
          Utilidades.showCadena(" ");
          mostrarAlumno(alumno);
          return menuM;
        });

        final Opcion opcionM8 = m.new Opcion("Guardar cambios", (Accion) () -> {
          if (!alumno.validarAlumno()){
              Utilidades.showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                                    "Las modificaciones realizadas no se guardaran");
          }else{
              Utilidades.showCadena(" ");
              mostrarAlumno(alumno);
              boolean resultado = daoAlumno.actualizar(alumno);
          }
          return menuM.getAnterior();
        });

        final Opcion opcionM9 = m.new Opcion("Volver al menu anterior", (Accion) () -> {
          return menuM.getAnterior();
        });
        
        
        // Opciones del Menu de permisos
        final Opcion opcionP1 = m.new Opcion("Crear permiso", (Accion) () -> {
            Utilidades.showCadena("Introduzca los siguientes datos del nuevo permiso: ");
            permiso = new Permiso();
            permiso.setValor(Utilidades.getCadena("Tipo de permiso"));
            permiso.setDescripcion(Utilidades.getCadena("Descripcion"));
            daoPermiso.crear(permiso);
            Utilidades.showCadena("El nuevo permiso creado tiene los siguientes datos: ");
            mostrarPermiso(permiso);
            return menuP;
        });
        
        final Opcion opcionP2 = m.new Opcion("Borrar permiso", (Accion) () -> {
            Utilidades.showCadena("Introduzca los siguientes datos del permiso a borrar: ");
            int id = Utilidades.getEntero("id");
            permiso = daoPermiso.leer(id);
            if (permiso == null){ // El permiso no existe
                  Utilidades.showCadena("ERROR: El permiso no existe en la base de datos");
            }else{
                daoPermiso.eliminar(id);
            }
            return menuP;
        });
        
        final Opcion opcionP3 = m.new Opcion("Actualizar tipo de permiso", (Accion) () -> {
            Utilidades.showCadena("Introduzca los siguientes datos del permiso a modificar: ");
            int id = Utilidades.getEntero("id");
            permiso = daoPermiso.leer(id);
            if (permiso == null){ // El permiso no existe
                Utilidades.showCadena("ERROR: El permiso no existe en la base de datos");
            }else{
                permiso.setValor(Utilidades.getCadena("Nuevo tipo de permiso"));
                daoPermiso.actualizar(permiso);
                Utilidades.showCadena("Los nuevos datos del permiso son: ");
                mostrarPermiso(permiso);
            }
            return menuP;
        });
        
        final Opcion opcionP4 = m.new Opcion("Actualizar descripcion de permiso", (Accion) () -> {
            Utilidades.showCadena("Introduzca los siguientes datos del permiso a modificar: ");
            int id = Utilidades.getEntero("id");
            permiso = daoPermiso.leer(id);
            if (permiso == null){ // El permiso no existe
                  Utilidades.showCadena("ERROR: El permiso no existe en la base de datos");
            }else{
                permiso.setDescripcion(Utilidades.getCadena("Nueva descripcion del permiso"));
                daoPermiso.actualizar(permiso);
                Utilidades.showCadena("Los nuevos datos del permiso son: ");
                mostrarPermiso(permiso);
            }
            return menuP;
        });
        
        final Opcion opcionP5 = m.new Opcion("Mostrar todos los permisos", (Accion) () -> {
            daoPermiso.mostrarPermisos();
            return menuP;
        });
        
        final Opcion opcionP6 = m.new Opcion("Volver al menu anterior", (Accion) () -> {
          return menuP.getAnterior();
        });
        
        
        // Incluir las opciones en el menu pricipal
        menu = new Menu();
        menu.addOpcion(opcion1);
        menu.addOpcion(opcion2);
        menu.addOpcion(opcion3);
        menu.setRotuloMenu("Menu principal");

        // Incluir las opciones en el menu de Alumnos
        menuA = new Menu();
        menuA.addOpcion(opcionA1);
        menuA.addOpcion(opcionA2);
        menuA.addOpcion(opcionA3);
        menuA.addOpcion(opcionA4);
        menuA.addOpcion(opcionA5);
        menuA.addOpcion(opcionA6);
        menuA.setRotuloMenu("Menu de alumnos");
        menuA.setAnterior(menu);

        // Incluir las opciones en el menu de Modificar Alumno
        menuM = new Menu();
        menuM.addOpcion(opcionM1);
        menuM.addOpcion(opcionM2);
        menuM.addOpcion(opcionM3);
        menuM.addOpcion(opcionM4);
        menuM.addOpcion(opcionM5);
        menuM.addOpcion(opcionM6);
        menuM.addOpcion(opcionM7);
        menuM.addOpcion(opcionM8);
        menuM.addOpcion(opcionM9);
        menuM.setRotuloMenu("Elija un campo del alumno a modificar");
        menuM.setAnterior(menuA);

        // Incluir las opciones en el menu de Permisos
        menuP = new Menu();
        menuP.addOpcion(opcionP1);
        menuP.addOpcion(opcionP2);
        menuP.addOpcion(opcionP3);
        menuP.addOpcion(opcionP4);
        menuP.addOpcion(opcionP5);
        menuP.addOpcion(opcionP6);
        menuP.setRotuloMenu("Menu de permisos de conducir");
        menuP.setAnterior(menu);

    }
    
    
    // Mostrar todas las opciones del menuActual y ejecutar la elegida por el usuario
    public void mostrarMenu() {
        Menu menuActual = menu;
        do{
            Utilidades.showCadena("\n------------------------------------------------------");
            Utilidades.showCadena("Autoescuela FORINEMAS. Software de gestion de alumnos:  ");
            Utilidades.showCadena(menuActual.getRotuloMenu());
            menuActual.mostrarOpciones();
            int opcion = Utilidades.getEntero("\n Elija una opcion del menu");
            if ((opcion>0) && (opcion <=menuActual.getNumAcciones())){
                Utilidades.showCadena(" ");
                menuActual = menuActual.ejecutar(opcion-1);
            }else{
                System.out.println("\n Opcion incorrecta, introduzca un numero "+
                                   "entre 1 y "+menuActual.getNumAcciones());
            }
        }while (true);
    }
    
    
    // Mostrar todos los campos del alumno, uno en cada linea
    private static void mostrarAlumno(Alumno alumno){
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
    
    // Mostrar todos los campos del permiso en una linea
    private static void mostrarPermiso(Permiso permiso){
        Utilidades.showCadena("ID: "+new Integer(permiso.getId()).toString()+
                              " | Permiso: "+permiso.getValor()+
                              " | Descripcion: "+permiso.getDescripcion());
    }
        

}
