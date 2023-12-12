package com.mythoshibernate.mythoshibernate.util;

import com.mythoshibernate.mythoshibernate.domain.Mytho;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import com.mythoshibernate.mythoshibernate.domain.Mytho;

public class HibernateConf {
    /*
	 *
	 * La clase que más usaremos en Hibernate es org.hibernate.Session.
	 * Esta clase contiene métodos para leer, guardar o borrar entidades sobre la base de datos.
	 * Pero antes de poder usarla hace falta crear el objeto SessionFactory
	 * que mediante el método SessionFactory.openSession() nos dará acceso a Session.

	Session session = sessionFactory.openSession();
	*/
    private static SessionFactory factory;

    private HibernateConf() {
    }

    public static SessionFactory getFactory() {
        if (factory == null) {

            /*
             *  Hibernate facilita proporcionar las configuraciones en un archivo XML (como hibernate.cfg.xml)
             *   o un archivo de propiedades (como hibernate.properties).
             */
            //en mi caso me gusta trabajar con el fichero de propiedades
            Configuration configuration = new Configuration();
            // Configuracion de Hibernate equivalente al fichero de propiedades  hibernate.cfg.xml's
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/cthulhu");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "root");

            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            settings.put(Environment.SHOW_SQL, "false");
            settings.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(Mytho.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            factory = configuration.buildSessionFactory(serviceRegistry);
        }

        return factory;
    }
}
