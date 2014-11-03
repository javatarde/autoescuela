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
public class MenuGenerico<T>{
    private Menu menu  = null;
    private Menu menuAnterior = null;
    private Menu menuSiguiente = null;
//    private T elemento = null;
    private final GestionCrud<T> dao;
    private final Vista<T> vista;
//    private String nombreClase = null;


    public MenuGenerico(Vista<T> vista, GestionCrud<T> dao, String nombreClase) {
      this.vista = vista;
      this.dao = dao;
/*        
    public MenuGenerico() {
      this.vista = new VistaAlumno();
      this.dao = new DAOAlumno();
*/      
//      nombreClase = elemento.getClass().getName();
    
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
      
      // Opciones del Menu de alumnos
      final Opcion opcionA1 = m.new Opcion("Crear nuevo "+nombreClase, new Accion(){
        @Override
        public Menu ejecutar() {
            Utilidades.showCadena("Introduzca los siguientes datos del nuevo "+nombreClase+": ");
            // Nota: el id se genera automaticamente en la bbdd al insertar el nuevo alumno
            T elemento = MenuGenerico.this.vista.get();
            // Comprobar si se han introducido todos los campos obligatorios
            if (MenuGenerico.this.dao.validar(elemento)){
              List <T> lista = MenuGenerico.this.dao.leer(elemento);
              // Comprobar si el alumno ya existe
              if (!lista.isEmpty()){
                  Utilidades.showCadena("ERROR: Ya existe un "+nombreClase+" con esos valores en la base de datos");
              }else{
                  boolean resultado = MenuGenerico.this.dao.crear(elemento);
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
            List <T> lista = MenuGenerico.this.dao.leer(id);
            if (!lista.isEmpty()){
                // Mostrar datos del alumno antes de borrarlo
                T elemento = lista.get(0);
                MenuGenerico.this.vista.show(elemento);
                String cadena = Utilidades.getCadena("¿Desea eliminar al "+nombreClase+"? (si/no) ");
                if (cadena.toLowerCase().equals("si")){
                    boolean resultado = MenuGenerico.this.dao.eliminar(id);
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
            List <T> lista = MenuGenerico.this.dao.leer(id);
            if (lista.size()==1){
                // Ir al menu Modificar datos del alumno
                T elemento = lista.get(0);
                MenuGenerico.this.vista.update(elemento);
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
            List <T> lista = MenuGenerico.this.dao.leer(id);
            if (!lista.isEmpty()){
                // Mostrar datos
                // Si hay varios elementos que se ajustan a esa busqueda, mostrarlos todos
                MenuGenerico.this.vista.show(lista);
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
            List <T> lista = MenuGenerico.this.dao.leer(0);
            if (!lista.isEmpty()){
                // Mostrar datos del todos los elementos
                // Si hay varios elementos que se ajustan a esa busqueda, mostrarlos todos
                MenuGenerico.this.vista.show(lista);
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

    MenuGenerico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Menu getMenu() {
        return menu;
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
    
}
