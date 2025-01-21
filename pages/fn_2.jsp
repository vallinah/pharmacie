<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>
<%@page import="java.util.Vector"%>
<%@page import="fn.All"%>
<%@page import="base.*"%>

<%
    All categorie_personne = new All(CategoriePersonne.class);
    All mode_administration = new All(ModeAdministration.class);

    Vector<Vector<String>> all_categorie_personne = categorie_personne.getAll("id_categorie_personne, categorie_personne");
    Vector<Vector<String>> all_mode_administration = mode_administration.getAll("id_mode_administration, mode_administration");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fn_2 (Vente)</title>
</head>
<body>
    <main>
        <menu>
            <div class="menu_ttr">
                <h1>Menu</h1>
            </div>
            <div class="menu_bd">
                <div class="acc">
                    <div class="acc_ttr">
                        <i class="bi bi-person-circle"></i>
                        <h2>Listes Entites</h2>
                        <i class="bi bi-chevron-right"></i>
                    </div>                                                                      
                    <div class="acc_body">
                        <% for (Class<?> kilasy : Function.listeClass()) { %>
                            <div class="prt">
                                <i class="bi bi-person-badge"></i>
                                <a href="crud.jsp?cls=<%= kilasy.getName() %>"><%= kilasy.getSimpleName() %></a>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </menu>
        <section class="body">
            <form action="fn_2" method="post">
                <select name="id_categorie_personne" id="">
                    <option value="">Choise</option>
                    <% for (Vector<String> prs : all_categorie_personne) { %>
                        <option value="<%= prs.get(0) %>"><%= prs.get(1) %></option>
                    <% } %>
                </select>
                 <select name="id_mode_administration" id="">
                    <option value="">Choise</option>
                    <% for (Vector<String> mode : all_mode_administration) { %>
                        <option value="<%= mode.get(0) %>"><%= mode.get(1) %></option>
                    <% } %>
                </select>
                <button type="submit">Valider</button>
            </form>

            <div class="list">
                <table border="1">
                    <tr>
                        <th>id_mouvement</th>
                        <th>quantite</th>
                        <th>prix_achat_unitaire</th>
                        <th>prix_vente_unitaire</th>
                        <th>date_mouvement</th>
                        <th>nom_produit</th>
                    </tr>
                </table>
            </div>
        </section>
    </main>
    <script src="../assets/styles/js/fn_1.js"></script>
</body>
</html>