package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;

@AnnotationClass(nameInBase = "forme", sequence = "forme_id_seq", prefix = "FRM")
public class Forme {

    @AnnotationAttr(nameInBase = "id_forme", inc = true)
    private String idForme;
    @AnnotationAttr(nameInBase = "forme")
    private String forme;
    @AnnotationAttr(nameInBase = "id_unite_mesure")
    @ForeingKey(col = "unite_mesure", cls = "unite_mesure")
    private String idUniteMesure;

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
