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
<%
    Class<?> cls = Class.forName("base.Mouvement");
    CRUD crd = new CRUD(cls);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
    <title>Mouvement</title>
</head>
<body>
    <%@include file="../inc/header.jsp" %>
    <menu class="active">
        <div class="menu_ttr">
            <h1>Menu</h1>
        </div>
        <div class="menu_bd">
            <div class="acc">
                <%@include file="../inc/menu.jsp" %>
            </div>
            <div class="lien">
                <i class="bi bi-plus"></i>
                <a href="insert.jsp?cls=<%= cls.getName() %>">Insertion</a>
            </div>
        </div>
    </menu>
    <main>
        <section class="body">
            <div class="filtre" id='filtre'>
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
            </div>
            <%= crd.html_liste() %>
        </section>
    </main>
    <script src="../assets/styles/js/fn_1.js"></script>
</body>
</html>