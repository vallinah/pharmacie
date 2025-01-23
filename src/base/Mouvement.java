package base;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;
import annotation.ForeingKey;
import base.connexe.Connexion;
import fn.Function;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

@AnnotationClass(nameInBase = "mouvement", sequence = "mouvement_id_seq", prefix = "MVT", page = "mouvement.jsp")
public class Mouvement {

    @AnnotationAttr(nameInBase = "id_mouvement", inc = true)
    private String idMouvement;
    @AnnotationAttr(nameInBase = "quantite")
    private int quantite;
    @AnnotationAttr(nameInBase = "prix_achat_unitaire", insert = false, show = false)
    private double prixAchatUnitaire;
    @AnnotationAttr(nameInBase = "date_mouvement")
    private Date dateMouvement;
    @ForeingKey(cls = "produit", col = "nom_produit")
    @AnnotationAttr(nameInBase = "id_produit")
    private String idProduit;
    @AnnotationAttr(nameInBase = "prix_vente_unitaire", insert = false)
    private double prixVenteUnitaire;
    @ForeingKey(cls = "client", col = "nom_client")
    @AnnotationAttr(nameInBase = "id_client")
    private String idClient;
    @ForeingKey(cls = "vendeur", col = "nom_vendeur")
    @AnnotationAttr(nameInBase = "id_vendeur")
    private String idVendeur;

    public Mouvement(ResultSet resultSet) throws Exception {
        this.setIdMouvement(resultSet.getString("id_mouvement"));
        this.setQuantite(resultSet.getInt("quantite"));
        this.setPrixAchatUnitaire(resultSet.getDouble("prix_achat_unitaire"));
        this.setPrixVenteUnitaire(resultSet.getDouble("prix_vente_unitaire"));
        this.setDateMouvement(resultSet.getDate("date_mouvement"));
        this.setIdProduit(resultSet.getString("id_produit"));
        this.setIdClient(resultSet.getString("id_client"));
        this.setIdVendeur(resultSet.getString("id_vendeur"));
    }

    public Mouvement() {}

    public void insert(Vector<String> listParams) throws Exception {
        String req = "INSERT INTO\n" + //
                        "    mouvement (\n" + //
                        "        quantite,\n" + //
                        "        date_mouvement,\n" + //
                        "        id_produit,\n" + //
                        "        id_client,\n" + //
                        "        id_vendeur,\n" + //
                        "        prix_vente_unitaire\n" + //
                        "    )\n" + //
                        "VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement prp = null;

        try {
            Connexion connexion = Function.dbConnect();
            connection = connexion.getConnexe();
            connection.setAutoCommit(false);
            String prixVenteUnitaire = Function.getDataByID("produit", "prix_vente_unitaire", "id_produit", listParams.get(2));

            prp = connection.prepareStatement(req);
                prp.setDouble(1, Double.parseDouble(listParams.firstElement()));
                prp.setDate(2, Function.dateByString(listParams.get(1)));
                prp.setString(3, listParams.get(2));
                prp.setString(4, listParams.get(3));
                prp.setString(5, listParams.get(4));
                prp.setDouble(6, Double.parseDouble(prixVenteUnitaire));

            prp.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (prp != null) {
                prp.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    public String getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(String idMouvement) {
        this.idMouvement = idMouvement;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixAchatUnitaire() {
        return prixAchatUnitaire;
    }

    public void setPrixAchatUnitaire(double prixAchatUnitaire) {
        this.prixAchatUnitaire = prixAchatUnitaire;
    }

    public double getPrixVenteUnitaire() {
        return prixVenteUnitaire;
    }

    public void setPrixVenteUnitaire(double prixVenteUnitaire) {
        this.prixVenteUnitaire = prixVenteUnitaire;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(String idVendeur) {
        this.idVendeur = idVendeur;
    }
}
