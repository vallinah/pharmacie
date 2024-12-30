package main;

import base.Categorie;
import base.SousCategorie;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {
            CRUD crd = new CRUD(Categorie.class);
            System.out.println(crd.html_liste());
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}