package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "vendeur", sequence = "vendeur_id_seq", prefix = "VND")
public class Vendeur {

    @AnnotationAttr(nameInBase = "id_vendeur", inc = true)
    String idVendeur;
    @AnnotationAttr(nameInBase = "nom_vendeur")
    String nomVendeur;

    public Vendeur(ResultSet set) throws Exception {
        idVendeur = set.getString("id_vendeur");
        nomVendeur = set.getString("nom_vendeur");
    }

    public String getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(String idVendeur) {
        this.idVendeur = idVendeur;
    }

    public String getNomVendeur() {
        return nomVendeur;
    }

    public void setNomVendeur(String nomVendeur) {
        this.nomVendeur = nomVendeur;
    }
}
