package main;

import java.util.Vector;

import base.Categorie;
import base.Produit;
import base.SousCategorie;
import base.Type;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {
            CRUD crd = new CRUD();
            // System.out.println(crd.scriptInsert(SousCategorie.class));
            Vector<String> vect = new Vector<>();
            vect.add("baba");
            crd.insert(Categorie.class, vect);
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}