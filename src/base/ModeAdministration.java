package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "mode_administration", sequence = "mode_administration_id_seq", prefix = "MODA", icone = "bi-droplet", title = "Mode d' Administration")
public class ModeAdministration {

    @AnnotationAttr(nameInBase = "id_mode_administration", inc = true, id = true)
    private String idModeAdministration;
    @AnnotationAttr(nameInBase = "mode_administration")
    private String modeAdministration;

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
