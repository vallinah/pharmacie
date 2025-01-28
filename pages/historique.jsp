<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Vector"%>
<%@page import="fn.All"%>
<%@page import="base.*"%>


<%
    Class<?> cls = Class.forName("base.Historique");
    CRUD crd = new CRUD(cls);
    LocalDate dt = LocalDate.now();

    All produit = new All(Produit.class);
    Vector<Vector<String>> all_produit = produit.getAll("id_produit, nom_produit");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
    <title>Historique</title>
</head>
<body>
    <%@include file="../inc/header.jsp" %>
    <menu class="active">
        <div class="menu_ttr">
            <h1>Menu</h1>
        </div>
        <div class="menu_bd">
            <%@include file="../inc/menu.jsp" %>
        </div>
    </menu>
    <main>
        <section class="body">
            <div class="filtre" id="filtre">
                <form action="fn_3" method="post">
                    <select name="idProduit" id="">
                        <option value="">Choise</option>
                        <% for (Vector<String> prd : all_produit) { %>
                            <option value="<%= prd.get(0) %>"><%= prd.get(1) %></option>
                        <% } %>
                    </select>
                    <label for="">
                        <span>Date1</span>
                        <input type="date" name="date1" value="<%= dt %>" required>
                    </label>
                    <label for="">
                        <span>Date2</span>
                        <input type="date" name="date2" value="<%= dt %>" required>
                    </label>
                    <button type="submit">Valider</button>
                </form>
            </div>
            <%= crd.html_liste() %>
        </section>
    </main>
    <script src="../assets/styles/js/historique.js"></script>
</body>
</html>