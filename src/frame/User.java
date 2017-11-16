/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

/**
 *
 * @author seanz
 */
public class User {
    //details for this user. They are all encrypted for safety
    private String fName,lName,uName,pass,profileURL;
    
    public User(String fName, String lName, String uName, String pass,String profileURL) {
        this.fName=fName;
        this.lName=lName;
        this.uName=uName;
        this.pass=pass;
        this.profileURL=profileURL;
    }
    
    public User(String[] s) {
        this.fName=s[0];
        this.lName=s[1];
        this.uName=s[2];
        this.pass=s[3];
        this.profileURL=s[4];
    }


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * 
     * @return 
     */
    public String getuName() {
        return uName;
    }

    /**
     * 
     * @param uName 
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * Gets the password
     * @return 
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets the password
     * @param pass 
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Gets the profile url
     * @return 
     */
    public String getProfileURL() {
        return profileURL;
    }

    /**
     * 
     * @param profileURL 
     */
    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
    
    /**
     * 
     */
    public static String delim = "***";
    
    public String toString() {
        return (getfName() + delim + getlName() + delim + getuName() + delim + getPass() + delim + getProfileURL());
    }
    
    
    
    
}
