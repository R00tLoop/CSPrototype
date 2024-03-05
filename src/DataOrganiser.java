import java.util.*;
import java.io.*;

public class DataOrganiser
{
    public static void main(String[] args)
    {
        StopList sLAllStops = new StopList();
        ServiceList sL = new ServiceList();
        Stop tempStop = new Stop();
        sL.readAllServices(new File("Saves\\Services\\")); // Load in all services
        //File tempSaveFile = new File("Saves\\temp\\");
        TempFileSearch tFS = new TempFileSearch();
        for(Service tempService : sL.allServices) // For each service
        {
            System.out.println("Running for Service: " + tempService.sName + "/////////////////////////////////////////////////////////////////");
            for(String[] sAry : tempService.stopTimes) // Go through its array
            {
                System.out.println("Running for Stop: " + sAry[0]);
                //sAry is in the form [STOP NAME, TIME OF ARRIVAL]
                tempStop.stName = sAry[0];
                if (sLAllStops.checkForStop(sAry[0]) == -1) // If there is no stop of that name in the array
                {
                    System.out.println("Stop not yet in stop list, making one");
                    sLAllStops.allStops.add(tempStop);
                    System.out.println("Added " + sAry[0] + " to the StopList");
                }
                tempStop = (Stop) sLAllStops.getStopByName(sAry[0]); // Load it
                tempStop.sortServiceTimes.add(new String[]{tempService.sName, sAry[1]}); // Record when this service will arrive
                System.out.println("Added: " + tempService.sName + " and " + sAry[1] + " to the array inside the stop");
                sLAllStops.allStops.set(sLAllStops.searchByStop(tempStop), tempStop); // Save the tempStop
                tFS = new TempFileSearch(); // Clear the tempFileSearch
                tempStop = new Stop(); // Clear the tempStop
            }
        }
        System.out.println("---------------------------------------------------------------------------------------SLALLSTOPS COMPLETELY LOADED");
        for(Stop s : sLAllStops.allStops)
        {
            System.out.println(s.stName + " is the name of a stop in sLAllStops ///////////////////////////////");
            for(String[] string : s.sortServiceTimes)
            {
                System.out.println("It has servicetime: " + string[0] + "," + string[1]);
            }
        }
        System.out.println("///////////////////////////////////////////////////////////////////////////////////////////SORT ALL STARTED");
        tFS.sortAll(sLAllStops); // Cause to sort all those arrays
    }
}