package fn;

import java.sql.Date;
import java.text.SimpleDateFormat;

import base.connexe.Connexion;

public class Function {
    
    public static Connexion dbConnect() throws Exception {
        return new Connexion("pharmacie");
    }

    public static Date dateByString(String dt) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dateFormat.parse(dt);
        return new Date(date.getTime());
    }
}