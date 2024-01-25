import java.util.*;
import java.io.*;

public class DataOrganiser
{
    public static void main(String[] args)
    {
        StopList allStops = new StopList();
        ServiceList sL = new ServiceList();
        sortStop tempStop = new sortStop();
        sL.readAllServices(new File("Saves\\Services\\")); // Load in all services
        //File tempSaveFile = new File("Saves\\temp\\");
        tempFileSearch tFS = new tempFileSearch();
        for(Service tempService : sL.allServices) // For each service
        {
            for(String[] sAry : tempService.stopTimes) // Go through its array
            {
                if (!tFS.searchForStop(sAry[0])) // If there is no temp file in the folder
                {
                    tFS.makeTempFile(sAry[0]); // Make a temp file for that stop VERY HACKY SOLUTION, ABSOLUTELY NEEDS REDOING
                    tempStop.stName = sAry[0];
                    allStops.allStops.add(tempStop);
                }
                //tempStop = tFS.load(sAry[0], tempStop); // Load it
                tempStop = (sortStop) allStops.getStopByName(sAry[0]); // Load it
                tempStop.sortServiceTimes.add(new String[]{tempService.sName, sAry[1]}); // Record when this service will arrive
                tFS = new tempFileSearch(); // Clear the tempFileSearch
                tempStop = new sortStop(); // Clear the tempStop
            }
        }
        tFS.sortAll(allStops); // Cause to sort all those arrays
    }
}