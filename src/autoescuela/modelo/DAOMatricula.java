/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Scanner;

/**
 * 
 * @author Formacion
 * 
 * Clase para gestionar CRUD en del tipo Matricula
 */
public class DAOMatricula implements GestionCrud<Matricula>{
    private final String tabla = "AU_MATRICULA";
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String SQL;
    private Statement st_default = null;

   /**
    * Crea <b>(INSERT INTO)</b> un nuevo elemento Matrícula en la base de datos 
    * devuelve <tt>true</tt> si se ha podido realizar la operacion en la <b>tabla AU_MATRICULA</b>,
    * la <tt>fecha de alta</tt> se genera automáticamente en la tabla con el valor del día de actual.
    * @param m del <i>tipo Matricula</i> para insertar en la <b>tabla AU_MATRICULA</b>
    * @return booleano que indica si se ha podido efectuar la inserción (<b>true</b>) o no se ha podido (<b>false</b>)
    * 
    * Devuelve excepción si no se ha podido realizar la inserción.
    */
    @Override
    public boolean crear (Matricula m){
        try{
        conn=ConnectDB.getInstance().getConnect();
        
        SQL="INSERT INTO "+tabla+" (id_alumno,id_permiso,id_tipomatricula) VALUES (?,?,?)";
        
        //hacer insercion
        PreparedStatement pst=conn.prepareStatement(SQL);
        
        
        pst.setInt(1, m.getIdAlumno());
        pst.setInt(2, m.getIdPermiso());
        pst.setInt(3, m.getIdTipoMatricula());
    
        
        int filas_afectadas=pst.executeUpdate();
        rs.close();
        conn=ConnectDB.closeInstance().getConnect();
        pst.close();
        return true;
    }catch(SQLException e){
//        System.out.println("Error al crear la matrícula"+e);
        return false;
    }
}
    

