package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

/**
 *
 * @author
 */
@AnnotationClass(nameInBase = "produit_categorie_personne", sequence="produit_categorie_personne_id_seq", prefix = "PCP")
public class ProduitCategoriePersonne {

    @AnnotationAttr(nameInBase = "id_produit", inc = true)
    String idProduit;

    @AnnotationAttr(nameInBase = "id_categorie_personne")
    String idCategoriePersonne;

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getIdCategoriePersonne() {
        return idCategoriePersonne;
    }

    public void setIdCategoriePersonne(String idCategoriePersonne) {
        this.idCategoriePersonne = idCategoriePersonne;
    }
}
