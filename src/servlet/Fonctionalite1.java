package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import com.google.gson.Gson;

import base.Produit;
import fn.Function;
import fn.Functionality;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/fn_1")
public class Fonctionalite1 extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        try {
            String idMaladie = req.getParameter(req.getParameter("id_maladie"));
            String idCategorie_personne = req.getParameter("id_categorie_personne");

            Functionality fn = new Functionality();
            Vector<Produit> listProduits = fn.getFonctinnalite(idMaladie, idCategorie_personne);

            Gson gson = new Gson();
            
            out.println(gson.toJson(listProduits));

        } catch (Exception e) {
            resp.setStatus(500);
            out.println(Function.giveJson("err", e.getMessage()));
        }
    }
}
