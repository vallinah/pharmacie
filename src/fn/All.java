package fn;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
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
    private Vector<Field> foreingKey = new Vector<>();
    private Vector<Field> fieldId = new Vector<>();

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
                if (f.getAnnotation(AnnotationAttr.class).id()) {
                    fieldId.add(f);
                }
            }
            if (f.isAnnotationPresent(ForeingKey.class)) {
                if (!foreingKey.contains(f)) {
                    foreingKey.add(f);
                }
            }
        }
    }

    private String fieldId(String req) throws Exception {
        String virgule = req.equals("select ") ? "" : ", ";
        String id = "";
        for (int a = 0; a < fieldId.size(); a++) {
            if (a != 0) id += ", ";
            id += nameInBase + "." + fieldId.get(a).getAnnotation(AnnotationAttr.class).nameInBase(); 
        }
        return virgule + id;
    }

    private String scriptJoin(Field fld) {
        String nameFld = fld.getAnnotation(AnnotationAttr.class).nameInBase();
        String otherCls = fld.getAnnotation(ForeingKey.class).cls();
        return   " join " + otherCls + " on " + nameInBase + "." + nameFld + " = " + otherCls + "." + nameFld;
    }

    private String checkForeignKey(String req) throws Exception {
        boolean checkVirgule = false;
        if (req.equals("select ")) {
            checkVirgule = true;
        }
        String join = "";
        if (!foreingKey.isEmpty()) {
            String colonne = "";
            for (int a = 0; a < foreingKey.size(); a++) {
                ForeingKey frk = foreingKey.get(a).getAnnotation(ForeingKey.class);
                String nameFld = frk.col();
                String otherCls = frk.cls();
                String virgule = ", ";
                if (checkVirgule) {
                    virgule = (a > 0) ? ", " : " ";
                }
                colonne += virgule + otherCls + "." + nameFld + " ";
                if (frk.id()) {
                    join += scriptJoin(foreingKey.get(a));
                }
            }
            req += colonne;
        }
        req += fieldId(req);
        req += " from " + nameInBase + join;
        return req;
    }

        public String mainRequest(String param) throws Exception {
            String req = "";
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
            req = "select " + param;
            req = checkForeignKey(req);
            System.out.println(req);
            return req;
        }

    public String requeteByID() throws Exception {
        String req = mainRequest("") + " where 1 = 1";
        for (Field fld : fieldId) {
            req += " and " + nameInBase + "." + fld.getAnnotation(AnnotationAttr.class).nameInBase() + " = ?";
        }
        return req;
    }

    public Vector<String> getById(String[] ids) throws Exception {
        Vector<String> all = new Vector<>();
        ResultSet set = null;
        Connexion connexion = Function.dbConnect();
        PreparedStatement prp = null;

        try {
            String req = requeteByID();
            prp = connexion.getConnexe().prepareStatement(req);
            for (int a = 0; a < ids.length; a++) {
                prp.setString(a + 1, ids[a].trim());
            }
            set = prp.executeQuery();
            ResultSetMetaData metaData = set.getMetaData();

            while (set.next()) {
                for (int a = 0; a < metaData.getColumnCount(); a++) {
                    if (a < metaData.getColumnCount() - fieldId.size()) {
                        all.add(set.getString(metaData.getColumnName(a + 1)));
                    } else {
                        String allIds = "";
                        for (int b = a; b <  metaData.getColumnCount(); b++) {
                            if (b != metaData.getColumnCount() - fieldId.size()) {
                                allIds += "_";
                            }
                            allIds += set.getString(metaData.getColumnName(b + 1));
                        }
                        all.add(allIds);
                        break;
                    }
                }
            }
            return all;
        } catch (Exception e) {
            throw e;
        } finally {
            if (prp != null) prp.close();
            if (set != null) set.close();
            connexion.finaleClose();
        }
    }

    public Vector<Vector<String>> getAll(String param) throws Exception {
        Vector<Vector<String>> all = new Vector<>();
        ResultSet set = null;
        Connexion connexion = Function.dbConnect();

        try {
            String req = mainRequest(param);
            Statement stmt = connexion.getConnexe().createStatement();
            set = stmt.executeQuery(req);
            ResultSetMetaData metaData = set.getMetaData();

            for (int a = 0; a <  metaData.getColumnCount(); a++) {
                allTitre.add(Function.toUcWords(metaData.getColumnName(a + 1)));
            }

            while (set.next()) {
                Vector<String> ligne = new Vector<>();
                for (int a = 0; a < metaData.getColumnCount(); a++) {
                    if (a < metaData.getColumnCount() - fieldId.size()) {
                        ligne.add(set.getString(metaData.getColumnName(a + 1)));
                    } else {
                        String allIds = "";
                        for (int b = a; b <  metaData.getColumnCount(); b++) {
                            if (b != metaData.getColumnCount() - fieldId.size()) {
                                allIds += "_";
                            }
                            allIds += set.getString(metaData.getColumnName(b + 1));
                        }
                        ligne.add(allIds);
                        break;
                    }
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