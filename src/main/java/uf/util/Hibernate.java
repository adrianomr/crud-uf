package uf.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Hibernate {

    private static EntityManager em;

    Hibernate() {

    }

    public static EntityManager getEntityManager() {
        if (em == null) {
            String databaseUrl = System.getenv("DATABASE_URL");
            String dbVendor = "";
            String userName = "postgres";
            String password = "root";
            String host = "localhost";
            String bdPort = "5432";
            String databaseName = "postgres";
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
            em = Persistence.createEntityManagerFactory("default", properties).createEntityManager();
        }
        return em;
    }
}
