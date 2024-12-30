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
            Vector<String> vect = new Vector<>();
            vect.add("test");
            vect.add("aa");
            vect.add("1");
            crd.insert(vect);
            // System.out.println(crd.html_liste());
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}