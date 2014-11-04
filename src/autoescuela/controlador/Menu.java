/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Formacion
 */
public class Menu {
    
    private Menu siguiente = null;
    private Menu anterior = null;
    private List<Opcion> listaOpciones = null;
    private String rotuloMenu = null;

    public Menu() {
        listaOpciones = new ArrayList<Opcion>();
    }
    
    public void mostrarOpciones(){
        Opcion opcion;
        for (int i=1; i<=getNumAcciones(); i++) {
            opcion = listaOpciones.get(i-1);
            System.out.println(i+") "+opcion.getRotulo());
        }
/*
        Iterator iter = listaOpciones.iterator();
        int i = 0;
        while (iter.hasNext()){
            i++;
            opcion = (Opcion) iter.next();
            System.out.println(i+") "+opcion.getRotulo());
        }
*/        
    }
    
    public Menu ejecutar(int index){
        if ((index>=0) && (index<getNumAcciones())){
            Opcion opcion=listaOpciones.get(index);
            return opcion.ejecutar();
        }else{
            return null;
        }
    }
    
    public boolean addOpcion(Opcion opcion){
        return listaOpciones.add(opcion);
    }
    
    public Opcion getOpcion(int index){
        return listaOpciones.get(index);
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

    public String getRotuloMenu() {
        return rotuloMenu;
    }
    
    public void setRotuloMenu(String rotuloMenu) {
        this.rotuloMenu = rotuloMenu;
    }
    
    
    
    public class Opcion implements Accion<Menu>{
        private final String rotulo;
        private final Accion<Menu> accion;

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

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.rotulo);
            hash = 97 * hash + Objects.hashCode(this.accion);
            return hash;
        }
/*
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Opcion other = (Opcion) obj;
            if (!rotulo.equals(other.rotulo) && !Objects.equals(this.accion, other.accion)) {
                return false;
            }
            return true;
        }
*/        
    }
    
}
