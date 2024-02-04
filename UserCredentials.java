import java.sql.*;              //useful imports
import java.util.ArrayList;
import java.util.Scanner;

public class UserCredentials {
    static final String DB_URL = "jdbc:mysql://localhost/kill myself";
    static final String USER = "root";
    static final String PASSWORD = "1309";
    static final String QUERY = "SELECT cmsid,password,name FROM usercredentials;";
    static ArrayList<Integer> arrayCMS = new ArrayList<Integer>();
    static ArrayList<String> arrayPassword = new ArrayList<String>();
    static ArrayList<String> arrayName = new ArrayList<String>();
    public static void getDBData(){
        Scanner input = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD); // creating a connection to the mysql workbench
             Statement statement = connection.createStatement(); // This is used to access data .

             ResultSet resultSet = statement.executeQuery(QUERY)) { // this lets the above query be executed

            while (resultSet.next()) {                    // boolean, filling the respective arrays with data.
                arrayCMS.add(resultSet.getInt("cmsid")); // data edded to array list
                arrayPassword.add(resultSet.getString("password"));
                arrayName.add(resultSet.getString("name"));
            }
            validateCredentials v=new validateCredentials();


            if (v.checkCredentials(397978,"rr978")){
                System.out.println("valid");
            }
            else{
                System.out.println("invalid");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {                //creating arraylist objects for CMS,Password and Name of legitimate users


        Scanner input = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD); // creating a connection to the mysql workbench
             Statement statement = connection.createStatement(); // This is used to access data .

             ResultSet resultSet = statement.executeQuery(QUERY)) { // this lets the above query be executed

            while (resultSet.next()) {                    // boolean, filling the respective arrays with data.
                arrayCMS.add(resultSet.getInt("cmsid")); // data edded to array list
                arrayPassword.add(resultSet.getString("password"));
                arrayName.add(resultSet.getString("name"));
            }
            validateCredentials v=new validateCredentials();



//            boolean passwordincorrect = true; //logical layer to process the data selected from the table
//            boolean cmsincorrect = true;
//            int cmsInput = 0;
//            while (cmsincorrect) {
//                System.out.println("Enter your CMS ID: ");
//                cmsInput = input.nextInt();
//                if (arrayCMS.contains(cmsInput)) {
//                    cmsincorrect = false;
//                }
//                else{
//                    System.out.println("Invalid username. Try again.");
//                }
//            }
//            while (passwordincorrect) {
//                System.out.print("Enter your password: ");
//                String password = input.next();
//                if (password.equals(arrayPassword.get(arrayCMS.indexOf(cmsInput)))) {
//                    passwordincorrect = false;
//                    System.out.println("Hello " + arrayName.get(arrayCMS.indexOf(cmsInput)) + "!." + " " +  "Your drone is ready for flight.");
//                } else {
//                    System.out.println("Password incorrect.Try again.");
//                }
//
//            }
            if (v.checkCredentials(397978,"rr978")){
                System.out.println("valid");
            }
            else{
                System.out.println("invalid");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }



    }

}