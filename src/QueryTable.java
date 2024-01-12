import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class QueryTable extends JTable
{
    /*
    String[] headings = {"Name", "Time", "Until"};

    public QueryTable(DefaultTableModel tModel)
    {

    }

    public DefaultTableModel update() {
        DefaultTableModel tmodel = getModel();
        int rows = tmodel.getRowCount();
        String queryVarName = "";
        String queryVarTime = "";
        for (int i = 0; i < rows; i++) {
            queryVarName = tmodel.getValueAt(i, 0).toString();
            queryVarTime = tmodel.getValueAt(i, 2).toString();
            if (queryVarTime != null) {
                LocalTime paramTime = LocalTime.parse(queryVarTime);
                LocalTime currentTime = LocalTime.now();
                if (paramTime.isAfter(currentTime)) {
                    long minsUntil = currentTime.until(paramTime, ChronoUnit.MINUTES);
                    int minsUntilInt = (int) minsUntil;
                    if (minsUntilInt > 60) {
                        long hoursUntil = currentTime.until(paramTime, ChronoUnit.HOURS);
                        minsUntilInt = minsUntilInt % 60;
                        //this.addRow(queryVarName, queryVarTime, String.valueOf(hoursUntil) + "hrs " + minsUntilInt + "mins");
                        tmodel.setValueAt(queryVarName, i, 0);
                        tmodel.setValueAt(queryVarTime, i, 1);
                        tmodel.setValueAt(String.valueOf(hoursUntil) + "hrs " + minsUntilInt + "mins", i, 2);
                    } else {
                        //addRow(queryVarName, queryVarTime, String.valueOf(minsUntil) + "mins");
                        tmodel.setValueAt(queryVarName, i, 0);
                        tmodel.setValueAt(queryVarTime, i, 1);
                        tmodel.setValueAt(String.valueOf(minsUntil) + "mins", i, 2);
                    }
                }
            }
        }
        return tmodel;
    }

    /*
    *
    *
    public void addRow(String c1, String c2, String c3)
    {
        String[] tempElements = new String[3];
        tempElements[0] = c1;
        tempElements[1] = c2;
        tempElements[2] = c3;
        //tableContents.add(tempElements);
        TableModel tModel = getModel();
        tModel.addRow(tempElements);
    }
    *
    *
    *
    *
    *


    public void createQueryTable()
    {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int prevMinute = LocalTime.now().getMinute();
                LocalTime time = LocalTime.now();
                if(time.getMinute() != prevMinute)
                {
                    prevMinute = time.getMinute();
                    System.out.println("Trying to update");
                }
            }
        });
        timer.start();
    }
    */

    public QueryTable(DefaultTableModel tM)
    {
        this.setModel(tM);
    }
}
