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
    public Boolean isUser(String user) {   //goes through the file as a string array
        String[] s =FILEIO.findAndReturn("login.txt", user, 2, User.delim);
        if (s==null) {
            return false;
        } else {
            u = new User(s);
            return true;
        }
        
        
    }

    /**
     * Loads the user into memory 
     * @param user the username of the user you want to load
     * @param pass the password of the user you want to load.
     * @return true or false depending whether or not the login details matched the database.
     */
    public boolean loadUser(String user, String pass) {
        //find user
        String[] s = FILEIO.findAndReturn("login.txt", user, 2, User.delim);

        try 
        {
            //checks if the password is correct
            System.out.println(FILEIO.encrypt(pass));
            if (s[3].equals(FILEIO.encrypt(pass))) 
            {
                return true;
            }
            else 
            {
                return false;
            }
        } catch (Exception ex) 
        { //nothing here
        }
        //defaults to false otherwise
        return false;
    }
    
    /**
     * Gets the user from memory
     * @return 
     */
    public User getUser() {
        //returns the user loaded.
        return u;
    }

}
