/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SAMUEL
 */
public class Conexion {

    private Connection conexion;

    public Conexion() throws SQLException {
        crearConexion();
    }

    private void crearConexion() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tienda_productos", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public Connection getConexion() {
        return conexion;
    }
    
    public static void main(String[] args) throws SQLException {
        new Conexion();
    }
}