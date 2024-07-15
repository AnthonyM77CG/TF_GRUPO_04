package conexion;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConexionBd{
    private static Connection con;
    
    public static Connection establecerConexion(){
       
        String host = "localhost";
        String puerto = "3306";
        String database = "panaderia";
        String usuario = "root";
        String contraseña = "";
        String driver = "com.mysql.cj.jdbc.Driver";
        
        String url = "jdbc:mysql://"+host+":"+puerto+"/"+database+"?useSSL=false";
 
        try{
         Class.forName(driver);
         con = DriverManager.getConnection(url,usuario,contraseña);
        }catch(Exception e){
            System.out.println("NO CONEXION: ERROR "+e.toString());
        }
        return con;
    }
}