package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import com.google.gson.Gson;

import gst.CRUD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/data")
public class Data extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setHeader("content-type", "application/json");
        try {
            Enumeration<String> paramList = req.getParameterNames();
            HashMap<String, String> map = new HashMap<>();
            String cls = null;
            String columName = null;
            String nameInBase = null;
            while (paramList.hasMoreElements()) {
                String nameKey = paramList.nextElement();
                if (nameKey.equalsIgnoreCase("cls")) {
                    cls = req.getParameter(nameKey);
                } else if (nameKey.equalsIgnoreCase("col")) {
                    columName = req.getParameter(nameKey);
                } else if (nameKey.equalsIgnoreCase("name_in_base")) {
                    nameInBase = req.getParameter(nameKey);
                } else {
                    map.put(nameKey, req.getParameter(nameKey));
                }
            }
            CRUD crd = new CRUD(Class.forName(cls));
            Gson gson = new Gson();
            out.println(gson.toJson(crd.getData(columName, nameInBase, map)));
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace(out);
        }
    }
}