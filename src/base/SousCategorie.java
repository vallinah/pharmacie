package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "sous_categorie", sequence = "sous_categorie_id_seq")
public class SousCategorie {

    @AnnotationAttr(nameInBase = "id_sous_categorie", showInForm = false)
    private String id_sous_categorie;
    @AnnotationAttr(nameInBase = "sous_categorie")
    private String sous_categorie;

    // ? Constructeur

    public SousCategorie(String id_sous_categorie, String sous_categorie) {
        this.id_sous_categorie = id_sous_categorie;
        this.sous_categorie = sous_categorie;
    }

    public SousCategorie(ResultSet set) throws Exception {
        id_sous_categorie = set.getString("ID_SOUS_CATEGORIE");
        sous_categorie = set.getString("SOUS_CATEGORIES");
    }

    // ! Getter and Setter

    public String getId_sous_categorie() {
        return id_sous_categorie;
    }

    public void setId_sous_categorie(String id_sous_categorie) {
        this.id_sous_categorie = id_sous_categorie;
    }

    public String getSous_categorie() {
        return sous_categorie;
    }

    public void setSous_categorie(String sous_categorie) {
        this.sous_categorie = sous_categorie;
    }
}