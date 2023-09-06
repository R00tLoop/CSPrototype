import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;

public class PrototypeGUI
{
    File saveFolder = new File("Saves\\Services\\");
    JFrame prototypeWindow = new JFrame();
    //JFrame declaration, highest level of GUI

    ServiceList sL = new ServiceList();
    Service s = new Service();
    //Objects to work with inside GUI processes

    JTabbedPane theTabs = new JTabbedPane();
    //Contains tabs, second level

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> listAll= new JList<>(listModel);
    //??????????????????????

    String[] arrSortBy = new String[] {"Name"};

    JPanel mainPanel = new JPanel(null);
    //Main panel, contains components
    JPanel mapPanel = new JPanel(null);
    JButton mapBtn = new JButton();
    JComboBox<String> cbxSortBy = new JComboBox<>(arrSortBy);

    JButton btnSort = new JButton();
    JButton btnSearch = new JButton();
    JButton btnRefreshTable = new JButton();
    JButton btnReadFile = new JButton();

    //Table stuff
    String[] headings = {"Name", "Driver ID", "Vehicle ID"};
    //Array of table headings to give table model
    DefaultTableModel tModel = new DefaultTableModel(headings, 0);
    //Declare default table model
    JTable mainTable = new JTable(tModel);
    //Use default table model to declare a JTable
    JScrollPane mainTableScroll = new JScrollPane(mainTable);
    //Use that JTable to declare a JScrollPane

    public void initFrame()
    {
        // Sets a layout for the JFrame
        prototypeWindow.setLayout(null);
        //Makes the tabs not appear (they're above the visible portion of the frame)
        theTabs.setSize(1200, 765);
        theTabs.setLocation(0, -50);
        // Adds the tabbed pane to the JFrame
        prototypeWindow.add(theTabs);

        // Makes the program quit upon GUI closure
        prototypeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the window
        prototypeWindow.setSize(1200,765);

        // Call initialisation methods to initialise the components and add them to panels
        //initLoginComps();
        //initComponents();
        //initComponentsTwo();
        //initCAComps();
        initMainComps();

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

        prototypeWindow.setIconImage(new ImageIcon("Icons\\SpotLOGO.png").getImage());

        prototypeWindow.setBackground(Color.WHITE);

        // Make the window visible
        prototypeWindow.setVisible(true);
    }

    public void initMainComps()
    {
        // Further initialisations

        cbxSortBy.setLocation(50, 460);
        cbxSortBy.setSize(150, 30);
        mainPanel.add(cbxSortBy);

        btnSort.setLocation(50,400);
        btnSort.setSize(150,50);
        btnSort.addActionListener(e->btnSort_Click());
        btnSort.setText("Sort File");
        mainPanel.add(btnSort);

        btnSearch.setLocation(250,400);
        btnSearch.setSize(150,50);
        btnSearch.addActionListener(e->btnSearch_Click());
        btnSearch.setText("Do Search");
        mainPanel.add(btnSearch);

        btnRefreshTable.setLocation(450,400);
        btnRefreshTable.setSize(150,50);
        btnRefreshTable.addActionListener(e->btnResetTable_Click());
        btnRefreshTable.setText("Refresh Table");
        mainPanel.add(btnRefreshTable);

        btnReadFile.setLocation(50,500);
        btnReadFile.setSize(550,50);
        btnReadFile.addActionListener(e->btnReadFile_Click());
        btnReadFile.setText("Read from File");
        mainPanel.add(btnReadFile);

        mapBtn.setLocation(650, 400);
        mapBtn.setSize(150, 50);
        mapBtn.addActionListener(e->btnMap_Click());
        mapBtn.setText("Map");
        mainPanel.add(mapBtn);

        mainTable.setAutoCreateRowSorter(true);
        mainTableScroll.setSize(1000,350);
        mainTableScroll.setLocation(30,30);
        mainPanel.add(mainTableScroll);
    }

    public void btnReadFile_Click()
    {
        System.out.println("btnReadFile_Click() called");
        sL = new ServiceList();
        sL.readAllServices(saveFolder);
        removeAllRows();
        addAllRows();
    }

    public void btnMap_Click()
    {
        theTabs.setSelectedComponent(mapPanel);
    }

    public void btnSort_Click()
    {
        /*
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
        */
    }

    public void btnSearch_Click()
    {
        // Receive search term from popup
        String searchName = JOptionPane.showInputDialog("Title to search?");
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
        String returnedIndexes = sL.searchByName(searchName);
        if(returnedIndexes != null)
        {
            try
            {
                String[] returnedIndexesSplit = returnedIndexes.split(",");

                for(int i = 1; i < returnedIndexesSplit.length; i++)
                {
                    tempService = sL.allServices.get(Integer.parseInt(returnedIndexesSplit[i]));
                    addRow(tempService);
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

    public void addRow(Service sTempAddRow)
    {
        String[] tempElements = new String[3];
        tempElements[0] = sTempAddRow.sName;
        tempElements[1] = sTempAddRow.dID;
        tempElements[2] = sTempAddRow.vID;
        tModel.addRow(tempElements);
    }

    public void addAllRows()
    {
        System.out.println("addAllRows() called");
        int slNumOfServices = sL.allServices.size();
        Service sTemp;

        for(int i = 0; i < slNumOfServices; i++)
        {
            sTemp = sL.allServices.get(i);
            addRow(sTemp);
        }
    }

    public void btnResetTable_Click()
    {
        removeAllRows();
        addAllRows();
    }

    public void removeAllRows()
    {
        System.out.println("Remove all rows called");
        int rowCount = tModel.getRowCount();
        //System.out.println("There are " + rowCount + " rows");
        for(int i = (rowCount - 1); i > -1; i--)
        {
            tModel.removeRow(i);
            //System.out.println("Removed row " + i); //---------------------------------------------------------------Shows that row 0 is removed every time this method is run which shouldn't be the case
        }
    }

    public static void main(String[] args)
    {
        PrototypeGUI pGUI = new PrototypeGUI();
        pGUI.initFrame();
    }
}
