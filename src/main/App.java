package main;

import java.util.Vector;

import base.*;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {

            CRUD crd = new CRUD(Mouvement.class);
            // Vector<String> data = new Vector<>();
            //     data.add("3");
            //     data.add("2025-01-10");
            //     data.add("PRD00000002");
            //     data.add("CLT00000002");
            //     data.add("VND00000001");
            // crd.insert(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}