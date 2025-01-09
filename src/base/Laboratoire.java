/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

/**
 *
 * @author
 */
@AnnotationClass(nameInBase="laboratoire", sequence="laboratoire_id_seq", prefix = "LAB")
public class Laboratoire {

    @AnnotationAttr(nameInBase="id_laboratoire", inc=true)
    String idLaboratoire;
    @AnnotationAttr(nameInBase="nom_laboratoire")
    String nomLaboratoire;


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