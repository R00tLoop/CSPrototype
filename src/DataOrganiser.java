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
                //sAry is in the form [STOP NAME, TIME OF ARRIVAL]
                tempStop.stName = sAry[0];
                if (allStops.checkForStop(sAry[0]) == -1) // If there is no stop of that name in the array
                {
<<<<<<< Updated upstream
                    allStops.allStops.add(tempStop);
                    System.out.println("Added " + sAry[0] + " to the StopList");
=======
                    tFS.makeTempFile(sAry[0], "Stops"); // Make it a temp file
                    System.out.println("Making temp file");
>>>>>>> Stashed changes
                }
                tempStop = (sortStop) allStops.getStopByName(sAry[0]); // Load it
                tempStop.sortServiceTimes.add(new String[]{tempService.sName, sAry[1]}); // Record when this service will arrive
                tFS = new tempFileSearch(); // Clear the tempFileSearch
                tempStop = new sortStop(); // Clear the tempStop
            }
        }
        tFS.sortAll(allStops); // Cause to sort all those arrays
    }
}