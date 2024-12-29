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
            if (prp != null) prp.close(); 
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

            int cpt = 0;

            for (int a = 0; a < listFields.size(); a++) {
                if (listFields.get(a).getType().equals(String.class)) {
                    if (listFields.get(a).getAnnotation(AnnotationAttr.class).inc()) {
                        prp.setString(a + 1, connexion.incrementSequence(sequenceName) + "");
                    } else {
                        prp.setString(a + 1, insertion.get(cpt));
                        cpt++;
                    }
                } else if (listFields.get(a).getClass().equals(Date.class) || listFields.get(a).getClass().equals(java.sql.Date.class) || listFields.get(a).getClass().equals(LocalDate.class)) {
                    prp.setDate(a + 1, Function.dateByString(insertion.get(cpt)));
                    cpt++;
                } else if (listFields.get(a).getClass().equals(Integer.class)) {
                    if (listFields.get(a).getAnnotation(AnnotationAttr.class).inc()) {
                        prp.setInt(a + 1, connexion.incrementSequence(sequenceName));
                    } else {
                        prp.setInt(a + 1, Integer.parseInt(insertion.get(cpt)));
                        cpt++;
                    }
                } else if (listFields.get(a).getClass().equals(Double.class)) {
                    if (listFields.get(a).getAnnotation(AnnotationAttr.class).inc()) {
                        prp.setDouble(a + 1, connexion.incrementSequence(sequenceName));
                    } else {
                        prp.setDouble(a + 1, Double.parseDouble(insertion.get(cpt)));
                        cpt++;
                    }
                } else if (listFields.get(a).getClass().equals(Float.class)) {
                    if (listFields.get(a).getAnnotation(AnnotationAttr.class).inc()) {
                        prp.setFloat(a + 1, connexion.incrementSequence(sequenceName));
                    } else {
                        prp.setFloat(a + 1, Float.parseFloat(insertion.get(cpt)));
                        cpt++;
                    }
                }
            }
            prp.executeUpdate();
            connection.commit();            
        } catch (Exception e) {
            if (connection != null) connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (prp != null) prp.close();
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