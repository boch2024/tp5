package rmiServer;

import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;
import rmiService.BanqueImpl;

public class BanqueServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            BanqueImpl obj = new BanqueImpl();
            Context ctx = new InitialContext();
            ctx.rebind("banque", obj);
            System.out.println("Serveur pret : service 'banque' publie via JNDI.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
