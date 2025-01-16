package main;

import java.time.LocalDate;
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
            // Functionality fn = new Functionality();

            // Vector<Mouvement> mvts = fn.getFn_2("CATP00000001", "MOD00000004");
            // for (Mouvement mvt : mvts) {
            //     System.out.println(mvt.getIdMouvement());
            // }

            LocalDate dt = LocalDate.now();
            System.out.println(dt);

        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Erreur : " + e.getMessage());
        }
    }
}