package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "type", sequence = "type_id_seq")
public class Type {

    @AnnotationAttr(nameInBase = "id_type", inc = true)
    private String id_type;
    @AnnotationAttr(nameInBase = "type")
    private String type;

    // ? Constructeur

    public Type(String id_type, String type) {
        this.id_type = id_type;
        this.type = type;
    }

    public Type(ResultSet set) throws Exception {
        id_type = set.getString("ID_TYPE");
        type = set.getString("TYPE");
    }    

    // ! Getter and Setter

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}