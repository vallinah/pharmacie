package base;

import java.sql.Date;
import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "commission", sequence = "commission_id_seq", prefix = "CDM", icone = "bi-piggy-bank")
public class Commission {
    
    @AnnotationAttr(nameInBase = "id_commission", inc = true)
    private String idCommission;
    @AnnotationAttr(nameInBase = "commission")
    private double commission;
    @AnnotationAttr(nameInBase = "date_commission")
    private Date dateCommission;

    public Commission(ResultSet set) throws Exception {
        idCommission = set.getString("id_commission");
        commission = set.getDouble("commission");
        dateCommission = set.getDate("date_commission");
    }

    public Commission() {}

    public String getIdCommission() {
        return idCommission;
    }
    public void setIdCommission(String idCommission) {
        this.idCommission = idCommission;
    }
    public double getCommission() {
        return commission;
    }
    public void setCommission(double commission) {
        this.commission = commission;
    }
    public Date getDateCommission() {
        return dateCommission;
    }
    public void setDateCommission(Date dateCommission) {
        this.dateCommission = dateCommission;
    }
}
