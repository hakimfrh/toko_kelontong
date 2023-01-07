/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toko.kelontong;

/**
 *
 * @author SYNSGATS
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi {
    private static Connection mysqlconfig;
    public static Connection configDB() throws SQLException{
        try{
            String url="jdbc:mysql://localhost:3306/toko_kelontong";// url database
            String user="root";//nama user database
            String pass="";//password database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig=DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            //System.err.println("Koneksi Gagal"+e.getMessage());
            JOptionPane.showMessageDialog(null, "koneksi gagal");
        }
        return mysqlconfig;
    }
}
