<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>
<%@page import="java.util.Vector"%>
<%@page import="fn.All"%>
<%@page import="base.*"%>

<%
    CRUD crd = new CRUD(Produit.class);

    All maladie = new All(Maladie.class);
    All categorie_personne = new All(CategoriePersonne.class);

    Vector<Vector<String>> all_maladie = maladie.getAll("id_maladie, nom_maladie");
    Vector<Vector<String>> all_categorie_personne = categorie_personne.getAll("id_categorie_personne, categorie_personne");

    Vector<Vector<String>> rehetra = (Vector<Vector<String>>) request.getAttribute("rehetra");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produit (Fn1)</title>
</head>
<body>
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
                    <h1><i class="bi bi-box-seam"></i>  Produit</h1>
                    <button class="ajout modal_active" data-active="ajout"><i class="bi bi-plus-circle"></i>Ajouter</button>
                </div>
                <div class="filtre">
                    <form action="produit" method="post">
                        <span>Maladie :</span>
                        <select name="id_maladie" id="">
                            <option value="">Choise</option>
                            <% for (Vector<String> mld : all_maladie) { %>
                                <option value="<%= mld.get(0) %>"><%= mld.get(1) %></option>
                            <% } %>
                        </select>
                        <span>Categorie-Personne :</span>
                        <select name="id_categorie_personne" id="">
                            <option value="">Choise</option>
                            <% for (Vector<String> catp : all_categorie_personne) { %>
                                <option value="<%= catp.get(0) %>"><%= catp.get(1) %></option>
                            <% } %>
                        </select>
                        <button type="submit">Valider</button>
                    </form>
                </div>
                <div class="bd">
                <% if (rehetra.size() > 0) { %>
                    <table border="1">
                        <tr>
                            <th>Id Produit</th>
                            <th>Nom Produit</th>
                            <th>Description</th>
                            <th>Mode Administration</th>                        
                            <th>Action</th>
                        </tr>
                        <% for (Vector<String> elements : rehetra) { %>
                            <tr>
                                <%  int cpt = 0; for (String element : elements) { %>
                                    <% if (element.equals(elements.lastElement()) && cpt != 0) { %>
                                        <td>
                                        <div class="action">
                                            <a href="update?cls=base.Produit&id=<%= element %>" class="update modal_active" data-active="update"><i class="bi bi-pencil"></i></a>
                                            <a href="crud?cls=base.Produit&id=<%= element %>" class="delete modal_active" data-active="delete"><i class="bi bi-trash"></i></a>                    
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