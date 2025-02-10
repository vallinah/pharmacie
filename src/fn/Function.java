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

    public static String toUcWords(String str) {
        String[] splt = str.split("[^\\w\\d]|_");
        String result = toUcFirst(splt[0]);
        for (int a = 1; a < splt.length; a++) {
            result += " " + toUcFirst(splt[a]);
        }
        return result;
    }

    public static String toUcFirst(String str) {
        if (str.isEmpty()) {
            return "";
        }
        String substtr = str.substring(1, str.length());
        String firstStr = str.charAt(0) + "";
        return firstStr.toUpperCase() + substtr;
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

    public static Vector<LinkHandler> listeLink() {
        Vector<LinkHandler> liste = new Vector<>();
        liste.add(new LinkHandler(Produit.class));
        liste.add(new LinkHandler(Maladie.class));
        liste.add(new LinkHandler(ModeAdministration.class));
        liste.add(new LinkHandler(ProduitCategoriePersonne.class));
        liste.add(new LinkHandler(ProduitMaladie.class));
        liste.add(new LinkHandler(CategoriePersonne.class));
        liste.add(new LinkHandler(HistoriquePrixProduit.class));
        liste.add(new LinkHandler(Genre.class));
        liste.add(new LinkHandler(Commission.class));
        liste.add(new LinkHandler(Vendeur.class));
        liste.add(new LinkHandler(Client.class));
        liste.add(new LinkHandler(Vente.class));
        liste.add(new LinkHandler(VenteDetail.class));
        liste.add(new LinkHandler(ConseilDuMois.class));
        return liste;
    }

    public static Date dateByString(String dt) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dateFormat.parse(dt);
        return new Date(date.getTime());
    }
}