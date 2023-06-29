import java.io.*;

public class ServiceList
{
    Service[] allServices = new Service[100];
    int position = 0;

    public boolean addAccountToList(Service temp)
    {
        boolean taken = false;
        for(int i = 0; i < position; i++)
        {
            if((allServices[i].sName).equals(temp.sName))
            {
                taken = true;
            }
        }
        if(taken == false)
        {
            allServices[position] = temp;
            position++;
            return true;
        }
        else
        {
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
        for(File thisFile : folder.listFiles())
        {
            tempService.load(thisFile);
            addAccountToList(tempService);
        }
    }
}