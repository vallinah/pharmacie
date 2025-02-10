package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import fn.All;
import fn.Function;
import gst.CRUD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/crud")
public class Crud extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Enumeration<String> enumeration = req.getParameterNames();
        Vector<String> values = new Vector<>();
        String clsName = null;
        while (enumeration.hasMoreElements()) {
            String val = enumeration.nextElement();
            if (val.equals("cls")) {
                clsName = req.getParameter(val);
            } else {
                values.add(req.getParameter(val));
            }
        }
        try {
            Class<?> cls = Class.forName(clsName);
            CRUD crd = new CRUD(cls);
            String[] ids = crd.insert(values);
            All all = new All(cls);

            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("rep", "Insertion avec succé !");
            jsonObject.addProperty("obj", gson.toJson(all.getById(ids)));

            out.println(gson.toJson(jsonObject));
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(Function.giveJson("err", e.getMessage()));
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Enumeration<String> enumeration = req.getParameterNames();
        Vector<String> values = new Vector<>();
        try (BufferedReader read = req.getReader()) {
            String bd = read.readLine();
            String[] all = bd.split("&");
            for (String s : all) {
                values.add(URLDecoder.decode(s.split("=")[1], "UTF-8"));
            }
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(Function.giveJson("err", e.getMessage()));
            return;
        }

        String clsName = null;
        String[] ids = new String[2];
        while (enumeration.hasMoreElements()) {
            String val = enumeration.nextElement();
            if (val.equals("cls")) {
                clsName = req.getParameter(val);
            } else if (val.equals("id")) {
                ids = req.getParameter(val).split("_");
            }
        }

        try {
            CRUD crd = new CRUD(Class.forName(clsName));
            String[] ireoId = crd.update(values, ids);
            All all = new All(Class.forName(clsName));

            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("rep", "Update avec succes");
            jsonObject.addProperty("obj", gson.toJson(all.getById(ireoId)));

            out.println(gson.toJson(jsonObject));
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(Function.giveJson("err", e.getMessage()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Class<?> cls = Class.forName(req.getParameter("cls"));
            String id = req.getParameter("id");
            CRUD crd = new CRUD(cls);
            crd.delete(id.split("_"));
            out.println(Function.giveJson("mess", "Elément supprimé avec succes !"));
        } catch (Exception e) {
            resp.setStatus(500);
            // e.printStackTrace(out);
            out.println(Function.giveJson("err", e.getMessage()));
        }
    }
}
