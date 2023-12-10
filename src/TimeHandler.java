import java.time.LocalTime;

public class TimeHandler
{
    public static void main(String[] args)
    {
        LocalTime myObj = LocalTime.now();
        System.out.println(myObj);
    }

    //Methods to use:
    /*
    compareTo(LocalTime other)
    isAfter isBefore(LocalTime other)
    getHour getMinute getSecond()
    minus(long amountToSubtract, TemporalUnit unit)
    now()
    of(int hour, int minute, int second)
    parse(CharSequence text) in format "10:15:30"
    toString()
    equals(LocalTime other)
     */
}
