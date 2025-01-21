package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

/**
 *
 * @author
 */
@AnnotationClass(nameInBase = "produit_categorie_personne", sequence="produit_categorie_personne_id_seq", prefix = "PCP")
public class ProduitCategoriePersonne {

    @AnnotationAttr(nameInBase = "id_produit_categorie_personne", inc = true)
    String idProduitCategoriePersonne;

    @AnnotationAttr(nameInBase = "id_produit")
    @ForeingKey(col = "nom_produit", cls = "produit")
    String idProduit;

    @AnnotationAttr(nameInBase = "id_categorie_personne")
    @ForeingKey(col = "categorie_personne", cls = "categorie_personne")
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
