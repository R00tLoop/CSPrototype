import java.io.*;
import java.util.*;
public class ServiceList
{
    ArrayList<Service> allServices = new ArrayList<>();

    ArrayList<String> serviceTimes = new ArrayList<>();
    Service tempService = new Service();

    Stop tempStop = new Stop();

    StopList tempStopList = new StopList();
    /*
    public boolean addAccountToList(Service temp)
    {
        System.out.println("addAccountToList() called. Temp.sName = " + temp.sName);
        System.out.println("Current array is:");
        for(int i = 0; i < allServices.size(); i++)
        {
            System.out.println("allServices(" + i + ") = " + allServices.get(i));
        }
        for(int i = 0; i < allServices.size(); i++)
        {
            System.out.println("Int i = " + i + ". allServices[i] = " + allServices.get(i));
            if((allServices.get(i).sName).equals(temp.sName))
            {
                System.out.println("Service " + temp.sName  + " was taken in array. Size is " + allServices.size() + ". I is " + i);
                return false;
            }
        }
        allServices.add(temp);
        System.out.println("Added " + temp.sName + ". Will now return true");
        System.out.println("allServices[0] = " + allServices.get(0));
        return true;
    }
    */

    // https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java


    public void readAllServices(File folder)
    {
        System.out.println("readAllServices() called");
        Service tempService = new Service();
        try
        {
            for (File thisFile : Objects.requireNonNull(folder.listFiles())) // To avoid nullPointerException with folder.listFiles() function
            {
                tempService.load(thisFile);
                //System.out.println("Attempting to read " + thisFile + ". Has read name as " + tempService);
                allServices.add(tempService);
                tempService = new Service(); // This line fixed everything
            }
        }
        catch(Exception e)
        {
            System.out.println("Error loading from saves folder");
        }
    }

    public String searchByName(String name)
    {
        String positions = "";

        for(int i = 0; i < allServices.size(); i++)
        {
            if(((allServices.get(i).sName).toUpperCase()).contains(name.toUpperCase()))
            {
                positions = positions + "," + i; // INVESTIGATE THIS, begins with comma i.e. ",1,2,3,..."----------------------------------------------------
            }
        }
        return positions;
    }
    public void servicesFrom(String tempStopName)
    {
        //Clear stopList obj
        tempStopList = new StopList();
        //Load that file into the stopList object
        tempStopList.load(tempStopList.getRegionFile(tempStopName));
        //Use this to avoid clunky method searching by name, maybe rework that later --------------------------------------------
        tempStop.stName = tempStopName;
        int index = tempStopList.searchByStop(tempStop);
        //Clear tempStop object (precaution)
        tempStop = new Stop();
        tempStop = tempStopList.allStops.get(index);
        serviceTimes = tempStop.serviceTimes;
    }


    public static void main(String[] args)// Remove later -----------------------------------------------
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