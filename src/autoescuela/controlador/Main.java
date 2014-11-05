/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela.controlador;

import autoescuela.vista.Componente;
import autoescuela.vista.ComponenteAlumno;
import autoescuela.vista.ComponentePermiso;
import autoescuela.modelo.GestionCrud;
import autoescuela.modelo.Permiso;
import autoescuela.modelo.DAOPermiso;
import autoescuela.modelo.DAOAlumno;
import autoescuela.modelo.Alumno;
import autoescuela.vista.Utilidades;
import autoescuela.controlador.Menu.Opcion;
import autoescuela.modelo.DAOMatricula;
import autoescuela.modelo.DAOTipoMatricula;
import autoescuela.modelo.Matricula;
import autoescuela.modelo.TipoMatricula;
import autoescuela.vista.ComponenteMatricula;
import autoescuela.vista.ComponenteTipoMatricula;
import static java.lang.System.exit;
import java.util.List;

/**
 *
 * @author Formacion
 */
public class Main {
    private final Menu menuAlumno;
    private final Menu menuPermiso;
    private final Menu menuMatricula;
    private final Menu menuTipoMatricula;
    private final Menu menu;
    private static final String tituloPrograma = "Autoescuela FORINEMAS. Software de gestion de alumnos:  ";
    
    
    public Main() {
        // Inicializar componentes para la vista
        Componente<Alumno> compAlumno = new ComponenteAlumno();
        Componente<Permiso> compPermiso = new ComponentePermiso();
        Componente<TipoMatricula> compTipoMatricula = new ComponenteTipoMatricula();
        Componente<Matricula> compMatricula = new ComponenteMatricula();
        
        
        // Inicializar DAOs del modelo
        GestionCrud<Alumno> daoAlumno =  new DAOAlumno();
        GestionCrud<Permiso> daoPermiso =  new DAOPermiso();
        GestionCrud<TipoMatricula> daoTipoMatricula =  new DAOTipoMatricula();
        GestionCrud<Matricula> daoMatricula =  new DAOMatricula();
        
        
        // Crear menus del programa
        menu = new Menu();
//        MenuGenerico<Alumno> constructorMenuAlumno = new MenuGenerico<>(compAlumno,
//                                                        daoAlumno,"alumno",menu,null);
        MenuAlumno constructorMenuAlumno = new MenuAlumno(compAlumno,daoAlumno,
                                                          "alumno",menu,null);
        menuAlumno = constructorMenuAlumno.getMenu();
        MenuPermiso constructorMenuPermiso = new MenuPermiso(compPermiso,daoPermiso,
                                                             "permiso",menu,null);
        menuPermiso = constructorMenuPermiso.getMenu();
        MenuTipoMatricula constructorMenuTipoMatricula = new MenuTipoMatricula(compTipoMatricula,
                                                             daoTipoMatricula,
                                                             "tipo de matricula",menu,null);
        menuTipoMatricula = constructorMenuTipoMatricula.getMenu();
        MenuMatricula constructorMenuMatricula = new MenuMatricula(compMatricula,
                                                     daoMatricula,"matricula",menu,null);
        menuMatricula = constructorMenuMatricula.getMenu();

        // Crear opciones del menu principal
        final Opcion opcion1 = menu.new Opcion("Gestion de alumnos", (Accion) () -> {
            return menuAlumno;
        });
        final Opcion opcion2 = menu.new Opcion("Gestion de matriculas", (Accion) () -> {
            return menuMatricula;
        });
        final Opcion opcion3 = menu.new Opcion("Gestion de permisos de conducir", (Accion) () -> {
            return menuPermiso;
        });
        final Opcion opcion4 = menu.new Opcion("Gestion de tipos de matricula de autoescuela", (Accion) () -> {
            return menuTipoMatricula;
        });
        final Opcion opcion5 = menu.new Opcion("Salir", (Accion) () -> {
            Utilidades.showCadena("\n Fin del programa");
            exit(0);
            return null;
        });
        
        // Incluir las opciones en el menu pricipal
        menu.addOpcion(opcion1);
        menu.addOpcion(opcion2);
        menu.addOpcion(opcion3);
        menu.addOpcion(opcion4);
        menu.addOpcion(opcion5);
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
