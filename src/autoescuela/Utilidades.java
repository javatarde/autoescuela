/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela;

import java.util.Scanner;

/**
 *
 * @author Administracion
 */
public class Utilidades {
    
    // Mostrar por pantalla una cadena y devolver otra (no vacia) leida por consola
    public static String getCadena(String cadena){
        String leido = null;
        do{
            System.out.print(cadena+": ");
            leido = new Scanner(System.in).nextLine();
        }while (leido.isEmpty());
        return leido;
    }
    
    // Mostrar por pantalla una cadena y devolver un entero leido por consola
    public static int getEntero(String cadena){
        String leido = null;
        do{
            try{
                System.out.print(cadena+": ");
                leido = new Scanner(System.in).nextLine();
                return new Integer(leido);
            }catch(NumberFormatException e){
                System.out.println(" ERROR: Debe introducir un numero entero");
            }
        }while (true);
    }
    
    public static void showCadena(String cadena){
      System.out.println(cadena);
    }
    
    public static void showCadena(String cadena, String cadenaOpcional){
      if (cadenaOpcional == null){
          showCadena(cadena);
      }else{
          showCadena(cadena + cadenaOpcional);
      }
    }
    
}
