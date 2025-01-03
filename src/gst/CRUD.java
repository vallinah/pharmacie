package gst;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import base.connexe.Connexion;
import fn.Compteur;
import fn.Function;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.Date;
import java.util.Vector;

public class CRUD {

    private Class<?> cls;
    private String nameInBase;
    private String sequenceName;
    private Vector<Field> listFields;
    private String idName;

    public CRUD(Class<?> cls) throws Exception {
        if (!cls.isAnnotationPresent(AnnotationClass.class)) {
            throw new Exception("Cette classe n'a aucun reference dans votre base de donnée");
        }
        this.cls = cls;
        nameInBase = cls.getAnnotation(AnnotationClass.class).nameInBase();
        sequenceName = cls.getAnnotation(AnnotationClass.class).sequence();
        listFields = fieldMapped();
        for (Field fld : listFields) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                idName = fld.getName();
                break;
            }
        }
    }

    public String inputType(Field fld) {
        String type = "text";
        if (fld.getType().equals(Integer.class) || fld.getType().equals(Double.class) || fld.getType().equals(Float.class)) {
            type = "number";
        } else if (fld.getType().equals(Date.class) || fld.getType().equals(java.sql.Date.class) || fld.getType().equals(LocalDate.class)) {
            type = "date";
        }
        return type;
    }

    public String html_liste() throws Exception {
        StringBuilder bld = new StringBuilder();
        StringBuilder body = new StringBuilder();
        String ttr = "";
        bld.append("    <section class=\"list\">\n"
                + //
                "       <div class='ttr'>\n"
                + "           <h1>Listes " + nameInBase + "</h1>\n"
                + //
                "        </div>\n"
                + "        <div class=\"bd\">\n");
        String idName = null;

        for (Field fld : listFields) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                idName = fld.getName();
                break;
            }
        }

        String req = "select * from " + nameInBase + " order by " + idName;
        ResultSet set = null;
        Connexion connexion = Function.dbConnect();

        try {
            set = connexion.getStmt().executeQuery(req);
            ResultSetMetaData metaData = set.getMetaData();
            int columnCpt = metaData.getColumnCount();

            for (int a = 1; a <= columnCpt; a++) {
                ttr += "                    <th>" + metaData.getColumnName(a) + "</th>\n";
            }
            ttr += "                    <th>Action</th>\n"
                    + //
                    "               </tr>\n"
                    + //
                    "               <tr>\n";
            int isEmpty = 0;
            while (set.next()) {
                for (int a = 1; a <= columnCpt; a++) {
                    body.append("                    <td>" + set.getString(a) + "</td>\n");
                }
                body.append("                    <td>\n"
                        + //
                        "                       <div class='action'>\n"
                        + "                           <a href=\"update.jsp?cls=" + cls.getName() + "&id=" + set.getString(1) + "\"><i class=\"bi bi-pencil\"></i></a>\n"
                        + //
                        "                           <a href=\"crud?cls=" + cls.getName() + "&id=" + set.getString(1) + "\"><i class=\"bi bi-trash\"></i></a>\n"
                        + //
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
            bld.append("        </div>\n"
                    + //
                    "    </section>");
            return bld.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (set != null) {
                set.close();
            }
            if(connexion != null){
                connexion.finaleClose();
            }
        }
    }

    public void preparedUpdate(PreparedStatement prp, Connexion connexion, Field fld, String value, Compteur cpt) throws Exception {
        int compteur = cpt.getCpt() + 1;
        if (fld.getType().equals(String.class)) {
            prp.setString(compteur, value);
        } else if (fld.getClass().equals(Date.class)
                || fld.getClass().equals(java.sql.Date.class)
                || fld.getClass().equals(LocalDate.class)) {
            prp.setDate(compteur, Function.dateByString(value));
        } else if (fld.getClass().equals(Integer.class)) {
            prp.setInt(compteur, Integer.parseInt(value));
        } else if (fld.getClass().equals(Double.class)) {
            prp.setDouble(compteur, Double.parseDouble(value));
        } else if (fld.getClass().equals(Float.class)) {
            prp.setFloat(compteur, Float.parseFloat(value));
        }
        cpt.setCpt(compteur);
    }

    public void prepared(PreparedStatement prp, Connexion connexion, Field fld, String value, int indexNum, Compteur cpt) throws Exception {
        if (fld.getType().equals(String.class)) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                prp.setString(indexNum, connexion.incrementSequence(sequenceName) + "");
            } else {
                prp.setString(indexNum, value);
                cpt.setCpt(cpt.getCpt() + 1);
            }
        } else if (fld.getClass().equals(Date.class)
                || fld.getClass().equals(java.sql.Date.class)
                || fld.getClass().equals(LocalDate.class)) {
            prp.setDate(indexNum, Function.dateByString(value));
            cpt.setCpt(cpt.getCpt() + 1);
        } else if (fld.getClass().equals(Integer.class)) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                prp.setInt(indexNum, connexion.incrementSequence(sequenceName));
            } else {
                prp.setInt(indexNum, Integer.parseInt(value));
                cpt.setCpt(cpt.getCpt() + 1);
            }
        } else if (fld.getClass().equals(Double.class)) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                prp.setDouble(indexNum, connexion.incrementSequence(sequenceName));
            } else {
                prp.setDouble(indexNum, Double.parseDouble(value));
                cpt.setCpt(cpt.getCpt() + 1);
            }
        } else if (fld.getClass().equals(Float.class)) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                prp.setFloat(indexNum, connexion.incrementSequence(sequenceName));
            } else {
                prp.setFloat(indexNum, Float.parseFloat(value));
                cpt.setCpt(cpt.getCpt() + 1);
            }
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

    private String scriptUpdate(Vector<String> updt, String id) throws Exception {
        StringBuilder req = new StringBuilder();
        String idName = null;
        req.append("update " + nameInBase + " set ");
        int cpt = 0;
        for (int a = 0; a < listFields.size(); a++) {
            if (listFields.get(a).getAnnotation(AnnotationAttr.class).inc()) {
                idName = listFields.get(a).getName();
            } else {
                req.append(listFields.get(a).getName() + " = ?");
                if (cpt != updt.size() - 1) {
                    req.append(",");
                }
                cpt++;
            }
        }
        req.append(" where " + idName + " = ?");
        return req.toString();
    }

    public void update(Vector<String> updt, String id) throws Exception {
        String req = scriptUpdate(updt, id);
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
            prp.setString(updt.size() + 1, id);
            prp.executeUpdate();
            connection.commit();
            System.out.println("update reussi : " + req);
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

    public String html_update(String id) throws Exception {
        String req = "select * from " + nameInBase + " where " + idName + " = ?";
        PreparedStatement prp = null;
        ResultSet set = null;
        Connexion connexion = Function.dbConnect();

        try {
            prp = connexion.getConnexe().prepareStatement(req);
            prp.setString(1, id);
            set = prp.executeQuery();

            if (!set.next()) {
                throw new Exception("Aucun donnees de cette id");
            }

            StringBuilder bld = new StringBuilder();
            bld.append("    <section class=\"gnr\">\n"
                    + //
                    "        <h1>Update " + nameInBase + "</h1>\n"
                    + //
                    "        <form action=\"./crud?cls=" + cls.getName() + "&id=" + id + "\" method=\"post\">\n");

            for (Field fld : listFields) {
                AnnotationAttr annotation = fld.getAnnotation(AnnotationAttr.class);
                if (annotation.insert() && !annotation.inc()) {
                    String nameInBaseFld = fld.getAnnotation(AnnotationAttr.class).nameInBase();
                    String name = fld.getName();
                    bld.append("            <div class=\"prt\">\n");
                    if (fld.getAnnotation(AnnotationAttr.class).textarea()) {
                        bld.append("                <textarea name=\"" + name + "\" placeholder=" + name + ">" + set.getString(nameInBaseFld) + "</textarea>\n");
                    } else {
                        bld.append("                <input type=\"" + inputType(fld) + "\" value=\"" + set.getString(nameInBaseFld) + "\" name=\"" + name + "\" required>\n"
                                + "                <span>" + name + "</span>\n");
                    }
                    bld.append("            </div>\n");
                }
            }
            bld.append("            <button type=\"submit\">Valider</button>\n"
                    + //
                    "        </form>\n"
                    + //
                    "    </section>");
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

    public void delete(String id) throws Exception {
        String id_in_base = null;
        for (Field fld : listFields) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                id_in_base = fld.getName();
                break;
            }
        }

        String req = "delete from " + nameInBase + " where " + id_in_base + " = ?";

        Connection connection = null;
        PreparedStatement prp = null;

        try {
            Connexion connexion = Function.dbConnect();
            connection = connexion.getConnexe();
            connection.setAutoCommit(false);
            prp = connection.prepareStatement(req);
            prp.setString(1, id);
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
            a_inserer.add(f.getName());
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

    public void insert(Vector<String> insertion) throws Exception {
        String req = scriptInsert();
        Connection connection = null;
        PreparedStatement prp = null;
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
        bld.append("    <section class=\"gnr\">\n"
                + //
                "        <h1>Insertion " + nameInBase + "</h1>\n"
                + //
                "        <form action=\"./crud?cls=" + cls.getName() + "\" method=\"post\">\n");

        for (Field fld : listFields) {
            AnnotationAttr annotation = fld.getAnnotation(AnnotationAttr.class);
            if (annotation.insert() && !annotation.inc()) {
                String name = fld.getName();
                bld.append("            <div class=\"prt\">\n");

                if (fld.getAnnotation(AnnotationAttr.class).textarea()) {
                    bld.append("                <textarea name=\"" + name + "\" placeholder=" + name + "></textarea>\n");
                } else {
                    bld.append("                <input type=\"" + inputType(fld) + "\" name=\"" + name + "\" required>\n"
                            + "                <span>" + name + "</span>\n");
                }
                bld.append("            </div>\n");
            }
        }
        bld.append("            <button type=\"submit\">Valider</button>\n"
                + //
                "        </form>\n"
                + //
                "    </section>");
        return bld.toString();
    }
}
