<%-- 
    Document   : accueil
    Created on : 2 janv. 2025, 18:44:58
    Author     : Otisoa Vallinah
--%>

<%@page import="fn.Function"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
</head>
<body>
    <%@include file="header.jsp" %>
    <section class="body">
        <div class="ls">
            <% for (Class<?> cls : Function.listeClass()) { %>
                <li><a href="crud.jsp?cls=<%= cls.getName() %>"><%= cls.getSimpleName() %></a></li>
            <% } %>
        </div>
    </section>
</body>
</html>
