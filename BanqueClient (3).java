package rmiClient;

import javax.naming.Context;
import javax.naming.InitialContext;
import metier.Compte;
import rmiService.IBanque;

public class BanqueClient {
    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();
            IBanque banque = (IBanque) ctx.lookup("banque");

            System.out.println(banque.creerCompte(new Compte(1, 5000)));
            System.out.println(banque.creerCompte(new Compte(2, 1200.5)));
            System.out.println(banque.creerCompte(new Compte(1, 300)));

            System.out.println(banque.getInfoCompte(1));
            System.out.println(banque.getInfoCompte(2));
            System.out.println(banque.getInfoCompte(99));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
