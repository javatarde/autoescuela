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
import java.util.Scanner;

/**
 *
 * @author Formacion
 */

//*********EN PRUEBAS*********
public class DAOPermiso {
    private final String tabla = "AU_PERMISO";
    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    Statement st_default = null;

    
    
    public void crear (Permiso p){
        
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
        }
        
    }
    
    public void leer (){
        
        
        try{
            conn = ConnectDB.getInstance().getConnect();
            SQL="select id,valor,descripcion from "+tabla+" order by valor";
        
            st_default=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
            //ejecutamos el statement (consulta)
            rs=st_default.executeQuery(SQL);       
            
            System.out.println("****--Consulta de Permisos de Conducir disponibles--****");
            while (rs.next()){
                System.out.println("ID: "+rs.getInt("id")+" | Permiso: "+rs.getString("valor")+" | Descripción: "+rs.getString("Descripcion"));
            }
            
            rs.close();
            st_default.close();
//            conn.close();
            conn=ConnectDB.closeInstance().getConnect();
            
        }catch(SQLException e){
            System.out.println("Error al consultar los permisos de conducir en la BD: "+e);
        }
        
        
    }
    
    public void eliminar (int IDPermiso){
        try{
            conn = ConnectDB.getInstance().getConnect();
            SQL = "DELETE FROM "+tabla+" WHERE ID = "+IDPermiso;
            st_default=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            rs=st_default.executeQuery(SQL);
            
            System.out.println("El permiso con ID: "+IDPermiso+ "ha sido eliminado");
            
            rs.close();
            st_default.close();
            conn=ConnectDB.closeInstance().getConnect();
            
        }catch(SQLException e){
            System.out.println("Error al eliminar permiso de conducir en la BD: "+e);
        }
    }
    
    public boolean actualizar (Permiso p){
        boolean ok;
        
        try{
            conn = ConnectDB.getInstance().getConnect();
            SQL = "UPDATE "+tabla+" SET VALOR='"+p.getValor()+"',DESCRIPCION='"+p.getDescripcion()+"'where ID="+p.getId();
            
            st_default=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            rs=st_default.executeQuery(SQL);
            
            rs.close();
            st_default.close();
            conn=ConnectDB.closeInstance().getConnect();
            ok=true;
        }catch(SQLException e){
            System.out.println("Error al actualizar permiso de conducir en la BD: "+e);
            ok=false;
        }
        return ok;
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
        //test insercion (Crear)
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
        
        //***TEST CONSULTA***
        DAOPermiso.leer();
        //***fin test consulta***
        
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
//     //   System.out.println("Valor actual--ID: "+permiso.getId()+" --Tipo: "+permiso.getValor()+" --Comentario: "+permiso.getDescripcion());
//        DAOPermiso.actualizar(permiso);
        //***fin test actualizacion***
    }
}
