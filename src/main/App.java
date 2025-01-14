package main;

import java.util.HashMap;
import java.util.Vector;

import base.Laboratoire;
import base.Mouvement;
import base.Produit;
import base.ProduitCategoriePersonne;
import fn.Functionality;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {

            // CRUD crd = new CRUD(Mouvement.class);
            // crd.html_liste();
            Functionality fn = new Functionality();
            System.out.println(fn.getReqFn_2(null, "4"));
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Erreur : " + e.getMessage());
        }
    }
}