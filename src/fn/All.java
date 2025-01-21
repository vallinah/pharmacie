package fn;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;
import base.connexe.Connexion;

public class All {
    
    private Class<?> cls;
    private String nameInBase;
    private Vector<String> allTitre = new Vector<>();
    private Vector<Field> fieldShow = new Vector<>();
    private String req = null;
    private Vector<Field> foreingKey = new Vector<>();
    private Vector<String> efaVitaJointure = new Vector<>();

    public All(Class<?> cls) throws Exception {
        this.cls = cls;
        nameInBase = cls.getAnnotation(AnnotationClass.class).nameInBase();
        setFieldShow();
    }

    
    private void setFieldShow() {
        Field[] fld = cls.getDeclaredFields();
        for (Field f : fld) {
            if (f.isAnnotationPresent(AnnotationAttr.class)) {
                if (f.getAnnotation(AnnotationAttr.class).show()) {
                    fieldShow.add(f);
                }
            } 
            if (f.isAnnotationPresent(ForeingKey.class)) {
                if (!foreingKey.contains(f)) {
                    foreingKey.add(f);
                }
            }
        }
    }

        private boolean checkEfaAoVe(String cls) {
            for (String str : efaVitaJointure) {
                if (cls.equalsIgnoreCase(str)) {
                    return true;
                }
            }
            return false;
        }

    private String scriptJoin(Field fld) {
        String nameFld = fld.getAnnotation(AnnotationAttr.class).nameInBase();
        String otherCls = fld.getAnnotation(ForeingKey.class).cls();
        if (checkEfaAoVe(otherCls)) {
            return "";
        }
        efaVitaJointure.add(otherCls);
        return   " join " + otherCls + " on " + nameInBase + "." + nameFld + " = " + otherCls + "." + nameFld;
    }

    private void checkForeignKey() throws Exception {
        String join = "";
        if (!foreingKey.isEmpty()) {
            String colonne = "";
            for (int a = 0; a < foreingKey.size(); a++) {
                String nameFld =foreingKey.get(a).getAnnotation(ForeingKey.class).col();
                String otherCls = foreingKey.get(a).getAnnotation(ForeingKey.class).cls();
                colonne += ", " + otherCls + "." + nameFld + " ";
                join += scriptJoin(foreingKey.get(a));
            }
            req += colonne;
        }
        req += " from " + nameInBase + join;
    }

    public Vector<Vector<String>> getAll(String param) throws Exception {
        Vector<Vector<String>> all = new Vector<>();
        
        ResultSet set = null;
        Connexion connexion = Function.dbConnect();
        if (param != null) {
            if (param.isEmpty()) {
                param = "";
                for (int a = 0; a < fieldShow.size(); a++)  {
                    if (!fieldShow.get(a).isAnnotationPresent(ForeingKey.class)) {
                        String virgule = (a >= 1) ? ", " : "  ";
                        param += virgule + nameInBase + "." + fieldShow.get(a).getAnnotation(AnnotationAttr.class).nameInBase();
                    }
                }
            }
        } else {
            param = "";
            for (int a = 0; a < fieldShow.size(); a++)  {
                if (!fieldShow.get(a).isAnnotationPresent(ForeingKey.class)) {
                    String virgule = (a >= 1) ? ", " : " ";
                    param += virgule + nameInBase + "." + fieldShow.get(a).getAnnotation(AnnotationAttr.class).nameInBase();
                }
            }
        }

        try {
            req = "select " + param;
            checkForeignKey();
            System.out.println(req);
            set = connexion.getStmt().executeQuery(req);
            ResultSetMetaData metaData = set.getMetaData();

            for (int a = 0; a <  metaData.getColumnCount(); a++) {
                allTitre.add(metaData.getColumnName(a + 1));
            }

            while (set.next()) {
                Vector<String> ligne = new Vector<>();
                for (int a = 0; a < allTitre.size(); a++) {
                    ligne.add(set.getString(allTitre.get(a)));
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