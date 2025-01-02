package fn;

import base.*;
import base.connexe.Connexion;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class Function {
    
    public static Connexion dbConnect() throws Exception {
        return new Connexion("pharmacie");
    }

    public static Vector<Class<?>> listeClass() {
        Vector<Class<?>> liste = new Vector<>();
        liste.add(Categorie.class);
        liste.add(Produit.class);
        liste.add(SousCategorie.class);
        liste.add(Type.class);
        return liste;
    }

    public static Date dateByString(String dt) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dateFormat.parse(dt);
        return new Date(date.getTime());
    }
}