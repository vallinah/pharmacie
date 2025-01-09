package fn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import base.Mouvement;
import base.Produit;
import base.connexe.Connexion;

public class Functionality {

    public String getReqFn_2(String idCategoriePersonne, String idMode) throws Exception {
        String req = "SELECT (m.id_mouvement, m.quantite, m.prix_unitaire_achat, m.prix_unitaire_vente, m.date_mouvement, p.nom_produit)\n" + //
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
