package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;
import java.sql.Date;

@AnnotationClass(nameInBase = "mouvement", sequence = "mouvement_id_seq")
public class Mouvement {
    @AnnotationAttr(nameInBase = "id_mouvement", inc = true)
    private String idMouvement;
    @AnnotationAttr(nameInBase = "quantite")
    private int quantite;
    @AnnotationAttr(nameInBase = "prix_unitaire_achat")
    private double prixUnitaireAchat;
    @AnnotationAttr(nameInBase = "prix_unitaire_vente")
    private double prixUnitaireVente;
    @AnnotationAttr(nameInBase = "date_mouvement")
    private Date dateMouvement;
    @ForeingKey(cls = "produit", col = "nom_produit")
    @AnnotationAttr(nameInBase = "id_produit")
    private String idProduit;

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

    public double getPrixUnitaireAchat() {
        return prixUnitaireAchat;
    }

    public void setPrixUnitaireAchat(double prixUnitaireAchat) {
        this.prixUnitaireAchat = prixUnitaireAchat;
    }

    public double getPrixUnitaireVente() {
        return prixUnitaireVente;
    }

    public void setPrixUnitaireVente(double prixUnitaireVente) {
        this.prixUnitaireVente = prixUnitaireVente;
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
