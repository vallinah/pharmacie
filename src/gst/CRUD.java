package gst;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;
import base.connexe.Connexion;
import fn.All;
import fn.Compteur;
import fn.Function;
import fn.LinkHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

public class CRUD {

    private Class<?> cls;
    private String nameInBase;
    private String sequenceName;
    private Vector<Field> listFields;
    private Vector<Field> listId = new Vector<>();

    public CRUD(Class<?> cls) throws Exception {
        if (!cls.isAnnotationPresent(AnnotationClass.class)) {
            throw new Exception("Cette classe n'a aucun reference dans votre base de donnée");
        }
        this.cls = cls;
        nameInBase = cls.getAnnotation(AnnotationClass.class).nameInBase();
        sequenceName = cls.getAnnotation(AnnotationClass.class).sequence();
        listFields = fieldMapped();
        for (Field fld : listFields) {
            if (fld.getAnnotation(AnnotationAttr.class).id()) {
                listId.add(fld);
            }
        }
    }

    private boolean isTypeInc(Field fld) throws Exception {
        return fld.getAnnotation(AnnotationAttr.class).inc();
    }

    private Field getFieldByName(String fldName) throws Exception {
        for (Field fld : listFields) {
            if (fldName.equalsIgnoreCase(fld.getName())) {
                return fld;
            }
        }
        throw new Exception("Cette Attribut n'existe pas");
    }

    private void preparedField(String fldName, PreparedStatement prp, int numero, String value) throws Exception {
        Field fld = getFieldByName(fldName);
        if (fld.getType().equals(Integer.class)) {
            prp.setInt(numero, Integer.parseInt(value));
        } else if (fld.getType().equals(float.class)) {
            prp.setFloat(numero, Float.parseFloat(value));
        } else if (fld.getType().equals(Double.class)) {
            prp.setDouble(numero, Double.parseDouble(value));
        } else if (fld.getType().equals(Date.class) || fld.getType().equals(java.sql.Date.class)
                || fld.getType().equals(LocalDate.class)) {
            prp.setDate(numero, Function.dateByString(value));
        } else {
            prp.setString(numero, value);
        }
    }

