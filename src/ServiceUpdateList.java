import java.lang.reflect.Array;
import java.util.ArrayList;

public class ServiceUpdateList
{
    ArrayList<ServiceUpdate> allServiceUpdates = new ArrayList<>();

    public ServiceUpdate searchArray(String name)
    {
        for(ServiceUpdate s : allServiceUpdates)
        {
            if(s.name.equals(name))
            {
                return s;
            }
        }
        return new ServiceUpdate();
    }

    public void deleteFromArray(ServiceUpdate sU)
    {
        for(int i = 0; i< allServiceUpdates.size(); i++)
        {
            if(allServiceUpdates.get(i).name.equals(sU.name))
            {
                allServiceUpdates.remove(i);
            }
        }
    }
}
