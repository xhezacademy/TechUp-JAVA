package org.auk.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DbUtil {
    private final static String DB_URL = "jdbc:mariadb://localhost/simple-blog?useSSL=false";

    private static Connection connection;
    private static ServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            // Create registry
            registry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties())
                    .configure("hibernate.cfg.xml")
                    .build();

            // Create MetadataSources
            MetadataSources sources = new MetadataSources(registry);

            // Create Metadata
            Metadata metadata = sources.getMetadataBuilder().build();

            // Create SessionFactory
//            sessionFactory = new Configuration().configure().buildSessionFactory();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
            shutdown();
        }

        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Initiates and returns the Connection instance of the specified Database driver
     * <p>
     * Bonus: Find out more about Maps
     *
     * @link https://www.baeldung.com/java-initialize-hashmap
     */
    public static Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }

        // Class.forName("org.mariadb.jdbc.Driver");
        DriverManager.registerDriver(new org.mariadb.jdbc.Driver());

        Properties props = new Properties();
        // The Java 8 Way
        props.putAll(Stream.of(new String[][]{
                {"user", "root"},
                {"password", "root"},
                {"autoReconnect", "true"}
        }).collect(Collectors.toMap(entry -> entry[0], entry -> entry[1])));

        // The Java 9 Way
//        props.putAll(Map.of("user","root", "password", "root", "autoReconnect", "true"));

        return connection = DriverManager.getConnection(DB_URL, props);
    }
}
