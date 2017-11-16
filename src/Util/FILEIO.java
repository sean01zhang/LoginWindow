/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Static Class for Inputting and Outputting files in Java (Including encryption
 * features).
 *
 * @author Sean and Only Sean
 */
public class FILEIO {

    /**
     * Prints string to a file specified. Can append or overwrite depending on
     * the append boolean
     *
     * @param fName the fileName that exists inside the Res (resources) folder
     * in the project.
     * @param s the string that is being written
     * @param append whether or not the user wants to append the file or
     * overwrite the data.
     */
    public static void printF(String fName, String s, Boolean append) {
        //creates a new file
        File f = new File("src/Res/" + fName);
        //creates a printWriter varaible
        PrintWriter p;

        //catches exceptions related to IO exception
        try {
            //creates a new printwriter that prints to file f and can append depending
            //on the boolean argument given.
            p = new PrintWriter(new FileWriter(f, append));
            //prints the string to the file
            p.println(s);
            //closes the printWriter.
            p.close();
        } catch (Exception e) {
            Logger.getLogger(FILEIO.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Finds where the given string resides in the file in terms of lines.
     *
     * @param fName the file name of the thing
     * @param s
     * @param detail
     * @param delim
     * @return
     */
    public static int find(String fName, String s, int detail, String delim) {
        //creates a new instance of file with the given path of the file.
        File f = new File("src/Res/" + fName);
        //creates a scanner variable
        Scanner input;
        int count = -1;

        //catches exceptions related to FileNotFoundException
        try {
            //creates a new instance of Scanner in the input variable
            input = new Scanner(f);

            //adds every line of the file into the output string.
            while (input.hasNext()) {
                count++;
                if (s.equals(input.nextLine().split(delim)[detail])) {
                    //closes the scanner
                    input.close();
                    return count;
                }
            }

        } catch (FileNotFoundException ex) {
        }

        return -1;
    }

    /**
     * Creates a new file in the Res (resources) folder.
     *
     * @param fName the filename
     */
    public static void printF(String fName) {
        //creates a new file in the Res (resources) folder.
        File f = new File("src/Res/" + fName);
    }

    /**
     * Gets the file as a string.
     *
     * @param fName the filename of the file.
     * @return the file as a String array
     */
    public static String getStrFromF(String fName, int index) {
        //creates a new instance of file with the given path of the file.
        File f = new File("src/Res/" + fName);
        //creates a scanner variable
        Scanner input;
        //creates an output variable
        String out = "";

        //catches exceptions related to FileNotFoundException
        try {
            //creates a new instance of Scanner in the input variable
            input = new Scanner(f);

            for (int i = 0; i <= index; i++) {
                if (i == index) {
                    return input.nextLine();
                } else {
                    input.nextLine();
                }
            }

            //closes the scanner
            input.close();
        } catch (FileNotFoundException ex) {
        }

        //returns the output string
        return null;
    }

    /**
     * Encrypts the String given in the argument by ciphering it with a key and
     * converting it to base 64.
     *
     * @param s the string that is to be encrypted
     * @return the encrypted string
     * @throws Exception
     */
    public static String encrypt(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        //give helper function the password
        md.update(s.getBytes());

        //perform encryption
        return new String(md.digest());

    }

}
