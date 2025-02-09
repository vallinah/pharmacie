package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "client", sequence = "client_id_seq", prefix = "CLI", page = "client.jsp", icone = "bi-shop")
public class Client {

    @AnnotationAttr(nameInBase = "id_client", inc = true, id = true)
    private String idClient;
    @AnnotationAttr(nameInBase = "nom_client")
    private String nomClient;

    public Client(ResultSet set) throws Exception {
        idClient = set.getString("id_client");
        nomClient = set.getString("nom_client");
    }

    public Client() {}

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
}
