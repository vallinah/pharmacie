package fn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import base.connexe.Connexion;

public class Functionality {

    // public Vector<Historique> getHistorique(String idProduit, Date d1, Date d2) throws Exception {
    //     String req = "SELECT * FROM historique_prix_produit hpp\n" + //
    //                     "WHERE 1 = 1\n";
    //     if (!idProduit.equals("")) {
    //         req += "AND id_produit = ?\n";
    //     }
    //     if (d1 != null) {
    //         req += "AND date_update >= ?\n";
    //     }

    //     if (d2 != null) {
    //         req += "AND date_update <= ?\n";
    //     }

    //     Vector<Historique> all = new Vector<>();                
    //     Connexion connexion = Function.dbConnect();
    //     PreparedStatement prepared = null;
    //     ResultSet set = null;

    //     try {
    //         prepared = connexion.getConnexe().prepareStatement(req);
    //         int cpt = 0;
    //         if (!idProduit.equals("")) {
    //             prepared.setString(++cpt, idProduit);
    //         }
    //         if (d1 != null) {
    //             prepared.setDate(++cpt, d1);
    //         }
    //         if (d2 != null) {
    //             prepared.setDate(++cpt, d2);
    //         }
    //         set = prepared.executeQuery();
    //         while (set.next()) {
    //             all.add(new Historique(set));
    //         }
    //         return all;
    //     } catch (Exception e) {
    //         throw  e;
    //     } finally {
    //         if (set != null) set.close();
    //         if (prepared != null) prepared.close();
    //         connexion.finaleClose();
    //     }        
    // }

    // public Vector<CommissionVendeur> getReqFn_5(Date daty1, Date daty2) throws Exception {
    //     String req = "SELECT v.nom_vendeur, sum(\n" + //
    //                     "        (\n" + //
    //                     "            5 * m.prix_vente_unitaire * m.quantite\n" + //
    //                     "        ) / 100\n" + //
    //                     "    ) commission\n" + //
    //                     "FROM\n" + //
    //                     "    mouvement m\n" + //
    //                     "    JOIN vendeur v on v.id_vendeur = m.id_vendeur\n";
    //     if (daty1 != null && daty2 != null) {
    //         req += "WHERE\n" + //
    //                             "    date_mouvement BETWEEN ? and ?\n";
    //     }
    //     req += "GROUP BY\n" + //
    //                     "    v.id_vendeur";


    //     Vector<CommissionVendeur> all = new Vector<>();                
    //     Connexion connexion = Function.dbConnect();
    //     PreparedStatement prepared = null;
    //     ResultSet set = null;

    //     try {
    //         prepared = connexion.getConnexe().prepareStatement(req);
    //         if (daty1 != null && daty2 != null) {
    //             prepared.setDate(1, daty1);
    //             prepared.setDate(2, daty2);
    //         }

    //         set = prepared.executeQuery();
    //         while (set.next()) {
    //             all.add(new CommissionVendeur(set));
    //         }
    //         return all;
    //     } catch (Exception e) {
    //         throw  e;
    //     } finally {
    //         if (set != null) set.close();
    //         if (prepared != null) prepared.close();
    //         connexion.finaleClose();
    //     }        
    // }

    // public Vector<Client> getReqFn_4(Date daty) throws Exception {

    //     String req = "SELECT DISTINCT c.*\n" + //
    //                     "from mouvement m\n" + //
    //                     "    JOIN client c ON c.id_client = m.id_client\n" + //
    //                     "WHERE\n" + //
    //                     "    m.date_mouvement = ?";

    //     Vector<Client> all = new Vector<>();                
    //     Connexion connexion = Function.dbConnect();
    //     PreparedStatement prepared = null;
    //     ResultSet set = null;

    //     try {
    //         prepared = connexion.getConnexe().prepareStatement(req);
    //         prepared.setDate(1, daty);

    //         set = prepared.executeQuery();
    //         while (set.next()) {
    //             all.add(new Client(set));
    //         }
    //         return all;
    //     } catch (Exception e) {
    //         throw  e;
    //     } finally {
    //         if (set != null) set.close();
    //         if (prepared != null) prepared.close();
    //         connexion.finaleClose();
    //     }        
    // } 

