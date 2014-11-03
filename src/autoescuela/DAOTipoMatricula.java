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
import java.util.List;

/**
 *
 * @author Administrador
 */
public class DAOTipoMatricula implements GestionCrud<TipoMatricula>{
    private final String tabla="AU_TIPOMATRICULA";
    private Connection conn;
    private String SQL;
    private Statement stmt;
    private ResultSet rs;    
    
    
    
    @Override
    public boolean crear(TipoMatricula tipoMatricula) {
        boolean ok;
        
        try{
            conn=ConnectDB.getInstance().getConnect();
                        
            SQL="INSERT INTO "+tabla+" (valor) VALUES (?)";
            PreparedStatement pst=conn.prepareStatement(SQL);
            
            pst.setString(1, tipoMatricula.getValor());
            stmt=conn.createStatement();
            rs=stmt.executeQuery(SQL);
            
            rs.close();
            pst.close();
            conn=ConnectDB.closeInstance().getConnect();
            ok=true;
        }catch(SQLException e){
            System.out.println("Error al crear Tipo de Matricula: "+ e);
            ok=false;
        }

        return ok;
    }

    @Override
    public boolean actualizar(TipoMatricula tipoMatricula) {
        boolean ok;
        
        try{
            conn=ConnectDB.getInstance().getConnect();
                        
            SQL="UPDATE FROM "+tabla+" SET (?)";
            PreparedStatement pst=conn.prepareStatement(SQL);
            
            pst.setString(1, tipoMatricula.getValor());
            
            rs=stmt.executeQuery(SQL);

            rs.close();
            pst.close();
            conn=ConnectDB.closeInstance().getConnect();            
            
            ok=true;
        }catch (SQLException e){
            System.out.println("Error al actualizar Tipo de Matricula: "+ e);
            ok=false;
        }
        
        return ok;
    }

    @Override
    public List<TipoMatricula> leer(int id) {
       List <TipoMatricula> lista=null;
       TipoMatricula tipoMatricula=null;

       if (id==0){
            SQL="SELECT id, valor FROM "+tabla+" ORDER BY valor";
        }
        else{
            SQL="SELECT id, valor FROM "+tabla+"WHERE id="+id+" ORDER BY valor";        
            }    
        
        try{
            conn=ConnectDB.getInstance().getConnect();
            stmt=conn.createStatement();
            rs=stmt.executeQuery(SQL);

            while(rs.next()){
                tipoMatricula.setId(rs.getInt("ID"));
                tipoMatricula.setValor(rs.getString("Valor"));
                
                lista.add(tipoMatricula);
            }
            
            rs.close();
            stmt.close();
            conn=ConnectDB.closeInstance().getConnect();            
            
        }catch (SQLException e){
            System.out.println("Error al buscar Tipo de Matricula: "+ e);
        }   
        
        return lista;
    }

    @Override
    public List<TipoMatricula> leer(TipoMatricula tipoMatricula) {
        List<TipoMatricula> lista=null;
        TipoMatricula tipoMat=null;
        
        SQL="SELECT id,valor FROM "+tabla+" WHERE valor="+tipoMatricula.getValor();
        
        try{
            conn=ConnectDB.getInstance().getConnect();
            
            stmt=conn.createStatement();
            rs=stmt.executeQuery(SQL);
            
            while (rs.next()){
                tipoMat.setId(rs.getInt("ID"));
                tipoMat.setValor(rs.getString("Valor"));
                
                lista.add(tipoMat);
            }
            
            rs.close();
            stmt.close();
            conn=ConnectDB.closeInstance().getConnect();
        }catch (SQLException e){
            System.out.println("Error al buscar Tipo de Matricula: "+ e);
        }  

        return lista;
    }

    @Override
    public void mostrarTodos() {
        SQL ="SELECT id,valor FROM "+tabla;
  
        try{
            conn=ConnectDB.getInstance().getConnect();
            stmt=conn.createStatement();
            rs=stmt.executeQuery(SQL);
            
            while (rs.next()){
                System.out.println("ID: "+rs.getInt("ID")+" TipoMatricula: "+rs.getString("VALOR"));
            }
            
            rs.close();
            stmt.close();
            conn=ConnectDB.closeInstance().getConnect();
        }catch(SQLException e){
            System.out.println("Error al mostrar los tipos de matrícula: "+e);
        }
    }

    @Override
    public boolean eliminar(int id) {
        boolean ok;
        SQL="DELETE FROM "+tabla+" WHERE id="+id;
        try{
            conn=ConnectDB.getInstance().getConnect();
            stmt=conn.createStatement();
            rs=stmt.executeQuery(SQL);
            
            System.out.println("Tipo de matrícula eliminado correctamente");
            
            rs.close();
            stmt.close();
            conn=ConnectDB.closeInstance().getConnect();
            ok=true;
        }catch(SQLException e){
            System.out.println("Error al eliminar tipo de matrícula: "+e);
            ok=false;
        }
        
        return ok;
    }
}