    public Vector<String> getData(String column_name, String nameInBase, HashMap<String, String> map) throws Exception {
        String req = "select " + column_name + " from " + nameInBase;
        Vector<String> key = new Vector<>();
        if (map != null) {
            key = new Vector<>(map.keySet());
        }

        if (!key.isEmpty()) {
            req += " where " + key.firstElement() + " = ?";
            for (String k : key) {
                if (!k.equals(key.firstElement())) {
                    req += " and " + k + " = ?";
                }
            }
        }

        Connexion connexion = Function.dbConnect();
        PreparedStatement prp = null;
        ResultSet set = null;

        try {
            prp = connexion.getConnexe().prepareStatement(req);
            for (int a = 0; a < key.size(); a++) {
                preparedField(key.get(a), prp, a + 1, map.get(key.get(a)));
            }
            set = prp.executeQuery();

            Vector<String> result = new Vector<>();
            while (set.next()) {
                result.add(set.getString(1));
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (set != null)
                set.close();
            if (prp != null)
                prp.close();
            connexion.finaleClose();
        }
    }

    public String inputType(Field fld) {
        String type = "text";
        if (fld.getType().equals(int.class) || fld.getType().equals(Integer.class) || 
        fld.getType().equals(double.class) || fld.getType().equals(Double.class) || 
        fld.getType().equals(float.class) || fld.getType().equals(Float.class) ) {
            type = "number";
        } else if (fld.getType().equals(Date.class) || fld.getType().equals(java.sql.Date.class)
                || fld.getType().equals(LocalDate.class)) {
            type = "date";
        }
        return type;
    }

    public String html_liste() throws Exception {
        StringBuilder bld = new StringBuilder();
        StringBuilder body = new StringBuilder();
        String ttr = "";

        All all = new All(cls);
        Vector<Vector<String>> rehetra = all.getAll(null);
        Vector<String> titre = all.getAllTitre();

        for (int a = 0; a < titre.size() - listId.size(); a++) {
            ttr += "                    <th>" + titre.get(a) + "</th>\n";
        }
        ttr += "                    <th>Action</th>\n"
                + //
                "               </tr>\n"
                + //
                "               <tr>\n";
        int isEmpty = 0;

        for (Vector<String> ligne : rehetra) {
            for (int a = 0; a < ligne.size(); a++) {
                if (a != ligne.size() - 1) {
                    body.append("                    <td>" + ligne.get(a) + "</td>\n");

                }
            }
            body.append("                    <td>\n"
                    + //
                    "                       <div class='action'>\n"
                    + "                           <a href=\"update?cls=" + cls.getName() + "&id="
                    + ligne.lastElement().trim() + "\" class=\"update modal_active\" data-active=\"update\"><i class=\"bi bi-pencil\"></i></a>\n"
                    + "                           <a href=\"crud?cls=" + cls.getName() + "&id=" + ligne.lastElement().trim() + "\" class='delete modal_active' data-active='delete'><i class=\"bi bi-trash\"></i></a" + //
                    "                       </div>\n"
                    + "                   </td>\n"
                    + //
                    "               </tr>\n");
            isEmpty++;
        }

        if (isEmpty != 0) {
            bld.append("            <table border=\"1\">\n"
                    + //
                    "               <tr>\n");
            bld.append(ttr);
        } else {
            body.append("           <h2>Aucun(s) element(s)</h2>\n");
        }

        bld.append(body);

        if (isEmpty != 0) {
            bld.append("            </table>\n");
        }
        return bld.toString();
    }

    public void preparedUpdate(PreparedStatement prp, Connexion connexion, Field fld, String value, Compteur cpt)
            throws Exception {
        int compteur = cpt.getCpt() + 1;
        if (fld.getType().equals(String.class)) {
            prp.setString(compteur, value);
        } else if (fld.getType().equals(Date.class)
                || fld.getType().equals(java.sql.Date.class)
                || fld.getType().equals(LocalDate.class)) {
            prp.setDate(compteur, Function.dateByString(value));
        } else if (fld.getType().equals(int.class) || fld.getType().equals(Integer.class)) {
            prp.setInt(compteur, Integer.parseInt(value));
        } else if (fld.getType().equals(double.class) || fld.getType().equals(Double.class)) {
            prp.setDouble(compteur, Double.parseDouble(value));
        } else if (fld.getType().equals(float.class) || fld.getType().equals(Float.class)) {
            prp.setFloat(compteur, Float.parseFloat(value));
        }
        cpt.setCpt(compteur);
    }

    public void prepared(PreparedStatement prp, Connexion connexion, Field fld, String value, int indexNum,
            Compteur cpt) throws Exception {
        boolean increment = true;
        if (fld.getType().equals(String.class)) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                String prefix = cls.getAnnotation(AnnotationClass.class).prefix();
                String id = connexion.incrementSequence(sequenceName) + "";
                int nbZero = 8 - id.length();
                prp.setString(indexNum, prefix + "0".repeat(nbZero) + id);
                increment = false;
            } else {
                prp.setString(indexNum, value);
            }
        } else if (fld.getType().equals(Date.class)
                || fld.getType().equals(java.sql.Date.class)
                || fld.getType().equals(LocalDate.class)) {
            prp.setDate(indexNum, Function.dateByString(value));
        } else if (fld.getType().equals(int.class) || fld.getType().equals(Integer.class)) {
            prp.setInt(indexNum, Integer.parseInt(value));
        } else if (fld.getType().equals(double.class) || fld.getType().equals(Double.class)) {
            prp.setDouble(indexNum, Double.parseDouble(value));
        } else if (fld.getType().equals(float.class) || fld.getType().equals(Float.class)) {
            prp.setFloat(indexNum, Float.parseFloat(value));
        }
        if (increment) {
            cpt.setCpt(cpt.getCpt() + 1);
        }
    }

    public Vector<Field> fieldMapped() throws Exception {
        Vector<Field> listFields = new Vector<>();
        Class<?> kilasy = cls;
        while (true) {
            if (kilasy.equals(Object.class)) {
                break;
            }
            if (kilasy != null) {
                for (Field fld : kilasy.getDeclaredFields()) {
                    if (fld.isAnnotationPresent(AnnotationAttr.class)) {
                        if (fld.getAnnotation(AnnotationAttr.class).insert()) {
                            listFields.add(fld);
                        }
                    }
                }
            }
            kilasy = kilasy.getSuperclass();
        }
        return listFields;
    }

    public String scriptUpdate() throws Exception {
        StringBuilder req = new StringBuilder();
        req.append("update " + nameInBase + " set ");
        int cpt = 0;
        int countInc = 0;
        for (Field fld : listFields) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) countInc++;
        }

        int counId = (countInc == 0) ? listId.size() : countInc;
        int limiteCpt = (listFields.size() == counId) ? counId - 1 : listFields.size() - counId - 1;

        for (int a = 0; a < listFields.size(); a++) {
            if (!listFields.get(a).getAnnotation(AnnotationAttr.class).inc()) {
                req.append(listFields.get(a).getAnnotation(AnnotationAttr.class).nameInBase() + " = ?");
                if (cpt !=  limiteCpt) {
                    req.append(",");
                }
                cpt++;
            }
        }
        req.append(" where 1 = 1");
        for (Field fld : listId) {
            req.append(" and " + fld.getAnnotation(AnnotationAttr.class).nameInBase() + " = ?");
        }
        return req.toString();
    }

    public String[] update(Vector<String> updt, String[] ids) throws Exception {
        String req = scriptUpdate();
        String[] ireoId = new String[listId.size()];

        if (listId.size() == listFields.size()) {
            for (int a = 0; a < listId.size(); a++) {
                ireoId[a] = updt.get(listFields.indexOf(listId.get(a)));
            }
        } else {
            ireoId = ids;
        }

        System.out.println(req);
        PreparedStatement prp = null;
        Connection connection = null;
        try {
            Connexion connexion = Function.dbConnect();
            connection = connexion.getConnexe();
            connection.setAutoCommit(false);

            prp = connection.prepareStatement(req);
            Compteur cpt = new Compteur(0);

            for (int a = 0; a < listFields.size(); a++) {
                if (!listFields.get(a).getAnnotation(AnnotationAttr.class).inc()) {
                    preparedUpdate(prp, connexion, listFields.get(a), updt.get(cpt.getCpt()), cpt);
                }
            }
            for (int a = 0; a < listId.size(); a++) {
                prp.setString(updt.size() + (a + 1), ids[a]);
            }
            
            prp.executeUpdate();
            connection.commit();
            System.out.println("update reussi : " + req);
            return ireoId;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (prp != null) {
                prp.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    public String html_update(String[] ids) throws Exception {
        String req = "select * from " + nameInBase + " where 1 = 1";

        for (Field fld : listId) {
            req +=" and " + fld.getAnnotation(AnnotationAttr.class).nameInBase() + " = ?"; 
        }
        System.out.println(req);

        PreparedStatement prp = null;
        ResultSet set = null;
        Connexion connexion = Function.dbConnect();

        try {
            prp = connexion.getConnexe().prepareStatement(req);
            for (int a = 0; a < listId.size(); a++) {
                prp.setString(a + 1, ids[a]);
            }
            set = prp.executeQuery();

            if (!set.next()) {
                throw new Exception("Aucun donnees de cette id");
            }

            StringBuilder bld = new StringBuilder();
            LinkHandler link = new LinkHandler(cls);
            bld.append("                <div class=\"form-container\">\n" + //
                            "                    <div class=\"form-title\">\n" + //
                            "                        <span class='icone'><i class='bi bi-pencil'></i></span>" + 
                            "                        <h1> <i class='bi " + link.getIcone() + "'></i>" + link.getTitre() + "</h1>\n" + //
                            "                    </div>\n" + //
                            "                    <form class='update' action=\"./crud?cls=" +  cls.getName() + "\" methode='post'>\n");
            for (Field fld : listFields) {
                AnnotationAttr annotation = fld.getAnnotation(AnnotationAttr.class);
                boolean typeNoPlaceHolder = !fld.getType().equals(Date.class) && !fld.getType().equals(java.sql.Date.class) && !fld.getType().equals(LocalDate.class);
                if (annotation.insert() && !annotation.inc()) {
                    String name = fld.getAnnotation(AnnotationAttr.class).nameInBase();
                    bld.append("                        <div class=\"form-group\">");
                    if (fld.getAnnotation(AnnotationAttr.class).textarea()) {
                        bld.append("                <textarea name=\"" + name + "\" placeholder=" + Function.toUcFirst(name) + ">"
                                + set.getString(name) + "</textarea>\n");
                    } else {
                        if (fld.isAnnotationPresent(ForeingKey.class)) {
                            ForeingKey foreingKey = fld.getAnnotation(ForeingKey.class);
                            String nomCol = foreingKey.col();
                            if (typeNoPlaceHolder) {
                                bld.append("<span>" + Function.toUcFirst(name) + " :</span>\n");
                            }
                            bld.append("                <select name=\"" + name + "\">\n" + //
                                        "                    <option value=\"\">Choise " + nomCol + "</option>\n");
                            Vector<String> list = getData(nomCol, foreingKey.cls(),null);
                            Vector<String> listId = getData(fld.getAnnotation(AnnotationAttr.class).nameInBase(), foreingKey.cls(),null);

                            String idInBase = set.getString(name);

                            for (int a = 0; a < list.size(); a++) {
                                String selected = "";
                                if (idInBase.equals(listId.get(a))) {
                                    selected = "selected";
                                }

                                bld.append("                    <option value=\"" + listId.get(a) + "\" " + selected
                                        + ">" + list.get(a) + "</option>\n");
                            }

                            bld.append("                </select>\n");
                        } else {
                            if (annotation.insert()) {

                                if (typeNoPlaceHolder) {
                                    bld.append("                <input type=\"" + inputType(fld) + "\" value=\""
                                    + set.getString(name) + "\" name=\"" + name + "\" placeholder=\"" + Function.toUcFirst(name) + "\" required>\n");
                                } else {
                                    bld.append("<span>" + Function.toUcFirst(name) + " :</span>\n" + 
                                        "                <input type=\"" + inputType(fld) + "\" value=\""
                                    + set.getString(name) + "\" name=\"" + name + "\" required>\n");
                                }
                            }
                        }
                    }
                    bld.append("            </div>\n");
                }
            }
            bld.append("                        <div class=\"form-submit\">\n" + //
                        "                            <button type=\"submit\">Valider</button>\n" + //
                        "                        </div>\n" + //
                        "                    </form>\n" + //
                        "                    <div class=\"message\">\n" + //
                        "                        <p class=\"\"></p>\n" + //
                        "                    </div>" +
                        "                </div>");
            return bld.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (prp != null) {
                prp.close();
            }
            if (set != null) {
                set.close();
            }
            connexion.finaleClose();
        }
    }

    public void delete(String[] ids) throws Exception {
        String req = "delete from " + nameInBase + " where 1 = 1" ;

        for (Field id : listId) {
            req += " and " + id.getAnnotation(AnnotationAttr.class).nameInBase() + " = ?";
        }

        Connection connection = null;
        PreparedStatement prp = null;

        try {
            Connexion connexion = Function.dbConnect();
            connection = connexion.getConnexe();
            connection.setAutoCommit(false);
            prp = connection.prepareStatement(req);
            for (int a = 0; a < ids.length; a++) {
                prp.setString(a + 1, ids[a]);
            }
            prp.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (prp != null) {
                prp.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    public String scriptInsert() throws Exception {
        Vector<String> a_inserer = new Vector<>();
        for (Field f : listFields) {
            a_inserer.add(f.getAnnotation(AnnotationAttr.class).nameInBase());
        }

        StringBuilder bld = new StringBuilder();
        bld.append("insert into " + nameInBase + "(");
        String values = "(";
        for (String nomColonne : a_inserer) {
            bld.append(nomColonne);
            values += "?";
            if (nomColonne != a_inserer.lastElement()) {
                bld.append(",");
                values += ",";
            }
        }
        bld.append(") values " + values + ")");
        return bld.toString();
    }

    public String[] insert(Vector<String> insertion) throws Exception {
        try {
            Object newInstance = cls.getConstructor().newInstance();
            Method methodeInsert  = cls.getMethod("insert", Vector.class);
            return (String[]) methodeInsert.invoke(newInstance, insertion);
        } catch (Exception e) {
            // System.out.println("ERROR : " + e.getMessage());
        }

        Connection connection = null;
        PreparedStatement prp = null;

        String req = scriptInsert();

        try {
            Connexion connexion = Function.dbConnect();
            connection = connexion.getConnexe();
            connection.setAutoCommit(false);

            prp = connection.prepareStatement(req);
            Compteur cpt = new Compteur(0);

            for (int a = 0; a < listFields.size(); a++) {
                prepared(prp, connexion, listFields.get(a), insertion.get(cpt.getCpt()), a + 1, cpt);
            }
            prp.executeUpdate();
            connection.commit();

            AnnotationClass annotationClass = cls.getAnnotation(AnnotationClass.class);

            String[] ids = new String[listId.size()];
            System.out.println(listId.size());
            for (int a = 0; a < listId.size(); a++) {
                if (isTypeInc(listId.get(a))) {
                    String sequence = connexion.currval(annotationClass.sequence()) + "";
                    ids[a] = annotationClass.prefix() + "0".repeat(8 - sequence.length()) + sequence;
                } else {
                    ids[a] = insertion.get(listFields.indexOf(listId.get(a)));
                }
            }
            return ids;
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (prp != null) {
                prp.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    public String html_insert() throws Exception {
        StringBuilder bld = new StringBuilder();
        LinkHandler link = new LinkHandler(cls);
        bld.append("                <div class=\"form-container\">\n" + //
                        "                    <div class=\"form-title\">\n" + //
                        "                        <span class='icone'><i class='bi bi-plus-circle'></i></span>" + 
                        "                        <h1> <i class='bi " + link.getIcone() + "'></i>" + link.getTitre() + "</h1>\n" + //
                        "                    </div>\n" + //
                        "                    <form class='ajout' action=\"./crud?cls=" +  cls.getName() + "\" methode='post'>\n");

        for (Field fld : listFields) {
            AnnotationAttr annotation = fld.getAnnotation(AnnotationAttr.class);
            boolean typeNoPlaceHolder = !fld.getType().equals(Date.class) && !fld.getType().equals(java.sql.Date.class) && !fld.getType().equals(LocalDate.class);
            if (annotation.insert() && !annotation.inc()) {
                String name = fld.getName();
                bld.append("                        <div class=\"form-group\">");

                if (fld.getAnnotation(AnnotationAttr.class).textarea()) {
                    bld.append("                <textarea name=\"" + name + "\" placeholder=" + Function.toUcFirst(name) + "></textarea>\n");
                } else {
                    if (fld.isAnnotationPresent(ForeingKey.class)) {
                        ForeingKey foreingKey = fld.getAnnotation(ForeingKey.class);
                        String nomCol = foreingKey.col();
                        if (typeNoPlaceHolder) {
                            bld.append("<span>" + Function.toUcFirst(name) + " :</span>\n");
                        }
                        bld.append("                <select name=\"" + name + "\">\n" + //
                                    "                    <option value=\"\">Choise " + nomCol + "</option>\n");
                        Vector<String> list = getData(nomCol, foreingKey.cls(),null);
                        Vector<String> listId = getData(fld.getAnnotation(AnnotationAttr.class).nameInBase(), foreingKey.cls(),null);

                        for (int a = 0; a < list.size(); a++) {
                            bld.append("                    <option value=\"" + listId.get(a) + "\">" + list.get(a)
                                    + "</option>\n");
                        }

                        bld.append("                </select>\n");
                    } else {
                        if (annotation.insert()) {
                            if (typeNoPlaceHolder) {
                                bld.append("                <input type=\"" + inputType(fld) + "\" name=\"" + name + "\" placeholder='" +  Function.toUcFirst(name)
                                + "'\" required>\n");
                            } else {
                                bld.append("<span>" + Function.toUcFirst(name) + " :</span>\n" + 
                                    "                <input type=\"" + inputType(fld) + "\" name=\"" + name
                                + "\" required>\n");
                            }
                        }
                    }
                }
                bld.append("            </div>\n");
            }
        }
        bld.append("                        <div class=\"form-submit\">\n" + //
                        "                            <button type=\"submit\">Valider</button>\n" + //
                        "                        </div>\n" + //
                        "                    </form>\n" + //
                        "                    <div class=\"message\">\n" + //
                        "                        <p class=\"\"></p>\n" + //
                        "                    </div>" +
                        "                </div>");
        return bld.toString();
    }

    public Class<?> getCls() {
        return cls;
    }
}
