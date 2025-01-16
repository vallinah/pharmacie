package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import com.google.gson.Gson;

import base.ConseilDuMois;
import fn.Function;
import fn.Functionality;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/fn_3")
public class Fn_3 extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        try {
            int year = Integer.parseInt(req.getParameter("year"));

            Functionality fn = new Functionality();
            Vector<ConseilDuMois> listmConseilDuMois = fn.getReqFn_3(1, year);

            Gson gson = new Gson();
            out.println(Function.giveJson("rep", gson.toJson(listmConseilDuMois)));
        } catch (Exception e) {
            resp.setStatus(500);
            // e.printStackTrace(out);
            out.println(Function.giveJson("err", e.getMessage()));
        }
    }
}
