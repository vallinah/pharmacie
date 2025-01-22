package fn;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import base.Client;
import base.CommissionVendeur;
import base.ConseilDuMois;
import base.Mouvement;
import base.Produit;
import base.connexe.Connexion;

public class Functionality {

    public Vector<CommissionVendeur> getReqFn_5(Date daty1, Date daty2) throws Exception {
        String req = "SELECT v.nom_vendeur, sum(\n" + //
                        "        (\n" + //
                        "            5 * m.prix_vente_unitaire * m.quantite\n" + //
                        "        ) / 100\n" + //
                        "    ) commission\n" + //
                        "FROM\n" + //
                        "    mouvement m\n" + //
                        "    JOIN vendeur v on v.id_vendeur = m.id_vendeur\n";
        if (daty1 != null && daty2 != null) {
            req += "WHERE\n" + //
                                "    date_mouvement BETWEEN ? and ?\n";
        }
        req += "GROUP BY\n" + //
                        "    v.id_vendeur";


        Vector<CommissionVendeur> all = new Vector<>();                
        Connexion connexion = Function.dbConnect();
        PreparedStatement prepared = null;
        ResultSet set = null;

        try {
            prepared = connexion.getConnexe().prepareStatement(req);
            if (daty1 != null && daty2 != null) {
                prepared.setDate(1, daty1);
                prepared.setDate(2, daty2);
            }

            set = prepared.executeQuery();
            while (set.next()) {
                all.add(new CommissionVendeur(set));
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

    public Vector<Client> getReqFn_4(Date daty) throws Exception {

        String req = "SELECT DISTINCT c.*\n" + //
                        "from mouvement m\n" + //
                        "    JOIN client c ON c.id_client = m.id_client\n" + //
                        "WHERE\n" + //
                        "    m.date_mouvement = ?";

        Vector<Client> all = new Vector<>();                
        Connexion connexion = Function.dbConnect();
        PreparedStatement prepared = null;
        ResultSet set = null;

        try {
            prepared = connexion.getConnexe().prepareStatement(req);
            prepared.setDate(1, daty);

            set = prepared.executeQuery();
            while (set.next()) {
                all.add(new Client(set));
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

    public Vector<ConseilDuMois> getReqFn_3(int month, int year) throws Exception {
        String req = "SELECT *\n" + //
                        "FROM\n" + //
                        "    conseil_du_mois cdm\n" + //
                        "    JOIN produit p ON p.id_produit = cdm.id_produit\n" + //
                        "WHERE (\n" + //
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
            prepared.setInt(1, year);
            prepared.setInt(2, year);

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
        String req = "SELECT distinct m.id_mouvement, m.quantite, m.prix_achat_unitaire, m.date_mouvement, p.id_produit, m.id_client\n" + //
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
                cnd_idMode += "    p.id_mode_administration = ?";
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
