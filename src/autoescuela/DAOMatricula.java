/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescuela;

import conexion.ConnectDB;
import gestion_fechas.GestorFechas;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;

import java.util.Scanner;

/**
 *
 * @author Formacion
 */
public class DAOMatricula implements GestionCrud<Matricula>{
    private final String tabla = "AU_MATRICULA";
    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    Statement st_default = null;


    @Override
    public boolean crear (Matricula m){
        boolean ok;
        try{
        conn=ConnectDB.getInstance().getConnect();
        
        SQL="INSERT INTO "+tabla+" (id_alumno,id_permiso,id_tipomatricula) VALUES (?,?,?)";
        
        //hacer insercion
        PreparedStatement pst=conn.prepareStatement(SQL);
        
        pst.setInt(1, m.getIdAlumno());
        pst.setInt(2, m.getIdPermiso());
        pst.setInt(3, m.getIdTipoMatricula());
    
        
        int filas_afectadas=pst.executeUpdate();
        System.out.println(filas_afectadas+" fila(s) afectada(s)");
        conn=ConnectDB.closeInstance().getConnect();
        
        ok=true;
    }catch(SQLException e){
        System.out.println("Error al crear la matrícula"+e);
        ok=false;
    }
        return ok;
}

    @Override
    public void mostrarTodos (){
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
        
    @Override
    public List<Matricula> leer(int id) {
        List<Matricula> lista =null;
        
    //cuando la ID es 0, se muestran todos los alumnos.
        if (id!=0) {
          SQL = "SELECT id, id_alumno, id_permiso, id_tipomatricula, fecha_alta, fecha_baja, motivo_baja"
              + " FROM "+tabla
              + " WHERE id = "+id;
        } else {
          SQL = "SELECT id, id_alumno, id_permiso, id_tipomatricula, fecha_alta, fecha_baja, motivo_baja"
              + " FROM "+tabla;
        }
        
        try {
            conn = ConnectDB.getInstance().getConnect();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);

            lista = new ArrayList<>();
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
        }catch(SQLException e){
          System.out.println("Error al hacer la búsqueda "+e);
        }

        return lista;
    }

    @Override
    public List<Matricula> leer(Matricula m) {
       List <Matricula> lista=null;
       
       SQL = "select id, id_alumno, id_permiso, id_tipomatricula, fecha_alta, fecha_baja, motivo_baja"
           + " FROM "+tabla
           + " WHERE id_alumno = "+ m.getIdAlumno()+"id_tipomatricula="+m.getIdTipoMatricula()+"id_permiso="+m.getIdPermiso();
       
       try {
      conn = ConnectDB.getInstance().getConnect();
      
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      
      lista = new ArrayList<>();
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
        }catch(SQLException e){
        System.out.println("Error al hacer la búsqueda "+e);
        }
       return lista;
    }
        
    @Override
    public boolean eliminar (int IDMatricula){
        boolean ok;
        try{
        conn=ConnectDB.getInstance().getConnect();
        SQL="DELETE FROM "+tabla+" WHERE ID="+IDMatricula;
        st_default=conn.createStatement();
        rs=st_default.executeQuery(SQL);
        
        System.out.println("La matrícula con el ID "+IDMatricula+" ha sido eliminada");
        
        rs.close();
        st_default.close();
        conn=ConnectDB.getInstance().getConnect();
        ok=true;
    }catch(SQLException e){
        System.out.println("Error al "+e);
        ok=false;
    }
        return ok;
}

    @Override
    public boolean actualizar (Matricula m){
    boolean ok;
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
        
//        GestorFechas gestorFechas = new GestorFechas();
//        String fechaCadenaA;
//        String fechaCadenaB;
//        java.sql.Date fechaDate;

        pst.setInt(1, m.getIdPermiso());
        pst.setInt(2, m.getIdTipoMatricula());
        pst.setDate(3, m.getFechaAlta());
        pst.setDate(4, m.getFechaBaja());
        pst.setString (5, m.getMotivoBaja());
        pst.setInt(6, m.getId());
        
        int filas=pst.executeUpdate();
        
        System.out.println("Datos solicitados actualizados");
        System.out.println("Filas: "+filas);
        rs.close();
        pst.close();
        st_default.close();
        conn=ConnectDB.closeInstance().getConnect();
        ok=true;
    }catch(SQLException e){
        System.out.println("Error al "+e);
        ok=false;
    }
    return ok;
}
/*******************************    
 *  Pruebas de funcionamiento. *
 *******************************/    
    public static void main(String[] args) throws ParseException {
        Matricula matricula = new Matricula();
//        DAOAlumno DAOalumno = new DAOAlumno() {};
        DAOMatricula DAOMatricula=new DAOMatricula() {};
//        DAOPermiso DAOpermiso = new DAOPermiso();
//        List <Alumno> listaAlumnos;
        
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
        DAOMatricula.mostrarTodos();
        //FIN PRUEBA LEER
        
        //*****PRUEBA ELIMINAR****
//        System.out.println("ID de matrícula a eliminar:");
//        datonum=sc.nextInt();
//        
//        DAOMatricula.eliminar(datonum);
        //FIN PRUEBA ELIMINAR
        
        //*****PRUEBA ACTUALIZAR******
//        String fechaCadena;
//        System.out.println("Modificación de matrícula");
//        System.out.println("ID a modificar:");
//        GestorFechas fechaGestor = new GestorFechas();
//        matricula.setId(sc.nextInt());
////        EL ID DE ALUMNO NO SE PODRÁ CAMBIAR
//        //System.out.println("Nuevo ID alumno");
////        matricula.setIdAlumno(sc.nextInt());
//        System.out.println("Nuevo ID permiso");
//        matricula.setIdPermiso(sc.nextInt());
//        
//        System.out.println("Nuevo ID tipomatricula");
//        matricula.setIdTipoMatricula(sc.nextInt());
//        
//        System.out.println("Fecha de alta: ");
//        fechaCadena = sc.nextLine();
//        fechaCadena = sc.nextLine();
//        matricula.setFechaAlta(fechaGestor.deStringToDateSQL(fechaCadena));
//        System.out.println("¿Fecha de baja? ");
//        fechaCadena = sc.nextLine(); //fecha en String
//        matricula.setFechaBaja(fechaGestor.deStringToDateSQL(fechaCadena));
//        
//        System.out.println("Motivo Baja");
//        matricula.setMotivoBaja(sc.nextLine());
//        DAOMatricula.actualizar(matricula);
        
      //FIN prueba actualizar
    }



}