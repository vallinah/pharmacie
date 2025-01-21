package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

/**
 *
 * @author
 */
@AnnotationClass(nameInBase = "mode_administration", sequence = "mode_administration_id_seq", prefix = "MOD")
public class ModeAdministration {

    @AnnotationAttr(nameInBase = "id_mode_administration", inc = true)
    String idModeAdministration;

    @AnnotationAttr(nameInBase = "mode_administration")
    String modeAdministration;

    public String getIdModeAdministration() {
        return idModeAdministration;
    }

    public void setIdModeAdministration(String idModeAdministration) {
        this.idModeAdministration = idModeAdministration;
    }

    public String getModeAdministration() {
        return modeAdministration;
    }

    public void setModeAdministration(String modeAdministration) {
        this.modeAdministration = modeAdministration;
    }
}
