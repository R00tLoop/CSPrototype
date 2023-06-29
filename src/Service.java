import java.io.*;
//import java.util.*;

public class Service extends Entity
{
    String sName = "";
    String dID = "";
    String vID = "";
    String[][] stopTimes = new String[100][2];
    int stopCount = 0;

    //Create sav file for each service
    //Initial information
    //Stop, time /n Stop, time /n ...

    public static void main(String[] args)
    {
        Service s = new Service();
        s.load(s.getSaveFile("T12"));
        System.out.println(s);
    }

    /*public void outputIt()
    {
        for(int i = 0; i < stopCount; i++)
        {
            System.out.println(stopTimes[i][0] + "," + stopTimes[i][1]);
        }
    }*/


    //Returns save file, makes one if it doesn't already exist
    public File getSaveFile(String name)
    {
        File f = new File("Saves\\Services\\" + name + ".txt");
        if(!f.exists())
        {
            newSaveFile(name);
        }
        return f;
    }

    public void newSaveFile(String name)
    {
        try
        {
            File tempFile = new File("Saves\\Services\\" + name + ".txt");
            if(tempFile.createNewFile())
            {
                System.out.println("File created successfully :)");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error generating save file.");
        }
    }

    public String toString()
    {
        // Concatenates all attributes into a String separated by commas
        // Returns this String
        return(sName+","+dID+","+vID);
    }

    public void save()
    {
        try(FileWriter fw = new FileWriter(getSaveFile(sName))) // Try-with-resources
        {
            fw.write(toString());

            //Iterates through 2d array writing
            for(int i = 0; i < stopCount; i++)
            {
                fw.write(stopTimes[i][0] + stopTimes[i][1]);
                fw.write("\r\n");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error thrown writing to file.");
        }
    }

    public void load(File tempSaveFile)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(tempSaveFile)))
        {
            int i = 0;
            String tempString = br.readLine();
            String[] tempStringSplit = tempString.split(","); // Splits at commas

            sName = tempStringSplit[0];
            dID = tempStringSplit[1];
            vID = tempStringSplit[2];

            tempString = br.readLine();

            while(tempString != null)
            {
                tempStringSplit = tempString.split(","); // Splits at commas

                stopTimes[i][0] = tempStringSplit[0];
                stopTimes[i][1] = tempStringSplit[1];

                tempString = br.readLine();
                i++;
            }
            stopCount = i;
        }
        catch(Exception e)
        {
            System.out.println("Error thrown writing to file.");
        }
    }
}
