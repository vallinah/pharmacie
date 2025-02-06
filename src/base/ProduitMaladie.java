package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "produit_maladie", prefix = "PMD")
public class ProduitMaladie {

    @ForeingKey(col = "nom_produit", cls = "produit")
    @AnnotationAttr(nameInBase = "id_produit")
    private String idProduit;

    @ForeingKey(col = "nom_maladie", cls = "maladie")
    @AnnotationAttr(nameInBase = "id_maladie")
    private String idMaladie;

    public ProduitMaladie(ResultSet set) throws Exception {
        setIdProduit(set.getString("id_produit"));
        setIdMaladie(set.getString("id_maladie"));
    }

    public ProduitMaladie() {}

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getIdMaladie() {
        return idMaladie;
    }

    public void setIdMaladie(String idMaladie) {
        this.idMaladie = idMaladie;
    }
}
