import java.io.*;
import java.util.ArrayList;

public class Stop
{
    ArrayList<String[]> sortServiceTimes = new ArrayList<>();

    String stName = "";
    ArrayList<Service> allServices = new ArrayList<>();

    ArrayList<String> serviceTimes = new ArrayList<>();
    String[] location = new String[2];

    public void load(File tempSaveFile) // OLD STYLE
    {
        //System.out.println("Loading" + tempSaveFile);
        try(BufferedReader br = new BufferedReader(new FileReader(tempSaveFile)))
        {
            stName = br.readLine();
            String tempString = br.readLine();
            Service tempService = new Service();

            while(tempString != null)
            {
                tempService.load(getSaveFile(tempString));
                allServices.add(tempService);
                tempString = br.readLine();
            }
        }
        catch(Exception e)
        {
            System.out.println("Error thrown reading from file.");
        }
    }

    public String getSaveString()
    {
        StringBuilder saveEntry = new StringBuilder(stName);
        //Iterates through arrayList
        for(String s : serviceTimes)
        {
            saveEntry.append(",").append(s);
        }
        return saveEntry.toString();
    }

    public File getSaveFile(String name) // OLD STYLE
    {
        File f = new File("Saves\\Stops\\" + name + ".txt");
        if(!f.exists())
        {
            newSaveFile(name);
        }
        return f;
    }

    public void newSaveFile(String name) // OLD STYLE
    {
        try
        {
            File tempFile = new File("Saves\\Stops\\" + name + ".txt");
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

    public void loadFromName(String name) // OLD STYLE
    {
        File file = getSaveFile(name);
        load(file);
    }
}