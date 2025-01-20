/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.ClockIn;
import model.Person;

/**
 *
 * @author wekisley
 */
public class ClockInDAO implements DAOInterface<ClockIn> {
    
    public ClockInDAO(){ }
    
    @Override
    public void save(ClockIn clockIn) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(clockIn);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ClockIn getById(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        ClockIn clockIn = null;
        try{
            clockIn = entityManager.find(ClockIn.class, id);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return clockIn;
    }

    @Override
    public void update(ClockIn clockIn) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(clockIn);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            ClockIn clockIn = entityManager.find(ClockIn.class, id);
            if (clockIn != null) {
                entityManager.remove(clockIn);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
    }

    public List getClockInOfPerson(Person person) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<ClockIn> clockInList = null;
        try{
            TypedQuery<ClockIn> query = entityManager.createQuery("SELECT clockIn FROM ClockIn clockIn WHERE clockIn.person.id = :id", ClockIn.class);
            query.setParameter("id", person.getId());
            clockInList = query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return clockInList;
    }

    @Override
    public List getAll() throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<ClockIn> clockInList = null;
        try{
            TypedQuery<ClockIn> query = entityManager.createQuery("SELECT clockIn FROM ClockIn clockIn", ClockIn.class);
            clockInList = query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return clockInList;
    }
}
