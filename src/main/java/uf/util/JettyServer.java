package uf.util;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyServer {
    private static Server server;

    public static void iniciaServidor(boolean join) {
        ResourceConfig config = new ResourceConfig();
        config.register(new CORSFilter());
        config.packages("uf.servico");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        int port = Integer.parseInt(System.getenv("PORT") != null ? System.getenv("PORT") : "5000");
        server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");
        try {
            server.start();
            if (join)
                server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void finalizaServidor() throws Exception {
        if (server != null)
            server.stop();
    }

    public static boolean isInicializado() {
        return server.isRunning();
    }
}
