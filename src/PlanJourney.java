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
    public void searchJourney(Stop start, Stop finish)
    {
        for(Service s : start.allServices)
        {
            services.add(s);
        }
    }
}
