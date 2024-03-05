import java.util.ArrayList;
import java.io.*;

public class PlanJourney
{
    //List of stops able to be reached from the first stop:
    //  Services hitting the first stop
    //  Any stop in those services reached after that time, and the times they are at them
    //If last is in list, found it
    //Else check each of those for a stop recursively
    //This will do for now
    ArrayList<Service> services = new ArrayList<>();

    int startTime = 0;
    Service tempService = new Service();

    public ArrayList<String[]> searchJourney(String start, String finish)
    {
        StopList startStopList = new StopList();
        File startFile = startStopList.getRegionFile(start);
        startStopList.load(startFile);
        Stop startStop = startStopList.getStopByName(start);
        System.out.println("StartStop is: " + startStop.stName + " ----------------------------------------");

        StopList finishStopList = new StopList();
        File finishFile = finishStopList.getRegionFile(finish);
        finishStopList.load(finishFile);
        Stop finishStop = finishStopList.getStopByName(finish);
        System.out.println("FinishStop is: " + finishStop.stName + " ----------------------------------------");

        ArrayList<String[]> outAL = new ArrayList<>();

        // Iterates through all the services visiting the start stop and puts them in an array
        //services.addAll(startStop.allServices);
        //Iterates through them
        for(String s : startStop.serviceTimes)
        {
            String[] split = s.split(",");
            tempService.load(tempService.getSaveFile(split[0]));
            services.add(tempService);
            System.out.println("Adding service " + tempService.sName);
            tempService = new Service();
        }

        String[] splitString1;
        String[] tempString = new String[3];
        for(Service s : services)
        {
            System.out.println("New Service " + s.sName + "---------");
            for(String[] sA : s.stopTimes)
            {
                System.out.println("Checking stop: " + sA[0]);
                //System.out.println("sA[1] is: " + sA[1]);
                if(sA[0].equals(finish))
                {
                    System.out.println("Found a service which works: " + s.sName + "##########################");
                    tempString[0] = s.sName;
                    //tempString[1] = // Time that the service arrives at the start stop
                    tempString[1] = sA[1]; // Time that the service arrives at the finish stop
                    outAL.add(tempString);
                    System.out.println("Added " + tempString + " to the output");
                    tempString = new String[3];
                }
            }
        }
        /*
        for(String s : startStop.serviceTimes)
        {
        int radical = 0;
        int i = -1;
        int stPos = 0;
        //Iterates through the stops in a service until the start is found
        while(radical == 0)
        {
        i++;
        if(s.stopTimes[i][0].equalsIgnoreCase(startStop.stName))
        {
        radical = 1;
        //Its time value is made into a single integer and the loop is broken
        stPos = i;
        startTime = getTime(s.stopTimes[i][1]);
        }
        }
        //Iterates through the rest of the stops
        for(i=i;s.stopTimes[i][0] != null; i++)
        {
        //If the finish stop is in the array, add it in some way to the end of an array - like a linked list
        if(s.stopTimes[i][0].equalsIgnoreCase(finishStop.stName));
        {
        path = path + "," + s.sName;
        return path;
        }
        }
        }
         */

        System.out.println("================================================================================================");
        for(String[] s : outAL)
        {
            System.out.println(s);
        }
        return outAL;
    }

    public String[] getServices(Stop tempStop)
    {
        String[] string = new String[1];
        return string;
    }

    public int getTime(String timeString)
    {
        try
        {
            String[] tempStringSplit = timeString.split(":");
            return Integer.parseInt(tempStringSplit[0] + tempStringSplit[1]);
        }
        catch(Exception e)
        {
            System.out.println("Error reading time from array");
        }
        return -1;
    }
}
