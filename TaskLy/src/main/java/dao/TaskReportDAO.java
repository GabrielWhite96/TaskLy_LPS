/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.TaskReport;

/**
 *
 * @author wekisley
 */
public class TaskReportDAO implements DAOInterface<TaskReport> {
    
    public TaskReportDAO(){ }

    @Override
    public void save(TaskReport report) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(report);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao salvar relatório" ,e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public TaskReport getById(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        TaskReport report = null;
        try{
            report = entityManager.find(TaskReport.class, id);
        } catch (Exception e) {
            throw new Exception("Erro ao obter relatório" ,e);
        } finally {
            entityManager.close();
        }
        return report;
    }

    @Override
    public void update(TaskReport report) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(report);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao atualizar relatório" ,e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            TaskReport report = entityManager.find(TaskReport.class, id);
            if (report != null) {
                entityManager.remove(report);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao deletar relatório" ,e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List getAll() throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<TaskReport> reportList = null;
        try{
            TypedQuery<TaskReport> query = entityManager.createQuery("SELECT report FROM TaskReport report", TaskReport.class);
            reportList = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao obter relatórios" ,e);
        } finally {
            entityManager.close();
        }
        return reportList;
    }
}
