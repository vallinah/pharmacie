package gst;

import java.lang.reflect.Field;
import java.util.Vector;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

public class CRUD {

    public Vector<Field> fieldMapped(Class<?> cls) throws Exception {
        Vector<Field> listFields = new Vector<>();
        Class<?> kilasy = cls;
        while (true) {
            if (kilasy.equals(Object.class))
                break;
            if (kilasy != null) {
                for (Field fld : kilasy.getDeclaredFields()) {
                    if (fld.isAnnotationPresent(AnnotationAttr.class)) {
                        if (fld.getAnnotation(AnnotationAttr.class).showInForm()) {
                            listFields.add(fld);
                        }
                    }
                }
            }
            kilasy = kilasy.getSuperclass();
        }
        return listFields;
    }

    public String scriptInsert(Class<?> cls) throws Exception {
        if (!cls.isAnnotationPresent(AnnotationClass.class))
            throw new Exception("Cette classe n'a aucun reference dans votre base de donnée");
        String nameInbase = cls.getAnnotation(AnnotationClass.class).nameInBase();

        Vector<String> a_inserer = new Vector<>();
        Vector<Field> listFields = fieldMapped(cls);
        for (Field f : listFields) {
            a_inserer.add(f.getName());
        }

        StringBuilder bld = new StringBuilder();
        bld.append("insert into " + nameInbase + "(");
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