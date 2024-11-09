package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Login;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wekisley
 */
public class LoginDAO implements DAOInterface<Login> {
    public LoginDAO(){ }

    @Override
    public void save(Login login) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(login);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao salvar login!");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Login getById(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        Login login = null;
        try{
            login = entityManager.find(Login.class, id);
        } catch (Exception e) {
            throw new Exception("Opa! Há algo de errado, tente mais tarde!");
        } finally {
            entityManager.close();
        }
        return login;
    }
    
    public Login getByEmail(String email) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        Login login = null;
        try{
            TypedQuery<Login> query = entityManager.createQuery("SELECT login FROM Login login WHERE login.email = :email", Login.class);
            query.setParameter("email", email);
            login = query.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Opa! Há algo de errado, tente mais tarde!");
        } finally {
            entityManager.close();
        }
        return login;
    }

    @Override
    public void update(Login login) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(login);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao atualizaro login!");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            Login login = entityManager.find(Login.class, id);
            if (login != null) {
                entityManager.remove(login);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao deletar login!");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List getAll() throws Exception {
        EntityManager entityManager = ConnectionDB.getEntityManager();
        List<Login> loginList = null;
        try{
            TypedQuery<Login> query = entityManager.createQuery("SELECT login FROM Login login", Login.class);
            loginList = query.getResultList();
        } catch (Exception e) {
            throw new Exception("Não foi possível obter os logins!");
        } finally {
            entityManager.close();
        }
        return loginList;
    }
}
