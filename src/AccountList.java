import java.io.*;

public class AccountList
{
    AdminAcct[] allAccounts = new AdminAcct[100];
    int position = 0;

    public boolean addAccountToList(AdminAcct tempAccount)
    {
        boolean taken = false;
        for(int i = 0; i < position; i++)
        {
            if((allAccounts[i].userID).equals(tempAccount.userID))
            {
                taken = true;
            }
        }
        if(taken == false)
        {
            allAccounts[position] = tempAccount;
            position++;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void writeArrayToFileCommas()
    {
        // Method begins with try-catch statements to catch exceptions potentially thrown by conversions and the fileWriter
        // The conversions would throw if the datatypes aren't compatable, e.g. trying to convert "fish" to an int
        // The fileWriter would throw if the text file cannot be found

        try
        {
            // A fileWriter is declared from the java.util.* library and is to write to "AccSavFileCommas.txt", a file in the project folder
            // A temp object of CustomSong was declared called temp
            FileWriter fw = new FileWriter("\\Saves\\Accounts\\AccSavFileCommas.txt");
            AdminAcct temp = new AdminAcct();

            // This increments through the allSongs array, up to the element before the first empty element
            // Which is indicated by the position variable
            for(int count = 0; count < position; count++)
            {
                // The temp object is set to the element in the allSongs array of CustomSong objects corresponding to the count variable
                // Each attribute of the temp object is converted to a String if not already so, then a comma is concatenated to the end of it
                // It is then passed into the encyption procedure which encrypts it, before being written to the "SaveFileCommas.txt" file
                // A carriage return is written so that the next CustomSong is stored on the next line

                temp = allAccounts[count];

                //# Attributes
                // Could use toString here but would complicate encryption

                fw.write(temp.userID + ",");
                fw.write(temp.pKey + ",");
                fw.write(temp.eKey);

                fw.write("\r\n");
            }

            //#Find out why the fileWriter is closed
            // The fileWriter is closed
            fw.close();
        }
        catch(Exception e)
        {
            //An error message is provided to the catch statement
            System.out.println("Error when writing Accounts Commas");
        }
    }

    public void readArrayFromFileCommas()
    {
        // Initialise a count variable so that the data writes over any data in the allSongs array
        // Everything involving the bufferedReader takes place in try-catch statements
        // This is as errors can be thrown from not finding the file correctly and is also convenient for the conversion of dataTypes later in the method
        // A BufferedReader is declared to read from the save file
        // A temp CustomSong object is declared to make attributes read from the file into an object before storing in the array
        // The first line of the file is read in and stored as tempString
        // A while loop is started so that the program does not try to perform operations on an empty string, or read multiple empty lines
        // tempString is split into a String array by the commas which separate its attributes
        // Each attribute is then decrypted and set to the respective attribute in the temp object
        // The temp object is then added to the array
        // The bufferedReader reads in a new line to be evaluated by the while loop and loaded into the array if not null
        int count = 0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("\\Saves\\Accounts\\AccSavFileCommas.txt")); // Declare the FileReader within a BufferedReader
            AdminAcct temp = new AdminAcct(); // New temp object to read to

            String tempString = br.readLine(); // Read the first line first so that the while loop works out, not that inefficient but not the most elegant solution

            while(tempString!=null) // While not EndOfFile OR would exceed the limitations of my array
            {
                System.out.println("Just read this: "+ tempString); //#FOR DEV/TESTING
                String[] tempStringSplit = tempString.split(","); // Splits at commas
                System.out.println("Successful Split");
                temp = new AdminAcct(); // Clears temp object

                temp.userID = tempStringSplit[0]; // Sets each attribute of temp object
                temp.pKey = tempStringSplit[1];
                temp.eKey = tempStringSplit[2];

                addAccountToList(temp) ; // Adds the temp object to the array

                tempString = br.readLine(); // Reads a new line
                //numberOfRecords++; // Increments count
            }
            //#FIND OUT WHY MUST BE CLOSED
            br.close(); // Must be closed afer use
        }
        catch(Exception e)
        {
            System.out.println("Error reading file"); // Error message in case an exception is thrown
        }

    }

}