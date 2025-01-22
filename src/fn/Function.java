package fn;

import base.*;
import base.connexe.Connexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Function {
    
    public static Connexion dbConnect() throws Exception {
        return new Connexion("pharmacie");
    }

    public static String getDataByID(String table, String columnGet, String columnSelect, String id) throws Exception {
        String req = "select " + columnGet + " from " + table + " where " + columnSelect + " = ?";
        String value = "";

        Connexion connexion = Function.dbConnect();
        Connection connection = null;
        PreparedStatement prp = null;
        ResultSet set = null;

        try {
            connection = connexion.getConnexe();
            prp = connection.prepareStatement(req);
            prp.setString(1, id);
            set = prp.executeQuery();

            if (set.next()) {
                value = set.getString(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (set != null)
                set.close();
            if (prp != null)
                prp.close();
            if (connection != null) {
                connection.close();
            }
        }
        return value;
    }

    public static String giveJson(String cls, String mess) {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        obj.addProperty(cls, mess);
        return gson.toJson(obj);
    }

    public static Vector<Class<?>> listeClass() {
        Vector<Class<?>> liste = new Vector<>();
        liste.add(Produit.class);
        liste.add(Maladie.class);
        liste.add(Laboratoire.class);
        liste.add(ModeAdministration.class);
        liste.add(ProduitCategoriePersonne.class);
        liste.add(ProduitMaladie.class);
        liste.add(CategoriePersonne.class);
        liste.add(UniteMesure.class);
        liste.add(Forme.class);
        return liste;
    }

    public static Date dateByString(String dt) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dateFormat.parse(dt);
        return new Date(date.getTime());
    }
}