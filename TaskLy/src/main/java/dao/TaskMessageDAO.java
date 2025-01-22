/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Task;
import model.TaskMessage;

/**
 *
 * @author wekisley
 */
public class TaskMessageDAO implements DAOInterface<TaskMessage> {
    
    public TaskMessageDAO(){ }

    @Override
    public void save(TaskMessage message) throws Exception {
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
    public TaskMessage getById(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        TaskMessage message = null;
        try{
            message = entityManager.find(TaskMessage.class, id);
        } catch (Exception e) {
            throw new Exception("Erro ao obter mensagem" ,e);
        } finally {
            entityManager.close();
        }
        return message;
    }

    @Override
    public void update(TaskMessage message) throws Exception {
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
            TaskMessage message = entityManager.find(TaskMessage.class, id);
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
        List<TaskMessage> messageList = null;
        try{
            TypedQuery<TaskMessage> query = entityManager.createQuery("SELECT message FROM ProjectMessage message", TaskMessage.class);
            messageList = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao obter mensagens" ,e);
        } finally {
            entityManager.close();
        }
        return messageList;
    }

    public List getMessagesOfTask(Task task) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<TaskMessage> messagesList = null;
        try{
            TypedQuery<TaskMessage> query = entityManager.createQuery("SELECT taskMessage FROM TaskMessage taskMessage WHERE taskMessage.task.id = :id", TaskMessage.class);
            query.setParameter("id", task.getId());
            messagesList = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao obter mensagens", e);
        } finally {
            entityManager.close();
        }
        return messagesList;
    }
}
