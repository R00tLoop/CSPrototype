import java.util.*;
import java.io.*;
public class StopList
{
    ArrayList<Stop> allStops = new ArrayList<>();

    public void readAllStops(File folder)
    {
        System.out.println("readAllStops() called");
        Stop tempStop = new Stop();
        try
        {
            for (File thisFile : Objects.requireNonNull(folder.listFiles())) // Avoids nullPointerException with folder.listFiles() function
            {
                tempStop.load(thisFile);
                //System.out.println("Attempting to read " + thisFile + ". Has read name as " + tempService);
                allStops.add(tempStop);
                tempStop = new Stop(); // This line fixed everything
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

        for(int i = 0; i < allStops.size(); i++)
        {
            if(((allStops.get(i).stName).toUpperCase()).contains(name.toUpperCase()))
            {
                positions = positions + "," + i; // INVESTIGATE THIS, begins with comma i.e. ",1,2,3,..."----------------------------------------------------
            }
        }
        return positions;
    }
}
