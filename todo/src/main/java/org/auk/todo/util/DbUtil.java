package org.auk.todo.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbUtil {

    public static SessionFactory getSessionFactory(){
        SessionFactory registry = new Configuration().configure().buildSessionFactory();
        return registry;
    }

}
