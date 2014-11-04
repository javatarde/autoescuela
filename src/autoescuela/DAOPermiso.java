/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela;

import conexion.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Formacion
 */

public class DAOPermiso implements GestionCrud<Permiso>{
    private final String tabla = "AU_PERMISO";
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String SQL;
    private Statement st_default = null;
    
    
    //CREAR
    @Override
    public boolean crear (Permiso p){
        boolean ok = true;
        try{
            //conexion a la BD, con getInstance hacemos la conexión y 
            //con getConnect() ganamos acceso a los métodos de Connection al devolver un objeto Connection
            conn = ConnectDB.getInstance().getConnect(); 

            //Statement y cursor.
            SQL="select id,valor,descripcion from "+tabla;
            st_default=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
            //ejecutamos el statement (consulta)
            rs=st_default.executeQuery(SQL);        
            
            conn.prepareStatement(SQL); 
            //nos posicionamos en la linea de insercion
            rs.moveToInsertRow();
            //introducimos los valores
            rs.updateString("valor",p.getValor());
            rs.updateString("descripcion", p.getDescripcion());
            //ejecutamos la insercion
            rs.insertRow();
            //nos posicionamos en la línea actual
            rs.moveToCurrentRow();
           
            //cerramos resultset, statement y conexion.
            rs.close();
            st_default.close();
            conn=ConnectDB.closeInstance().getConnect();
        
        }catch (SQLException e){
            System.out.println("Error al crear permiso de conducir en la BD: "+e);
            ok = false;
        }
        return ok;
    }
    
    
    // LEER
    @Override
    public List<Permiso> leer (int IDPermiso){
        List<Permiso> lista = null;
        try{
            conn = ConnectDB.getInstance().getConnect();
            SQL="select id,valor,descripcion from "+tabla+" WHERE ID ="+IDPermiso+" order by valor";
            st_default=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
            //ejecutamos el statement (consulta)
            rs=st_default.executeQuery(SQL);   
            
            lista = new ArrayList<>();
            Permiso permiso = null;
            
            if (rs.next()){
              permiso = new Permiso();
              permiso.setId(rs.getInt("id"));
              permiso.setValor(rs.getString("valor"));
              permiso.setDescripcion(rs.getString("Descripcion"));
              lista.add(permiso);
            }
            
            rs.close();
            st_default.close();
            conn=ConnectDB.closeInstance().getConnect();
            
        }catch(SQLException e){
            System.out.println("Error al consultar los permisos de conducir en la BD: "+e);
        }
        return lista;
    }
    
    
    @Override
    public List<Permiso> leer(Permiso p) {
        List<Permiso> lista = null;
        
        try{
            if (p == null){ // Buscar todos los permisos
                SQL="select id,valor,descripcion from "+tabla+" order by valor";
            }else{ // Buscar por id
               SQL="select id,valor,descripcion from "+tabla+" WHERE valor="+p.getValor()+" order by valor";
            }
            conn=ConnectDB.getInstance().getConnect();
//            st_default=conn.createStatement();
            st_default=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
            rs=st_default.executeQuery(SQL);
            
            Permiso permiso;
            lista = new ArrayList<>();
            
            while(rs.next()){
                permiso = new Permiso();
                permiso.setId(rs.getInt("id"));
                permiso.setValor(rs.getString("valor"));
                permiso.setDescripcion(rs.getString("Descripcion"));
                lista.add(permiso);
            }
            
            rs.close();
            st_default.close();
            conn=ConnectDB.closeInstance().getConnect();
            
        }catch(SQLException e){
            System.out.println("Error al leer los permisos de conducir en la BD: "+e);
        }
        
        return lista;
    }
    
    
    //ACTUALIZAR
    @Override
    public boolean actualizar (Permiso p){
        boolean ok = true;
        try{
            conn = ConnectDB.getInstance().getConnect();
            SQL = "UPDATE "+tabla+" SET VALOR='"+p.getValor()+
                  "',DESCRIPCION='"+p.getDescripcion()+"'where ID="+p.getId();
            st_default=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            st_default.executeUpdate(SQL);
            
            st_default.close();
            conn=ConnectDB.closeInstance().getConnect();
        }catch(SQLException e){
            System.out.println("Error al actualizar permiso de conducir en la BD: "+e);
            ok=false;
        }
        return ok;
    }    
    
    
    //ELIMINAR
    @Override
    public boolean eliminar (int IDPermiso){
        boolean ok = true;
        try{
            conn = ConnectDB.getInstance().getConnect();
            SQL = "DELETE FROM "+tabla+" WHERE ID = "+IDPermiso;
            st_default=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            rs=st_default.executeQuery(SQL);
          
            System.out.println("El permiso con ID "+IDPermiso+ " ha sido eliminado");
            
            rs.close();
            st_default.close();
            conn=ConnectDB.closeInstance().getConnect();
        }catch(SQLException e){
            System.out.println("Error al eliminar permiso de conducir en la BD: "+e);
            ok=false;
        }
        return ok;
    }
    
    @Override
    public boolean eliminar(Permiso p) {
      if (p != null){
        int id = p.getId();
        return eliminar(id);
      }else{
          return false;
      }
    }
    
    
    //VALIDAR
    @Override
    public boolean validar(Permiso p) {
        return p.validar();
    }

/*******************************    
 *  Pruebas de funcionamiento. *
 *******************************/    
    
    public static void main(String[] args) {
        Permiso permiso = new Permiso();
        DAOPermiso DAOPermiso=new DAOPermiso();
        String valor, descripcionPermiso;
        Scanner sc = new Scanner(System.in);
        int valornum;
    
        //***TEST CONSULTA***/
        DAOPermiso.leer(null);
        //***fin test consulta***/
        
    //**TEST INSERCION (Crear)
//        System.out.println("Tipo Permiso:");
//        valor= sc.nextLine();
//        System.out.println("Descripcion Permiso:");
//        descripcionPermiso=sc.nextLine();
//                
//        permiso.setValor(valor);
//        permiso.setDescripcion(descripcionPermiso);
//        
//        DAOPermiso.crear(permiso);
        //fin test insercion
        
        
        //***TEST ELIMINACION***
//        System.out.println("ID de permiso a eliminar: ");
//        valornum=sc.nextInt();
//        DAOPermiso.eliminar(valornum);
        //***fin test eliminacion***
        
        //***TEST ACTUALIZACION***
//        System.out.println("Introducir datos de actualización");
//        //introduce id
//        System.out.println("ID del elemento a actualizar: ");
//        permiso.setId(valornum=sc.nextInt());
//        //introduce nuevo tipo
//        System.out.println("Nuevo tipo permiso:");
//        valor=sc.nextLine();
//                valor=sc.nextLine();
//        permiso.setValor(valor);
//        //introudce nueva descripcion
//        System.out.println("Nueva descripcion:");
//        valor=sc.nextLine();
//        permiso.setDescripcion(valor);
//        
//        DAOPermiso.actualizar(permiso);
        //***fin test actualizacion***
    }

}
