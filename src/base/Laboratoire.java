package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase="laboratoire", sequence="laboratoire_id_seq", prefix = "LAB")
public class Laboratoire {

    @AnnotationAttr(nameInBase="id_laboratoire", inc=true)
    private String idLaboratoire;
    @AnnotationAttr(nameInBase="nom_laboratoire")
    private String nomLaboratoire;


    public String getIdLaboratoire() {
        return idLaboratoire;
    }
    public void setIdLaboratoire(String idLaboratoire) {
        this.idLaboratoire = idLaboratoire;
    }
    public String getNomLaboratoire() {
        return nomLaboratoire;
    }
    public void setNomLaboratoire(String nomLaboratoire) {
        this.nomLaboratoire = nomLaboratoire;
    }
}