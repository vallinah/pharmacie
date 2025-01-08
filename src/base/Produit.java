package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "produit", sequence = "produit_id_seq")
public class Produit {
    @AnnotationAttr(nameInBase = "id_produit", inc = true)
    String idProduit;
    @AnnotationAttr(nameInBase = "nom_produit")
    String nomProduit;
    @AnnotationAttr(nameInBase = "prix_vente_unitaire")
    double prixVenteUnitaire;
    @AnnotationAttr(nameInBase = "nombre")
    int nombre;
    @AnnotationAttr(nameInBase = "prix_achat_unitaire")
    double prixAchatUnitaire;
    @AnnotationAttr(nameInBase = "quantite")
    String quantite;
    @AnnotationAttr(nameInBase = "description", textarea = true)
    String description;
    @AnnotationAttr(nameInBase = "id_forme")
    @ForeingKey(cls = "forme", col = "forme")
    String idForme;
    @ForeingKey(cls = "mode_administration", col = "mode_administration")
    @AnnotationAttr(nameInBase = "id_mode_administration")
    String idModeAdministration;
    @ForeingKey(cls = "laboratoire", col = "nom_laboratoire")
    @AnnotationAttr(nameInBase = "id_laboratoire")
    String idLaboratoire;

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

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public double getPrixAchatUnitaire() {
        return prixAchatUnitaire;
    }

    public void setPrixAchatUnitaire(double prixAchatUnitaire) {
        this.prixAchatUnitaire = prixAchatUnitaire;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdForme() {
        return idForme;
    }

    public void setIdForme(String idForme) {
        this.idForme = idForme;
    }

    public String getIdModeAdministration() {
        return idModeAdministration;
    }

    public void setIdModeAdministration(String idModeAdministration) {
        this.idModeAdministration = idModeAdministration;
    }

    public String getIdLaboratoire() {
        return idLaboratoire;
    }

    public void setIdLaboratoire(String idLaboratoire) {
        this.idLaboratoire = idLaboratoire;
    }
}
