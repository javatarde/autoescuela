/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela;

/**
 *
 * @author Formacion
 */
public class MenuAlumno extends MenuGenerico<Alumno>{
//public class MenuAlumno {
    
    public MenuAlumno(Vista<Alumno> vista, GestionCrud<Alumno> dao, String cadena) {
        super(vista, dao, cadena);
    }
    
    public static void main(String[] args) {
        Vista<Alumno> vistaAlumno = new VistaAlumno();
        GestionCrud<Alumno> daoAlumno = new DAOAlumno();
        
//        MenuGenerico<Alumno> menuAlumno = new MenuGenerico (Vista<Alumno>, GestionCrud<Alumno>);
//        Vista vistaAlumno = new VistaAlumno();
//        GestionCrud daoAlumno = new DAOAlumno0();
//        MenuGenerico<Alumno> menuAlumno = new MenuGenerico<Alumno> (vistaAlumno,daoAlumno);
        
        String cadena = new Alumno().getClass().getName();
        cadena = "alumno";
        MenuGenerico<Alumno> menuAlumno = new MenuAlumno (vistaAlumno,daoAlumno,cadena);
        
        menuAlumno.mostrarMenu();
/*        
        Menu m = menuAlumno.getMenu();
        
        mostrarMenu();
*/        
    }
    
}
