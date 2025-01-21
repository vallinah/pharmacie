<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>
<%@page import="java.util.Vector"%>
<%@page import="fn.All"%>
<%@page import="base.*"%>

<%
    All maladie = new All(Maladie.class);
    All categorie_personne = new All(CategoriePersonne.class);

    Vector<Vector<String>> all_maladie = maladie.getAll("id_maladie, nom_maladie");
    Vector<Vector<String>> all_categorie_personne = categorie_personne.getAll("id_categorie_personne, categorie_personne");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fn_1</title>
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
            <form action="fn_1" method="post">
                <select name="" id="">
                    <% for (Vector<String> mld : all_maladie) { %>
                        <option value="<%= mld.get(0) %>"><%= mld.get(1) %></option>
                    <% } %>
                </select>
                 <select name="" id="">
                    <% for (Vector<String> prs : all_categorie_personne) { %>
                        <option value="<%= prs.get(0) %>"><%= prs.get(1) %></option>
                    <% } %>
                </select>
                <button type="submit">Valider</button>
            </form>
        </section>
    </main>
    <script src="../assets/styles/js/fn_1.js"></script>
</body>
</html>