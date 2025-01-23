package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "produit_maladie", sequence="produit_maladie_id_seq", prefix = "PMD")
public class ProduitMaladie {

    @AnnotationAttr(nameInBase = "id_produit_maladie", inc = true)
    private String idProduitMaladie;

    @ForeingKey(col = "nom_produit", cls = "produit")
    @AnnotationAttr(nameInBase = "id_produit")
    private String idProduit;

    @ForeingKey(col = "nom_maladie", cls = "maladie")
    @AnnotationAttr(nameInBase = "id_maladie")
    private String idMaladie;

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
