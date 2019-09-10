package uf;

import uf.util.Hibernate;
import uf.util.Jetty.JettyServer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Hibernate.getEntityManager();
        JettyServer server = new JettyServer();
        JettyServer.iniciaServidor(true);
    }
}
