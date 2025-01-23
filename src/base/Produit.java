package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "produit", sequence = "produit_id_seq", prefix = "PRD")
public class Produit {

    @AnnotationAttr(nameInBase = "id_produit", inc = true)
    private String idProduit;
    @AnnotationAttr(nameInBase = "nom_produit")
    private String nomProduit;
    @AnnotationAttr(nameInBase = "prix_vente_unitaire", show = false)
    private double prixVenteUnitaire;
    @AnnotationAttr(nameInBase = "nombre", show = false)
    private int nombre;
    @AnnotationAttr(nameInBase = "prix_achat_unitaire", show = false)
    private double prixAchatUnitaire;
    @AnnotationAttr(nameInBase = "quantite", show = false)
    private String quantite;
    @AnnotationAttr(nameInBase = "description", textarea = true)
    private String description;
    
    @AnnotationAttr(nameInBase = "id_forme", show = false)
    @ForeingKey(cls = "forme", col = "forme")
    private String idForme;

    @ForeingKey(cls = "mode_administration", col = "mode_administration")
    @AnnotationAttr(nameInBase = "id_mode_administration", show = false)
    private String idModeAdministration;

    @ForeingKey(cls = "laboratoire", col = "nom_laboratoire")
    @AnnotationAttr(nameInBase = "id_laboratoire")
    private String idLaboratoire;

    // ? Constructeur

    public Produit(ResultSet set) throws Exception {
        idProduit  = set.getString("id_produit");
        nomProduit = set.getString("nom_produit");
        prixAchatUnitaire = set.getDouble("prix_vente_unitaire");
        nombre = set.getInt("nombre");
        prixAchatUnitaire = set.getDouble("prix_achat_unitaire");
        quantite = set.getString("quantite");
        description = set.getString("description");
        idForme = set.getString("idForme");
        idModeAdministration = set.getString("id_mode_administration");
        idLaboratoire = set.getString("id_laboratoire");
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
