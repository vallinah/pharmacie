package base;

import java.sql.Date;
import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "produit", sequence = "produit_id_seq", prefix = "PRO", icone = "bi-box-seam", page = "produit")
public class Produit {

    @AnnotationAttr(nameInBase = "id_produit", inc = true, id = true)
    private String idProduit;
    @AnnotationAttr(nameInBase = "nom_produit")
    private String nomProduit;
    @AnnotationAttr(nameInBase = "prix_vente_unitaire", show = false)
    private double prixVenteUnitaire;
    @AnnotationAttr(nameInBase = "description", textarea = true)
    private String description;
    
    @ForeingKey(cls = "mode_administration", col = "mode_administration")
    @AnnotationAttr(nameInBase = "id_mode_administration", show = false)
    private String idModeAdministration;

    @AnnotationAttr(nameInBase = "date_modification", show = false)
    private Date dateModification;

    // ? Constructeur

    public Produit(ResultSet set) throws Exception {
        idProduit  = set.getString("id_produit");
        nomProduit = set.getString("nom_produit");
        description = set.getString("description");
        idModeAdministration = set.getString("id_mode_administration");
        dateModification = set.getDate("date_modification");
    }

    public Produit() {}

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrixVenteUnitaire() {
        return prixVenteUnitaire;
    }

    public void setPrixVenteUnitaire(double prixVenteUnitaire) {
        this.prixVenteUnitaire = prixVenteUnitaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getIdModeAdministration() {
        return idModeAdministration;
    }

    public void setIdModeAdministration(String idModeAdministration) {
        this.idModeAdministration = idModeAdministration;
    }
}
