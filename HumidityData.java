import java.sql.*;
public class HumidityData {
    static final String DB_URL = "jdbc:mysql://localhost/drone_database";
    static final String USER = "root";
    static final String PASSWORD = "12345678";

    static final String QUERY = ("Insert into humiditysensor Values(?,?)");

    public void humiditydata(){
        WifiModule Data = new WifiModule();
        int count = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            while (true){
                count = count + 1;
                System.out.println("Record" + count);
                preparedStatement.setString(1, Data.getData()); //Updates the data received from humidity sensor.
                preparedStatement.setInt(2, count); //Updates the data with respect to corresponding observation records.

                int w = preparedStatement.executeUpdate();

                if (count == 30){
                    System.out.println("Data stored for humidity."); //the sensor reads data for 30 seconds which is used to tabulate the table and draw graphs from it.
                    break;
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HumidityData H = new HumidityData();
        H.humiditydata();
    }

}