
// MainImports
import javax.swing.*;

///////// WEBCAM IMPORTS ///////////////////
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DroneGui extends JFrame{
    // Create UI COMPONENTS
    private JPanel panel1;
    private JPanel powerPanel;
    private JPanel cameraPanel;
    private JPanel throttlePanel;
    private JPanel joyStickPanel;
    private JButton moveUpButton;
    private JButton moveDownButton;
    private JButton moveLeftButton;
    private JButton moveRightButton;
    private JLabel timeOfFlight;
    private JSlider slider1;
    private JSlider slider2;
    private JProgressBar BatteryIndicator;
    private JButton info;
    private JButton helpButton;
    private JButton altButton;
    private JButton windButton;
    private JButton gps;
    private JButton speedButton;
    private JToolBar infoToolBar;
    private JToolBar altToolbar;
    private JButton infoButton;
    private JButton CameraButton;
    WifiModule wifi;

    // Constructor For Drone, Takes the title and makes MainFrame
    public DroneGui(String title) throws InterruptedException{

        super(title);
        wifi = new WifiModule();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //addWebCam(this.cameraPanel);
        this.setContentPane(panel1);
        //this.pack();
        windButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                Graphs.makeHumidityGraph();
                            }catch (Exception E){
                            E.getMessage();
                            E.printStackTrace();
                        }

                        }

                    });
                    t.start();

                }catch (Exception E){
                    E.getMessage();
                    E.printStackTrace();
                }

            }
        });
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                wifi.sendData("U");
            }
        });
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                wifi.sendData("D");
            }
        });
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                wifi.sendData("L");
            }
        });
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                wifi.sendData("R");
            }
        });
    }

    //////////////////////////////////////////////////////////////

    // Code to fill the Progress bar,
    // Will take analouge Singals from Drone
    public static void fill(JProgressBar battery) {
        int i = 0;
        try {
            while (i <= 100) {
                // fill the menu bar
                battery.setValue(i + 10);

                // delay the thread
                // it pauses the execution for a some milliseconds
                Thread.sleep(1000);
                i += 20;
            }
        } catch (Exception e) {
        }
    }

    public static void addMenuBar(JFrame mainFrame){
        // Creates Menu Bar
        JMenuBar menuBar = new JMenuBar();
        //create menus
        JMenu fileMenu = new JMenu("File");
        JMenu navigate=new JMenu("Navigate");
        JMenu tools=new JMenu("Tools");
        //create menu items
        JMenuItem newMenuItem = new JMenuItem("Save Image");
        JMenuItem give_feedBack = new JMenuItem("Give FeedBack");
        JMenuItem troubleshoot = new JMenuItem("Troubleshoot");
        JMenuItem exitMenuItem = new JMenuItem("Exit");


        JMenuItem getFilePath=new JMenuItem("Get File Path");
        JMenuItem getDroneCode=new JMenuItem("Get Drone Code");
        // Adds the reuired parts to the menu item
        navigate.add(getFilePath);
        navigate.add(getDroneCode);

        JMenuItem set_altitude=new JMenuItem("Set Altitude");
        JMenuItem setSpeed=new JMenuItem("Set Speed");
        tools.add(set_altitude);
        tools.add(setSpeed);

        //add menu items to menus
        fileMenu.add(newMenuItem);
        fileMenu.add(give_feedBack);
        fileMenu.add(troubleshoot);
        fileMenu.add(exitMenuItem);
        //add menu to menubar
        menuBar.add(fileMenu);
        menuBar.add(navigate);
        menuBar.add(tools);
        //add menubar to the frame
        mainFrame.setJMenuBar(menuBar);

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

    }
    /////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////

    // Adds the Webcam to the Jframe
    // Adds it to the camera Panel
    // ADD THROWS INTERRUPTED EXCEPTION
    private void createUIComponents() {
        // TODO: place custom component creation code here
        cameraPanel=new JPanel();
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel panel = new WebcamPanel(webcam);
        //panel.setFPSDisplayed(true);
        //panel.setDisplayDebugInfo(true);
        //panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        panel.setLayout(new GridLayout(3, 1));
        cameraPanel.add(panel);
        //cameraPanel.setSize(580,1000);

    }

    public static void  main(String[] args)throws InterruptedException {
        // ADD try catch block here.

        JFrame frame=new DroneGui("Drone GUI");
        //frame.setPreferredSize(new Dimension(1200, 480));
        addMenuBar(frame);

        frame.pack();
        //frame.setLocationByPlatform(true);


        frame.setVisible(true);
    }
}
