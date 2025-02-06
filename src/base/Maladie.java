package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "maladie", sequence = "maladie_id_seq", prefix = "MLD", icone = "bi-virus")
public class Maladie {

    @AnnotationAttr(nameInBase = "id_maladie", inc = true)
    private String idMaladie;
    @AnnotationAttr(nameInBase = "nom_maladie")
    private String nomMaladie;

    public String getIdMaladie() {
        return idMaladie;
    }

    public void setIdMaladie(String idMaladie) {
        this.idMaladie = idMaladie;
    }

    public String getNomMaladie() {
        return nomMaladie;
    }

    public void setNomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
    }
}
