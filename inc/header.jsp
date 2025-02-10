<%@page pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../assets/icons/bootstrap-icons.min.css">
<link rel="stylesheet" href="../assets/styles/css/font.css">
<link rel="stylesheet" href="../assets/styles/css/template.css">
<link rel="stylesheet" href="../assets/styles/css/style.css">
<script src="../assets/styles/js/template.js" defer type="module"></script>
<script src="../assets/styles/js/crud.js" defer type="module"></script>
<style>
    nav .titre h1 {
        font-family: "rez";
        font-size: 55px;
        letter-spacing: 5px;
        color: white;
        text-shadow: 3px 3px 2px rgba(0, 0, 0, 0.647), -3px -3px 2px #00990074;
    }
</style>
<header>
    <div class="logo">
            <a href="accueil.jsp">
                <img src="../assets/photos/logo.png" alt="logo" width="50">
            </a>
            <i class="bi bi-list active_menu"></i>
        </div>
        <nav>
            <div class="titre">
                <h1>Gestion Pharmacie</h1>
            </div>
        </nav>
        <div class="control">
            <i class="bi bi-search"></i>
            <a href="#" class="user">
                <img src="../assets/photos/user.jpg" width="30" alt="">
            </a>
            <a href="#">
                <i class="bi bi-gear"></i>
            </a>
            <a href="#" class="log-out">
                <i class="bi bi-power" style="margin-right: 10px;"></i>
                <span>Déconnexion</span>
            </a>
        </div>
</header>