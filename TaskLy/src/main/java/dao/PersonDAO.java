/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import model.Person;
import model.Project;
import model.Task;

/**
 *
 * @author wekisley
 */
public class PersonDAO implements DAOInterface<Person> {
    
    public PersonDAO(){ }

    @Override
    public void save(Person person) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao salvar pessoa.", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Person getById(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        Person person = null;
        try{
            person = entityManager.find(Person.class, id);
        } catch (Exception e) {
            throw new Exception("Erro ao obter pessoa.", e);
        } finally {
            entityManager.close();
        }
        return person;
    }
    
    public List<Person> getByProject(Project project) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<Person> person = new ArrayList<>();;
        try{
            TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p WHERE p.project.id = :id", Person.class);
            query.setParameter("id", project.getId());
            person = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao obter pessoas.", e);
        } finally {
            entityManager.close();
        }
        return person;
    }
    
    public List<Person> getByTask(Task task) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<Person> person = new ArrayList<>();;
        try{
            TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p WHERE p.task.id = :id", Person.class);
            query.setParameter("id", task.getId());
            person = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao obter pessoas.", e);
        } finally {
            entityManager.close();
        }
        return person;
    }
    
    public List<Person> getByEmail(String email) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<Person> person = null;
        try{
            TypedQuery<Person> query = entityManager.createQuery("SELECT person Person Login person WHERE login.email = :email", Person.class);
            query.setParameter("email", email);
            person = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao obter email de pessoa.", e);
        } finally {
            entityManager.close();
        }
        return person;
    }

    @Override
    public void update(Person person) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(person);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao atualizar pessoa.", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            Person person = entityManager.find(Person.class, id);
            if (person != null) {
                entityManager.remove(person);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao deletar pessoa.", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List getAll() throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<Person> personList = null;
        try{
            TypedQuery<Person> query = entityManager.createQuery("SELECT person FROM Person person", Person.class);
            personList = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Erro ao obter todas pessoas.", e);
        } finally {
            entityManager.close();
        }
        return personList;
    }

    public List getPersonsOfProject(Project project) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<Person> personList = null;
        try{
            TypedQuery<Person> query = entityManager.createQuery("SELECT person FROM Person person WHERE person.project.id = :id", Person.class);
            query.setParameter("id", project.getId());
            personList = query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return personList;
    }
}
