<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>
<%@page import="java.time.LocalDate"%>

<%
    Class<?> cls = Class.forName("base.Vendeur");
    CRUD crd = new CRUD(cls);
    LocalDate dt = LocalDate.now();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
    <script src="../assets/styles/js/delete.js" defer type="module"></script>
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
                        <span>Date</span>
                        <input type="date" name="date1" value="<%= dt %>" required>
                    </label>
                    <label for="">
                        <span>Date</span>
                        <input type="date" name="date2" value="<%= dt %>" required>
                    </label>
                    <button type="submit">Valider</button>
                </form>
            </div>
            <%= crd.html_liste() %>
        </section>
    </main>
    <script src="../assets/styles/js/fn_5.js"></script>
</body>
</html>