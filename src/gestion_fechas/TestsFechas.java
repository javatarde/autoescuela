/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_fechas;

/**
 *
 * @author Formacion
 */
public class TestsFechas {

    
    public static void main(String[] args) {
        String fechaCadena;
        GestorFechas fechaManejador=new GestorFechas();
        java.util.Date fechaDate;
        
        fechaDate = GestorFechas.deStringToDate("30-12-2014");
        System.out.println(fechaDate);
        
        System.out.println(fechaManejador.getHoraActual());
        System.out.println(fechaManejador.getFechaActual());
        
        
        
    }
   
    
}
