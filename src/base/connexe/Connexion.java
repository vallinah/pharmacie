package base.connexe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connexion {
    
    private Connection connexe = null;
    private String userName = "mihary";
    private String passwd = "mihary";
    private String url = "jdbc:postgresql://localhost:5432/";
    private Statement stmt;

    // ? Constructeur

    public Connexion(String dbName) {
        url += dbName;
        try {
            Class.forName("org.postgresql.Driver");
            connexe = DriverManager.getConnection(url, userName, passwd);
            stmt = connexe.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tsy hita trace");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // $ Fonction tsotra

    public void finaleClose() throws Exception {
        stmt.close();
        connexe.close();
    }

    // ! Getter

    public Connection getConnexe() {
        return connexe;
    }

    public Statement getStmt() {
        return stmt;
    }    
}