package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import com.google.gson.Gson;

import base.Mouvement;
import base.Produit;
import fn.Function;
import fn.Functionality;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/fn_2")
public class Fn_2 extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        try {
            String id_mode = req.getParameter(req.getParameter("id_mode_administration"));
            String idCategorie_personne = req.getParameter("id_categorie_personne");

            Functionality fn = new Functionality();
            Vector<Mouvement> listmMouvements = fn.getFn_2(idCategorie_personne, id_mode);

            Gson gson = new Gson();
            out.println(Function.giveJson("rep", gson.toJson(listmMouvements)));
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(Function.giveJson("err", e.getMessage()));
        }
    }
}
