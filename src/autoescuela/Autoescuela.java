/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Formacion
 */
public class Autoescuela {

  /**
   * @param args the command line arguments
   * @throws java.sql.SQLException
   */
  public static void main(String[] args) throws SQLException {
    // TODO code application logic here
    DAOAlumno a = new DAOAlumno();
    //a.crear();
    ArrayList alumnos = a.leer();
    for (Object alumno : alumnos) {
      System.out.println("hola"+alumno);
    }
  }
  
}
