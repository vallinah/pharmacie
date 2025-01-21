<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion</title>
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
    <script src="../assets/styles/js/crud.js" defer></script>
    <script src="../assets/styles/js/insert.js" defer type="module"></script>
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
                    <i class="bi bi-arrow-left"></i>
                     <a href="crud.jsp?cls=<%= request.getParameter("cls") %>">Retour</a>
                </div>
            </div>
        </menu>
        <section class="body">
            <%
                CRUD crd = new CRUD(Class.forName(request.getParameter("cls")));
                out.println(crd.html_insert());
            %>
        </section>
    </main>

</body>
</html>