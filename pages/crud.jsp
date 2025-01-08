<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>

<%
    Class<?> cls = Class.forName(request.getParameter("cls"));
    CRUD crd = new CRUD(cls);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD</title>
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
    <script src="../assets/styles/js/delete.js" defer type="module"></script>
</head>
<body>
    <%@include file="../inc/header.jsp" %>
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
                <div class="lien">
                    <i class="bi bi-plus"></i>
                    <a href="insert.jsp?cls=<%= cls.getName() %>">Insertion</a>
                </div>
            </div>
        </menu>
        <section class="body">
            <%= crd.html_liste() %>
        </section>
    </main>
   
</body>
</html>