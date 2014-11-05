package autoescuela.controlador;

import autoescuela.vista.Componente;
import autoescuela.modelo.GestionCrud;
import autoescuela.vista.Utilidades;
import autoescuela.controlador.Menu.Opcion;
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
 * @param <T>
 */
public abstract class MenuGenerico<T>{
    private Menu menu = null;
    private Menu menuAnterior = null;
    private Menu menuSiguiente = null;
    private final GestionCrud<T> dao;
    private final Componente<T> componente;
    private final String nombreClase;
    private final String bd = "la base de datos";
    
    
    public MenuGenerico(Componente<T> componente, GestionCrud<T> dao, 
                        String nombreClase, Menu menuAnt, Menu menuSig) {
      this.componente = componente;
      this.dao = dao;
      this.nombreClase = nombreClase;
      menuAnterior = menuAnt;
      menuSiguiente = menuSig;
//      nombreClase = elemento.getClass().getName();
    
      Menu m = new Menu();

      // Opciones del Menu de elementos
      final Opcion opcion1 = m.new Opcion("Crear nuevo "+nombreClase, (Accion) () -> {
          Utilidades.showCadena("Introduzca los siguientes datos del nuevo "+nombreClase+": ");
          T elemento = MenuGenerico.this.componente.get();
          if (MenuGenerico.this.dao.validar(elemento)) {
              List <T> lista = MenuGenerico.this.dao.leer(elemento);
if (lista==null){
    System.out.println("lista vacia");
}else{
    System.out.println("hay "+lista.size());
}
                      
              if (!lista.isEmpty()) {
                  Utilidades.showCadena("ERROR: Ya existe un "+nombreClase+" con esos valores en "+bd);
              } else {
                  boolean resultado = MenuGenerico.this.dao.crear(elemento);
                  if (resultado){
                        Utilidades.showCadena(nombreClase+" creado correctamente en "+bd);
                  }else{
                        Utilidades.showCadena("ERROR al crear "+nombreClase+" en "+bd);
                  }
              }
          } else {
              Utilidades.showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                      "El nuevo "+elemento.getClass()+" no se guardara");
          }
          return menu;
      });
    
    
      final Opcion opcion2 = m.new Opcion("Borrar "+nombreClase, (Accion) () -> {
          Utilidades.showCadena("Introduzca los siguientes datos del "+nombreClase+" a borrar: ");
          int id = Utilidades.getEntero("id");
          List <T> lista = MenuGenerico.this.dao.leer(id);
          if (!lista.isEmpty()) {
              T elemento = lista.get(0);
              MenuGenerico.this.componente.set(elemento);
              String cadena = Utilidades.getCadena("¿Desea eliminar al "+nombreClase+"? (si/no) ");
              if (cadena.toLowerCase().equals("si")){
                  boolean resultado = MenuGenerico.this.dao.eliminar(id);
                  if (resultado){
                        Utilidades.showCadena(nombreClase+" borrado correctamente en "+bd);
                  }else{
                        Utilidades.showCadena("ERROR al borrar "+nombreClase+" en "+bd);
                  }
              }
          } else {
              Utilidades.showCadena("ERROR: El "+nombreClase+" no existe en "+bd);
          }
          return menu;
      });
    
    
      final Opcion opcion3 = m.new Opcion("Modificar "+nombreClase, (Accion) () -> {
          Utilidades.showCadena("Introduzca los siguientes datos del "+nombreClase+" a modificar: ");
          int id = Utilidades.getEntero("id");
          List <T> lista = MenuGenerico.this.dao.leer(id);
          if (lista.size()==1) {
              T elemento = lista.get(0);
              elemento = MenuGenerico.this.componente.update(elemento);
              if (!MenuGenerico.this.dao.validar(elemento)){
                  Utilidades.showCadena("ERROR: No se han introducido todos los campos obligatorios. "+
                                        "Las modificaciones en "+elemento.getClass()+" no se guardaran en "+bd);
              }else{ // Guardar modificaciones
                  boolean resultado = MenuGenerico.this.dao.actualizar(elemento);
                  if (resultado){
                        Utilidades.showCadena(nombreClase+" modificado correctamente en "+bd);
                  }else{
                        Utilidades.showCadena("ERROR al modificar "+nombreClase+" en "+bd);
                  }
              }
          } else {
              Utilidades.showCadena("ERROR: El "+nombreClase+" no existe en "+bd);
          }
          return menu;
      });
    
    
      final Opcion opcion4 = m.new Opcion("Buscar "+nombreClase, (Accion) () -> {
          Utilidades.showCadena("Introduzca los siguientes datos del "+nombreClase+" a buscar: ");
          int id = Utilidades.getEntero("id");
          List <T> lista = MenuGenerico.this.dao.leer(id);
          if (!lista.isEmpty()){
              // Mostrar datos
              // Si hay varios elementos que se ajustan a esa busqueda, mostrarlos todos
              MenuGenerico.this.componente.set(lista);
          }else{
              Utilidades.showCadena("ERROR: No existen resultados para esa busqueda");
          }
          return menu;
      });
      
      final Opcion opcion5 = m.new Opcion("Mostrar todos", (Accion) () -> {
          List <T> lista = MenuGenerico.this.dao.leer(null);
          if (!lista.isEmpty()) {
              MenuGenerico.this.componente.set(lista);
          } else {
              Utilidades.showCadena("No existe ningun elemento "+nombreClase+" en "+bd);
          }
          return menu;
      });
      
      final Opcion opcion6 = m.new Opcion("Volver al menu principal", (Accion) () -> {
          return menu.getAnterior();
      });
      
      // Incluir todas las opciones en el menu
      menu = new Menu();
      menu.addOpcion(opcion1);
      menu.addOpcion(opcion2);
      menu.addOpcion(opcion3);
      menu.addOpcion(opcion4);
      menu.addOpcion(opcion5);
      menu.addOpcion(opcion6);
      menu.setRotuloMenu("Menu de "+nombreClase);
      menu.setAnterior(menuAnterior);
      menu.setSiguiente(menuSiguiente);

    }

    public Menu getMenu() {
        return menu;
    }

}
