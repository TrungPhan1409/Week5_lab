/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import user.User;

/**
 *
 * @author darkn
 */
public class AccountService {
    public User login(String username, String password) {
        if (username.equals("abe") && password.equals("password")) {
            User user = new User(username,null);
            return user;
        } else if (username.equals("barb") && password.equals("password")) {
            User user = new User(username,null);
            return user;
        } else {
            return null;
        }
    }
}

