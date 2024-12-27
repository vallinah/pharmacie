package fn;

import base.connexe.Connexion;

public class Function {
    
    public static Connexion dbConnect() throws Exception {
        return new Connexion("Pharmacie");
    }
}