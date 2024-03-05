import java.io.*;
import java.time.*;
import java.util.*;

public class ServiceUpdate
{
    String name = "ServiceUpdates.txt";
    LocalTime[] times = new LocalTime[2];
    String description;
    File location;
    ArrayList<String> servicesAffected = new ArrayList<>();

    public void save()
    {
        getLocation();
        try(FileWriter fw = new FileWriter(location))
        {
            fw.write(name + "," + times[0] + "," + times[1] + "," + description);
            for(String s : servicesAffected)
            {
                fw.write("\r\n");
                fw.write(s);
            }
            fw.close();
        }
        catch(Exception e)
        {
            System.out.println("Failed to make fileWriter for Service Update");
        }
    }

    public void getLocation()
    {
        try
        {
            location = new File("\\Saves\\ServiceUpdates\\" + name);
        }
        catch(Exception e)
        {
            System.out.println("Service Update file cannot be found");
        }
    }

    public void load()
    {
        getLocation();
        try(BufferedReader br = new BufferedReader(new FileReader(location)))
        {
            String[] s = br.readLine().split(",");
            name = s[0];
            times[0] = LocalTime.parse(s[1]);
            times[1] = LocalTime.parse(s[2]);
            description = s[3];
            String readLine = br.readLine();
            while(readLine != null)
            {
                servicesAffected.add(readLine);
                readLine = br.readLine();
            }
        }
        catch(Exception e)
        {
            System.out.println("Failed to load Service Update file");
        }
    }

    public boolean isServiceIn(String serviceName)
    {
        for(String s : servicesAffected)
        {
            if(Objects.equals(serviceName, s))
            {
                return true;
            }
        }
        return false;
    }

    public ServiceUpdate()
    {
        System.out.println("THE SERVICE UPDATE CONSTRUCTOR METHODS WERE RUN ############################################################################################");
        getLocation();
        load();
    }
}
