import java.io.*;
import java.util.ArrayList;

public class Stop extends Entity
{
    String stName = "";
    ArrayList<Service> allServices = new ArrayList<>();
    String[] location = new String[2];

    public void load(File tempSaveFile)
    {
        System.out.println("Loading" + tempSaveFile);
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

    public void save()
    {
        try(FileWriter fw = new FileWriter(getSaveFile(stName))) // Try-with-resources
        {
            fw.write(toString());
            fw.write("\r\n");

            //Iterates through arrayList
            for(Service s : allServices)
            {
                fw.write(s.sName);
                fw.write("\r\n");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error thrown writing to file.");
        }
    }

    public File getSaveFile(String name)
    {
        File f = new File("Saves\\Stops\\" + name + ".txt");
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
}