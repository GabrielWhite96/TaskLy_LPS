/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Person;

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
            throw new Exception(e);
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
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return person;
    }
    
    public Person getByEmail(String email) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        Person person = null;
        try{
            TypedQuery<Person> query = entityManager.createQuery("SELECT person Person Login person WHERE login.email = :email", Person.class);
            query.setParameter("email", email);
            person = query.getSingleResult();
        } catch (Exception e) {
            throw new Exception(e);
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
            Person person = entityManager.find(Person.class, id);
            if (person != null) {
                entityManager.remove(person);
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
        List<Person> personList = null;
        try{
            TypedQuery<Person> query = entityManager.createQuery("SELECT login FROM Login login", Person.class);
            personList = query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            entityManager.close();
        }
        return personList;
    }
}
