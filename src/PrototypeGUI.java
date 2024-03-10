import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
public class PrototypeGUI
{
    File saveFolder = new File("Saves\\Services\\");
    JFrame prototypeWindow = new JFrame();
    //JFrame declaration, highest level of GUI

    ArrayList<String[]> tableContents = new ArrayList<>();

    Stop tempStop = new Stop();
    StopList tempStopList = new StopList();

    ServiceList tempServiceList = new ServiceList();
    Service tempService = new Service();
    //Objects to work with inside GUI processes

    JTabbedPane theTabs = new JTabbedPane();
    //Contains tabs, second level

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> listAll= new JList<>(listModel);
    //??????????????????????

    String[] arrSortBy = new String[] {"Name"};

    JPanel mainPanel = new JPanel(null);
    JPanel mapPanel = new JPanel(null);
    JPanel planJourneyPanel = new JPanel(null);
    JPanel loginPanel = new JPanel(null);
    //Main panel, contains components
    JButton addSong = new JButton();
    JComboBox<String> cbxSortBy = new JComboBox<>(arrSortBy);

    JButton btnSort = new JButton();
    JButton btnSearch = new JButton();
    JButton btnRefreshTable = new JButton();
    JButton btnReadFile = new JButton();
    JButton btnPlanJourney = new JButton();

    JLabel banner = new JLabel();
    JLabel banner2 = new JLabel();
    JLabel banner3 = new JLabel();
    JLabel banner4 = new JLabel();
    JLabel banner5 = new JLabel();
    JLabel exampleMap = new JLabel();

    JButton twitterBtn = new JButton();
    JButton twitterBtn_3 = new JButton();
    JButton website = new JButton();

    JTextField searchBar = new JTextField();
    JButton serviceUpdates = new JButton();

    String[] stCombo_data = {"Start", "Stop A", "Stop B", "Stop C", "Stop D", "Stop E", "Stop F"};
    String[] stCombo_9_data = {"Destination", "Stop A", "Stop B", "Stop C", "Stop D", "Stop E", "Stop F"};
    JComboBox<String> stLocation = new JComboBox<>(stCombo_data);
    JComboBox<String> stLocation_9 = new JComboBox<>(stCombo_9_data);

    String[] jpCombo_data={"Depart at","Arrive by"};
    JComboBox<String> jpCombo = new JComboBox<>(jpCombo_data);
    JTextField timeTF = new JTextField();

    //Table stuff
    String[] headings = {"Name", "Time", "Until"};
    //Array of table headings to give table model
    DefaultTableModel tModel = new DefaultTableModel(headings, 0);
    //Declare default table model

    //Multithreading thing (complicated) -------------------------------------------------------------------------

    //JTable mainTable = new JTable(tModel);
    JTable mainTable = new JTable(tModel);


    //Use default table model to declare a JTable
    JScrollPane mainTableScroll = new JScrollPane(mainTable);
    //Use that JTable to declare a JScrollPane


    JButton loginButton = new JButton();



    JCheckBox cBremember = new JCheckBox();
    JLabel jLUName = new JLabel();
    JTextField jtUName = new JTextField();
    JLabel jLPass = new JLabel();
    JButton jBLogin = new JButton();
    JPasswordField jPPass = new JPasswordField();
    JButton jBForgot = new JButton();
    JButton jBSignUp = new JButton();


    JLabel jLNameC = new JLabel();
    JTextField jtUNameC = new JTextField();
    JLabel jLPassC = new JLabel();
    JPasswordField jPPassC = new JPasswordField();
    JButton jBSignUpC = new JButton();
    JButton jBgoBack = new JButton();


    JButton btnClearAll = new JButton();

    AccountList accList = new AccountList();

    AdminAcct guiAccount = new AdminAcct();

    JPanel newAccPanel = new JPanel(null);



    SpinnerDateModel sDM = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
    JSpinner timeSpinner = new JSpinner(sDM);



