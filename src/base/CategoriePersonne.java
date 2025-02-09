package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "categorie_personne", sequence = "categorie_personne_id_seq", prefix = "CATP", icone = "bi-person-badge", title = "Categorie de Personne")
public class CategoriePersonne {

    @AnnotationAttr(nameInBase = "id_categorie_personne", inc = true, id = true)
    String idCategoriePersonne;
    @AnnotationAttr(nameInBase = "categorie_personne", id = true)
    String categoriePersonne;

    public String getIdCategoriePersonne() {
        return idCategoriePersonne;
    }

    public void setIdCategoriePersonne(String idCategoriePersonne) {
        this.idCategoriePersonne = idCategoriePersonne;
    }

    public String getCategoriePersonne() {
        return categoriePersonne;
    }

    public void setCategoriePersonne(String categoriePersonne) {
        this.categoriePersonne = categoriePersonne;
    }
}