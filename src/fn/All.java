package fn;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import annotation.AnnotationClass;
import base.connexe.Connexion;

public class All {
    
    private Class<?> cls;
    private String nameInBase;
    private Vector<String> allTitre = new Vector<>();

    public All(Class<?> cls) throws Exception {
        this.cls = cls;
        nameInBase = cls.getAnnotation(AnnotationClass.class).nameInBase();
    }

    public Vector<Vector<String>> getAll(String param) throws Exception {
        Vector<Vector<String>> all = new Vector<>();
        
        ResultSet set = null;
        Connexion connexion = Function.dbConnect();
        if (param != null) {
            if (param.isEmpty()) {
                param = "*";
            }
        } else {
            param = "*";
        }

        try {
            String req = "select " + param + " from " + nameInBase;
            set = connexion.getStmt().executeQuery(req);
            ResultSetMetaData metaData = set.getMetaData();

            int columnCompt = metaData.getColumnCount();

            for (int a = 0; a < columnCompt; a++) {
                allTitre.add(metaData.getColumnName(a + 1));
            }
            while (set.next()) {
                Vector<String> ligne = new Vector<>();
                for (int a = 0; a < columnCompt; a++) {
                    ligne.add(set.getString(a + 1));
                }
                all.add(ligne);
            }
            return all;
        } catch (Exception e) {
            throw e;
        } finally {
            if (set != null) set.close();
            connexion.finaleClose();
        }
    }

    // ! Getter

    public Vector<String> getAllTitre() {
        return allTitre;
    }
}
