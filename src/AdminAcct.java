import java.util.*;
import java.io.*;

public class AdminAcct
{
    String userID;
    String pKey;
    String eKey;
    //String email; //# Maybe encrypt?
    //String dateOfC;
    SecureHash hash = new SecureHash(); // Create object of hashing class

    public void createAccount(String usrName, String pWord) // MAYBE NOT NECESSARY
    {
        //# Generate a userID
        Random rand = new Random();

        userID = hash.strHash(usrName);

        createFile();

        // Generate encryption key
        eKey = String.valueOf(rand.nextInt(11));

        //# Hash the password

        pKey = hash.strHash(pWord);
    }

    public void createFile() // MAYBE NOT NECESSARY
    {
        //# Make a save file for the user
        try
        {
            File tempObj = new File("Saves\\" + userID + ".txt");
            if(tempObj.createNewFile() == true)
            {
                System.out.println("File created successfully.");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error generating save file.");
        }
    }

    public void deleteAccount() // MAYBE NOT NECESSARY
    {
        File tempObj = new File("Saves\\" + userID + ".txt");
        if(tempObj.delete() == true)
        {
            System.out.println("File deleted successfully.");
        }
    }

    public boolean verifyPass(String pass)
    {
        return pKey.equals(hash.strHash(pass));
    }
}