import java.io.*;  //useful imports
import java.net.Socket;
import java.sql.*;

public class HumiditySensor {
    static final String DB_URL = "jdbc:mysql://localhost/drone_database";
    static final String USER = "root";
    static final String PASSWORD = "12345678";

    static final String QUERY = ("Insert into humiditysensor Values(  ?,  ?)"); //UPDATE query to insert the values received from humidity sensor
    //in the humiditysensor table.
    private static String convert(int[] inputData) {
        int index = 0;
        char converted;
        String Output = "";
        while (inputData[index] != 0) {
            converted = (char) inputData[index];
            if (converted != '!') {
                Output += converted;
            }
            index++;
        }

        return Output;
    }

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

            Socket soc = new Socket("192.168.4.1", 80);
            InputStream in = soc.getInputStream();

            System.out.println("Connected to Arduino");

            String Data;
            int count = 0;
            int seconds = 0;

            int inputData[] = new int[1000];
            int index;
            BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            for (int i = 0; i < 1000; i++) {
                inputData[i] = 0;
            }
            while (true) {
                count = count + 1;
                seconds = seconds + 1;
                index = -1;
                do {
                    index++;
                    inputData[index] = br.read();
                } while (inputData[index] != 33);

                if (inputData[0] != 0) {
                    Data = convert(inputData);
                    System.out.println("Record " + count);
                    System.out.println(Data);
                    preparedStatement.setString(1, Data); //Updates the data received from humidity sensor.
                    preparedStatement.setInt(2, count); //Updates the data with respect to corresponding observation records.

                    int w = preparedStatement.executeUpdate();

                    if (count == 30){
                        System.out.println("Data stored for humidity."); //the sensor reads data for 30 seconds which is used to tabulate the table and draw graphs from it.
                        break;
                    }
                }

            }
        }
        catch (SQLException a) {
            a.printStackTrace();
        }
        catch (Exception A){
            System.out.println(A.getMessage());
        }
    }

}

