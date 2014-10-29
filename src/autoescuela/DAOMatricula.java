/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela;

import conexion.ConnectDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Formacion
 */
public class DAOMatricula {
    private final String tabla = "AU_MATRICULA";
    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    Statement st_default = null;


public void crear (Matricula m){
    try{
        conn=ConnectDB.getInstance().getConnect();
        
        SQL="INSERT INTO "+tabla+" VALUES (null,?,?,?,null,null,?)";
        
        //st_default=conn.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        //hacer insercion
        PreparedStatement pst=conn.prepareStatement(SQL);
        
        pst.setInt(2, m.getIdAlumno());
        pst.setInt(3, m.getIdPermiso());
        pst.setInt(4, m.getIdTipoMatricula());
        pst.setString(7, m.getMotivoBaja());
        
        int filas_afectadas=pst.executeUpdate();
        
    }catch(SQLException e){
        System.out.println("Error al crear la matrícula"+e);
    }
}

public void leer (Matricula m){
    try{
        conn=ConnectDB.getInstance().getConnect();
        SQL="";
        st_default=conn.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        rs=st_default.executeQuery(SQL);
        //hacer select
    }catch(SQLException e){
        System.out.println("Error al "+e);
    }
}
public void eliminar (Matricula m){
    try{
        conn=ConnectDB.getInstance().getConnect();
        SQL="";
        st_default=conn.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        rs=st_default.executeQuery(SQL);
        //hacer delete
    }catch(SQLException e){
        System.out.println("Error al "+e);
    }
}

public void actualizar (Matricula m){
    try{
        conn=ConnectDB.getInstance().getConnect();
        SQL="";
        st_default=conn.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        rs=st_default.executeQuery(SQL);
        //hacer update
    }catch(SQLException e){
        System.out.println("Error al "+e);
    }
}
/*******************************    
 *  Pruebas de funcionamiento. *
 *******************************/    
    public static void main(String[] args) {
        Matricula matricula = new Matricula();
        DAOAlumno DAOalumno = new DAOAlumno();
        DAOMatricula DAOMatricula=new DAOMatricula();
        DAOPermiso DAOpermiso = new DAOPermiso();
        List <Alumno> listaAlumnos;
        
        String dato;
        Scanner sc = new Scanner(System.in);
        int datonum;    
        
        //**** PRUEBAS DE CREACION (INSERT)****
        System.out.println("***ALTA DE MATRÍCULA****");
       
        listaAlumnos=DAOalumno.leer(); //listamos alumnos
       
        for (Alumno alumno : listaAlumnos) {
            System.out.println(alumno.getId());
        }
            
        
        
        System.out.println("ID de Alumno:");
        datonum= sc.nextInt();
        matricula.setIdAlumno(datonum);
        
        DAOpermiso.leer(); //listamos los permisos
        System.out.println("ID del Tipo de Permiso:");
        datonum=sc.nextInt();
        matricula.setIdPermiso(datonum);
        
        System.out.println("ID del Tipo de Matricula (1 a 3)");
        datonum=sc.nextInt();
        matricula.setIdTipoMatricula(datonum);
        
        
//        descripcionPermiso=sc.nextLine();
//                
//        permiso.setValor(valor);
//        permiso.setDescripcion(descripcionPermiso);
//        
//        DAOPermiso.crear(permiso);        
        
        
    }

}