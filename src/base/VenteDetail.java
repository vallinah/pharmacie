package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "vente_detail", sequence = "vente_detail_id_seq", prefix = "VDT", icone = "bi-ui-checks-grid")
public class VenteDetail {

    @AnnotationAttr(nameInBase = "id_vente_detail", inc = true)
    private String idVenteDetail;
    @AnnotationAttr(nameInBase = "quantite")
    private int quantite;
    @AnnotationAttr(nameInBase = "prix_unitaire")
    private double prixUnitaire;
    @AnnotationAttr(nameInBase = "id_vente")
    @ForeingKey(col = "prix_total", cls = "vente")
    private String idVente;

    public VenteDetail(ResultSet set) throws Exception {
        setIdVenteDetail(set.getString("id_vente_detail"));
        setQuantite(set.getInt("quantite"));
        setPrixUnitaire(set.getDouble("prix_unitaire"));
        setIdVente(set.getString("id_vente"));
    }

    public VenteDetail() {}

    public String getIdVenteDetail() {
        return idVenteDetail;
    }

    public void setIdVenteDetail(String idVenteDetail) {
        this.idVenteDetail = idVenteDetail;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getIdVente() {
        return idVente;
    }

    public void setIdVente(String idVente) {
        this.idVente = idVente;
    }
}
