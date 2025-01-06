package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "produit", sequence = "produit_id_seq", prefix = "PRD")
public class Produit {

    @AnnotationAttr(nameInBase = "id_produit", inc = true)
    private String id_produit;
    @AnnotationAttr(nameInBase = "nom_produit")
    private String nom_produit;
    @AnnotationAttr(nameInBase = "description", textarea = true)
    private String description;
    @AnnotationAttr(nameInBase = "id_type")
    @ForeingKey(col = "type", cls = "type")
    private String id_type;

    public Produit(String id_produit, String nom_produit, String description) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.description = description;
    }

    public Produit(ResultSet set) throws Exception {
        this.id_produit = set.getString("ID_PRODUIT");
        this.nom_produit = set.getString("NOM_PRODUIT");
        this.description = set.getString("DESCRIPTION");
    }

    // ! Getter and Setter

    public String getId_produit() {
        return id_produit;
    }

    public void setId_produit(String id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }
}