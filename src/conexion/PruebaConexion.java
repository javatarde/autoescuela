/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Formacion
 */
public class PruebaConexion {
    
    
    public static void main(String[] args) {
        Connection con;
        
        con=ConnectDB.getInstance().getConnect();
        
       
    }
}
