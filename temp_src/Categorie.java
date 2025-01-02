package base;

import annotation.*;
import java.sql.ResultSet;

@AnnotationClass(nameInBase = "categorie", sequence = "categorie_id_seq")
public class Categorie {

    @AnnotationAttr(nameInBase = "id_categorie", inc = true)
    private String id_categorie;
    @AnnotationAttr(nameInBase = "categorie")
    private String categorie;

    // ? Constructor

    public Categorie(String id_categorie, String categorie) {
        this.id_categorie = id_categorie;
        this.categorie = categorie;
    }

    public Categorie(ResultSet set) throws Exception {
        this.id_categorie = set.getString("ID_CATEGORIE");
        this.categorie = set.getString("CATEGORIE");
    }

    // ! Getter and Setter

    public String getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(String id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}