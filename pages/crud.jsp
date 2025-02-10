<%@page import="gst.CRUD" pageEncoding="UTF-8" %>
<%@page import="fn.Function"%>

<%
    Class<?> cls = Class.forName(request.getParameter("cls"));
    CRUD crd = new CRUD(cls);
    LinkHandler clsLink = null;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD</title>
</head>
<body>
    <span id="emplacement" hidden><%= cls.getName() %></span>
    
    <%@include file="../inc/header.jsp" %>
    <%@include file="../inc/menu.jsp" %>
    <%
        for (LinkHandler link : Function.listeLink()) {
            if (link.getCls().equals(cls)) {
                clsLink = link;
                break;
            }
        }
    %>
    </menu>
    <div class="modal">
        <div class="modal_overlay modal_close"></div>
        <div class="modal_main">
            <div class="modal_md" data-mapping="ajout">
                <%= crd.html_insert() %>
            </div>
            <div class="modal_md update" data-mapping="update"></div>
            <div class="modal_md delete" data-mapping="delete">
                <div class="delete">
                    <div class="title">
                        <i class="bi bi-trash icone"></i>
                        <h1>Suppression</h1>
                    </div>
                    <div class="body">
                        <p>Voulez-vous supprimer vraiment cette donnée ?</p>
                    </div>
                    <div class="action">
                        <button class='confirm'><i class="bi bi-check-circle"></i>Supprimer</button>
                        <button class="modal_close"><i class="bi bi-x-circle"></i>Annuler</button>
                    </div>
                    <div class="message">
                        <p class=""></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <main>
        <section class="body">
            <section class="list">
                <div class="ttr">
                    <h1><i class="bi <%= clsLink.getIcone() %>"></i>  <%= clsLink.getTitre() %></h1>
                    <button class="ajout modal_active" data-active="ajout"><i class="bi bi-plus-circle"></i>Ajouter</button>
                </div>
                <div class="bd">
                    <%= crd.html_liste() %>
                </div>
            </section>
        </section>
    </main>
   
</body>
</html>