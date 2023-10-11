import java.util.ArrayList;

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

    public String searchJourney(Stop start, Stop finish)
    {
        String path = "";
        // Iterates through all the services visiting the start stop and puts them in an array
        services.addAll(start.allServices);
        //Iterates through them
        for(Service s : services)
        {
            int radical = 0;
            int i = -1;
            int stPos = 0;
            //Iterates through the stops in a service until the start is found
            while(radical == 0)
            {
                i++;
                if(s.stopTimes[i][0].equalsIgnoreCase(start.stName))
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
                if(s.stopTimes[i][0].equalsIgnoreCase(finish.stName));
                {
                    path = path + "," + s.sName;
                    return path;
                }
            }
        }
        return "error";
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
