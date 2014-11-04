/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela;

import autoescuela.Menu.Opcion;
import static java.lang.System.exit;

/**
 *
 * @author Formacion
 */
public class Main {
    private final Menu menuAlumno;
    private final Menu menuPermiso;
    private final Menu menuMatricula;
    private final Menu menu;
    private final String tituloPrograma = "Autoescuela FORINEMAS. Software de gestion de alumnos:  ";
    
    
    public Main() {
        // Inicializar componentes de la vista
        Componente<Alumno> compAlumno = new ComponenteAlumno();
        Componente<Permiso> compPermiso = new ComponentePermiso();
//        Componente<TipoMatricula> compTipoMatricula = new ComponenteTipoMatricula();
//        Componente<Matricula> compMatricula = new ComponenteMatricula();
        
        
        // Inicializar DAOs
        GestionCrud<Alumno> daoAlumno =  new DAOAlumno();
        GestionCrud<Permiso> daoPermiso =  new DAOPermiso();
//        GestionCrud<TipoMatricula> daoTipoMatricula =  new DAOTipoMatricula();
//        GestionCrud<Matricula> daoMatricula =  new DAOMatricula();
        
        
        // Crear menus del programa
        menu = new Menu();
        
        MenuGenerico<Alumno> constructorMenuAlumno = new MenuGenerico<>(compAlumno,
                                                        daoAlumno,"alumno",menu,null);
        menuAlumno = constructorMenuAlumno.getMenu();
        MenuGenerico<Permiso> constructorMenuPermiso = new MenuGenerico<>(compPermiso,
                                                        daoPermiso,"permiso",menu,null);
        menuPermiso = constructorMenuPermiso.getMenu();
        MenuGenerico<Alumno> constructorMenuMatricula = new MenuGenerico<>(compAlumno,
                                                        daoAlumno,"matricula",menu,null);
//        MenuGenerico<Matricula> constructorMenuMatricula = new MenuGenerico<>(compMatricula,
//                                                        daoMatricula,"matricula",menu,null);
        menuMatricula = constructorMenuMatricula.getMenu();

        // Opciones del Menu principal
        final Opcion opcion1 = menu.new Opcion("Gestion de alumnos", (Accion) () -> {
            return menuAlumno;
        });
        final Opcion opcion2 = menu.new Opcion("Gestion de permisos", (Accion) () -> {
            return menuPermiso;
        });
        final Opcion opcion3 = menu.new Opcion("Gestion de matriculas", (Accion) () -> {
            return menuMatricula;
        });
        final Opcion opcion4 = menu.new Opcion("Salir", (Accion) () -> {
            Utilidades.showCadena("\n Fin del programa");
            exit(0);
            return null;
        });
        
        // Incluir las opciones en el menu pricipal
        menu.addOpcion(opcion1);
        menu.addOpcion(opcion2);
        menu.addOpcion(opcion3);
        menu.addOpcion(opcion4);
        menu.setRotuloMenu("Menu principal");
        
    }
    
    
    // Mostrar todas las opciones del menuActual y ejecutar la elegida por el usuario
    public void iniciar() {
        Menu menuActual = menu;
        do{
            Utilidades.showCadena("\n------------------------------------------------------");
            Utilidades.showCadena(tituloPrograma);
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
    
    
    public static void main(String[] args) {
        Main m = new Main();
        m.iniciar();
    }
    
}
