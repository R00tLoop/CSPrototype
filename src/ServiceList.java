import java.io.*;

public class ServiceList
{
    Service[] allServices = new Service[100];
    int position = 0;

    public boolean addAccountToList(Service temp)
    {
        // NEEDS REWORK!!!!! -----------------------------------------------------
        boolean taken = false;
        int i = 0;
        System.out.println("The boolean taken is " + taken);
        while(!taken && i < position)
        {
            System.out.println("AllServices i = " + i + ", " + allServices[i].sName + allServices[i].vID + allServices[i].dID + " ---- " + temp.sName + temp.vID + temp.dID);
            System.out.println("Therefore " + ((allServices[i].sName).equals(temp.sName)));
            if((allServices[i].sName).equals(temp.sName))
            {
                taken = true;
                //break;
            }
            i++;
        }
        if(!taken)
        {
            System.out.println("Wasn't taken");
            allServices[position] = temp;
            System.out.println("Added " + temp.sName  + temp.vID + temp.dID + " to position " + position);
            position++;
            return true;
        }
        else
        {
            System.out.println("Service " + temp.sName  + " was taken in array. Position is " + position);
            return false;
        }
    }


    //Read all names of files from directory
    //For each of them, pass as parameter into load function
    //Pass that object into the addAccountToList function
    // https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java

    public void readAllServices(File folder)
    {
        Service tempService = new Service();
        try
        {
            for (File thisFile : folder.listFiles()) {
                tempService.load(thisFile);
                System.out.println("Attempting to read " + thisFile);
                if(!addAccountToList(tempService))
                {
                    System.out.println("Account already in list");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error loading from saves folder");
        }
    }

    public static void main(String[] args)
    {
        try
        {
            File f = new File("Saves\\Services\\");
            ServiceList sL = new ServiceList();
            sL.readAllServices(f);
        }
        catch(Exception e)
        {
            System.out.println("Failed");
        }
    }
}