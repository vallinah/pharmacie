package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

/**
 *
 * @author
 */
@AnnotationClass(nameInBase = "unite_mesure", sequence = "unite_mesure_id_seq", prefix = "UNI")
public class UniteMesure {

    @AnnotationAttr(nameInBase = "id_unite_mesure", inc = true)
    String idUniteMesure;

    @AnnotationAttr(nameInBase = "unite_mesure")
    String uniteMesure;

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
