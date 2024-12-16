/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PersonDAO;
import java.util.ArrayList;
import java.util.List;
import model.Login;
import model.Person;
import model.Project;

/**
 *
 * @author wekisley
 */
public class PersonController {
    public final PersonDAO personDAO;
    
    
    public PersonController(){
        this.personDAO = new PersonDAO();
    }
    
    public void createNewUser(String name, Login login, String address, String phoneNumber, String jobTitle, String gender) throws Exception {
        Person person = new Person(name, login, address, phoneNumber, jobTitle, gender);
        try {
            this.personDAO.save(person);
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o usuário!");
        }
    }
    
    public void update(Person person) throws Exception{
        try {
            this.personDAO.update(person);
        } catch (Exception e) {
            throw new Exception("Não foi possível atualizar o usuário!");
        }
    }
    
    public void update(List<Person> persons) throws Exception{
        try {
            for(Person person: persons){
                this.update(person);
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível atualizar os usuários!");
        }
    }
    
    public void removePersonsOfProject(Project project) throws Exception{
        try {
            List<Person> persons = (List<Person>) personDAO.getByProject(project);
            for(Person person: persons){
                person.setProject(null);
                this.update(person);
            }
        } catch(Exception e) {
            throw new Exception("Não foi possível remover as pessoas do projeto!", e);
        }
    }
    
    public Person find(int id) throws Exception{
        try {
            return personDAO.getById(id);
        } catch (Exception e) {
            throw new Exception("Não foi possível encontrar o usuário!");
        }
    }
    
    public Person find(Person person) throws Exception{
        try {
            return personDAO.getById(person.getId());
        } catch (Exception e) {
            throw new Exception("Não foi possível encontrar o usuário!");
        }
    }
    
    public List<Person> getAllPersons() throws Exception{
        try {
            return this.personDAO.getAll();
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
}
