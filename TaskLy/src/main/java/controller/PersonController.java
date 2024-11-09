/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PersonDAO;
import model.Login;
import model.Person;

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
            personDAO.save(person);
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o usuário!", e);
        }
    }
}
