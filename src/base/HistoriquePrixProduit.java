package base;

import java.sql.Date;
import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "historique_prix_produit", sequence = "historique_prix_produit_id_seq", prefix = "HST")
public class HistoriquePrixProduit {
 
    @AnnotationAttr(nameInBase = "id_historique_prix_produit", inc = true)
    private String idHistorique;
    @AnnotationAttr(nameInBase = "prix_vente_unitaire")
    private double prixVenteUnitaire;
    @AnnotationAttr(nameInBase = "date_modification")
    private Date dateModification;
    @AnnotationAttr(nameInBase = "id_produit")
    @ForeingKey(col = "nom_produit", cls = "produit")
    private String idProduit;

    public HistoriquePrixProduit(ResultSet set) throws Exception {
        setIdHistorique(set.getString("id_historique_prix_produit"));
        setPrixVenteUnitaire(set.getDouble("prix_vente_unitaire"));
        setDateModification(set.getDate("date_modification"));
        setIdProduit(set.getString("id_produit"));
    }

    public HistoriquePrixProduit() {}

    public String getIdHistorique() {
        return idHistorique;
    }
    public void setIdHistorique(String idHistorique) {
        this.idHistorique = idHistorique;
    }
    public double getPrixVenteUnitaire() {
        return prixVenteUnitaire;
    }
    public void setPrixVenteUnitaire(double prixVenteUnitaire) {
        this.prixVenteUnitaire = prixVenteUnitaire;
    }
    public Date getDateModification() {
        return dateModification;
    }
    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }
    public String getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }
}
