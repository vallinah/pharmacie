package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "unite_mesure", sequence = "unite_mesure_id_seq", prefix = "UNI")
public class UniteMesure {

    @AnnotationAttr(nameInBase = "id_unite_mesure", inc = true)
    private String idUniteMesure;

    @AnnotationAttr(nameInBase = "unite_mesure")
    private String uniteMesure;

    public String getIdUniteMesure() {
        return idUniteMesure;
    }

    public void setIdUniteMesure(String idUniteMesure) {
        this.idUniteMesure = idUniteMesure;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }
}
