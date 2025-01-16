package base;

import java.sql.Date;
import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "conseil_du_mois", sequence = "conseil_du_mois_id_seq", page = "conseildumois.jsp")
public class ConseilDuMois {
    
    @AnnotationAttr(nameInBase = "id_conseil_du_mois", inc = true)
    String id_conseil_du_mois;
    @AnnotationAttr(nameInBase = "date_debut")
    Date dateDebut;
    @AnnotationAttr(nameInBase = "date_fin")
    Date dateFin;

    @ForeingKey(col = "nom_produit", cls = "produit")
    @AnnotationAttr(nameInBase = "id_produit")
    String idProduit;

    public ConseilDuMois(ResultSet set) throws Exception {
        id_conseil_du_mois = set.getString("id_conseil_du_mois");
        dateDebut = set.getDate("date_debut");
        dateFin = set.getDate("date_fin");
        idProduit = set.getString("id_produit");
    }

    public String getId_conseil_du_mois() {
        return id_conseil_du_mois;
    }

    public void setId_conseil_du_mois(String id_conseil_du_mois) {
        this.id_conseil_du_mois = id_conseil_du_mois;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    
}
