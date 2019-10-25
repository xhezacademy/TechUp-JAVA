package org.auk.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class DbUtil {

    private static ServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
//        Configuration configuration = new Configuration();
//        Properties props = new Properties();
//        props.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
//        props.put(Environment.URL, "jdbc:mariadb://localhost/hibernatedb?useSSL=false");
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
//                        .applySettings(configuration.getProperties())
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
}
