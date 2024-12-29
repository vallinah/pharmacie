package gst;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Vector;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import base.connexe.Connexion;
import fn.Function;

public class CRUD {

    private Class<?> cls;
    private String nameInBase;
    private String sequenceName;
    private Vector<Field> listFields;

    public CRUD(Class<?> cls) throws Exception {
        if (!cls.isAnnotationPresent(AnnotationClass.class))
            throw new Exception("Cette classe n'a aucun reference dans votre base de donnée");
        this.cls = cls;
        nameInBase = cls.getAnnotation(AnnotationClass.class).nameInBase();
        sequenceName = cls.getAnnotation(AnnotationClass.class).sequence();
        listFields = fieldMapped();
    }

    public void preparedUpdate(PreparedStatement prp, Connexion connexion, Field fld, String value, Integer cpt)  throws Exception{
        if (fld.getType().equals(String.class)) {
                prp.setString(++cpt, value);
            }
        else if (fld.getClass().equals(Date.class)
                || fld.getClass().equals(java.sql.Date.class)
                || fld.getClass().equals(LocalDate.class)) {
            prp.setDate(++cpt, Function.dateByString(value));
        } else if (fld.getClass().equals(Integer.class)) {
                prp.setInt(++cpt, Integer.parseInt(value));
        } else if (fld.getClass().equals(Double.class)) {
                prp.setDouble(++cpt, Double.parseDouble(value));
        } else if (fld.getClass().equals(Float.class)) {
                prp.setFloat(++cpt, Float.parseFloat(value));
        }
    }

    public void prepared(PreparedStatement prp, Connexion connexion, Field fld, String value, int indexNum, Integer cpt) throws Exception {
        if (fld.getType().equals(String.class)) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                prp.setString(indexNum, connexion.incrementSequence(sequenceName) + "");
            } else {
                prp.setString(indexNum, value);
                cpt++;
            }
        } else if (fld.getClass().equals(Date.class)
                || fld.getClass().equals(java.sql.Date.class)
                || fld.getClass().equals(LocalDate.class)) {
            prp.setDate(indexNum, Function.dateByString(value));
            cpt++;
        } else if (fld.getClass().equals(Integer.class)) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                prp.setInt(indexNum, connexion.incrementSequence(sequenceName));
            } else {
                prp.setInt(indexNum, Integer.parseInt(value));
                cpt++;
            }
        } else if (fld.getClass().equals(Double.class)) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                prp.setDouble(indexNum, connexion.incrementSequence(sequenceName));
            } else {
                prp.setDouble(indexNum, Double.parseDouble(value));
                cpt++;
            }
        } else if (fld.getClass().equals(Float.class)) {
            if (fld.getAnnotation(AnnotationAttr.class).inc()) {
                prp.setFloat(indexNum, connexion.incrementSequence(sequenceName));
            } else {
                prp.setFloat(indexNum, Float.parseFloat(value));
                cpt++;
            }
        }
    }

    public Vector<Field> fieldMapped() throws Exception {
        Vector<Field> listFields = new Vector<>();
        Class<?> kilasy = cls;
        while (true) {
            if (kilasy.equals(Object.class))
                break;
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
            if (prp != null)
                prp.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
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
            Integer cpt = 0;

            for (int a = 0; a < listFields.size(); a++) {
                prepared(prp, connexion, listFields.get(a), insertion.get(cpt), a + 1, cpt);
            }
            prp.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            if (connection != null)
                connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (prp != null)
                prp.close();
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
} 