    //# NEED TO MAKE TIMER TASK EITHER NOT START IMMEDIATELY OR CANCEL WHEN LOGIN IS TAKING PLACE
    //# NEED TO FIX LOGIN BUTTON LOCATION
    //# NEED TO MAKE UI CONSISTENT WITH LOGIN BUTTON
    //# NEED TO MAKE ADMINACCT FILES ANDD LOGINS





    public void initLoginComps()
    {
        cBremember.setLocation(80,320);
        cBremember.setSize(115,30);
        cBremember.setForeground( new Color(-10066330) );
        cBremember.setBackground( new Color(-1) );
        cBremember.setText("Remember me");
        cBremember.setSelected(false);
        loginPanel.add(cBremember);

        jLUName.setLocation(80,120);
        jLUName.setSize(70,30);
        jLUName.setOpaque(true);
        jLUName.setBackground( new Color(-1) );
        jLUName.setText(" Username");
        loginPanel.add(jLUName);

        jtUName.setLocation(80,160);
        jtUName.setSize(500,30);
        loginPanel.add(jtUName);

        jLPass.setLocation(80,200);
        jLPass.setSize(70,30);
        jLPass.setOpaque(true);
        jLPass.setBackground( new Color(-1) );
        jLPass.setText("  Password");
        loginPanel.add(jLPass);

        jBLogin.setLocation(480,305);
        jBLogin.setSize(100,50);
        jBLogin.setForeground(Color.WHITE);
        jBLogin.addActionListener(e->jBLogin_Click());
        jBLogin.setBackground( new Color(-11221164) );
        jBLogin.setText("LOG IN");
        loginPanel.add(jBLogin);

        jPPass.setLocation(80,240);
        jPPass.setSize(500,30);
        jPPass.setColumns(10);
        jPPass.setEchoChar('*');
        loginPanel.add(jPPass);

        jBForgot.setLocation(80,280);
        jBForgot.setSize(170,30);
        //jBForgot.addActionListener(e->jBForgot_Click());
        jBForgot.setText("Forgot your password?");
        loginPanel.add(jBForgot);

        jBSignUp.setLocation(300,305);
        jBSignUp.setSize(160,50);
        jBSignUp.addActionListener(e->jBSignUp_Click());
        jBSignUp.setText("CREATE AN ACCOUNT");
        loginPanel.add(jBSignUp);

        accList.readArrayFromFileCommas();
    }

    public void initCAComps()
    {
        jLNameC.setLocation(80,120);
        jLNameC.setSize(70,30);
        jLNameC.setOpaque(true);
        jLNameC.setBackground( new Color(-1) );
        jLNameC.setText(" Username");
        newAccPanel.add(jLNameC);

        jtUNameC.setLocation(80,159);
        jtUNameC.setSize(500,30);
        newAccPanel.add(jtUNameC);

        jLPassC.setLocation(80,200);
        jLPassC.setSize(70,30);
        jLPassC.setOpaque(true);
        jLPassC.setBackground( new Color(-1) );
        jLPassC.setText("  Password");
        newAccPanel.add(jLPassC);

        jPPassC.setLocation(80,240);
        jPPassC.setSize(500,30);
        jPPassC.setColumns(10);
        jPPassC.setEchoChar('*');
        newAccPanel.add(jPPassC);

        jBSignUpC.setLocation(250,290);
        jBSignUpC.setSize(150,50);
        jBSignUpC.addActionListener(e->jBSignUpC_Click());
        jBSignUpC.setText("CREATE ACCOUNT");
        newAccPanel.add(jBSignUpC);

        jBgoBack.setLocation(440, 300);
        jBgoBack.setSize(100,30);
        jBgoBack.addActionListener(e->jBgoBack_Click());
        jBgoBack.setText("Back");
        newAccPanel.add(jBgoBack);
    }

