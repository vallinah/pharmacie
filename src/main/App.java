package main;

import java.util.HashMap;
import java.util.Vector;

import base.Mouvement;
import base.Produit;
import fn.Functionality;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {
            // CRUD crd = new CRUD(Produit.class);
            // HashMap<String, String> mapy = new HashMap<>();
            // mapy.put("id_produit", "26");
            // // System.out.println(crd.getData("nom_produit", mapy));

            // Vector<String> insert = new Vector<>();
            // insert.add("e");
            // insert.add("1");
            // insert.add("1");
            // insert.add("1");
            // insert.add("3");    
            // insert.add("3");    
            // insert.add("FRM00000002");    
            // insert.add("MOD00000002");    
            // insert.add("LAB00000002"); 

            Vector<String> insert = new Vector<>();
            insert.add("e");
            insert.add("2");
            insert.add("1");

            CRUD crd = new CRUD(Mouvement.class);
            crd.insert(insert);

            // CRUD crd = new CRUD(Produit.class);
            // crd.insert(insert);

            // Functionality fn = new Functionality();
            // fn.getFn_2("", "e");
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}