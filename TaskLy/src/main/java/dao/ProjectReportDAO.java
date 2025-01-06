/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Project;
import model.ProjectReport;
import org.hibernate.Hibernate;

/**
 *
 * @author wekisley
 */
public class ProjectReportDAO implements DAOInterface<ProjectReport> {
    
    public ProjectReportDAO(){ }

    @Override
    public void save(ProjectReport report) throws Exception {
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
    public ProjectReport getById(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        ProjectReport report = null;
        try{
            report = entityManager.find(ProjectReport.class, id);
        } catch (Exception e) {
            throw new Exception("Erro ao obter relatório" ,e);
        } finally {
            entityManager.close();
        }
        return report;
    }

    @Override
    public void update(ProjectReport report) throws Exception {
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
            Project report = entityManager.find(Project.class, id);
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
        List<ProjectReport> reportList = null;
        try{
            TypedQuery<ProjectReport> query = entityManager.createQuery("SELECT report FROM ProjectReport report", ProjectReport.class);
            reportList = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao obter relatórios" ,e);
        } finally {
            entityManager.close();
        }
        return reportList;
    }
}