    public void jBLogin_Click()
    {
        SecureHash hash = new SecureHash();
        String uName = jtUName.getText();
        int positionFound = -1;
        int count = 0;
        while(positionFound == -1 && count < accList.position)
        {
            if((accList.allAccounts[count].userID).equals(hash.strHash(uName)) == true)
            {
                positionFound = count;
            }
            count++;
        }
        if(positionFound != -1)
        {
            String pass = jPPass.getText();
            if((accList.allAccounts[positionFound].pKey).equals(hash.strHash(pass)) == true)
            {
                guiAccount = accList.allAccounts[positionFound];
                //theTabs.setSelectedComponent(searchSortPanel); ---------------------------------------------------------------
                jtUName.setText("");
                jPPass.setText("");
                btnReadFile_Click();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect password.", "Incorrect password.", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Account not found.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void jBgoBack_Click()
    {
        theTabs.setSelectedComponent(loginPanel);
    }

    public void jBSignUp_Click()
    {
        theTabs.setSelectedComponent(newAccPanel);
    }

    public void jBSignUpC_Click()
    {
        guiAccount.createAccount(jtUNameC.getText(), jPPassC.getText());
        if(accList.addAccountToList(guiAccount) == false)
        {
            JOptionPane.showMessageDialog(null, "This username is taken.", "Account already exists", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Account created successfully.", "Account created", JOptionPane.INFORMATION_MESSAGE);
            jtUNameC.setText("");
            jPPassC.setText("");
            accList.writeArrayToFileCommas();
            //theTabs.setSelectedComponent(searchSortPanel); // ----------------------------------------------------------------------------------
            btnReadFile_Click();
        }
    }

    public void btnReadFile_Click()
    {
    }

    public void loginButton_Click()
    {
        theTabs.setSelectedComponent(loginPanel);
    }





    public void initFrame()
    {
        // Sets a layout for the JFrame
        prototypeWindow.setLayout(null);
        //Makes the tabs not appear (they're above the visible portion of the frame)
        theTabs.setSize(1000, 765);
        theTabs.setLocation(0, -22);
        // Adds the tabbed pane to the JFrame
        prototypeWindow.add(theTabs);

        // Makes the program quit upon GUI closure
        prototypeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the window
        prototypeWindow.setSize(1000,700);

        // Call initialisation methods to initialise the components and add them to panels
        //initLoginComps();
        //initComponents();
        //initComponentsTwo();
        initCAComps();
        initLoginComps();
        initMainComps2();
        loginPanel.setBackground(Color.WHITE);
        theTabs.addTab("Login", loginPanel);
        newAccPanel.setBackground(Color.WHITE);
        theTabs.addTab("Create account", newAccPanel);

        //loginPanel.setBackground(Color.WHITE);
        //newAccPanel.setBackground(Color.WHITE);
        //mainPanel.setBackground(Color.BLACK);
        //titlePanel.setBackground(Color.BLACK);

        // Add those panels to the tabs of the tabbedPane
        //theTabs.addTab("Login", loginPanel);
        //theTabs.addTab("Create account", newAccPanel);
        //theTabs.addTab("Search Sort",mainPanel);
        //theTabs.addTab("Title",titlePanel);
        theTabs.addTab("Main", mainPanel);
        theTabs.addTab("Map", mapPanel);
        theTabs.addTab("Plan Journey", planJourneyPanel);

        prototypeWindow.setIconImage(new ImageIcon("Icons\\SpotLOGO.png").getImage());
        theTabs.setSelectedComponent(mainPanel);

        prototypeWindow.setBackground(Color.WHITE);

        // Make the window visible
        prototypeWindow.setVisible(true);
    }

    public void initMainComps2()
    {
        Border blackline = BorderFactory.createLineBorder(Color.black);

        try
        {
            ImageIcon img1 = new ImageIcon("resources/Lloyds-Footer-Logo.png");
            JLabel labelImg1 = new JLabel();
            labelImg1.setIcon(img1);
            labelImg1.setLocation(0,-10);
            labelImg1.setSize(250,70);
            mainPanel.add(labelImg1);
            ImageIcon twit1 = new ImageIcon("resources/twit2.png");
            ImageIcon face1 = new ImageIcon("resources/face2.png");
            ImageIcon exMap = new ImageIcon("resources/exMap.png");
            ImageIcon sb = new ImageIcon("resources/sb.png");
            twitterBtn = new JButton(twit1);
            twitterBtn_3 = new JButton(face1);
            exampleMap = new JLabel(exMap);
            btnPlanJourney = new JButton(sb);
            btnSearch = new JButton(sb);
        }
        catch(Exception e)
        {
            System.out.println("Adding image didnt work :(");
        }

        exampleMap.setLocation(200, 49);
        exampleMap.setSize(800, 687);
        mainPanel.add(exampleMap);

        twitterBtn.setLocation(700,10);
        twitterBtn.setSize(30,30);
        twitterBtn.addActionListener(e->twitterBtn_Click());
        mainPanel.add(twitterBtn);

        loginButton.setLocation(895, 12); //#-------------------------------------------------------------------------------------------------------
        loginButton.setSize(75, 25);
        loginButton.addActionListener(e->loginButton_Click());
        loginButton.setText("Log in");
        loginButton.setBackground( new Color(-1));
        mainPanel.add(loginButton);

        twitterBtn_3.setLocation(750,10);
        twitterBtn_3.setSize(30,30);
        twitterBtn_3.addActionListener(e->twitterBtn_3_Click());
        mainPanel.add(twitterBtn_3);

        searchBar.setLocation(5,54);
        searchBar.setSize(250,20);
        searchBar.setText("Search");
        mainPanel.add(searchBar);

        serviceUpdates.setLocation(690,54);
        serviceUpdates.setSize(200,20);
        serviceUpdates.addActionListener(e->serviceUpdates_Click());
        serviceUpdates.setBackground( new Color(-1));
        serviceUpdates.setText("See Service Updates");
        serviceUpdates.setBorder(blackline);
        mainPanel.add(serviceUpdates);

        website.setLocation(800,12);
        website.setSize(85,25);
        website.setBackground( new Color(-1));
        website.addActionListener(e->website_Click());
        website.setText("Website");
        mainPanel.add(website);

        stLocation.setLocation(10,90);
        stLocation.setSize(180,25);
        mainPanel.add(stLocation);

        stLocation_9.setLocation(10,135);
        stLocation_9.setSize(180,25);
        mainPanel.add(stLocation_9);

        jpCombo.setLocation(10,185);
        jpCombo.setSize(80,25);
        jpCombo.setEditable(false );
        mainPanel.add(jpCombo);

        //timeTF.setLocation(100,185);
        //timeTF.setSize(50,25);
        //mainPanel.add(timeTF);

        btnPlanJourney.setLocation(160, 185);
        btnPlanJourney.setSize(25, 25);
        btnPlanJourney.addActionListener(e->btnPlanJourney_Click());
        btnPlanJourney.setBackground( new Color(-1));
        mainPanel.add(btnPlanJourney);

        btnSearch.setLocation(260,54);
        btnSearch.setSize(20,20);
        btnSearch.addActionListener(e->btnSearch_Click());
        btnSearch.setBackground( new Color(-1));
        mainPanel.add(btnSearch);

        mainTable.setAutoCreateRowSorter(true);
        mainTableScroll.setSize(190,455);
        mainTableScroll.setLocation(5,220);
        mainTable.setDefaultRenderer(Object.class, new MyCellRenderer());
        //mainTable.checkUpdate();//--------------------------------------------------------------------------------------------------------------------
        mainPanel.add(mainTableScroll);

        banner.setLocation(0,0);
        banner.setSize(1000,50);
        banner.setForeground( new Color(-1) );
        banner.setOpaque(true);
        banner.setBackground( new Color(-65536) );
        banner.setText("");
        banner.setBorder(blackline);
        mainPanel.add(banner);

        banner2.setLocation(0,49);
        banner2.setSize(1000,30);
        banner2.setOpaque(true);
        banner2.setBackground( new Color(-2) );
        banner2.setText("");
        banner2.setBorder(blackline);
        mainPanel.add(banner2);

        banner3.setLocation(0,78);
        banner3.setSize(200,100);
        banner3.setOpaque(true);
        banner3.setBackground( new Color(-1) );
        banner3.setText("");
        banner3.setBorder(blackline);
        mainPanel.add(banner3);

        JSpinner.DateEditor dE = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(dE);
        timeSpinner.setLocation(95, 185);
        timeSpinner.setSize(60, 25);
        timeSpinner.setBorder(blackline);
        mainPanel.add(timeSpinner);

        banner4.setLocation(0,177);
        banner4.setSize(200,40);
        banner4.setOpaque(true);
        banner4.setBackground( new Color(-1) );
        banner4.setText("");
        banner4.setBorder(blackline);
        mainPanel.add(banner4);

        banner5.setLocation(0,216);
        banner5.setSize(200,455);
        banner5.setOpaque(true);
        banner5.setBackground( new Color(-1) );
        banner5.setText("");
        banner5.setBorder(blackline);
        mainPanel.add(banner5);

        tempServiceList = new ServiceList();
        tempServiceList.readAllServices(saveFolder);
        removeAllRows();
        //addAllRows();

        newTimer();
    }

    public void twitterBtn_Click()
    {
        try {
            String myUrl = "https://twitter.com/LloydsCoaches?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor";

            java.awt.Desktop.getDesktop().browse(java.net.URI.create(myUrl));
        }
        catch(Exception e)
        {
            System.out.println("Failed to reach website");
        }
    }

    public void twitterBtn_3_Click()
    {
        try
        {
            String myUrl = "https://www.facebook.com/LloydsCoaches/?locale=en_GB";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(myUrl));
        }
        catch(Exception e)
        {
            System.out.println("Failed to reach website");
        }
    }

    public void website_Click()
    {
        try
        {
            String myUrl = "https://lloydscoaches.com/";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(myUrl));
        }
        catch(Exception e)
        {
            System.out.println("Failed to reach website");
        }
    }

    public void serviceUpdates_Click()
    {
        try
        {
            String myUrl = "https://lloydscoaches.com/serviceupdates/";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(myUrl));
        }
        catch(Exception e)
        {
            System.out.println("Failed to reach website");
        }
    }

    public void initPlanJourney()
    {

    }

    public void btnPlanJourney_Click()
    {
        //Question mark button method
        //System.out.println("btnPlanJourney_Click() method called");
        removeAllRows();
        String start = "";
        start = (String) stLocation.getSelectedItem();
        String finish = (String) stLocation_9.getSelectedItem();

        LocalTime startFinishTime = LocalTime.parse((CharSequence) timeSpinner.getValue());
        String ab = (String) jpCombo.getSelectedItem();

        assert finish != null; // ---------------------------------------------------------- Useful, start using more

        if(finish.equals("Destination"))
        {
            //System.out.println("Trying to work with start string:" + start);
            tempServiceList.servicesFrom(start);
            for(String s : tempServiceList.serviceTimes)
            {
                String[] split = s.split(",");
                update(split[0], split[1]);
            }
        }
        else
        {
            PlanJourney jP = new PlanJourney();
            ArrayList<String[]> services = jP.searchJourney((String) stLocation.getSelectedItem(), (String) stLocation_9.getSelectedItem()); //Returns a series of services and times
            for(String[] i : services)
            {
                update(i[0], i[1]); // Returns the name of the service and the time it arrives at the finish stop currently
            }
        }
    }

    public void myPlanJourneyClick()
    {
        //Question mark button method
        //System.out.println("btnPlanJourney_Click() method called");
        removeAllRows();
        String start = "";
        start = (String) stLocation.getSelectedItem();
        String finish = (String) stLocation_9.getSelectedItem();
        assert finish != null; // ---------------------------------------------------------- Useful, start using more
        if(finish.equals("Destination"))
        {
            //System.out.println("Trying to work with start string:" + start);
            tempServiceList.servicesFrom(start);
            for(String s : tempServiceList.serviceTimes)
            {
                String[] split = s.split(",");
                update(split[0], split[1]);
            }
        }
    }

    public void updateFromTable()
    {

    }

    public void update(String queryVarName, String queryVarTime)
    {
        /*
        MyTimerTask mTT = new MyTimerTask();
        java.util.Timer timer = new java.util.Timer("Timer");
        mTT.getInstance(this);
        timer.schedule(mTT, 10000);
        */

        //mainTable.update(queryVarName, queryVarTime);
        System.out.println("Running update with " + queryVarName + " and " + queryVarTime);
        if(queryVarTime!=null)
        {
            LocalTime paramTime = LocalTime.parse(queryVarTime);
            LocalTime currentTime = LocalTime.now();
            //System.out.println("Local time = " + currentTime);
            if (paramTime.isAfter(currentTime)) {
                long minsUntil = currentTime.until(paramTime, ChronoUnit.MINUTES);
                int minsUntilInt = (int) minsUntil;
                //System.out.println("MinsUntil = " + minsUntilInt);
                if (minsUntilInt > 60) {
                    long hoursUntil = currentTime.until(paramTime, ChronoUnit.HOURS);
                    minsUntilInt = minsUntilInt % 60;
                    System.out.println("Update: adding row " + queryVarName + " " + queryVarTime + " " + String.valueOf(hoursUntil) + "hrs" + minsUntilInt);
                    addRow(queryVarName, queryVarTime, String.valueOf(hoursUntil) + "hrs " + minsUntilInt + "mins");
                } else {
                    System.out.println("Update: adding row " + queryVarName + " " + queryVarTime + " " + String.valueOf(minsUntil));
                    addRow(queryVarName, queryVarTime, String.valueOf(minsUntil) + "mins");
                }
            }
        }
    }


    /*
    public void updateTimes()
    {
        removeAllRows();
        for(String[] s : tableContents)
        {
            LocalTime paramTime = LocalTime.parse(s[1]);
            LocalTime currentTime = LocalTime.now();
            if (paramTime.isAfter(currentTime)) {
                long minsUntil = currentTime.until(paramTime, ChronoUnit.MINUTES);
                int minsUntilInt = (int) minsUntil;
                if (minsUntilInt > 60) {
                    long hoursUntil = currentTime.until(paramTime, ChronoUnit.HOURS);
                    minsUntilInt = minsUntilInt % 60;
                    addRow(s[0], s[1], String.valueOf(hoursUntil) + "hrs " + minsUntilInt + "mins");
                } else {
                    addRow(s[0], s[1], String.valueOf(minsUntil) + "mins");
                }
            }
        }
    }
    */
    /*
    public void btnReadFile_Click()
    {
        System.out.println("btnReadFile_Click() called");
        tempServiceList = new ServiceList();
        tempServiceList.readAllServices(saveFolder);
        removeAllRows();
        addAllRows();
    }
    */
    /*
    public void btnAddSong_Click()
    {
        //initMap();
        theTabs.setSelectedComponent(mapPanel);
    }
    */
    /*
    public void btnSort_Click()
    {
        // Sets
        String sortBy = (String) cbxSortBy.getSelectedItem();
        if(sortBy.equalsIgnoreCase("rating"))
        {
            //sL.ratingSort();
        }
        if(sortBy.equalsIgnoreCase("duration"))
        {
            //sL.durationSort();
        }
        if(sortBy.equalsIgnoreCase("total listens"))
        {
            //sL.totalListensSort();
        }
        sL.writeArrayToFileCommas();
        removeAllRows();
        addAllRows();
    }
    */
    public void btnSearch_Click() //#-----------------------------------------------------------------------------------------------------------------------------
    {
        //System.out.println("btnSearch_Click() method called");
        // Receive search term from popup
        //String searchName = JOptionPane.showInputDialog("Title to search?");
        String searchName = searchBar.getText();
        // Initialise new temp song
        Service tempService;
        // Empty the table
        removeAllRows();

        // Pass the search term given by the user in the popup into the searchTitle procedure for the sL object
        // This procedure returns the indexes of potential matches as a string - see CustomsL class for more details
        // If the string is not empty still (no results found)
        // This returned string is split by the commas separating the indexes, and each split string becomes an element of the returnedIndexes array
        // This is done in a try statement in case errors are thrown
        // The returned indexes are iterated through and for each the index is parsed as an integer
        // Then set the temp object as the object at that index in the allSongs array of the sL object
        // This passes the temp object into the add row procedure which adds it to the table
        // If an exception is thrown, then a popup appears saying that an error has occurred
        // If the returned string of indexes is empty then a popup appears stating this and all rows are added to the table
        String returnedIndexes = tempServiceList.searchByName(searchName);

        if(returnedIndexes != null)
        {
            try
            {
                String[] returnedIndexesSplit = returnedIndexes.split(",");

                for(int i = 1; i < returnedIndexesSplit.length; i++)
                {
                    tempService = tempServiceList.allServices.get(Integer.parseInt(returnedIndexesSplit[i]));
                    addRow(tempService.sName, tempService.dID, tempService.vID);

                    for(String[] s : tempService.stopTimes)
                    {
                        update(s[0], s[1]);
                    }

                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error converting data types.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Unable to find results for search", "Search Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
            addAllRows();
        }
    }

    public void addRow(String c1, String c2, String c3)
    {
        //mainTable.addRow(c1, c2, c3);
        String[] tempElements = new String[3];
        tempElements[0] = c1;
        tempElements[1] = c2;
        tempElements[2] = c3;
        tableContents.add(tempElements);
        System.out.println("Adding " + tempElements[0] + " " + tempElements[1] + " " + tempElements[2]);
        tModel.addRow(tempElements);
        //mainTable.
    }

    java.util.Timer timer = new java.util.Timer("Timer");
    MyTimerTask mTT = new MyTimerTask();
    public void newTimer()
    {
        LocalTime currentTime = LocalTime.now();
        long seconds = currentTime.getSecond();
        seconds = Math.round(seconds);
        int secondsInt = (int) seconds;
        secondsInt = 60 - secondsInt;
        long secondsLong = secondsInt*1000L;
        System.out.println(secondsLong + " miliseconds left in minute");
        mTT.getInstance(this);
        timer.schedule(mTT, secondsLong, 60000);
    }

    public void updateTimer(long secondsLong)
    {
        timer.cancel();
        timer = new java.util.Timer("Timer");
        mTT.getInstance(this);
        timer.schedule(mTT, secondsLong, 60000);
    }

    public void addAllRows() // ------------------------------------------------------------------ With old table structure
    {
        //System.out.println("addAllRows() called");
        int slNumOfServices = tempServiceList.allServices.size();
        Service sTemp;

        for(int i = 0; i < slNumOfServices; i++)
        {
            sTemp = tempServiceList.allServices.get(i);
            addRow(sTemp.sName, sTemp.dID, sTemp.vID);
        }
    }

    public void removeAllRows()
    {
        //System.out.println("Remove all rows called");
        //tableContents = new ArrayList<>();
        int rowCount = tModel.getRowCount();
        //System.out.println("There are " + rowCount + " rows");
        try
        {
            for(int i = (rowCount - 1); i > -1; i--)
            {
                tModel.removeRow(i);
                //System.out.println("Removed row " + i); //---------------------------------------------------------------Shows that row 0 is removed every time this method is run which shouldn't be the case
            }
        }
        catch(Exception e)
        {
            System.out.println("Error trying to remove all rows from table");
        }
    }
    /*
    public static void main(String[] args)
    {
        PrototypeGUI pGUI = new PrototypeGUI();
        pGUI.initFrame();
    }
     */
}
