<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>
<%@page import="java.util.Vector"%>
<%@page import="fn.All"%>
<%@page import="base.*"%>

<%
    CRUD crd = new CRUD(Vente.class);

    All categorie_personne = new All(CategoriePersonne.class);
    All mode_administration = new All(ModeAdministration.class);

    Vector<Vector<String>> all_categorie_personne = categorie_personne.getAll("id_categorie_personne, categorie_personne");
    Vector<Vector<String>> all_mode_administration = mode_administration.getAll("id_mode_administration, mode_administration");

    Vector<Vector<String>> rehetra = (Vector<Vector<String>>) request.getAttribute("rehetra");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vente (Fn2)</title>
</head>
<body>
    <span id="emplacement" hidden>base.Vente</span>
    <%@include file="../inc/header.jsp" %>
    <%@include file="../inc/menu.jsp" %>
    <div class="modal">
        <div class="modal_overlay modal_close"></div>
        <div class="modal_main">
            <div class="modal_md" data-mapping="ajout">
                <%= crd.html_insert() %>
            </div>
            <div class="modal_md update" data-mapping="update"></div>
            <div class="modal_md delete" data-mapping="delete">
                <div class="delete">
                    <div class="title">
                        <i class="bi bi-trash icone"></i>
                        <h1>Suppression</h1>
                    </div>
                    <div class="body">
                        <p>Voulez-vous supprimer vraiment cette donnée ?</p>
                    </div>
                    <div class="action">
                        <button class='confirm'><i class="bi bi-check-circle"></i>Supprimer</button>
                        <button class="modal_close"><i class="bi bi-x-circle"></i>Annuler</button>
                    </div>
                    <div class="message">
                        <p class=""></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <main>
        <section class="body">
            <section class="list">
                <div class="ttr">
                    <h1><i class='bi bi-credit-card'></i> Vente</h1>
                    <button class="ajout modal_active" data-active="ajout"><i class="bi bi-plus-circle"></i>Ajouter</button>
                </div>
                <div class="filtre">
                    <form action="vente" method="post">
                        <span>Categorie-Personne :</span>
                        <select name="id_categorie_personne" id="">
                            <option value="">Choise</option>
                            <% for (Vector<String> catp : all_categorie_personne) { %>
                                <option value="<%= catp.get(0) %>"><%= catp.get(1) %></option>
                            <% } %>
                        </select>
                        <span>Mode-administration :</span>
                        <select name="id_mode_administration" id="">
                            <option value="">Choise</option>
                            <% for (Vector<String> mld : all_mode_administration) { %>
                                <option value="<%= mld.get(0) %>"><%= mld.get(1) %></option>
                            <% } %>
                        </select>
                        <button type="submit">Valider</button>
                    </form>
                </div>
                <div class="bd">
                <% if (rehetra.size() > 0) { %>
                    <table border="1">
                        <tr>
                            <th>Id Vente</th>
                            <th>Prix Total</th>
                            <th>Date Vente</th>
                            <th>Commission</th>
                            <th>Nom Produit</th>
                            <th>Nom Client</th>
                            <th>Nom Vendeur</th>
                            <th>Action</th>
                        </tr>
                        <% for (Vector<String> elements : rehetra) { %>
                            <tr>
                                <%  int cpt = 0; for (String element : elements) { %>
                                    <% if (element.equals(elements.lastElement()) && cpt != 0) { %>
                                        <td>
                                        <div class="action">
                                            <a href="update?cls=base.Vente&id=<%= element %>" class="update modal_active" data-active="update"><i class="bi bi-pencil"></i></a>
                                            <a href="crud?cls=base.Vente&id=<%= element %>" class="delete modal_active" data-active="delete"><i class="bi bi-trash"></i></a>                    
                                        </div>
                                        </td>
                                    <% } else { %>
                                        <td>
                                            <%= element %>
                                        </td>
                                    <% } %>
                                <% cpt++;} %>
                            </tr>
                        <% } %>
                    </table>
                <% } else { %>
                    <h1>Aucune(s) Liste(s) de Produit(s)</h1>
                <% } %>
                </div>
            </section>
        </section>
    </main>
</body>
</html>