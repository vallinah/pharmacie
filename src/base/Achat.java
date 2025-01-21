package base;

import java.sql.Date;
import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.ForeingKey;

public class Achat {
    
    @AnnotationAttr(nameInBase = "id_mouvement", inc = true)
    private String idMouvement;
    @AnnotationAttr(nameInBase = "quantite")
    private int quantite;
    @AnnotationAttr(nameInBase = "prix_achat_unitaire", insert = false, show = false)
    private double prixAchatUnitaire;
    @AnnotationAttr(nameInBase = "date_mouvement")
    private Date dateMouvement;
    @ForeingKey(cls = "produit", col = "nom_produit")
    @AnnotationAttr(nameInBase = "id_produit")
    private String idProduit;
    @ForeingKey(cls = "client", col = "nom_client")
    @AnnotationAttr(nameInBase = "id_client")
    private String idClient;

    public Achat(ResultSet resultSet) throws Exception {
        this.setIdMouvement(resultSet.getString("id_mouvement"));
        this.setQuantite(resultSet.getInt("quantite"));
        this.setPrixAchatUnitaire(resultSet.getDouble("prix_achat_unitaire"));
        this.setDateMouvement(resultSet.getDate("date_mouvement"));
        this.setIdProduit(resultSet.getString("id_produit"));
        this.setIdClient(resultSet.getString("id_client"));
    }

    public Achat() {
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

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
}
