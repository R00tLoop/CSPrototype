import java.util.*;
import java.io.*;

public class DataOrganiser
{
    public void organiseByServices()
    {
        ArrayList<Stop> allStops = new ArrayList<>();
        ServiceList sL = new ServiceList();
        sortStop tempStop = new sortStop();
        sL.readAllServices(new File("Saves\\Services\\")); // Load in all services
        //File tempSaveFile = new File("Saves\\temp\\");
        tempFileSearch tFS = new tempFileSearch();
        int rogue = -1;
        for(Service tempService : sL.allServices) // For each service
        {
            for(String[] sAry : tempService.stopTimes) // Go through its array
            {
                if (!tFS.searchForStop(sAry[0]))
                {
                    tFS.makeTempFile(sAry[0]); // Make it a temp file
                }
                tempStop = tFS.load(sAry[0], tempStop); // Load it
                tempStop.sortServiceTimes.add(new String[]{tempService.sName, sAry[1]}); // Record when this service will arrive
                tFS = new tempFileSearch(); // Clear the tempFileSearch
                tempStop = new sortStop(); // Clear the tempStop
            }
        }
        tFS.sortAll(); // Cause to sort all those arrays
    }
}