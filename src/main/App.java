package main;

import java.util.HashMap;

import base.Produit;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {
            CRUD crd = new CRUD(Produit.class);
            HashMap<String, String> mapy = new HashMap<>();
            mapy.put("id_produit", "26");
            // System.out.println(crd.getData("nom_produit", mapy));
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}