    public Vector<Vector<String>> getFonctinnalite3(String date1, String date2) throws Exception {
        Vector<Vector<String>> all = new Vector<>();
        String req = "SELECT cdm.id_conseil_du_mois, cdm.date_debut, cdm.date_fin, p.nom_produit, cdm.id_conseil_du_mois\n" + //
                        "FROM\n" + //
                        "    conseil_du_mois cdm\n" + //
                        "    JOIN produit p ON p.id_produit = cdm.id_produit\n" + //
                        "WHERE\n" + //
                        "    1 = 1\n";
        Connexion connexion = Function.dbConnect();
        PreparedStatement prepared = null;
        ResultSet set = null;
        
        String[] splitDate1 = null, splitDate2 = null;

        int counter = 0;

        String month = "and 1 = 1\n";
        String year = "and 1 = 1\n";

        if (!date1.isEmpty()) {
            month += "and EXTRACT(\n" + //
                                "            MONTH\n" + //
                                "            FROM cdm.date_debut\n" + //
                                "        ) >= ?\n";
            year += "and EXTRACT(\n" + //
                                "            YEAR\n" + //
                                "            FROM cdm.date_debut\n" + //
                                "        ) >= ?\n";
            counter += 2;
            splitDate1 = date1.split("-");
        }
        if (!date2.isEmpty()) {
            month += "and EXTRACT(\n" + //
                                "            MONTH\n" + //
                                "            FROM cdm.date_fin\n" + //
                                "        ) <= ?\n";
            year += "and EXTRACT(\n" + //
                                "            YEAR\n" + //
                                "            FROM cdm.date_fin\n" + //
                                "        ) <= ?\n";
            counter += 2;
            splitDate2 = date2.split("-");
        }

        req += month; req += year;

        try {
            prepared = connexion.getConnexe().prepareStatement(req);
            if (!date1.isEmpty()) {
                prepared.setInt(1, Integer.parseInt(splitDate1[1]));
                if (counter == 4) {
                    prepared.setInt(3, Integer.parseInt(splitDate1[0]));
                } else {
                    prepared.setInt(2, Integer.parseInt(splitDate1[0]));
                }
            }
            if (!date2.isEmpty()) {
                if (counter == 4) {
                    prepared.setInt(2,Integer.parseInt(splitDate2[1]));
                    prepared.setInt(4,Integer.parseInt(splitDate2[0]));
                } else {
                    prepared.setInt(1,Integer.parseInt(splitDate2[1]));
                    prepared.setInt(2,Integer.parseInt(splitDate2[0]));
                }
            }

            set = prepared.executeQuery();
            ResultSetMetaData metaData = set.getMetaData();
            
            while (set.next()) {
                Vector<String> list = new Vector<>();
                for (int a = 0; a < metaData.getColumnCount(); a++) {
                    list.add(set.getString(a + 1));
                }
                all.add(list);
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


    public Vector<Vector<String>> getFonctinnalite2(String idCategoriePersonne, String idModeAdministratioin) throws Exception {
        Vector<Vector<String>> all = new Vector<>();
        String req = "SELECT v.id_vente, v.prix_total, v.date_vente, c.commission, p.nom_produit, cli.nom_client, vnd.nom_vendeur, v.id_vendeur\n" + //
                        "FROM\n" + //
                        "    vente v\n" + //
                        "    JOIN produit p ON p.id_produit = v.id_produit\n" + //
                        "    JOIN produit_categorie_personne pcp ON pcp.id_produit = p.id_produit\n" + //
                        "    JOIN commission c ON c.id_commission = v.id_commission\n" + //
                        "    JOIN client cli ON cli.id_client = v.id_client\n" + //
                        "    JOIN vendeur vnd ON vnd.id_vendeur = v.id_vendeur\n" + //
                        "WHERE 1 = 1\n";
        Connexion connexion = Function.dbConnect();
        PreparedStatement prepared = null;
        ResultSet set = null;
        if (!idCategoriePersonne.isEmpty()) {
            req += "and pcp.id_categorie_personne = ?\n";
        }
        if (!idModeAdministratioin.isEmpty()) {
            req += "and p.id_mode_administration = ?\n";
        }

        try {
            prepared = connexion.getConnexe().prepareStatement(req);
            int cpt = 0;
            if (!idCategoriePersonne.isEmpty()) {
                prepared.setString(++cpt, idCategoriePersonne);
            }
            if (!idModeAdministratioin.isEmpty()) {
                prepared.setString(++cpt, idModeAdministratioin);
            }

            set = prepared.executeQuery();
            ResultSetMetaData metaData = set.getMetaData();
            
            while (set.next()) {
                Vector<String> list = new Vector<>();
                for (int a = 0; a < metaData.getColumnCount(); a++) {
                    list.add(set.getString(a + 1));
                }
                all.add(list);
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

    public Vector<Vector<String>> getFonctinnalite(String idMaladie, String idCategoriePersonne) throws Exception {
        Vector<Vector<String>> all = new Vector<>();
        String req = "SELECT DISTINCT p.id_produit, p.nom_produit, p.description, mda.mode_administration, p.id_produit\n" + //
                        "from\n" + //
                        "    produit p\n" + //
                        "    JOIN produit_categorie_personne pcp on pcp.id_produit = p.id_produit\n" + //
                        "    JOIN produit_maladie pm on pm.id_produit = p.id_produit\n" + //
                        "    JOIN mode_administration mda ON mda.id_mode_administration = p.id_mode_administration\n" + //
                        "WHERE 1 = 1\n";
        Connexion connexion = Function.dbConnect();
        PreparedStatement prepared = null;
        ResultSet set = null;
        if (!idMaladie.isEmpty()) {
            req += "and pm.id_maladie = ? \n";
        }
        if (!idCategoriePersonne.isEmpty()) {
            req += "and pcp.id_categorie_personne = ?\n";
        }

        try {
            prepared = connexion.getConnexe().prepareStatement(req);
            int cpt = 0;
            if (!idMaladie.isEmpty()) {
                prepared.setString(++cpt, idMaladie);
            }
            if (!idCategoriePersonne.isEmpty()) {
                prepared.setString(++cpt, idCategoriePersonne);
            }

            set = prepared.executeQuery();
            ResultSetMetaData metaData = set.getMetaData();
            
            while (set.next()) {
                Vector<String> list = new Vector<>();
                for (int a = 0; a < metaData.getColumnCount(); a++) {
                    list.add(set.getString(a + 1));
                }
                all.add(list);
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
