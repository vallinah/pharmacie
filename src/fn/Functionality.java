package fn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import base.ConseilDuMois;
import base.Mouvement;
import base.Produit;
import base.connexe.Connexion;

public class Functionality {

    public Vector<ConseilDuMois> getReqFn_3(int month, int year) throws Exception {
        String req = "SELECT *\n" + //
                        "FROM\n" + //
                        "    conseil_du_mois cdm\n" + //
                        "    JOIN produit p ON p.id_produit = cdm.id_produit\n" + //
                        "WHERE (\n" + //
                        "        EXTRACT(\n" + //
                        "            MONTH\n" + //
                        "            FROM cdm.date_debut\n" + //
                        "        ) >= ?\n" + //
                        "        and EXTRACT(\n" + //
                        "            MONTH\n" + //
                        "            FROM cdm.date_fin\n" + //
                        "        ) <= ?\n" + //
                        "    )\n" + //
                        "    and (\n" + //
                        "        EXTRACT(\n" + //
                        "            YEAR\n" + //
                        "            FROM cdm.date_debut\n" + //
                        "        ) >= ?\n" + //
                        "        and EXTRACT(\n" + //
                        "            YEAR\n" + //
                        "            FROM cdm.date_fin\n" + //
                        "        )\n" + //
                        "        <= ?\n" + //
                        "    );";

        Vector<ConseilDuMois> all = new Vector<>();

        Connexion connexion = Function.dbConnect();
        PreparedStatement prepared = null;
        ResultSet set = null;

        try {
            prepared = connexion.getConnexe().prepareStatement(req);
                prepared.setInt(1, month);
                prepared.setInt(2, month);
            prepared.setInt(3, year);
            prepared.setInt(4, year);

            set = prepared.executeQuery();
            while (set.next()) {
                all.add(new ConseilDuMois(set));
            }
            return all;
        } catch (Exception e) {
            throw  e;
        } finally {
            if (set != null) set.close();
            if (prepared != null) prepared.close();
            connexion.finaleClose();
        }
    }

    public String getReqFn_2(String idCategoriePersonne, String idMode) throws Exception {
        String req = "SELECT m.id_mouvement, m.quantite, m.prix_achat_unitaire, m.prix_vente_unitaire, m.date_mouvement, p.id_produit\n" + //
                        "from\n" + //
                        "    produit p\n" + //
                        "    JOIN produit_categorie_personne pcp ON pcp.id_produit = p.id_produit\n" + //
                        "    JOIN mouvement m ON m.id_produit = p.id_produit";

        boolean cnd = false;
        String cnd_categorie = "";
        String  cnd_idMode = "";
        boolean first = false;

        if (idCategoriePersonne != null) {
            if (!idCategoriePersonne.isEmpty()) {
                cnd_categorie = "    pcp.id_categorie_personne = ?";
                cnd = true;
                first = true;
            }
        }

        if (idMode != null) {
            if (!idMode.isEmpty()) {
                if (first) {
                    cnd_idMode = " and ";
                }
                cnd_idMode += "    id_mode_administration = ?";
                cnd = true;
            }
        }

        if (cnd) {
            req += " where \n";
        }

        req += cnd_categorie + cnd_idMode;
        return req;
    }
    
    public Vector<Mouvement> getFn_2(String idCategoriePersonne, String idMode) throws Exception {
        
        String req = getReqFn_2(idCategoriePersonne, idMode);
        Vector<Mouvement> all = new Vector<>();

        Connexion connexion = Function.dbConnect();
        PreparedStatement prepared = null;
        ResultSet set = null;

        try {
            prepared = connexion.getConnexe().prepareStatement(req);
            int cpt = 1;
            if (idCategoriePersonne != null) {
                if (!idCategoriePersonne.isEmpty()) {
                    prepared.setString(cpt, idCategoriePersonne);
                    cpt++;
                }
            }
    
            if (idMode != null) {
                if (!idMode.isEmpty()) {
                    prepared.setString(cpt, idMode);
                    cpt++;
                }
            }
            set = prepared.executeQuery();
            while (set.next()) {
                all.add(new Mouvement(set));
            }
            return all;
        } catch (Exception e) {
            throw  e;
        } finally {
            if (set != null) set.close();
            if (prepared != null) prepared.close();
            connexion.finaleClose();
        }
    }

    public Vector<Produit> getFonctinnalite(String idMaladie, String idCategoriePersonne) throws Exception {
        Vector<Produit> all = new Vector<>();
        String req = "SELECT *\n" + //
                        "from\n" + //
                        "    produit p\n" + //
                        "    JOIN produit_categorie_personne pcp on pcp.id_produit = p.id_produit\n" + //
                        "    JOIN produit_maladie pm on pm.id_produit = p.id_produit\n" + //
                        "WHERE\n" + //
                        "    pm.id_maladie = ?\n" + //
                        "    AND pcp.id_categorie_personne = ?";
        Connexion connexion = Function.dbConnect();
        PreparedStatement prepared = null;
        ResultSet set = null;

        try {
            prepared = connexion.getConnexe().prepareStatement(req);
            prepared.setString(1, idMaladie);
            prepared.setString(2, idCategoriePersonne);

            set = prepared.executeQuery();
            
            while (set.next()) {
                all.add(new Produit(set));
            }
            return all;
        } catch (Exception e) {
            throw  e;
        } finally {
            if (set != null) set.close();
            if (prepared != null) prepared.close();
            connexion.finaleClose();
        }
    }
}
