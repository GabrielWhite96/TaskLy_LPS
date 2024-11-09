/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author wekisley
 */
public class ConnectionDB {
    private static EntityManagerFactory factory = null;

    private ConnectionDB() {}

    public static EntityManagerFactory getFactory() {
        if (factory == null) {
            synchronized (ConnectionDB.class) {
                try {
                    factory = Persistence.createEntityManagerFactory("taskly-jpa");
                } catch (Exception e) {
                    throw new ExceptionInInitializerError("Falha ao criar conexão com o banco!" + e);
                }
            }
        }
        return factory;
    }

    public static EntityManager getEntityManager() {
        return getFactory().createEntityManager();
    }

    public static void closeFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
