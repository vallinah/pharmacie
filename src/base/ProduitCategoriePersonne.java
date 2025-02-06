package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "produit_categorie_personne", icone = "bi-person-lines-fill")
public class ProduitCategoriePersonne {

    @AnnotationAttr(nameInBase = "id_produit")
    @ForeingKey(col = "nom_produit", cls = "produit")
    private String idProduit;

    @AnnotationAttr(nameInBase = "id_categorie_personne")
    @ForeingKey(col = "categorie_personne", cls = "categorie_personne")
    private String idCategoriePersonne;

    public ProduitCategoriePersonne(ResultSet set) throws Exception {
        setIdProduit(set.getString("id_produit"));
        setIdCategoriePersonne(set.getString("id_categorie_personne"));
    }

    public ProduitCategoriePersonne() {}

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
