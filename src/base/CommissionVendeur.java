package base;

import java.sql.ResultSet;

public class CommissionVendeur {
    public String nom;
    public double commission;

    public CommissionVendeur(ResultSet set)  throws Exception{
        this.nom = set.getString("nom_vendeur");
        this.commission = set.getDouble("commission");
    }
}
