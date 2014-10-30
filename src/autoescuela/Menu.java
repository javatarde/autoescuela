/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela;

import java.util.List;

/**
 *
 * @author Administracion
 */
public class Menu {
    
    private Menu siguiente = null;
    private Menu anterior = null;
    private List<Opcion> listaOpciones = null;
    private String rotuloMenu = null;

    public Menu() {
        // constructor vacio
    }
    
    public Menu(List <Opcion> listaOpciones) {
        this.listaOpciones = listaOpciones;
        siguiente = null;
        anterior = null;
    }
    
    public void mostrarOpciones(){
        Opcion opcion;
        for (int i=1; i<=getNumAcciones(); i++) {
            opcion = getAccion(i-1);
            System.out.println(i+") "+opcion.getRotulo());
        }
    }
    
    public Opcion getAccion(int index){
        if ((index>=0) && (index<getNumAcciones())){
            return listaOpciones.get(index);
        }else{
            return null;
        }
    }
    
    public int getNumAcciones(){
        if (listaOpciones != null){
            return listaOpciones.size();
        }else{
            return 0;
        }
    }

    public Menu getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Menu siguiente) {
        this.siguiente = siguiente;
    }

    public Menu getAnterior() {
        return anterior;
    }

    public void setAnterior(Menu anterior) {
        this.anterior = anterior;
    }

    public void setRotuloMenu(String rotuloMenu) {
        this.rotuloMenu = rotuloMenu;
    }

    public String getRotuloMenu() {
        return rotuloMenu;
    }
    
    
    
    public class Opcion implements Accion<Menu>{
        private String rotulo;
        private Accion<Menu> accion;

        public Opcion(String rotulo, Accion accion) {
            this.rotulo = rotulo;
            this.accion = accion;
        }

        @Override
        public Menu ejecutar() {
            return accion.ejecutar();
        }

        public String getRotulo() {
            return rotulo;
        }
        
    }
    
}
