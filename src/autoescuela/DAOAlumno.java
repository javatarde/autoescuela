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
import java.util.List;

/**
 *
 * @author Formacion
 */
public class DAOAlumno {
  private final String tabla = "AU_ALUMNO";
  //CRUD
  //CREAR
  public boolean crear(Alumno a) {
    Connection conn = null;
    String SQL;
    
    //conexion
    try {
      //usamos getConnect porque es el contructor del singleton es privado
      conn = ConnectDB.getInstance().getConnect();
      
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
      
      conn = ConnectDB.closeInstance().getConnect();
      
      System.out.println("Se han creado: "+fila_afectadas+" alumno(s).");
      return true;   
    } catch (SQLException esql) {
      System.out.println("Error al crear alumno en la BD: "+esql.getMessage());
      return false;
    }        
  }
  
  //LEER
  public List<Alumno> leer() {
    List<Alumno> lista = null;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    
    SQL = "SELECT id, nombre, apellidos, dni, telefono, estado, comentarios FROM "+tabla;
    
    //System.out.println(SQL);
    
    try {
      conn = ConnectDB.getInstance().getConnect();
      
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      
      lista = new ArrayList<>();
      
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
        lista.add(alumno);
      } //while      
      
      rs.close();
      stmt.close();
      ConnectDB.getInstance().closeInstance(); //cerrar
    } catch (SQLException sqle) {
      System.out.println("Error al mostrar alumnos: "+sqle.getMessage());
    }
    return lista;
  }
  
  public List<Alumno> leer(String nombre, String apellidos) {
    List<Alumno> lista = null;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    
    SQL = "SELECT id, nombre, apellidos, dni, telefono, estado, comentarios"
        + " FROM "+tabla
        + " WHERE nombre = '"+nombre+"' AND apellidos='"+apellidos+"'";
    
    //System.out.println(SQL);
    
    try {
      conn = ConnectDB.getInstance().getConnect();
      
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      
      lista = new ArrayList<>();
      
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
        lista.add(alumno);
      } //while      
      
      rs.close();
      stmt.close();
      ConnectDB.getInstance().closeInstance(); //cerrar
    } catch (SQLException sqle) {
      System.out.println("Error al mostrar alumno por nombre: "+sqle.getMessage());
    }
    return lista;  
  }
  
  public List<Alumno> leer(int id) {
    List<Alumno> lista = null;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    
    //cuando la ID es 0, se muestran todos los alumnos.
    if (id!=0) {
      SQL = "SELECT id, nombre, apellidos, dni, telefono, estado, comentarios"
          + " FROM "+tabla
          + " WHERE id = "+id;
    } else {
      SQL = "SELECT id, nombre, apellidos, dni, telefono, estado, comentarios"
          + " FROM "+tabla;
    }
    
    //System.out.println(SQL);
    
    try {
      conn = ConnectDB.getInstance().getConnect();
      
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      
      lista = new ArrayList<>();
      
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
        lista.add(alumno);
      } //while      
      
      rs.close();
      stmt.close();
      conn = ConnectDB.closeInstance().getConnect();
    } catch (SQLException sqle) {
      System.out.println("Error al mostrar alumnos: "+sqle.getMessage());
    }
    return lista;   
  }
  
  //ACTUALIZAR
  public boolean actualizar(Alumno a) {
    Connection conn = null;
    Statement stmt = null;
    String SQL;
    
    SQL = "UPDATE "+tabla
         +" SET nombre='"+a.getNombre()
         +"',apellidos='"+a.getApellidos()
         +"',dni='"+a.getDni()
         +"',telefono='"+a.getTelefono()
         +"',estado='"+a.getEstado()
         +"',comentarios='"+a.getComentarios()
         +"' WHERE ID = "+a.getId();
    
    //System.out.println(SQL);
    
    try {
      conn = ConnectDB.getInstance().getConnect();
      
      stmt = conn.createStatement();
      stmt.executeUpdate(SQL);
      
      stmt.close();
      conn = ConnectDB.closeInstance().getConnect();
      
      System.out.println("Alumno actualizado");
      return true;
    } catch (SQLException sqle) {
      System.out.println("Error al eliminar alumno: "+sqle.getMessage());
      return false;
    }
  }
  
  //ELIMINAR
  public boolean eliminar(int id) {
    Connection conn = null;
    Statement stmt = null;
    String SQL;
    
    SQL = "DELETE FROM "+tabla+" WHERE ID = "+id;
    
    //System.out.println(SQL);
    
    //conexion
    try {
      //usamos getConnect porque es el contructor del singleton es privado
      conn = ConnectDB.getInstance().getConnect();      
      stmt = conn.createStatement();
      
      stmt.executeUpdate(SQL);
      
      stmt.close();
      conn = ConnectDB.closeInstance().getConnect();
      System.out.println("Alumno borrado.");
      return true;
    } catch (SQLException sqle) {
      System.out.println("Error al eliminar alumno: "+sqle.getMessage());
      return false;
    }    
  }
}
