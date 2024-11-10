/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Task;

/**
 *
 * @author wekisley
 */
public class TaskDAO implements DAOInterface<Task> {
    
    public TaskDAO(){ }

    @Override
    public void save(Task task) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(task);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Task getById(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        Task task = null;
        try{
            task = entityManager.find(Task.class, id);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return task;
    }

    @Override
    public void update(Task task) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(task);
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
            Task task = entityManager.find(Task.class, id);
            if (task != null) {
                entityManager.remove(task);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List getAll() throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<Task> taskList = null;
        try{
            TypedQuery<Task> query = entityManager.createQuery("SELECT task FROM Task task", Task.class);
            taskList = query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return taskList;
    }
    
}

