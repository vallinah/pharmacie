/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Otisoa Vallinah
 */
public class Connexion {
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/pharmacie", "postgres", "vallinah");
        
            // TODO: handle exception
        }catch (ClassNotFoundException | SQLException e) {
            // TODO: handle exception
            throw e;
        }
    }
}
