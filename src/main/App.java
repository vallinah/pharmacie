package main;

import base.*;
import gst.CRUD;

public class App {

    public static void main(String[] args) {
        try {

            CRUD crd = new CRUD(Maladie.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}