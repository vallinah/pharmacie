package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Vector;

import com.google.gson.Gson;

import base.CommissionVendeur;
import fn.Function;
import fn.Functionality;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/fn_5")
public class fn_5 extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        try {


            Date daty1 = req.getParameter("date1") == null ? null : Function.dateByString(req.getParameter("date1"));
            Date daty2 = req.getParameter("date1") == null ? null :Function.dateByString(req.getParameter("date2"));

            Functionality fn = new Functionality();
            Vector<CommissionVendeur> listCommissionVendeurs = fn.getReqFn_5(daty1, daty2);

            Gson gson = new Gson();
            out.println(Function.giveJson("rep", gson.toJson(listCommissionVendeurs)));
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace(out);
            // out.println(Function.giveJson("err", e.getMessage()));
        }
    }
}
