<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>update</title>
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
    <script src="../assets/styles/js/crud.js" defer></script>
    <script src="../assets/styles/js/update.js" defer type="module"></script>
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
                <div class="acc">
                    <div class="acc_ttr">
                        <i class="bi bi-person-circle"></i>
                        <h2>Fonctionality</h2>
                        <i class="bi bi-chevron-right"></i>
                    </div>                                                                      
                    <div class="acc_body">
                        <div class="prt">
                            <i class="bi bi-person-badge"></i>
                            <a href="fn_1.jsp">Functionality 1</a>
                        </div>
                    </div>
                </div>
                <div class="lien">
                    <i class="bi bi-arrow-left"></i>
                     <a href="crud.jsp?cls=<%= request.getParameter("cls") %>">Retour</a>
                </div>
            </div>
        </menu>
        <section class="body">
            <%
                Class<?> cls = Class.forName(request.getParameter("cls"));
                CRUD crd = new CRUD(cls);
                out.println(crd.html_update(request.getParameter("id")));
            %>
        </section>
    </main>
</body>
</html>