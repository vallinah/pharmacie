package main;

import base.Produit;
import base.SousCategorie;
import base.Type;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {
            CRUD crd = new CRUD();
            System.out.println(crd.scriptInsert(SousCategorie.class));
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}