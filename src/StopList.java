import java.util.*;
import java.io.*;
public class StopList
{
    ArrayList<Stop> allStops = new ArrayList<>();

    Stop tempStop = new Stop();

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
        System.out.println("SearchByName method reached with name: " + name);
        String positions = "";

        for(int i = 0; i < allStops.size(); i++)
        {
            System.out.println("Searching " + i + " " + allStops.get(i).stName + " against " + name);
            if(((allStops.get(i).stName).toUpperCase()).contains(name.toUpperCase()))
            {
                System.out.println("i");
                positions = positions + "," + i; // INVESTIGATE THIS, begins with comma i.e. ",1,2,3,..."----------------------------------------------------
            }
        }
        return positions;
    }


    public void load(File tempSaveFile)
    {
        System.out.println("Loading from file");
        String oneLine = "";
        System.out.println("Loading" + tempSaveFile);
        try(BufferedReader br = new BufferedReader(new FileReader(tempSaveFile)))
        {
            oneLine = br.readLine();
            while(oneLine != null) {
                String[] split = oneLine.split(",");
                int length = split.length;
                tempStop.stName = split[0];
                for (int i = 1; i < length - 1; i++) {
                    if (i % 2 == 1) {
                        tempStop.serviceTimes.add(split[i] + "," + split[i + 1]);
                    }
                }
                allStops.add(tempStop);
                System.out.println("Read a stop:" + tempStop.stName);
                oneLine = br.readLine();
            }
        }
        catch(Exception e)
        {
            System.out.println("Error thrown reading from file.");
        }
    }

    public int searchByStop(Stop stop)
    {
        int rtrn = -1;
        for(int i = 0; i < allStops.size(); i++)
        {
            if(stop.stName.equalsIgnoreCase(allStops.get(i).stName))
            {
                rtrn = i;
            }
        }
        return rtrn;
    }
}
