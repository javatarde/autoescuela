/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela;

import conexion.ConnectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        
        SQL="INSERT INTO "+tabla+" (id_alumno,id_permiso,id_tipomatricula) VALUES (?,?,?)";
        
        //st_default=conn.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        //hacer insercion
        PreparedStatement pst=conn.prepareStatement(SQL);
        
        pst.setInt(1, m.getIdAlumno());
        pst.setInt(2, m.getIdPermiso());
        pst.setInt(3, m.getIdTipoMatricula());
     
        // pst.setString(7, m.getMotivoBaja());
        
        int filas_afectadas=pst.executeUpdate();
        System.out.println(filas_afectadas+" fila(s) afectada(s)");
        conn=ConnectDB.closeInstance().getConnect();
        
    }catch(SQLException e){
        System.out.println("Error al crear la matrícula"+e);
    }
}

public void leer (){
    try{
        conn=ConnectDB.getInstance().getConnect();
        SQL="SELECT id,id_alumno,id_permiso,id_tipomatricula,fecha_alta,fecha_baja,motivo_baja FROM "+tabla+" ORDER BY id_alumno";
       
        st_default=conn.createStatement();
                
        rs=st_default.executeQuery(SQL);
        
        System.out.println("***Listado de matrículas***");
        System.out.println("ID  |   ID Alumno   |   ID_PERMISO  | ID Tipo matrícula |  Fecha Alta  |  Fecha Baja  |  Motivo Baja");
        
        while (rs.next()) {
            System.out.println(rs.getInt("ID")+" | "+rs.getInt("ID_ALUMNO")+" | "+ rs.getInt("ID_PERMISO")+" | "+rs.getInt("ID_TIPOMATRICULA")+" | "+rs.getDate("FECHA_ALTA")+" | "+rs.getDate("FECHA_BAJA")+ " | "+rs.getString("MOTIVO_BAJA"));
        }
        
        rs.close();
        st_default.close();
        conn=ConnectDB.closeInstance().getConnect();
        
    }catch(SQLException e){
        System.out.println("Error al "+e);
    }
}
public void eliminar (int IDMatricula){
    try{
        conn=ConnectDB.getInstance().getConnect();
        SQL="DELETE FROM "+tabla+" WHERE ID="+IDMatricula;
        st_default=conn.createStatement();
        rs=st_default.executeQuery(SQL);
        
        System.out.println("La matrícula con el ID "+IDMatricula+" ha sido eliminada");
        
        rs.close();
        st_default.close();
        conn=ConnectDB.getInstance().getConnect();
    }catch(SQLException e){
        System.out.println("Error al "+e);
    }
}

public void actualizar (Matricula m){
    try{
        conn=ConnectDB.getInstance().getConnect();
        SQL="UPDATE "+tabla+" SET "
                +"ID_PERMISO=?"
                +",ID_TIPOMATRICULA=?"
                +",FECHA_ALTA=?"
                +",FECHA_BAJA=?"
                +",MOTIVO_BAJA=?"
                +" WHERE ID=?";
   
        
        
        PreparedStatement pst = conn.prepareStatement(SQL);

        pst.setInt(1, m.getIdPermiso());
        pst.setInt(2, m.getIdTipoMatricula());
        pst.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        pst.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        pst.setString (5, m.getMotivoBaja());
        pst.setInt(6, m.getId());
        
        int filas=pst.executeUpdate();
        
        System.out.println("Datos solicitados actualizados");
        System.out.println("Filas: "+filas);
        rs.close();
        pst.close();
        st_default.close();
        conn=ConnectDB.closeInstance().getConnect();
        
    }catch(SQLException e){
        System.out.println("Error al "+e);
    }
}
/*******************************    
 *  Pruebas de funcionamiento. *
 *******************************/    
    public static void main(String[] args) throws ParseException {
        Matricula matricula = new Matricula();
        DAOAlumno DAOalumno = new DAOAlumno();
        DAOMatricula DAOMatricula=new DAOMatricula();
        DAOPermiso DAOpermiso = new DAOPermiso();
        List <Alumno> listaAlumnos;
        
        String dato;
        Scanner sc = new Scanner(System.in);
        int datonum;    
        
        //**** PRUEBAS DE CREACION (INSERT)****
//        System.out.println("***ALTA DE MATRÍCULA****");
//       
//        listaAlumnos=DAOalumno.leer(); //listamos alumnos
//       
//        for (Alumno alumno : listaAlumnos) {
//            System.out.println(alumno.getId());
//        }
//            
//        System.out.println("ID de Alumno:");
//        datonum= sc.nextInt();
//        matricula.setIdAlumno(datonum);
//        
//        DAOpermiso.leer(); //listamos los permisos
//        System.out.println("ID del Tipo de Permiso:");
//        datonum=sc.nextInt();
//        matricula.setIdPermiso(datonum);
//        
//        System.out.println("ID del Tipo de Matricula (1 a 3)");
//        datonum=sc.nextInt();
//        matricula.setIdTipoMatricula(datonum);
//        
//        DAOMatricula.crear(matricula);
        //FIN PRUEBA INSERT
        
        //*****PRUEBA LEER****
        DAOMatricula.leer();
        //FIN PRUEBA LEER
        
        //*****PRUEBA ELIMINAR****
//        System.out.println("ID de matrícula a eliminar:");
//        datonum=sc.nextInt();
//        
//        DAOMatricula.eliminar(datonum);
        //FIN PRUEBA ELIMINAR
        
        //*****PRUEBA ACTUALIZAR******
         String fechabaja;
        System.out.println("Modificación de matrícula");
        System.out.println("ID a modificar:");
//        matricula.setId(sc.nextInt());
//        System.out.println("Nuevo ID alumno");
        matricula.setIdAlumno(sc.nextInt());
        System.out.println("Nuevo ID permiso");
        matricula.setIdPermiso(sc.nextInt());
        System.out.println("Nuevo ID tipomatricula");
        matricula.setIdTipoMatricula(sc.nextInt());
        
        //NO ME FUNCIONA LA FECHA.
//        System.out.println("¿Fecha de baja? (AAAA-MM-DD)");
       
//        fechabaja = sc.nextLine(); //fecha en String
//        fechabaja = sc.nextLine();
        //para pasar el String a Calendar
//        DateFormat df = new SimpleDateFormat("YYYY-MM-DD"); //indicamos formato
//        Calendar cal  = Calendar.getInstance();
//        cal.setTime(df.parse(fechabaja));
//        matricula.setFechaBaja(cal);//parseamos el resultado
                
        System.out.println("Motivo Baja");
        matricula.setMotivoBaja(sc.nextLine());
        DAOMatricula.actualizar(matricula);
        
      //FIN prueba actualizar
    }

}