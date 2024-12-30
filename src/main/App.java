package main;

import java.util.Vector;

import base.Categorie;
import base.Produit;
import base.SousCategorie;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {
            CRUD crd = new CRUD(Produit.class);
            // System.out.println(crd.html_update("21"));
            Vector<String> vect = new Vector<>();
            vect.add("dd");
            vect.add("rr");
            vect.add("1");
            crd.update(vect, "1");
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}