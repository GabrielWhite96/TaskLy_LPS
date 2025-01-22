/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PersonDAO;
import java.util.ArrayList;
import java.util.List;
import model.AppStateSingleton;
import model.Login;
import model.Person;
import model.Project;
import model.Task;
import utils.Roles;

/**
 *
 * @author wekisley
 */
public class PersonController {
    private final PersonDAO personDAO;
    private AppStateSingleton appStateSingleton;
    
    public PersonController(){
        this.personDAO = new PersonDAO();
        this.appStateSingleton = AppStateSingleton.getInstance();
    }
    
    public void createNewUser(String name, Login login, String address, String phoneNumber, String jobTitle, String gender) throws Exception {
        if(this.appStateSingleton.userIs(Roles.ADMIN)){
            Person person = new Person(name, login, address, phoneNumber, jobTitle, gender);
            try {
                this.personDAO.save(person);
            } catch (Exception e) {
                throw new Exception("Não foi possível salvar o usuário!");
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public void update(Person person) throws Exception {
        if(this.appStateSingleton.userIs(Roles.ADMIN)){
            try {
                this.personDAO.update(person);
            } catch (Exception e) {
                throw new Exception("Não foi possível atualizar o usuário!");
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public void update(List<Person> persons) throws Exception {
        if(this.appStateSingleton.userIs(Roles.ADMIN)){
            try {
                for(Person person: persons){
                    this.update(person);
                }
            } catch (Exception e) {
                throw new Exception("Não foi possível atualizar os usuários!");
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public void removePersonsOfProject(Project project) throws Exception {
        if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
            try {
                List<Person> persons = (List<Person>) personDAO.getByProject(project);
                for(Person person: persons){
                    person.setProject(null);
                    this.update(person);
                }
            } catch(Exception e) {
                throw new Exception("Não foi possível remover as pessoas do projeto!", e);
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public void removePersonsOfTask(Task task) throws Exception {
        if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
            try {
                List<Person> persons = (List<Person>) personDAO.getByTask(task);
                for(Person person: persons){
                    person.setProject(null);
                    this.update(person);
                }
            } catch(Exception e) {
                throw new Exception("Não foi possível remover as pessoas do projeto!", e);
            }
        } else {
            throw new Exception("Você não tem a permissão necessária!");
        }
    }
    
    public Person find(int id) throws Exception {
        try {
            if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
                return personDAO.getById(id);
            } else {
                throw new Exception("Você não tem a permissão necessária!");
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível encontrar o usuário!");
        }
    }
    
    public List<Person> getAllPersons() throws Exception {
        try {
            if(!this.appStateSingleton.userIs(Roles.EMPLOYEE)){
                return this.personDAO.getAll();
            } else {
                ArrayList<Person> persons = new ArrayList<Person>();
                persons.add(this.appStateSingleton.getUser());
                return persons;
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o usuário!");
        }
    }
    
    public List<Person> getEmployeesByRole(List<Person> persons, String role){
        List<Person> personsFiltered = new ArrayList<>();
        for(Person person: persons){
            if(person.getJobTitle().equals(role)){
                personsFiltered.add(person);
            }
        }
        return personsFiltered;
    }
    
    public List<Person> getPersonsOf(Project project) throws Exception{
        Person user = this.appStateSingleton.getUser();
        if(this.appStateSingleton.userIs(Roles.ADMIN) || user.getProject().getId() == project.getId()){
            try {
                return this.personDAO.getByProject(project);
            } catch (Exception e) {
                throw new Exception("Não foi possível encontrar o usuário!");
            }
        } else {
            throw new Exception("Você não pertence ao projeto!");
        }
    }
}
