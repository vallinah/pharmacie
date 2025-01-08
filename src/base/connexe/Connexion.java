package base.connexe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connexion {

    private Connection connexe = null;
    private String userName = "postgres";
    private String passwd = "vallinah";
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
            System.out.println("Aucun Driver trouvee");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // $ Fonction tsotra

    public void finaleClose() throws Exception {
        if (stmt != null)
            stmt.close();
        if (connexe != null)
            connexe.close();
    }

    public int incrementSequence(String sequenceName) throws Exception {
        String req = "select nextval(\'" + sequenceName + "\')";
        ResultSet set = null;
        try {
            set = stmt.executeQuery(req);
            if (set.next()) {
                return set.getInt(1);
            }
            throw new Exception("Il y des problemes sur votre sequence");
        } catch (Exception e) {
            throw e;
        } finally {
            if (set != null)
                set.close();
        }
    }

    // ! Getter

    public Connection getConnexe() {
        return connexe;
    }

    public Statement getStmt() {
        return stmt;
    }
}