/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LoginDAO;
import model.Login;
import model.Person;

/**
 *
 * @author wekisley
 */
public class LoginController {
    private final LoginDAO loginDAO;
    
    public LoginController(){
        this.loginDAO = new LoginDAO();
    }
    
    public Login createLogin(String email, String password) throws Exception {
        Login login = new Login(email, password);
        try {
            this.loginDAO.save(login);
        } catch (Exception e){
            throw new Exception("Não foi possível criar o login!");
        }
        return login;
    }
    
    public Person getUserAccount(String email, String password) throws Exception {
        Login login = this.loginDAO.getByEmail(email);
        Person person = login.validatePassword(password);
        if(login == null || person == null){
            throw new Exception("Não foi possível encontrar o usuário!");
        }
        return person;
    }
}
