package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

/**
 *
 * @author
 */
@AnnotationClass(nameInBase = "produit_maladie", sequence="produit_maladie_id_seq")
public class ProduitMaladie {

    @AnnotationAttr(nameInBase = "id_produit", inc = true)
    String idProduit;

    @AnnotationAttr(nameInBase = "id_maladie")
    String idMaladie;

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
