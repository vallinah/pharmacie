package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

/**
 *
 * @author
 */
@AnnotationClass(nameInBase = "forme", sequence = "forme_id_seq")
public class Forme {

    @AnnotationAttr(nameInBase = "id_forme", inc = true)
    String idForme;

    @AnnotationAttr(nameInBase = "forme")
    String forme;

    @AnnotationAttr(nameInBase = "id_unite_mesure")
    String idUniteMesure;

    public String getIdForme() {
        return idForme;
    }

    public void setIdForme(String idForme) {
        this.idForme = idForme;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public String getIdUniteMesure() {
        return idUniteMesure;
    }

    public void setIdUniteMesure(String idUniteMesure) {
        this.idUniteMesure = idUniteMesure;
    }
}
