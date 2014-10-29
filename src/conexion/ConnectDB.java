/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Formacion
 */

//CONEXION A LA BASE DE DATOS SIGUIENDO EL PATRON SINGLETON
public class ConnectDB {
    private static Connection conexion;
    private static ConnectDB instancia;
    
   //Constructor
    private ConnectDB(){
        try{
            conexion=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EJEMPLO_JAVA_TARDE", "ejemplo_java_tarde");
            System.out.println("BBDD ONLINE");
        }
        catch(SQLException e){
            System.out.println("Error al conectar: "+ e.getMessage());
        }
        
    }
    
    //método a través del cual se llama al constructor privado para que se cree la conexión.
    public static ConnectDB getInstance(){
        //para comprobar que se hace sólo una vez.
        if (instancia == null){ 
            instancia = new ConnectDB(); //si no hay ninguna instacia de la conexión, se genera una nueva
        }
        return instancia;
    }
    
    //se cierra la conexion
    //********VERSION ORIGINAL***********
//    public static void closeInstance(){
//        try{
//            conexion.close();
//            instancia=null;
//            System.out.println("BBDD OFFLINE");
//        }
//        catch(SQLException e){
//            System.out.println("Error al cerrar conexion: "+e.getMessage());
//        }
//    }
    
    //****PRUEBA que FUNCIONA OK*****
   
    public static ConnectDB closeInstance(){
        try{
            conexion.close();
            instancia=null;
            conexion = null;
            System.out.println("BBDD OFFLINE");
        }
        catch(SQLException e){
            System.out.println("Error al cerrar conexion: "+e.getMessage());
        }
        return instancia;
    }
    
    
    //Getter & Setter
    public static Connection getConnect() {
        return conexion;
    }

    public static void setConnect(Connection conexion) {
        ConnectDB.conexion = conexion;
    }
}
