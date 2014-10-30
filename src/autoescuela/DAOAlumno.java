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
public abstract class DAOAlumno implements GestionCrud {
  private final String tabla = "AU_ALUMNO";
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  String SQL;
  
  //CREAR
  //@Override
  public boolean crear(Alumno a) {
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
      
      Utilidades.showCadena("Se han creado: "+fila_afectadas+" alumno(s).");
      return true;   
    } catch (SQLException esql) {
          Utilidades.showCadena("ERROR al crear alumno en la BD: "+esql.getMessage());
      return false;
    }        
  }
  
  
  //LEER
  @Override
  public List<Alumno> leer() {
    List<Alumno> lista = null;
    
    SQL = "SELECT id, nombre, apellidos, dni, telefono, estado, comentarios FROM "+tabla;
    
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
      Utilidades.showCadena("ERROR al mostrar los alumnos: "+sqle.getMessage());
    }
    return lista;
  }
  
  
  @Override
  public List<Alumno> leer(String nombre, String apellidos) {
    List<Alumno> lista = null;
    
    SQL = "SELECT id, nombre, apellidos, dni, telefono, estado, comentarios"
        + " FROM "+tabla
        + " WHERE nombre = '"+nombre+"' AND apellidos='"+apellidos+"'";
    
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
      Utilidades.showCadena("ERROR al mostrar alumno por nombre: "+sqle.getMessage());
    }
    return lista;  
  }
  
  
  @Override
  public List<Alumno> leer(int id) {
    List<Alumno> lista = null;
    
    //cuando la ID es 0, se muestran todos los alumnos.
    if (id!=0) {
      SQL = "SELECT id, nombre, apellidos, dni, telefono, estado, comentarios"
          + " FROM "+tabla
          + " WHERE id = "+id;
    } else {
      SQL = "SELECT id, nombre, apellidos, dni, telefono, estado, comentarios"
          + " FROM "+tabla;
    }
    
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
      Utilidades.showCadena("ERROR al mostrar alumnos: "+sqle.getMessage());
    }
    return lista;   
  }
  
  //ACTUALIZAR
  //@Override
  public boolean actualizar(Alumno a) {
    
    SQL = "UPDATE "+tabla
         +" SET nombre='"+a.getNombre()
         +"',apellidos='"+a.getApellidos()
         +"',dni='"+a.getDni()
         +"',telefono='"+a.getTelefono()
         +"',estado='"+a.getEstado()
         +"',comentarios='"+a.getComentarios()
         +"' WHERE ID = "+a.getId();
    
    try {
      conn = ConnectDB.getInstance().getConnect();
      
      stmt = conn.createStatement();
      stmt.executeUpdate(SQL);
      
      stmt.close();
      conn = ConnectDB.closeInstance().getConnect();
      
      Utilidades.showCadena("Alumno actualizado correctamente");
      return true;
    } catch (SQLException sqle) {
      Utilidades.showCadena("ERROR al eliminar alumno: "+sqle.getMessage());
      return false;
    }
  }
  
  //ELIMINAR
  @Override
  public boolean eliminar(int id) {
    
    SQL = "DELETE FROM "+tabla+" WHERE ID = "+id;
    //conexion
    try {
      //usamos getConnect porque es el contructor del singleton es privado
      conn = ConnectDB.getInstance().getConnect(); 
      
      stmt = conn.createStatement();      
      stmt.executeUpdate(SQL);
      
      stmt.close();
      conn = ConnectDB.closeInstance().getConnect();
      Utilidades.showCadena("Alumno borrado correctamente");
      return true;
    } catch (SQLException sqle) {
      Utilidades.showCadena("ERROR al eliminar el alumno: "+sqle.getMessage());
      return false;
    }    
  }  
}
