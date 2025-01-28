package base;

import java.sql.Date;
import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "historique_prix_produit", page = "historique.jsp")
public class Historique {
    
    @AnnotationAttr(nameInBase = "id_historique", insert = false)
    private int idHistorique;

    @ForeingKey(cls = "produit", col = "nom_produit")
    @AnnotationAttr(nameInBase = "id_produit")
    private String idProduit;

    @AnnotationAttr(nameInBase = "prix_vente_unitaire")
    private double prixVenteUnitaire;

    @AnnotationAttr(nameInBase = "date_update")
    private Date dateUpdate;

    public Historique(ResultSet set) throws Exception {
        idHistorique = set.getInt("id_historique");
        idProduit = set.getString("id_produit");
        prixVenteUnitaire = set.getDouble("prix_vente_unitaire");
        dateUpdate = set.getDate("date_update");
    }

    public int getIdHistorique() {
        return idHistorique;
    }
    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }
    public String getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }
    public double getPrixVenteUnitaire() {
        return prixVenteUnitaire;
    }
    public void setPrixVenteUnitaire(double prixVenteUnitaire) {
        this.prixVenteUnitaire = prixVenteUnitaire;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
