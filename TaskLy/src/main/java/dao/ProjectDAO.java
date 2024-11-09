/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Project;

/**
 *
 * @author wekisley
 */
public class ProjectDAO implements DAOInterface<Project> {
    
    public ProjectDAO(){ }

    @Override
    public void save(Project project) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Project getById(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        Project project = null;
        try{
            project = entityManager.find(Project.class, id);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return project;
    }

    @Override
    public void update(Project project) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(project);
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
            Project project = entityManager.find(Project.class, id);
            if (project != null) {
                entityManager.remove(project);
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
        List<Project> projectList = null;
        try{
            TypedQuery<Project> query = entityManager.createQuery("SELECT login FROM Login login", Project.class);
            projectList = query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return projectList;
    }
    
}
