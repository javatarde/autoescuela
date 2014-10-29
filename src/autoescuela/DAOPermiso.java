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
            SQL="select id,valor,descripcion from AU_PERMISO";
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
            conn.close();
        
        }catch (SQLException e){
            System.out.println("Error al crear permiso de conducir en la BD: "+e);
        }
        
    }
    
    public void leer (){
        conn = ConnectDB.getInstance().getConnect();
        
        try{
        SQL="select id,valor,descripcion from AU_PERMISO order by valor";
        
        st_default=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
            //ejecutamos el statement (consulta)
            rs=st_default.executeQuery(SQL);       
            
            System.out.println("****--Consulta de Permisos de Conducir disponibles--****");
            while (rs.next()){
                System.out.println("Permiso: "+rs.getString("valor")+" | Descripción: "+rs.getString("Descripcion"));
            }
            
        }catch(SQLException e){
            System.out.println("Error al consultar los permisos de conducir en la BD: "+e);
        }
        
        
    }
    
    //Prueba de funcionamiento.
    public static void main(String[] args) {
        Permiso permiso = new Permiso();
        DAOPermiso DAOPermiso=new DAOPermiso();
        String valor, descripcionPermiso;
        Scanner sc = new Scanner(System.in);
       
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
        
        //test consulta
        DAOPermiso.leer();
        //fin test consulta
    }
}
