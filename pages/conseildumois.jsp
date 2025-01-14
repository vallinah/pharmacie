<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>

<%
    Class<?> cls = Class.forName("base.ConseilDuMois");
    CRUD crd = new CRUD(cls);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
    <title>Conseil du mois</title>
</head>
<body>
    <%@include file="../inc/header.jsp" %>
    <main>
        <menu>
            <div class="menu_ttr">
                <h1>Menu</h1>
            </div>
            <div class="menu_bd">
                <%@include file="../inc/menu.jsp" %>
                <div class="lien">
                    <i class="bi bi-plus"></i>
                    <a href="insert.jsp?cls=<%= cls.getName() %>">Insertion</a>
                </div>
            </div>
        </menu>
        <section class="body">
            <div class="filtre" id="filtre">
                <form action="fn_3" method="post">
                <label for="">
                    <span>Month and Year</span>
                    <input type="month" name="month_year" id="" required>
                </label>
                    <button type="submit">Valider</button>
                </form>
            </div>
            <%= crd.html_liste() %>
        </section>
    </main>
    <script src="../assets/styles/js/fn_3.js"></script>
</body>
</html>