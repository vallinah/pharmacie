<%@page import="fn.Function"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
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
            </div>
        </menu>
    </main>
    <section class="body"></section>
</body>
</html