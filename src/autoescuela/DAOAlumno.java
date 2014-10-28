/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoescuela;

import conexion.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Formacion
 */
public class DAOAlumno {
  private final String tabla = "AU_ALUMNO";
  //CRUD
  //CREAR
  public void crear(Alumno a) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    
    //conexion
    try {
      //usamos getConnect porque es el contructor del singleton es privado
      conn = ConnectDB.getConnect();
      
      SQL = "INSERT INTO "+tabla+" VALUES (?,?,?,?,?,?,?)";
      
      PreparedStatement pst = conn.prepareStatement(SQL);
      
      pst.setObject(1, null);
      pst.setString(2, a.getNombre());
      pst.setString(3, a.getApellidos());
      pst.setString(4, a.getDni());
      pst.setString(5, a.getTelefono());
      pst.setString(6, a.getEstado());
      pst.setString(7, a.getComentarios());
      
      //ejecuto la SQL
      int fila_afectadas=pst.executeUpdate();
      
      rs.close();
      stmt.close();
      conn.close();      
      
      System.out.println("Se han creado: "+fila_afectadas+" alumno(s).");
    } catch (SQLException e) {
      System.out.println("Error al crear alumno en la BD: "+e);      
    }
  }
  
  //LEER
  public ArrayList leer() {
    ArrayList lista = null;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    
    SQL = "SELECT id, nombre, apellidos dni, telefono, estado, comentarios FROM "+tabla;
    
    //conexion
    try {
      //usamos getConnect porque es el contructor del singleton es privado
      conn = ConnectDB.getConnect();
      
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      
      lista = new ArrayList();
      
      Alumno alumno = null;
      while (rs.next()) {
        alumno = new Alumno();
        alumno.setId(rs.getInt("id"));
        alumno.setNombre(rs.getString("nombre"));
        alumno.setApellidos(rs.getString("apellidos"));
        alumno.setDni(rs.getString("dni"));
        alumno.setTelefono(rs.getString("telefono"));
        alumno.setEstado(rs.getString("estado"));
        alumno.setComentarios(rs.getString("comentarios"));        
      } //while
      
      lista.add(alumno);
      
      rs.close();
      stmt.close();
      conn.close(); 
    } catch (SQLException esql) {
      
    }
    return lista;
  }
  
  public void mostrarAlumno(int id) {
    String SQL = "SELECT id, nombre, apellidos dni, telefono, estado, comentarios FROM "+tabla+" WHERE ID = "+id;
    System.out.println("LEO ALUMNO");
  }
  
  //ACTUALIZAR
  public void actualizar(Alumno a) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    
    SQL = "UPDATE FROM "+tabla
         +" SET nombre="+a.getNombre()
         +",apellidos="+a.getApellidos()
         +",dni="+a.getDni()
         +",telefono="+a.getTelefono()
         +",estado="+a.getEstado()
         +",comentarios="+a.getComentarios()
         +" WHERE ID = "+a.getId();
    
    try {
      conn = ConnectDB.getConnect();
      
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException esql) {
      
    }    
    
    System.out.println("ACTUALIZO ALUMNO");    
  }
  
  //ELIMINAR
  public void eliminar(Alumno a) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    
    SQL = "DELETE FROM "+tabla+" WHERE ID = "+a.getId();
    
    //conexion
    try {
      //usamos getConnect porque es el contructor del singleton es privado
      conn = ConnectDB.getConnect();
      
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      
      rs.close();
      stmt.close();
      conn.close();      
    } catch (SQLException esql) {
    
    }
    System.out.println("ELIMINO ALUMNO");
  }
}
