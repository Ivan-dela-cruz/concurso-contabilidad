package conexion;

import java.sql.*;

public class Conexion {

    public Connection sql;
    /*
     public static String connectString = "jdbc:mysql://remotemysql.com:3306/0DRQrUiprw"; // llamamos nuestra bd
    public static String user = "0DRQrUiprw"; // usuario mysql
    public static String password = "NS5BOUo2U2"; // no tiene password nuestra bd.
    */
    
    public static String connectString = "jdbc:mysql://localhost:3306/sistema_encuestas"; // llamamos nuestra bd
    public static String user = "root"; // usuario mysql
    public static String password = ""; // no tiene password nuestra bd.
    public static Statement guardar;

    public Conexion() {
        sql = Conexion.conectar();
    }

    public static Connection conectar() {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(connectString, user, password);
            System.out.println("Conexion Exitosa!");
            return c;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Conexion Fallida!");
            return c;
        }
    }
    ///////////////Insertar///////////////////////////

    public void insertar(String sentencia) {
        try {
            guardar = sql.createStatement();
            int resul = guardar.executeUpdate(sentencia);
            if (resul == 1) {
                System.out.println("Informaciòn guardada con Exito!");
            } else {
                System.out.println("Informaciòn  no guardada Error!");
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    ///////////////////Eliminar/////////////////////////////

    public void eliminar(String sentencia) {
        try {
            guardar = sql.createStatement();
            int resul = guardar.executeUpdate(sentencia);
            if (resul == 1) {
                System.out.println("Informaciòn eliminada con Exito!");
            } else {
                System.out.println("Informaciòn  no eliminada Error!");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    ////////////////////Editar////////////////////////////////

    public void editar(String sentencia) {
        try {
            guardar = sql.createStatement();
            int resul = guardar.executeUpdate(sentencia);
            if (resul == 1) {
                System.out.println("Informaciòn actualizo con Exito!");
            } else {
                System.out.println("Informaciòn  no actualizo Error!");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
