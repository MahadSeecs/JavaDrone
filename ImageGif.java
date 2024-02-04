// Refrences
//https://stackoverflow.com/questions/10836832/show-an-animated-bg-in-swing

// Imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class ImageGif extends JPanel implements ActionListener {

    private Image image;

    ImageGif(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Painting the gif image on the panel
        // After getting the image input from user
        g.drawImage(image,0,0,getWidth(),getHeight(),this);
    }

    // Method 2 of adding background to the the Frame
    // Now useless as we are using the upper Method

    // this initalizes the background Panel with the graphics
    public static void addBgPanel(JFrame frame){

        // Gets the image from the file location and converts it to the appropriate Type
        final Image image = new ImageIcon("C:\\Users\\Super\\IdeaProjects\\OOP FINAL\\src\\DroneController.gif").getImage();

        SwingUtilities.invokeLater(new Runnable() {
            // Invoke later Ensures that the code in these {} is run on the Event diapatch Thread
            //That simply adds a task to the tasks queue of the Java thread which
            // is in charge of the rendering & events for your whole application.
            // GUI related task, any update should be made to GUI while painting process must happen on the EDT, which involves wrapping the request in an event and processing it onto the EventQueue. Then the event are dispatched from the same queue in the one by one in order they en-queued, FIRST IN FIRST OUT

            public void run() {


                ImageGif imagePanel = new ImageGif(image);
                imagePanel.setLayout(new GridLayout(5,10,10,10));
                imagePanel.setBorder(new EmptyBorder(20,20,20,20));
                //for (int ii=1; ii<51; ii++) {
                //imagePanel.add(new JButton("" + ii));
                //}


                //////////////////////////////////
                imagePanel.setLayout(null);
                // Creating all the Required Lables

                JLabel userLabel=new JLabel("Username");
                JLabel passwordLabel=new JLabel("Password");
                JTextField userTextField=new JTextField();
                JPasswordField passwordField=new JPasswordField();
                JButton loginButton=new JButton("Login");
                JButton resetButton=new JButton("Reset");
                JCheckBox showPassword=new JCheckBox("Show Password");


                // Manually setting the location of all the labels
                userLabel.setBounds(200,150,100,30);
                passwordLabel.setBounds(200,220,100,30);
                userTextField.setBounds(300,150,150,30);
                passwordField.setBounds(300,220,150,30);
                showPassword.setBounds(300,250,150,30);

                loginButton.setBounds(450,300,100,30);
                resetButton.setBounds(200,300,100,30);

                // Adding all the Required to the Image panel
                imagePanel.add(userLabel);
                imagePanel.add(passwordLabel);
                imagePanel.add(userTextField);
                imagePanel.add(passwordField);
                imagePanel.add(showPassword);
                imagePanel.add(loginButton);
                imagePanel.add(resetButton);

                //Adding the action listeners=--First For Login
                //
                loginButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String userText;
                        String passwordText;
                        userText = userTextField.getText();
                        passwordText = passwordField.getText(); //SECURITY PROBLEMS,When calling getText you get a String (immutable object)
                        // that may not be changed (except reflection) and so the password stays in the memory until garbage collected.

                        // RUN a WHILE LOOP HERE AND Replace with
                        //Check EqualsIgnoreCase
                        // validator(username,password)
                        //userText.equals("397978") && passwordText.equals("rr879")
                        //validateCredentials.checkCredentials(397978,"rr978")
                        if ( validateCredentials.checkCredentials(Integer.parseInt(userText),passwordText)) {
                            // ADD try catch block here.
                            JFrame Mainframe= null;
                            try {
                                Mainframe = new DroneGui("Drone GUI");
                                DroneGui.addMenuBar(Mainframe);
                                Mainframe.pack();

                                //frame.setLocationByPlatform(true);
                                Mainframe.setVisible(true);
                                frame.dispose();

                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }

                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
                        }
                    }
                });

                // Action Listener for the reset Button with anonymous class
                resetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        userTextField.setText("");
                        passwordField.setText("");
                    }
                });

                // Action Listener for cehckbox to show password
                showPassword.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (showPassword.isSelected()) {
                            passwordField.setEchoChar((char) 0);
                        } else {
                            passwordField.setEchoChar('*');
                        }
                    }
                });

                ///////////////////////////////////

                frame.setContentPane(imagePanel);
                            }
        });

    }

    public static void main(String[] args) throws Exception {
        UserCredentials.getDBData();
        JFrame f = new JFrame("Image");
        f.setPreferredSize(new Dimension(720, 480));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationByPlatform(true);
        addBgPanel(f);


        f.pack();
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}




