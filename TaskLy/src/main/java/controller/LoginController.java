/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LoginDAO;
import model.Login;

/**
 *
 * @author wekisley
 */
public class LoginController {
    private LoginDAO loginDAO;
    
    public LoginController(){
        this.loginDAO = new LoginDAO();
    }
    
    public int getUserAccount(String email, String password) throws Exception {
        Login login = this.loginDAO.getByEmail(email);
        if(login == null){
            throw new Exception("Não foi possível encontrar o usuário!");
        }
        return login.validatePassword(password);
    }
}
