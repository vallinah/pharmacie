package base;

import java.sql.Date;
import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "vente", sequence = "vente_id_seq", prefix = "VNT", icone = "bi-credit-card")
public class Vente {

    @AnnotationAttr(nameInBase = "id_vente", inc = true, id = true)
    private String idVente;
    @AnnotationAttr(nameInBase = "prix_total")
    private double prixTotal;
    @AnnotationAttr(nameInBase = "date_vente")
    private Date dateVente;
    @AnnotationAttr(nameInBase = "id_commission")
    @ForeingKey(col = "commission", cls = "commission")
    private String idCommission;
    @ForeingKey(cls = "produit", col = "nom_produit")
    @AnnotationAttr(nameInBase = "id_produit")
    private String idProduit;
    @ForeingKey(cls = "client", col = "nom_client")
    @AnnotationAttr(nameInBase = "id_client")
    private String idClient;
    @ForeingKey(cls = "vendeur", col = "nom_vendeur")
    @AnnotationAttr(nameInBase = "id_vendeur")
    private String idVendeur;

    public Vente(ResultSet set) throws Exception {
        setIdVente(set.getString("id_vente"));
        setPrixTotal(set.getDouble("prix_total"));
        setDateVente(set.getDate("date_vente"));
        setIdCommission(set.getString("id_commissioin"));
        setIdProduit(set.getString("id_produit"));
        setIdClient(set.getString("id_client"));
        setIdVendeur(set.getString("id_vendeur"));
    }

    public Vente() {}

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

    public String getIdVente() {
        return idVente;
    }

    public void setIdVente(String idVente) {
        this.idVente = idVente;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public String getIdCommission() {
        return idCommission;
    }

    public void setIdCommission(String idCommission) {
        this.idCommission = idCommission;
    }

    public String getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(String idVendeur) {
        this.idVendeur = idVendeur;
    }
}
