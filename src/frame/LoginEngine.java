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
    String[] f;
    //the arraylist for the users
    ArrayList<User> userList = new ArrayList();
    //the current user that is logged in. This makes it easy to get user details for the current user.
    int currentUser;

    /**
     * The Constructor. This loads in all the users from the text file to the arraylist.
     */
    public LoginEngine() 
    {
        //fills String array
        f = FILEIO.getFArr("login.txt", "\n").clone();
        
        //checks if it is not empty. If so, then do not load anything into the arraylist.
        if (!FILEIO.getF("login.txt").isEmpty()) 
        {
            //loops through the text file, given as a String[] (thanks to the FILEIO.getFArr method)
            for (int i = 0; i < FILEIO.getFArr("login.txt", "\n").length; i++) 
            {
                //splits the current line into several indexes of an array for storing into an instance of user class
                FILEIO.getFArr("login.txt", "\n")[i].split(" ");
                userList.add(new User(s[0], s[1], s[2], s[3], s[4]));
            }
        }
    }

    /**
     * The adduser method. This adds a user to the userList arraylist and the text file.
     * @param fName the first name
     * @param lName the last name
     * @param uName the username
     * @param pass the password
     * @param profileURL the profile url
     */
    public void addUser(String fName, String lName, String uName, String pass, String profileURL) {
        try 
        {   //checks if the url is empty. If not, then set it to the default picture.
            if (profileURL.isEmpty()) 
            {
                profileURL = "https://www.1plusx.com/app/mu-plugins/all-in-one-seo-pack-pro/images/default-user-image.png";
            }
            //add new user to the arraylist
            userList.add(new User((fName), (lName), (uName), FILEIO.encrypt(pass), (profileURL)));
            currentUser = userList.size() - 1;
        } catch (Exception ex) 
        { //nothing here
        }
        //add new user to the text file.
        FILEIO.printF("login.txt", userList.get(currentUser).toString(), true);
    }

    /**
     * The isUser method finds out if a user with that username already exists. If so, then it return which line it is located. If not, then it will return -1.
     * @param user the username that it is trying to find.
     * @return the index where it is located, or -1 if it cannot find it.
     */
    public static int isUser(String user) 
    {   //goes through the file as a string array
        for (int i = 0; i < FILEIO.getFArr("login.txt", "\n").length; i++) 
        {
            try 
            {   //checks if the current user at the index is the user given in the parameter
                if (((FILEIO.getFArr("login.txt", "\n")[i].split(" ")[2])).equals(user)) 
                {   //returns the index of the username
                    return i;
                }
            } catch (Exception ex) {}
        }
        //returns -1 if nothing was found.
        return -1;
    }

    /**
     * Checks if the password corresponding to the given username is correct.
     * @param user the given username
     * @param pass the given password
     * @return 
     */
    public static boolean isPass(String user, String pass) {
        //finds the index where the user details reside and checks if the password given is the
        //same as the corresponding password in the text file (when encrypted)
        if(FILEIO.getFArr("login.txt", "\n")[isUser(user)].equals(FILEIO.encrypt(pass))) 
        {   return true;
        }
        
        //loops through the file as a string array
        for (int i = 0; i < FILEIO.getFArr("login.txt", "\n").length; i++) {
            try {
                //finds the index of the user
                if ((FILEIO.getFArr("login.txt", "\n")[i].split(" ")[2]).equals(user)) {
                    //checks if the password is the same as the 
                    if ((FILEIO.getFArr("login.txt", "\n")[i].split(" ")[3]).equals(FILEIO.encrypt(pass))) {
                        return i;
                    }
                }
            } catch (Exception ex) {
                return -1;
            }
        }
        System.out.println("hello");
        return -1;
    }
    

    public void loadUser(String user, String pass) {
        currentUser = isPass(user, pass);
    }

    public void loadUser(String user) {
        currentUser = isUser(user);
    }

    public User getUser() {
        return userList.get(currentUser);
    }

    public ArrayList<User> getUserArr() {
        return userList;
    }

}
