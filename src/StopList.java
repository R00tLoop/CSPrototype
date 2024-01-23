import java.util.*;
import java.io.*;
public class StopList
{
    ArrayList<Stop> allStops = new ArrayList<>();

    Stop tempStop = new Stop();

    public void saveAllStops(File f)
    {
        Stop[] stopListForSave = new Stop[allStops.size()];
        for(int i = 0; i < allStops.size(); i++)
        {
            stopListForSave[i] = allStops.get(i);
        }
        SLSort sortAlgo = new SLSort();
        Stop[] sortedStopList = sortAlgo.quickSort(stopListForSave, 0, allStops.size()-1);
        try(FileWriter fW = new FileWriter(f))
        {
            for(Stop s : sortedStopList)
            {
                fW.write(s.getSaveString());
            }
        }
        catch(Exception e)
        {
            System.out.println("Error writing to file");
        }
    }

    public void readAllStops(File folder)
    {
        //System.out.println("readAllStops() called");
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
        //System.out.println("SearchByName method reached with name: " + name);
        String positions = "";

        for(int i = 0; i < allStops.size(); i++)
        {
            //System.out.println("Searching " + i + " " + allStops.get(i).stName + " against " + name);
            if(((allStops.get(i).stName).toUpperCase()).contains(name.toUpperCase()))
            {
                //System.out.println("i");
                positions = positions + "," + i; // INVESTIGATE THIS, begins with comma i.e. ",1,2,3,..."----------------------------------------------------
            }
        }
        return positions;
    }

    public Stop getStopByName(String name)
    {
        System.out.println("Trying to get stop with name: " + name);
        Stop tempStop = new Stop();
        int index = -1;
        int rogue = -1;
        for(int i = 0; i < allStops.size(); i++)
        {
            System.out.println("Comparing stop against: " + allStops.get(i).stName);
            //System.out.println("Searching " + i + " " + allStops.get(i).stName + " against " + name);
            if(((allStops.get(i).stName).toUpperCase()).equals(name.toUpperCase()) && rogue==-1)
            {
                //System.out.println("i");
                index = i;
                rogue = 0;
            }
        }
        if(index != -1)
        {
            tempStop = allStops.get(index);
        }
        else
        {
            System.out.println("Failed to find stops with that name");
        }
        return tempStop;
    }


    public void load(File tempSaveFile)
    {
        //System.out.println("Loading from file");
        String oneLine;
        //System.out.println("Loading" + tempSaveFile);
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
                //System.out.println("Read a stop:" + tempStop.stName);
                tempStop = new Stop();
                oneLine = br.readLine();
            }
        }
        catch(Exception e)
        {
            System.out.println("Error thrown reading from file.");
        }
    }

    public File getRegionFile(String name)// ----------------------------------------------------- Not very resilient code
    {
        String fileName = "";
        String readLine;
        try(BufferedReader bR = new BufferedReader(new FileReader(new File("Saves\\Stops\\StopRegions.txt"))))
        {
            readLine = bR.readLine();
            while(readLine!=null && fileName == "")
            {
                //System.out.println("Line read in = " + readLine);
                String[] split = readLine.split(",");
                if(name.equals(split[0]))
                {
                    fileName = split[1];
                    //System.out.println("fileName = " + fileName);
                }
                readLine = bR.readLine();
            }
        }
        catch(Exception e)
        {
            System.out.println("Region file not found");
        }
        System.out.println("Found region file: " + fileName);
        return new File("Saves//Stops//" + fileName + ".txt");
    }

    public String getRegionFileName(String name)
    {
        String fileName = "";
        String readLine;
        try(BufferedReader bR = new BufferedReader(new FileReader(new File("Saves\\Stops\\StopRegions.txt"))))
        {
            readLine = bR.readLine();
            while(readLine!=null && Objects.equals(fileName, ""))
            {
                //System.out.println("Line read in = " + readLine);
                String[] split = readLine.split(",");
                if(name.equals(split[0]))
                {
                    fileName = split[1];
                    //System.out.println("fileName = " + fileName);
                }
                readLine = bR.readLine();
            }
        }
        catch(Exception e)
        {
            System.out.println("Region file not found");
        }
        return fileName;
    }

    public int searchByStop(Stop stop)
    {
        int rtrn = -1;
        for(int i = 0; i < allStops.size(); i++)
        {
            if (stop.stName.equalsIgnoreCase(allStops.get(i).stName))
            {
                rtrn = i;
            }
        }
        return rtrn;
    }
}
