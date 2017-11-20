package frame;

import Util.FILEIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sean Zhang
 */
public class LoginEngine {

    //the current user that is logged in. This makes it easy to get user details for the current user.
    User u;

    /**
     * The Constructor.
     */
    public LoginEngine() {

    }

    /**
     * The adduser method. This adds a user to the database.
     *
     * @param fName the first name
     * @param lName the last name
     * @param uName the username
     * @param pass the password
     * @param profileURL the profile url
     */
    public void addUser(String fName, String lName, String uName, String pass, String profileURL) {
        try {   //checks if the url is empty. If not, then set it to the default picture.
            if (profileURL.isEmpty()) {
                profileURL = "https://www.1plusx.com/app/mu-plugins/all-in-one-seo-pack-pro/images/default-user-image.png";
            }
            //adds new user to the database
            FILEIO.printF("login.txt", new User((fName), (lName), (uName), FILEIO.encrypt(pass), (profileURL)).toString(), true);
        } catch (Exception ex) { //nothing here
        }
    }

    /**
     * The isUser method finds out if a user with that username already exists.
     * If so, then it return true. Otherwise, it will return false
     *
     * @param user the username that it is trying to find.
     * @return true or false depending on if the user exists.
     */
    public Boolean isUser(String user) 
    {   //goes through the file as a string array
        //finds the line that the username exists. Returns null if the user does not exist.
        String[] s =FILEIO.findAndReturn("login.txt", user, 2, User.delim);
        
        //If it does not exist, return null.
        if (s==null) 
        {   return false;
        } //If it does exist, return true.
        else 
        {   //create a new instance of user with the details of the user on that line.
            u = new User(s);
            return true;
        }
    } 

    /**
     * Checks if the password given in the argument is equal to the password in the instance of the user class.
     * @param pass the password of the user you want to load.
     * @return true or false depending whether or not the login details matched the database.
     */
    public boolean isPass(String pass) {
        try 
        {
            //checks if the password is correct
            return (u.getPass().equals(FILEIO.encrypt(pass)));
        } catch (Exception ex) 
        { //nothing here
        }
        //defaults to false otherwise
        return false;
    }
    
    /**
     * Gets the user from memory
     * @return the instance of user that is stored in this instance of LoginEngine.
     */
    public User getUser() {
        //returns the user loaded.
        return u;
    }

}
