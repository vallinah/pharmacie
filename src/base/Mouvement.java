package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;
import java.sql.Date;
import java.sql.ResultSet;

@AnnotationClass(nameInBase = "mouvement", sequence = "mouvement_id_seq")
public class Mouvement {
    @AnnotationAttr(nameInBase = "id_mouvement", inc = true)
    private String idMouvement;
    @AnnotationAttr(nameInBase = "quantite")
    private int quantite;
    @AnnotationAttr(nameInBase = "prix_achat_unitaire")
    private double prixAchatUnitaire;
    @AnnotationAttr(nameInBase = "prix_vente_unitaire")
    private double prixVenteUnitaire;
    @AnnotationAttr(nameInBase = "date_mouvement")
    private Date dateMouvement;
    @ForeingKey(cls = "produit", col = "nom_produit")
    @AnnotationAttr(nameInBase = "id_produit")
    private String idProduit;

    public Mouvement(ResultSet resultSet) throws Exception {
        this.setIdMouvement(resultSet.getString("id_mouvement"));
        this.setQuantite(resultSet.getInt("quantite"));
        this.setPrixAchatUnitaire(resultSet.getDouble("prix_achat_unitaire"));
        this.setPrixVenteUnitaire(resultSet.getDouble("prix_vente_unitaire"));
        this.setDateMouvement(resultSet.getDate("date_mouvement"));
        this.setIdProduit(resultSet.getString("id_produit"));
    }

    public Mouvement() {
    }

    public String getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(String idMouvement) {
        this.idMouvement = idMouvement;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixAchatUnitaire() {
        return prixAchatUnitaire;
    }

    public void setPrixAchatUnitaire(double prixAchatUnitaire) {
        this.prixAchatUnitaire = prixAchatUnitaire;
    }

    public double getPrixVenteUnitaire() {
        return prixVenteUnitaire;
    }

    public void setPrixVenteUnitaire(double prixVenteUnitaire) {
        this.prixVenteUnitaire = prixVenteUnitaire;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

}
