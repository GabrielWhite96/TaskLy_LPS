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
    private static EntityManagerFactory factory;
  
    static {
        try {
            factory = Persistence.createEntityManagerFactory("taskly-jpa");
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Falha ao criar conexão com o banco!" + e);
        }
    }
    
    public static EntityManagerFactory getFactory() {
        return factory;
    }
    
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
