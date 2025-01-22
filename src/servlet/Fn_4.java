package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Vector;

import com.google.gson.Gson;

import base.Client;
import fn.Function;
import fn.Functionality;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/fn_4")
public class Fn_4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        try {

            Date daty = Function.dateByString(req.getParameter("date"));

            Functionality fn = new Functionality();
            Vector<Client> listmConseilDuMois = fn.getReqFn_4(daty);

            Gson gson = new Gson();
            out.println(Function.giveJson("rep", gson.toJson(listmConseilDuMois)));
        } catch (Exception e) {
            resp.setStatus(500);
            // e.printStackTrace(out);
            out.println(Function.giveJson("err", e.getMessage()));
        }
    }
}
