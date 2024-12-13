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
        Project project = persons.get(0).getProject();
        try {
            for(Person person: persons){
                this.update(person);
                project = person.getProject();
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível atualizar os usuários!");
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