     /**
      * <p>Lee <b>(SELECT)</b> la fila a la que hace referencia el parámetro recibido, 
      * en caso de que el parámetro sea <tt>0</tt> se muestran todas las filas y devuelve
      * una lista de elementos del tipo Matricula.
      * 
      * @param id entero que hace referencia al campo <tt>id</tt> de la <b>tabla AU_MATRICULA</b>, si vale 0 se devolverán todas las lineas.
      * @return Lista de elementos del tipo Matricula.
      */   
    @Override
    public List<Matricula> leer(int id) {
        List<Matricula> lista =new ArrayList<>();
        
        //cuando la ID es 0, se muestran todos los alumnos.
        if (id!=0) {
          SQL = "SELECT id, id_alumno, id_permiso, id_tipomatricula, fecha_alta, fecha_baja, motivo_baja"
              + " FROM "+tabla
              + " WHERE id = "+id;
        } else {
          SQL = "SELECT id, id_alumno, id_permiso, id_tipomatricula, fecha_alta, fecha_baja, motivo_baja"
              + " FROM "+tabla
              + " ORDER BY id_alumno";
        }
        
        try {
            conn = ConnectDB.getInstance().getConnect();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);

            Matricula matricula = null;

            while (rs.next()) {
                matricula = new Matricula();
                matricula.setId(rs.getInt("ID"));
                matricula.setIdAlumno(rs.getInt("ID_Alumno"));
                matricula.setIdPermiso(rs.getInt("ID_Permiso"));
                matricula.setIdTipoMatricula(rs.getInt("ID_TipoMatricula"));
                matricula.setFechaAlta(rs.getDate("Fecha_Alta"));
                matricula.setFechaBaja(rs.getDate("Fecha_Baja"));
                matricula.setMotivoBaja(rs.getString("Motivo_Baja"));
                lista.add(matricula);
            }
            rs.close();
            stmt.close();
            conn=ConnectDB.closeInstance().getConnect();
            return lista;
        }catch(SQLException e){
//          System.out.println("Error al hacer la búsqueda "+e);
            return lista;
        }
    }

    /**
     * <p>Lee <b>(SELECT)</b> la fila a la que hace referencia los campos <tt>id_alumno, id_tipomatricula, id_permiso</tt> del parámetro recibido, 
     * del tipo Matricula y devuelve una lista de elementos del tipo Matricula; devuelve excepción en caso de que no se pueda realizar la consulta.
     * 
     * @param m
     * @return 
     */
    @Override
    public List<Matricula> leer(Matricula m) {
       List <Matricula> lista=new ArrayList<>();
       
       if (m == null){ //cuando la ID es null, se muestran todas las matricullas.
            SQL= "SELECT id,id_alumno,id_permiso,id_tipomatricula,fecha_alta,fecha_baja,motivo_baja"
               + " FROM "+tabla
               + " ORDER BY id_alumno";
       }else{ //buscar por idAlumno, idTipoMatricula y idPermiso
            SQL= "select id, id_alumno, id_permiso, id_tipomatricula, fecha_alta, fecha_baja, motivo_baja"
               + " FROM "+tabla
               + " WHERE id_alumno="+ m.getIdAlumno()
               + " AND id_tipomatricula="+m.getIdTipoMatricula()+" AND id_permiso="+m.getIdPermiso();
       }
       
       try {
      
      conn = ConnectDB.getInstance().getConnect();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      
      Matricula matricula = null;
      
      while (rs.next()) {
          matricula=new Matricula();
          matricula.setId(rs.getInt("ID"));
          matricula.setIdAlumno(rs.getInt("ID_Alumno"));
          matricula.setIdPermiso(rs.getInt("ID_Permiso"));
          matricula.setIdTipoMatricula(rs.getInt("ID_TipoMatricula"));
          matricula.setFechaAlta(rs.getDate("Fecha_Alta"));
          matricula.setFechaBaja(rs.getDate("Fecha_Baja"));
          matricula.setMotivoBaja(rs.getString("Motivo_Baja"));
          lista.add(matricula);
      }
      rs.close();
      stmt.close();
      conn=ConnectDB.closeInstance().getConnect();
      return lista;
        }catch(SQLException e){
//           System.out.println("Error al hacer la búsqueda "+e);
           return lista;
        }
    }
        
    @Override
    public boolean eliminar (int IDMatricula){
        boolean ok;
        try{
        conn=ConnectDB.getInstance().getConnect();
        SQL="DELETE FROM "+tabla+" WHERE ID="+IDMatricula;
        st_default=conn.createStatement();
        st_default.executeUpdate(SQL);
        
//        System.out.println("La matrícula con el ID "+IDMatricula+" ha sido eliminada");
        
        st_default.close();
        conn=ConnectDB.getInstance().getConnect();
        return true;
    }catch(SQLException e){
//        System.out.println("Error al eliminar"+e);
        return false;
    }
    }

    
    @Override
    public boolean eliminar(Matricula m){
        if (m != null){
          int id = m.getId();
          return eliminar(id);
        }else{
            return false;
        }
    }
    
    
    @Override
    public boolean actualizar (Matricula m){
    try{
        conn=ConnectDB.getInstance().getConnect();
        SQL="UPDATE "+tabla+" SET "
                +"ID_PERMISO=?"
                +",ID_TIPOMATRICULA=?"
                +",FECHA_ALTA=?"
                +",FECHA_BAJA=?"
                +",MOTIVO_BAJA=?"
                +",ID_ALUMNO=?"
                +" WHERE ID=?";
        
        PreparedStatement pst = conn.prepareStatement(SQL);

        pst.setInt(1, m.getIdPermiso());
        pst.setInt(2, m.getIdTipoMatricula());
        pst.setDate(3, m.getFechaAlta());
        pst.setDate(4, m.getFechaBaja());
        pst.setString (5, m.getMotivoBaja());
        pst.setInt(6, m.getIdAlumno());
        pst.setInt(7, m.getId());

        
        int filas=pst.executeUpdate();
//        System.out.println("Datos solicitados actualizados");
//        System.out.println("Filas afectadas: "+filas);
        
        pst.close();
        conn=ConnectDB.closeInstance().getConnect();
        return true;
    }catch(SQLException e){
//        System.out.println("Error al actualizar"+e);
        return false;
    }
    }
    
    
    @Override
    public boolean validar(Matricula m) {
      // Comprobar si la matricula tiene relleno todos los campos obligatorios
      return m.validar();
    }

}