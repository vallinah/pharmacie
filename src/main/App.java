package main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;

import annotation.AnnotationClass;
import base.Laboratoire;
import base.Maladie;
import base.Mouvement;
import base.Produit;
import base.ProduitCategoriePersonne;
import fn.Functionality;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {

            CRUD crd = new CRUD(Maladie.class);
            // crd.delete("MLD00000012");
            // System.out.println(crd.scriptInsert());
            // Functionality fn = new Functionality();

            // Vector<Mouvement> mvts = fn.getFn_2("CATP00000001", "MOD00000004");
            // for (Mouvement mvt : mvts) {
            //     System.out.println(mvt.getIdMouvement());
            // }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Erreur : " + e.getMessage());
        }
    }
}