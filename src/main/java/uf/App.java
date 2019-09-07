package uf;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String databaseUrl = System.getenv("DATABASE_URL");
        String dbVendor = "";
        String userName = "postgres";
        String password = "root";
        String host = "localhost";
        String bdPort = "5432";
        String databaseName = "uf";
        String jdbcUrl = String.format("jdbc:postgresql://%s/%s", host, databaseName);
        if (databaseUrl != null) {
            StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
            dbVendor = st.nextToken(); //if DATABASE_URL is set
            userName = st.nextToken();
            password = st.nextToken();
            host = st.nextToken();
            bdPort = st.nextToken();
            databaseName = st.nextToken();
            jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", host, bdPort, databaseName);
        }
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.url", jdbcUrl);
        properties.put("javax.persistence.jdbc.user", userName);
        properties.put("javax.persistence.jdbc.password", password);
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default", properties);
        ResourceConfig config = new ResourceConfig();
        config.packages("uf");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        int port = Integer.parseInt(System.getenv("PORT") != null ? System.getenv("PORT") : "5000");
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}
