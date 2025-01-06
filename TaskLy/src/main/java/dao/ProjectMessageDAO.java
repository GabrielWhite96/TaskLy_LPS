/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.ProjectMessage;

/**
 *
 * @author wekisley
 */
public class ProjectMessageDAO implements DAOInterface<ProjectMessage> {
    
    public ProjectMessageDAO(){ }

    @Override
    public void save(ProjectMessage message) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(message);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao salvar mensagem" ,e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ProjectMessage getById(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        ProjectMessage message = null;
        try{
            message = entityManager.find(ProjectMessage.class, id);
        } catch (Exception e) {
            throw new Exception("Erro ao obter mensagem" ,e);
        } finally {
            entityManager.close();
        }
        return message;
    }

    @Override
    public void update(ProjectMessage message) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(message);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao atualizar mensagem" ,e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            ProjectMessage message = entityManager.find(ProjectMessage.class, id);
            if (message != null) {
                entityManager.remove(message);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao deletar mensagem" ,e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List getAll() throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<ProjectMessage> messageList = null;
        try{
            TypedQuery<ProjectMessage> query = entityManager.createQuery("SELECT message FROM ProjectMessage message", ProjectMessage.class);
            messageList = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao obter mensagens" ,e);
        } finally {
            entityManager.close();
        }
        return messageList;
    }
}
