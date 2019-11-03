package org.auk.utils;

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
    private final static String DB_URL = "jdbc:mariadb://localhost/techupdb?useSSL=false";

    private static Connection connection;
    private static ServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
//        Configuration configuration = new Configuration();
//        Properties props = new Properties();
//        props.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
//        props.put(Environment.URL, DB_URL);
//        props.put(Environment.USER, "root");
//        props.put(Environment.PASS, "root");
//        props.put(Environment.DIALECT, "org.hibernate.dialect.MariaDB103Dialect");
//        props.put(Environment.HBM2DDL_AUTO, "update");
//        configuration.addProperties(props);
//        configuration.addAnnotatedClass(Student.class);
//        configuration.addAnnotatedClass(Course.class);

        try {
            // Create registry
            registry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties())
                    .configure()
                    .build();

            // Create MetadataSources
            MetadataSources sources = new MetadataSources(registry);

            // Create Metadata
            Metadata metadata = sources.getMetadataBuilder().build();

            // Create SessionFactory
//            sessionFactory = configuration.buildSessionFactory();
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
