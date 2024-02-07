import java.math.*;
import java.security.*;

public class SecureHash
{
    // Companies do not store the passwords of users in their databases as this is incredibly insecure
    // Instead they hash the password using a system of complex functions that are easy to compute one way but not the other
    // An example of this are the SHA (secure hashing algorithm) variants made by the American NSA (National Security Agency)
    // This class implements the SHA-1 hashing algorithm to generate a 40-digit hex hash using the java.security libraries
    // Each password is hashed with SHA-1 before storage and passwords are hashed before comparison to expected hash
    // If the hashes are the same then the inputted passwords were the same, this is all done without storing the user's password

    public static String strHash(String usrStr) // This method returns a 40-digit string from a passed string parameter
    {
        // Try-catch to catch exceptions, complicated as returns a String, explained below
        try
        {
            // Initialises SHA-1 algorithm from java.security library as an object of MessageDigest called mdSHA1
            MessageDigest mdSHA1 = MessageDigest.getInstance("SHA-1");

            // input.getBytes() encodes the userPass string into a sequence of bytes stored as an array (necessary for SHA-1 algorithm)
            // The digest method of the md object (Initialised with the SHA-1 algorithms) is run on this array
            // The output is stored in an array of datatype byte
            byte[] messageDigest = mdSHA1.digest(usrStr.getBytes());

            // Using the java.math.BigInteger library, this array is converted to an object of BigInteger
            // This object stores the array in signum (sign - number) representation which is necessary as "-" does not convert to hex
            // And the output of the SHA-1 algorithm can be negative
            BigInteger no = new BigInteger(1, messageDigest); // The 1 indicates signum

            // The BigInteger in signum representation is then converted to base-16 using the toString method
            String hashtext = no.toString(16);

            // Needs to be 32-bit so add 0s to beginning until it is
            while(hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }

            // Return the hashtext String
            return hashtext;
        }
        catch(Exception e) // Catches an exception
        {
            // Throws a new runtime error so that in the event an error is thrown, a string does not have to be returned
            throw new RuntimeException(e);
        }
    }

}