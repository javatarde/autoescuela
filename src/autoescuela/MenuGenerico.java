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
    private Menu menuAnterior = null;
    private Menu menuSiguiente = null;
    private T elemento = null;
    private GestionCrud<T> dao = new GestionCrud<T>();
    private String nombreClase = null;
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
            Utilidades.showCadena("Introduzca los siguientes datos del nuevo "+nombreClase+": ");
            // Nota: el id se genera automaticamente en la bbdd al insertar el nuevo alumno
            elemento = new T();
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
    
    
      final Opcion opcionA2 = m.new Opcion("Borrar "+nombreClase, new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del "+nombreClase+" a borrar: ");
            int id = Utilidades.getEntero("id");
            // Comprobar si el alumno existe
            List <T> lista = dao.leer(id);
            if (!lista.isEmpty()){
                // Mostrar datos del alumno antes de borrarlo
                elemento = lista.get(0);
                elemento.mostrar();
                String cadena = Utilidades.getCadena("¿Desea eliminar al "+nombreClase+"? (si/no) ");
                if (cadena.toLowerCase().equals("si")){
                    boolean resultado = dao.eliminar(id);
                }
            }else{
                Utilidades.showCadena("ERROR: El "+nombreClase+" no existe en la base de datos");
            }
            return menu;
        }
      });
    
    
      final Opcion opcionA3 = m.new Opcion("Modificar "+nombreClase, new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del "+nombreClase+" a modificar: ");
            int id = Utilidades.getEntero("id");
            // Comprobar si el alumno existe
            List <T> lista = dao.leer(id);
            if (lista.size()==1){
                // Ir al menu Modificar datos del alumno
                elemento = lista.get(0);
                return menu;
            }else{
                Utilidades.showCadena("ERROR: El "+nombreClase+" no existe en la base de datos");
            }
            return menu;
        }
      });
    
    
      final Opcion opcionA4 = m.new Opcion("Buscar "+nombreClase, new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del "+nombreClase+" a buscar: ");
            int id = Utilidades.getEntero("id");
// nota: se podria ampliar la busqueda con otros campos
            // Comprobar si el alumno existe
            List <T> lista = dao.leer(id);
            if (!lista.isEmpty()){
                // Mostrar datos del alumno
                // Si hay varios alumnos que se ajustan a esa busqueda, mostrarlos todos
                Utilidades.showCadena(cadenaDatos);
                for (T elemento : lista) {
                    // Mostrar los datos de cada alumno en una sola linea                
                    elemento.mostrar();
                }
            }else{
                Utilidades.showCadena("ERROR: No existen resultados para esa busqueda");
            }
            return menu;
        }
      });
      
      final Opcion opcionA5 = m.new Opcion("Mostrar todos los "+nombreClase, new Accion(){
        @Override
        public Menu ejecutar() {
            // Al buscar con id=0, se devuelven todos los alumnos
            List <T> lista = dao.leer(0);
            if (!lista.isEmpty()){
                // Mostrar datos del todos los elementos
                // Si hay varios alumnos que se ajustan a esa busqueda, mostrarlos todos
                Utilidades.showCadena(cadenaDatos);
                for (T elemento : lista) {
                    // Mostrar los datos de cada alumno en una sola linea                
                    elemento.mostrar();
                }
            }else{
                Utilidades.showCadena("No existen ningun "+nombreClase+" en la base de datos");
            }
            return menu;
        }
      });
      
      final Opcion opcionA6 = m.new Opcion("Volver al menu principal", (Accion) () -> {
          return menu.getAnterior();
      });
        
        // Incluir las opciones en el menu
        menu = new Menu();
        menu.addOpcion(opcionA1);
        menu.addOpcion(opcionA2);
        menu.addOpcion(opcionA3);
        menu.addOpcion(opcionA4);
        menu.addOpcion(opcionA5);
        menu.addOpcion(opcionA6);
        menu.setRotuloMenu("Menu de "+nombreClase);
        menu.setAnterior(menuAnterior);
        menu.setSiguiente(menuSiguiente);

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
    
}
